package ordo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class CallBackImpl extends UnicastRemoteObject implements CallBack {

	Semaphore semaphore;
	
	protected CallBackImpl() throws RemoteException {
		super();
		
		semaphore = new Semaphore(0);
	}

	@Override
	public void onComplete() throws RemoteException {
		semaphore.release();
	}

	@Override
	public void waitCompletion() throws RemoteException {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
