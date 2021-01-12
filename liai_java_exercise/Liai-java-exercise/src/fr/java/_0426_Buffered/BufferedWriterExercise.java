package fr.java._0426_Buffered;
//字符缓冲输出流:可以写字符串
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExercise {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/aaa.txt",true),1024);
        bw.write("enseeiht");
       //换行指令，不用区分系统
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
