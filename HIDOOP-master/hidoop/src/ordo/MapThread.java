package ordo;

import java.rmi.RemoteException;

import formats.Format;
import formats.Format.OpenMode;
import map.Mapper;

public class MapThread extends Thread {

	private Mapper mapper;

	private Format reader;
	private Format writer;

	private CallBack callBack;

	public MapThread(Mapper mapper_, Format reader_, Format writer_, CallBack callBack_) {
		this.mapper = mapper_;
		this.reader = reader_;
		this.writer = writer_;
		this.callBack = callBack_;
	}

	public void run() {
		System.out.println("starting map");
		this.reader.open(OpenMode.R);
		this.writer.open(OpenMode.W);

		this.mapper.map(reader, writer);

		this.writer.close();
		this.reader.close();
		
		try {
			callBack.onComplete();
			System.out.println("map complete");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
