package hdfs.datanode;

import config.Project;
import formats.Format;
import formats.LineFormat;

import java.io.*;
import java.net.Socket;


public class HdfsClientHandler implements Runnable {
    final Socket socket;
    final InputStream is;
    final OutputStream os;
    String command;
    String filename;
    String chunkName;


    public HdfsClientHandler(Socket socket, InputStream is, OutputStream os, String command, String filename, String chunkName){
        this.socket = socket;
        this.is = is;
        this.os = os;
        this.command = command;
        this.filename = filename;
        this.chunkName = chunkName;
    }

    @Override
    public void run() {
        try {
            if(command.equals("write")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                File folder = new File(Project.FOLDER_PATH_DATANODE_FILES + "/" + filename);
                boolean folderCreated = folder.mkdirs();
                LineFormat file = new LineFormat(Project.FOLDER_PATH_DATANODE_FILES + "/" + filename + "/" + chunkName);
                file.open(Format.OpenMode.W);
                file.receiveFile(reader);
                file.close();
            } else if(command.equals("read")) {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
                LineFormat file = new LineFormat(Project.FOLDER_PATH_DATANODE_FILES + "/" + filename + "/" + chunkName);
                file.open(Format.OpenMode.R);
                file.sendFile(pw);
                file.close();
            }
            is.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur getLine";
        }
    }

    public static void sendLine(PrintWriter pw, String line) {
        pw.println(line);
        pw.flush();
    }
}
