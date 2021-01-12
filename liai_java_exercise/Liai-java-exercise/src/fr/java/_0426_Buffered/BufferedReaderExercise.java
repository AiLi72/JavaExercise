package fr.java._0426_Buffered;
//字符缓冲输入流

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExercise {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/aaa.txt"), 1024);

        /*
        //读取的方法一：read方法：一个字符一个字符读取
        int len = 0;
        //注意：字符输入流里的read方法读的是char[]
        char[] chars = new char[1024];
        while ((len = br.read(chars) )!= -1) {
            System.out.println(new String(chars, 0, len));
        }
         */

        //读取的方法二：readLine方法：读取一整行数据,但是不包含任何终止复合，意思是返回的只有字符串，没有空格或换行，如果读到每行的终止标志，则返回null
        String line;
        while ((line = br.readLine()) != null) {
             System.out.println(line);
        }
        br.close();
    }
}
