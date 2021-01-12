package ordo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallBack extends Remote {

    public void onComplete() throws RemoteException;

    public void waitCompletion() throws RemoteException;

}
