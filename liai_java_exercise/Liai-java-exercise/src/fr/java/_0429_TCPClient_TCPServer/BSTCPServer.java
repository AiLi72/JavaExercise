package fr.java._0429_TCPClient_TCPServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//浏览器-服务器
//客户端变成了浏览器，输入网址就是客户端向服务器的请求，所以我们只需要写服务器端代码就可以
//当客户端想访问服务器时，会发送一个地址，这个地址告诉服务器所需要的文件路径，服务器读取这个文件，再将其回写给客户端
public class BSTCPServer {
    public static void main(String[] args) throws IOException {
       //创建服务器对象，索要指定端口号
        ServerSocket serverSocket = new ServerSocket(8888);
        //让服务器一直出于监听状态
        while (true){
            Socket socket = serverSocket.accept();//每获得一个socket对象就开启一个线程，不应放在线程里面
          //html页面：所有的图片都需要线程来加载，有一个图片，客户端请求一次服务器，服务器开启一个线程返回这个图片
            new Thread(new Runnable() {
              @Override
              public void run() {
                 //服务器获取请求的客户端
                  try {
                      InputStream is = socket.getInputStream();
                      //[重点]将网络字节输入流转换为字符缓冲输入流（因为字符缓冲输入流可以读取一整行，而我们需要第一行）
                      BufferedReader br = new BufferedReader(new InputStreamReader(is));
                      //读写来自客户端访问信息的第一行
                      String line = br.readLine();
                      //第一行文件用空格为标志进行切割
                      String[] s = line.split(" ");
                      //然后去掉首位\，得到要读取文件的地址
                      String htmlpath = s[1].substring(1);
                      //用自身的输入流读取文件,参数传递文件地址
                      System.out.println(htmlpath);
                      FileInputStream fis = new FileInputStream(htmlpath);
                      int len = 0;
                      byte[] bytes = new byte[1024];
                      while ((len = fis.read(bytes)) != -1) {
                          OutputStream os = socket.getOutputStream();
                          //写入http响应头，固定写法
                          os.write("HTTP/1.1 200 OK\r".getBytes());
                          os.write("Content-Type:text/html\r".getBytes());
                          //必须要写入空行，否则服务器不解析
                          os.write("\r".getBytes());
                          //把读到的东西回写给客户端显示页面
                          socket.getOutputStream().write(bytes, 0, len);
                          socket.close();
                          fis.close();
                          //serverSocket.close(); 不需要关闭服务器了
                      }
                  }catch (IOException e){
                      e.printStackTrace();
                  }
              }
          }).start();

        }
    }
}
