package ordo;

import config.Project;
import hdfs.datanode.CommandsDataNode;
import hdfs.datanode.CommandsDataNodeImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class DaemonRunnable implements Runnable {

    private Registry registry;

    public DaemonRunnable(Registry registry) {
        this.registry = registry;
    }

    @Override
    public void run() {
        try {
            //On lie le stub à l'objet remote dans le registre
            DaemonImpl obj = new DaemonImpl();
            Daemon stubDaemon = (Daemon) obj;
            registry.bind(Project.REGISTRY_DAEMON_NAME, stubDaemon);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
