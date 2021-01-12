package fr.java._0426_Buffered;
//字节缓冲输出流
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamExercise {
    public static void main(String[] args) {
        try (//1.创建OutputStream对象
             FileOutputStream fos = new FileOutputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/aaa.txt", true);
            //2.创建ButteredOutputStream对象，将fos作为参数传递进去,并且指定缓冲区的大小，不指定就是默认
            BufferedOutputStream bos = new BufferedOutputStream(fos,1024);) {
            //3.用bos中的方法write将数据写入缓冲区中（注意不是写到硬盘，是写到缓冲区）
            byte[] bytes = {65,66,67,68};
            bos.write(bytes,0,3);
            bos.write("i am the most beautiful girl in the world".getBytes());
            //4.使用bos中的flush方法将缓冲区的内容刷新到硬盘中
            bos.flush();
        } catch (IOException e) {
            System.out.println(e);
        }//使用此种try catch方法不需要关闭流了
    }
}
