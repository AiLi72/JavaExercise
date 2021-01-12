package fr.java._0426_Buffered;
////字节缓冲输入流
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamExercise {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/aaa.txt");

        BufferedInputStream bis = new BufferedInputStream(fis,1024);
        /*一次读取一个字节
        int len = 0;
        while ((len=bis.read())!=-1){
            System.out.println((char)len);
        }
         */

        //一次读取多个字节，在缓冲区的基础上再加上字节数组，效率更高
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len=bis.read(bytes))!=-1){
            //将字节数组转换为string字符串
            System.out.println(new String(bytes,0,len));
        }
        //只需关闭缓冲流就好，系统自动关系普通流
        bis.close();
    }
}
