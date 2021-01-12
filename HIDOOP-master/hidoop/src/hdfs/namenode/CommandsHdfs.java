package hdfs.namenode;

import formats.Format;
import hdfs.distributedfilesystem.Chunk;
import hdfs.distributedfilesystem.HdfsFile;
import nodemanager.NodeManager;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CommandsHdfs extends Remote {

    public void createFile(String filename, Format.Type format, int repFactor) throws RemoteException;

    public Format.Type getFileFormat(String filename) throws RemoteException;

    public int getChunksNumber(String filename) throws RemoteException;

    public NodeManager getRandomNodeManager() throws RemoteException;

    public List<NodeManager> getNodesManager() throws RemoteException;

    public void addChunk(String filename, Chunk chunk) throws RemoteException;

    public void addChunkInfo(String filename, String chunkName, int id, int chunkSize, NodeManager nm) throws RemoteException;

    public Chunk getChunk(String filename, int id) throws RemoteException;

    public void delete(String filename) throws RemoteException;

    public boolean doesFileNameExist(String filename) throws RemoteException;

}
