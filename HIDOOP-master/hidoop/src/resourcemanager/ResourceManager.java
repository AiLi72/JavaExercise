package resourcemanager;

import config.Project;
import hdfs.datanode.DataNode;
import hdfs.namenode.NameNode;
import nodemanager.NodeManager;
import ordo.DaemonManager;
import ordo.DaemonManagerRunnable;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import static java.lang.Thread.sleep;

public class ResourceManager {

    public static Hashtable<Integer, NodeManager> nodesInfo = new Hashtable<Integer, NodeManager>();

    public static int id = 0;

    public static void addNodeManager(NodeManager nm) {
        nodesInfo.put(nm.getId(), nm);
    }

    public static void main(String[] args) {
        try {
            //Ecoute des node manager pour le protocole systolique
            new Thread(new NodeManagerListener()).start();
            //Creation du registre pour RMI
            Registry registry = LocateRegistry.createRegistry(Project.RMIPORT_RM);
            //Lancement du NameNode
            new Thread(new NameNode(registry)).start();
            new Thread(new DaemonManagerRunnable(registry)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
