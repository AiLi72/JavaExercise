package hdfs.distributedfilesystem;

import nodemanager.NodeManager;

import java.io.Serializable;

public class Chunk implements Serializable {

    private String chunkName;
    private int id;
    private int chunkSize;
    private NodeManager nm;

    public Chunk(String chunkName, int id, int chunkSize, NodeManager nm) {
        this.chunkName = chunkName;
        this.id = id;
        this.chunkSize = chunkSize;
        this.nm = nm;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "chunkName='" + chunkName + '\'' +
                ", id=" + id +
                ", chunkSize=" + chunkSize +
                ", nm=" + nm +
                '}';
    }

    public String getChunkName() {
        return chunkName;
    }

    public void setChunkName(String chunkName) {
        this.chunkName = chunkName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public NodeManager getNodeManager() {
        return nm;
    }

    public void setNodeManager(NodeManager nm) {
        this.nm = nm;
    }
}
