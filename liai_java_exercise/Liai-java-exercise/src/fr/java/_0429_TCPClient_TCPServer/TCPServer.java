package fr.java._0429_TCPClient_TCPServer;
//⚠️服务器端没有io流，他通过获取来自客户端的io流和客户端通信：用你的钱请你吃饭
//运行的时候先运行服务器端，这是服务器端处于监听状态，等待客户端的访问

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        //创建服务器端ServerSocket对象，构造方法中指定端口号。这样客户端才可以找到他
        ServerSocket ss = new ServerSocket(8888);
        //使用accept方法获得请求连接的客户端名称，这样我们可以知道给谁回复；返回值是个Socket对象
        Socket socket = ss.accept();
        //下面的步骤和客户端一样，通过获取到的SOCKET对象或者网络字节输入流和输入流
        byte[] bytes = new byte[1024];
        int len = socket.getInputStream().read(bytes);
        System.out.println(new String(bytes, 0, len));
        socket.getOutputStream().write("hello Client!".getBytes());
        //关闭流：两个流都要关闭
        socket.close();
        ss.close();
    }
}
