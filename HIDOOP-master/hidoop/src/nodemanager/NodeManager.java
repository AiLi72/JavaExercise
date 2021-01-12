package nodemanager;

import config.Project;
import hdfs.datanode.DataNode;
import ordo.DaemonRunnable;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NodeManager implements Serializable {

    private int id;
    private InetAddress address;
    private boolean reliable;

    public NodeManager() {
        this.reliable = true;
    }

    public NodeManager(InetAddress address) {
        this.id = -1;
        this.address = address;
        this.reliable = true;
    }

    public NodeManager(int id, InetAddress address, boolean reliable) {
        this.id = id;
        this.address = address;
        this.reliable = reliable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public boolean isReliable() {
        return reliable;
    }

    public void setReliable(boolean reliable) {
        this.reliable = reliable;
    }

    @Override
    public String toString() {
        return "NodeManager{" +
                "id=" + id +
                ", address=" + address +
                ", reliable=" + reliable +
                '}';
    }

    public static void main(String[] args) {
        try {
            //Envoie des information du NodeManager
            Socket socketRM = new Socket(Project.RESOURCE_MANAGER_HOSTNAME, Project.RESOURCE_MANAGER_PORT);
            System.out.println("Connexion avec le ressource manager sur le port: " + Project.RESOURCE_MANAGER_PORT);
            NodeManager nm = new NodeManager(InetAddress.getLocalHost());
            ObjectOutputStream oos = new ObjectOutputStream(socketRM.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socketRM.getInputStream());
            oos.flush();
            oos.writeObject(nm);
            oos.flush();
            oos.close();
            ois.close();
            //Creation du registre pour RMI
            Registry registry = LocateRegistry.createRegistry(Project.RMIPORT_NM);
            //Lancement du DataNode
            new Thread(new DataNode(registry)).start();
            new Thread(new DaemonRunnable(registry)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
