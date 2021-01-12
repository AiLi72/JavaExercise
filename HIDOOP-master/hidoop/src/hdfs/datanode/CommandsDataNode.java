package hdfs.datanode;

import formats.Format;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommandsDataNode extends Remote {

    void transferDataNode (InetAddress address, int port, String command, String hdfsFname, String chunkName) throws RemoteException;

    boolean deleteHdfsFile(String hdfsFname) throws RemoteException;
}
