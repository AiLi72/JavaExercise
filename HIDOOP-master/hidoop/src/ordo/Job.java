package ordo;

import config.Project;
import formats.Format;
import formats.Format.Type;
import formats.KVFormat;
import formats.LineFormat;
import hdfs.client.HdfsClient;
import hdfs.distributedfilesystem.Chunk;
import hdfs.namenode.CommandsHdfs;
import hdfs.namenode.NameNode;
import map.MapReduce;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Job implements JobInterfaceX {

    private Type inputFormat;
    private Type outputFormat;

    private String inputFName;
    private String outputFName;

    private int numberOfReduces;
    private int numberOfMaps;

    private SortComparator sortComparator;

    @Override
    public void setInputFormat(Type ft) {
        this.inputFormat = ft;
    }

    @Override
    public void setInputFname(String fname) {
        this.inputFName = fname;
    }

    @Override
    public void setNumberOfReduces(int tasks) {
        this.numberOfReduces = tasks;
    }

    @Override
    public void setNumberOfMaps(int tasks) {
        this.numberOfMaps = tasks;
    }

    @Override
    public void setOutputFormat(Type ft) {
        this.outputFormat = ft;
    }

    @Override
    public void setOutputFname(String fname) {
        this.outputFName = fname;
    }

    @Override
    public void setSortComparator(SortComparator sc) {
        this.sortComparator = sc;
    }

    @Override
    public int getNumberOfReduces() {
        return this.numberOfReduces;
    }

    @Override
    public int getNumberOfMaps() {
        return this.numberOfMaps;
    }

    @Override
    public Type getInputFormat() {
        return this.inputFormat;
    }

    @Override
    public Type getOutputFormat() {
        return this.outputFormat;
    }

    @Override
    public String getInputFname() {
        return this.inputFName;
    }

    @Override
    public String getOutputFname() {
        return this.outputFName;
    }

    @Override
    public SortComparator getSortComparator() {
        return this.sortComparator;
    }

    @Override
    public void startJob(MapReduce mr) {
        CallBack cb;
        try {
            cb = new CallBackImpl();
            Registry registryResourceManager = LocateRegistry.getRegistry(Project.RESOURCE_MANAGER_HOSTNAME, Project.RMIPORT_RM);
            CommandsHdfs stubCH = (CommandsHdfs) registryResourceManager.lookup("CommandsHdfs");

            String mrFilename = this.getInputFname() + Project.RESULT_SUFFIX;
            stubCH.createFile(mrFilename, Type.KV, 1);

            /*
             * On �crit le fichier source sur les diff�rentes machines
             */

            System.out.println("Starting all maps");

            int chunksNumber = stubCH.getChunksNumber(this.getInputFname());

            for (int k = 0; k < chunksNumber; k++) {
                Chunk chunk = stubCH.getChunk(this.getInputFname(), k);
                String ip = chunk.getNodeManager().getAddress().getHostName();
                Registry registryNodeManager = LocateRegistry.getRegistry(ip, Project.RMIPORT_NM);
                Daemon daemon = (Daemon) registryNodeManager.lookup(Project.REGISTRY_DAEMON_NAME);

                Format reader;
                String chunkPath = Project.FOLDER_PATH_DATANODE_FILES + this.getInputFname()+ "/" + this.getInputFname() + k;
                if (this.getInputFormat() == Type.KV) {
                    reader = new KVFormat(chunkPath);
                } else {
                    reader = new LineFormat(chunkPath);
                }

                Format writer;
                String newChunkPath = Project.FOLDER_PATH_DATANODE_FILES + mrFilename + "/" + mrFilename + k;
                if (this.getOutputFormat() == Type.KV) {
                    writer = new KVFormat(newChunkPath);
                } else {
                    writer = new LineFormat(newChunkPath);
                }
                chunk.setChunkName(getInputFname() + Project.RESULT_SUFFIX + k);
                stubCH.addChunk(mrFilename, chunk);


                daemon.runMap(mr, reader, writer, cb);
            }

            /*
             * Attente bloquante de tous les maps afin qu'on soit s�r qu'ils soient tous finis
             */
            for (int k = 0; k < chunksNumber; k++) {
                cb.waitCompletion();
            }

            System.out.println("All maps are finished");
            /*
             * On r�cup�re les r�sultats gr�ce � hdfs
             */
            HdfsClient.HdfsRead(this.getInputFname()+Project.RESULT_SUFFIX, this.getInputFname() + "-hdfsed");

            Format reader;
            if (this.getOutputFormat() == Type.KV) {
                reader = new KVFormat(this.getInputFname() + "-hdfsed");
            } else {
                reader = new LineFormat(this.getInputFname() + "-hdfsed");
            }
            reader.open(Format.OpenMode.R);

            Format writer;
            if (this.getOutputFormat() == Type.KV) {
                writer = new KVFormat(this.getOutputFname());
            } else {
                writer = new LineFormat(this.getOutputFname());
            }
            writer.open(Format.OpenMode.W);

            mr.reduce(reader, writer);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}
