package ordo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DaemonManagerImpl extends UnicastRemoteObject implements DaemonManager {

    public DaemonManagerImpl() throws RemoteException {
        super();
    }

}
