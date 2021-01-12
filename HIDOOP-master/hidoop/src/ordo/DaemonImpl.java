package ordo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import config.Project;
import formats.Format;
import map.Mapper;
import map.Reducer;

public class DaemonImpl extends UnicastRemoteObject implements Daemon {

    protected DaemonImpl() throws RemoteException {
        super();
    }

    @Override
    public void runMap(Mapper m, Format reader, Format writer, CallBack cb) throws RemoteException {

        /*
         * Initialisation d'un thread pour ex�cuter le map
         */

        File folder = new File(writer.getFname());
        boolean folderCreated = folder.getParentFile().mkdirs();
        MapThread mt = new MapThread(m, reader, writer, cb);
        mt.start();
    }

    @Override
    public void runReduce(Reducer r, Format reader, Format writer, CallBack cb) throws RemoteException {

        /*
         * Initialisation d'un thread pour ex�cuter le reduce
         */

        ReduceThread rt = new ReduceThread(r, reader, writer, cb);
        rt.start();
    }

}
