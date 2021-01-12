package resourcemanager;

import config.Project;
import nodemanager.NodeManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NodeManagerListener implements Runnable{

    ServerSocket ss;

    public NodeManagerListener() throws IOException {
        this.ss = new ServerSocket(Project.RESOURCE_MANAGER_PORT);
    }

    @Override
    public void run() {
        System.out.println("ResourceManager is listening to NodeManager on port : " + Project.RESOURCE_MANAGER_PORT);
        while (true) {
            try {
                Socket socketNM = ss.accept();
                System.out.println("NodeManager connected " + socketNM);
                ObjectOutputStream oos = new ObjectOutputStream(socketNM.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socketNM.getInputStream());
                NodeManager nm = (NodeManager) ois.readObject();
                nm.setId(ResourceManager.id++);
                ResourceManager.addNodeManager(nm);
                ois.close();
                oos.close();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Server socket resourcemanager.ResourceManager exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
