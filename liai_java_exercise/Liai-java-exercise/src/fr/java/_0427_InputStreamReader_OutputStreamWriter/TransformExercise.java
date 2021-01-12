package fr.java._0427_InputStreamReader_OutputStreamWriter;
//练习：将u8格式的文件转化到gbk2中
import java.io.*;

public class TransformExercise {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/utf-8.txt"));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/gbk2.txt"),"gbk");

        char[] chars = new char[1024];
        int len;
        while ((len = isr.read(chars)) != -1) {
            osw.write(chars,0,len);
            osw.flush();
        }
        isr.close();
        osw.close();
    }
}
