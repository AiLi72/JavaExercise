package fr.java._0429_TCPClient_TCPServer;
//当服务器为建立或者未开启时，运行客户端会抛出异常：java.net.ConnectException

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        //建立Socket对象,构造方法中绑定服务器的IP地址和端口号(String host,int port)
        Socket socket = new Socket("127.0.0.1", 8888);
        //使用Socket对象获得网络字节输出流和输入流，分别读和写
        //⚠️不是自己创建的，是Socket对象分配的
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        os.write("Hello Server!".getBytes());
        //注意这里不能用-1的形式，因为在Socket中，只有对方关闭时，才为-1
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
            System.out.println(new String(bytes, 0, len));
        //释放流，释放Socket即可
        socket.close();
    }
}
