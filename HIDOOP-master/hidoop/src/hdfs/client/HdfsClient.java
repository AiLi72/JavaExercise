/* une PROPOSITION de squelette, incomplète et adaptable... */

package hdfs.client;

import config.Project;
import formats.Format;
import formats.InfoEnvoieFichier;
import formats.KVFormat;
import formats.LineFormat;
import hdfs.datanode.CommandsDataNode;
import hdfs.distributedfilesystem.Chunk;
import hdfs.namenode.CommandsHdfs;
import nodemanager.NodeManager;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class HdfsClient {

    private static void usage() {
        System.out.println("Usage: java HdfsClient read <hdfsfilename> <file>");
        System.out.println("Usage: java HdfsClient write <line|kv> <nom> <file>");
        System.out.println("Usage: java HdfsClient delete <file>");
    }
	
    public static void HdfsDelete(String hdfsFname) {
        try {
            Registry registryResourceManager = LocateRegistry.getRegistry(Project.RESOURCE_MANAGER_HOSTNAME, Project.RMIPORT_RM);
            CommandsHdfs stubHdfs = (CommandsHdfs) registryResourceManager.lookup("CommandsHdfs");
            //demande si l'on souhaite écraser le fichier si le nom existe déjà
            if(stubHdfs.doesFileNameExist(hdfsFname)) {
                String stop = "";
                do {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Souhaitez-vous vraiment supprimer: " + hdfsFname + " de HDFS ? (o/n)");
                    stop = scanner.nextLine().toLowerCase();
                    if (stop.equals("n")) {
                        System.out.println();
                        return;
                    }
                } while (!stop.equals("o"));
                List<NodeManager> listNodes = stubHdfs.getNodesManager();
                for(NodeManager nm : listNodes) {
                    Registry registryNodeManager = LocateRegistry.getRegistry(nm.getAddress().getHostName(), Project.RMIPORT_NM);
                    CommandsDataNode stubDN = (CommandsDataNode) registryNodeManager.lookup("CommandsDataNode");
                    if(!stubDN.deleteHdfsFile(hdfsFname)) {
                        System.out.println("Problème pour la suppresion de " + hdfsFname);
                        return;
                    }
                }
                stubHdfs.delete(hdfsFname);

            } else {
                System.out.println("Aucun fichier se nommant " + hdfsFname + " existe.");
            }


        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void HdfsWrite(Format.Type fmt, String localFSSourceFname, String hdfsFname,
     int repFactor) {
        long t1 = System.currentTimeMillis();
        try{
            Registry registryResourceManager = LocateRegistry.getRegistry(Project.RESOURCE_MANAGER_HOSTNAME, Project.RMIPORT_RM);
            CommandsHdfs stubHdfs = (CommandsHdfs) registryResourceManager.lookup("CommandsHdfs");
            //demande si l'on souhaite écraser le fichier si le nom existe déjà
            if(stubHdfs.doesFileNameExist(hdfsFname)){
                String stop = "";
                do {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Un fichier avec ce nom existe déjà, voulez-vous l'écraser ? (o/n)");
                    stop = scanner.nextLine().toLowerCase();
                    if (stop.equals("n")) {
                        System.out.println();
                        return;
                    }
                } while (!stop.equals("o"));
            }

            stubHdfs.createFile(hdfsFname, fmt, repFactor); //création fichier namenode

            //declaration des variables pour l'envoie du fichier en fragments
            int nbChar = config.Project.MAXFILESIZE/2; //char à une valeur de 2B ;
            char[] buffer;
            int nbCharRetenue = 0;
            InfoEnvoieFichier ief;

            Format file;
            switch (fmt) {
                case LINE:
                    file = new LineFormat(localFSSourceFname);
                    break;
                case KV:
                    file = new KVFormat(localFSSourceFname);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + fmt);
            }

            buffer = new char[nbChar];
            Arrays.fill(buffer, ' ');
            ief = new InfoEnvoieFichier(buffer, nbCharRetenue, true,false);

            //envoie des fragments
            file.open(Format.OpenMode.R);
            ServerSocket serverSocketClient = new ServerSocket(Project.HDFS_FILE_TRANSFERT_PORT); //creation d'un server au niveau du client
            InetAddress clientAddress = InetAddress.getLocalHost();
            int count = 0;
            do{
                NodeManager nm = stubHdfs.getRandomNodeManager(); //recuperation d'un datanode
                System.out.println(nm.toString());
                Registry registryNodeManager = LocateRegistry.getRegistry(nm.getAddress().getHostName(), Project.RMIPORT_NM);
                CommandsDataNode stubDN = (CommandsDataNode) registryNodeManager.lookup("CommandsDataNode");
                String chunkName = hdfsFname + count;
                stubDN.transferDataNode(clientAddress, Project.HDFS_FILE_TRANSFERT_PORT, "write", hdfsFname, chunkName);
                Socket socketDN = serverSocketClient.accept();
                System.out.println(socketDN);
                OutputStream os = socketDN.getOutputStream();
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
                ief = file.sendFilePart(pw, ief, nbChar); //envoie du fragment
                stubHdfs.addChunkInfo(hdfsFname, chunkName, count, nbChar-ief.getNbCharRetenu(), nm); //on ajoute les infos du fragments dans le namenode
                System.out.println(nbChar-ief.getNbCharRetenu());
                System.out.println(count);
                count++;
                socketDN.close();
            } while(!ief.isEndOfFileReached());
            file.close();
            serverSocketClient.close();
            long t2 = System.currentTimeMillis();
            System.out.println("Temps d'écriture dans hdfs ="+(t2-t1)+" ms");
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    public static void HdfsRead(String hdfsFname, String localFSDestFname) {
        try{
            Registry registry = LocateRegistry.getRegistry(Project.RESOURCE_MANAGER_HOSTNAME, Project.RMIPORT_RM);
            CommandsHdfs stubHdfs = (CommandsHdfs) registry.lookup("CommandsHdfs");
            //verifications si le fichier existe
            if(stubHdfs.doesFileNameExist(hdfsFname)){
                Format file;
                Format.Type fmt = stubHdfs.getFileFormat(hdfsFname);
                switch (fmt) {
                    case LINE:
                        file = new LineFormat(localFSDestFname);
                        break;
                    case KV:
                        file = new KVFormat(localFSDestFname);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + fmt);
                }
                file.open(Format.OpenMode.W);
                ServerSocket serverSocketClient = new ServerSocket(Project.HDFS_FILE_TRANSFERT_PORT); //creation d'un server au niveau du client
                InetAddress clientAddress = InetAddress.getLocalHost();
                for(int i = 0; i < stubHdfs.getChunksNumber(hdfsFname); i++) {
                    System.out.println(i);
                    Chunk chunk = stubHdfs.getChunk(hdfsFname, i);
                    NodeManager nm = chunk.getNodeManager();
                    Registry registryNodeManager = LocateRegistry.getRegistry(nm.getAddress().getHostName(), Project.RMIPORT_NM);
                    CommandsDataNode stubDN = (CommandsDataNode) registryNodeManager.lookup("CommandsDataNode");
                    stubDN.transferDataNode(clientAddress, Project.HDFS_FILE_TRANSFERT_PORT, "read", hdfsFname, chunk.getChunkName());
                    Socket socketDN = serverSocketClient.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socketDN.getInputStream()));
                    file.receiveFile(reader);
                    socketDN.close();
                }
                file.close();
                serverSocketClient.close();
            } else {
                System.out.println("Aucun fichier de ce nom n'existe dans HDFS");
            }
        } catch (IOException | NotBoundException ex) {
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) {
        // java HdfsClient <read|write> <line|kv> <file>

        try {
            //Registry registry = LocateRegistry.getRegistry(Project.NNHOSTNAME, Project.RMIPORT);
            //Commands stub = (Commands) registry.lookup("Commands");

            if (args.length<2) {usage(); return;}

            switch (args[0]) {
              case "read": HdfsRead(args[1],args[2]); break;
              case "delete": HdfsDelete(args[1]); break;
              case "write": 
                Format.Type fmt;
                if (args.length<3) {usage(); return;}
                if (args[1].equals("line")) fmt = Format.Type.LINE;
                else if(args[1].equals("kv")) fmt = Format.Type.KV;
                else {usage(); return;}
                HdfsWrite(fmt,args[3], args[2], 1);
            }	
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
