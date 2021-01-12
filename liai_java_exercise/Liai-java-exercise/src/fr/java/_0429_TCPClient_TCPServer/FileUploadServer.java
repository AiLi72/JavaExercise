package fr.java._0429_TCPClient_TCPServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        //在现实情况中，服务器永远不关闭，并一直处于监听状态：while死循环
        while (true) {
            Socket socket = serverSocket.accept();
            //每收到一个客户端的accept，就创立一个多线程
            new Thread(new Runnable() {
                //重写run方法：实现文件上传
                @Override
                public void run() {
                    try {
                        FileOutputStream fos = new FileOutputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/3/ccc.txt", true);
                        byte[] bytes = new byte[1024];
                        int len = 0;
                        while ((len = socket.getInputStream().read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                            socket.close();
                            fos.close();
                        }

                        socket.getOutputStream().write("i have got the file".getBytes());
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }).start();
        }
        //不需要关闭服务器了
        //serverSocket.close();
    }
}
