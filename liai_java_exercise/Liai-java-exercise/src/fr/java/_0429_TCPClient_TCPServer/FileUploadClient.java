package fr.java._0429_TCPClient_TCPServer;
//文件的上传：客户端读取本地的文件，把文件传递给服务器，服务器读取文件并写入服务器硬盘中
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileUploadClient {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/aaa.txt");
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream os = socket.getOutputStream();

        //⚠️往服务器写数据的时候不会把-1标记写进去，所以服务器永远读不到-1结束标记位，进入阻塞状态
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        //上传完文件，给服务器写一个结束标记
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        int len2 = 0;
        while ((len2 = is.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len2));
        }
        socket.close();
        fis.close();
    }
}
