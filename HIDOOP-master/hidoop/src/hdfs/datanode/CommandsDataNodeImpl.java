package hdfs.datanode;

import config.Project;
import formats.Format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CommandsDataNodeImpl extends UnicastRemoteObject implements CommandsDataNode {

    public CommandsDataNodeImpl() throws RemoteException {
        super();
    }

    @Override
    public void transferDataNode(InetAddress address, int port, String command, String filename, String chunkName) {
        try {
            Socket socketClient = new Socket(address, port);
            OutputStream os = socketClient.getOutputStream();
            InputStream is = socketClient.getInputStream();
            HdfsClientHandler hch = new HdfsClientHandler(socketClient, is, os, command, filename, chunkName);
            Thread t = new Thread(hch);
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteHdfsFile(String hdfsFname) {
        File dir = new File(Project.FOLDER_PATH_DATANODE_FILES + "/" + hdfsFname);
        return deleteDir(dir);
    }

    private boolean deleteDir(File dir) {
        File[] files = dir.listFiles();
        if(files != null) {
            for (final File file : files) {
                deleteDir(file);
            }
        }
        return dir.delete();
    }

}
