package hdfs.distributedfilesystem;

import formats.Format;

import java.io.Serializable;
import java.util.ArrayList;

public class HdfsFile implements Serializable {

    private String fileName;
    private Format.Type format;
    private long fileSize;
    private transient ArrayList<Chunk> chunks;
    private int repFactor;

    public HdfsFile(String fileName, Format.Type format, int repFactor) {
        this.fileName = fileName;
        this.format = format;
        this.repFactor = repFactor;
        this.chunks = new ArrayList<Chunk>();
    }

    public void addChunk(Chunk chunk){
        this.chunks.add(chunk);
    }

    public ArrayList<Chunk> getChunks() {
        return chunks;
    }

    public Chunk getChunk(int index){
        return this.chunks.get(index);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Format.Type getFormat() {
        return format;
    }

    public void setFormat(Format.Type format) { this.format = format; }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setChunks(ArrayList<Chunk> chunks) {
        this.chunks = chunks;
    }

    public int getRepFactor() {
        return repFactor;
    }

    public void setRepFactor(int repFactor) {
        this.repFactor = repFactor;
    }

    @Override
    public String toString() {
        return "HdfsFile{" +
                "fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", chunks=" + chunks.toString() +
                ", repFactor=" + repFactor +
                '}';
    }
}
