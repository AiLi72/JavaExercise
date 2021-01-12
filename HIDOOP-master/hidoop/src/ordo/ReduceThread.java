package ordo;

import java.rmi.RemoteException;

import formats.Format;
import formats.Format.OpenMode;
import map.Reducer;

public class ReduceThread extends Thread {

	private Reducer reducer;

	private Format reader;
	private Format writer;
	
	private CallBack callBack;
	
	public ReduceThread(Reducer reducer_, Format reader_, Format writer_, CallBack callBack_) {
		this.reducer = reducer_;
		this.reader = reader_;
		this.writer = writer_;
		this.callBack = callBack_;
	}
	
	public void run() {
		this.reader.open(OpenMode.R);
		this.writer.open(OpenMode.W);
		
		
		this.reducer.reduce(reader, writer);
		
		this.writer.close();
		this.reader.close();
		
		try {
			callBack.onComplete();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}