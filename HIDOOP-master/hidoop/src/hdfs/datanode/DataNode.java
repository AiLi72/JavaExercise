package hdfs.datanode;

import config.Project;
import hdfs.namenode.CommandsHdfs;
import hdfs.namenode.CommandsHdfsImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Timer;

public class DataNode implements Runnable{

    Registry registry;

    public DataNode(Registry registry) {
        this.registry = registry;
    }

    @Override
    public void run() {
        try {
            //On lie le stub à l'objet remote dans le registre
            CommandsDataNodeImpl obj = new CommandsDataNodeImpl();
            CommandsDataNode stubDN = (CommandsDataNode) obj;
            registry.bind("CommandsDataNode", stubDN);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        while(true) {

        }
    }
}
