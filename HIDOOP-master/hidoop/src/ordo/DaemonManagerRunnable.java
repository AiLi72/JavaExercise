package ordo;

import config.Project;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class DaemonManagerRunnable implements Runnable {

    Registry registry;
    HashMap<String, Daemon> daemons;

    public DaemonManagerRunnable(Registry registry) {
        this.registry = registry;
        daemons = new HashMap<String, Daemon>();
    }

    public void run() {
        System.out.println("Lancement du DaemonManager");

        try {
            //On lie le stub à l'objet remote dans le registre
            DaemonManagerImpl dmi = new DaemonManagerImpl();
            DaemonManager stubDmi = (DaemonManager) dmi;
            registry.bind(Project.REGISTRY_DAEMONMANAGER_NAME, stubDmi);
        } catch (IOException | AlreadyBoundException ex) {
            ex.printStackTrace();
        }
    }

}
