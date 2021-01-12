package hdfs.namenode;

import formats.Format;
import hdfs.distributedfilesystem.Chunk;
import hdfs.distributedfilesystem.HdfsFile;
import nodemanager.NodeManager;
import resourcemanager.ResourceManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CommandsHdfsImpl extends UnicastRemoteObject implements CommandsHdfs {



    protected CommandsHdfsImpl() throws RemoteException {
        super();
    }


    @Override
    public void createFile(String filename, Format.Type format, int repFactor) {
        NameNode.hdfsFileHashtable.put(filename, new HdfsFile(filename, format, repFactor));
    }

    @Override
    public Format.Type getFileFormat(String filename) throws RemoteException {
        return NameNode.hdfsFileHashtable.get(filename).getFormat();
    }

    @Override
    public int getChunksNumber(String filename) throws RemoteException {
        return NameNode.hdfsFileHashtable.get(filename).getChunks().size();
    }

    @Override
    public NodeManager getRandomNodeManager() {
        Object[] keys = ResourceManager.nodesInfo.keySet().toArray();
        Object key = keys[new Random().nextInt(keys.length)];
        return ResourceManager.nodesInfo.get(key);
    }
    @Override
    public List<NodeManager> getNodesManager() throws RemoteException {
        return new ArrayList<>(ResourceManager.nodesInfo.values());
    }

    @Override
    public void addChunk(String filename, Chunk chunk) throws RemoteException {
        NameNode.hdfsFileHashtable.get(filename).addChunk(chunk);
    }


    @Override
    public void addChunkInfo(String filename, String chunkName, int id, int chunkSize, NodeManager nm) throws RemoteException {
        addChunk(filename, new Chunk(chunkName, id, chunkSize, nm));
    }

    @Override
    public Chunk getChunk(String filename, int id) throws RemoteException {
        return NameNode.hdfsFileHashtable.get(filename).getChunk(id);
    }


    @Override
    public void delete(String filename) throws RemoteException {
        NameNode.hdfsFileHashtable.remove(filename);
    }

    @Override
    public boolean doesFileNameExist(String filename) throws RemoteException {
        return NameNode.hdfsFileHashtable.containsKey(filename);
    }

    public HdfsFile getHdfsFile(String filename) throws RemoteException {
        return NameNode.hdfsFileHashtable.get(filename);
    }
}
