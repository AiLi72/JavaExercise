package fr.java._0423_Reader_Writer;
//文件字符输入流：以字符的方式读取

import java.io.FileReader;
import java.io.IOException;

public class FileReaderExercise {
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/bbb.txt");
        /*一次读取一个字符
        int len = 0;
        while ((len = fr.read()) != -1) {
            System.out.println((char)len);
        }
         */
        //一次读取多个字符
        int len = 0;
        char[] chars = new char[1024];
        while ((len = fr.read(chars) )!= -1) {
            System.out.println(new String(chars,0,len));
        }
        fr.close();
    }
}
