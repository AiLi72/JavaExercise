package hdfs.namenode;

import config.Project;
import hdfs.distributedfilesystem.HdfsFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Scanner;

public class NameNode implements Runnable{

    protected static Hashtable<String, HdfsFile> hdfsFileHashtable = new Hashtable<String, HdfsFile>();

    private Registry registry;

    public NameNode(Registry registry) {
        this.registry = registry;
    }

    @Override
    public void run() {
        System.out.println("Lancement du Namenode");
        try {
            //On lie le stub à l'objet remote dans le registre
            CommandsHdfsImpl obj = new CommandsHdfsImpl();
            CommandsHdfs stubHdfs = (CommandsHdfs) obj;
            registry.bind("CommandsHdfs", stubHdfs);

            while (true) {

            }
        } catch (IOException | AlreadyBoundException ex) {
            System.out.println("Namenode exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
