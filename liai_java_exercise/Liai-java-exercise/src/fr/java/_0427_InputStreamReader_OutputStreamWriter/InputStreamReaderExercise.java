package fr.java._0427_InputStreamReader_OutputStreamWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//InputStreamReader:可以读取指定编码字符集的文字，否则只能读utf-8
public class InputStreamReaderExercise {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/gbk.txt"), "gbk");
        char[] chars = new char[1024];
        int len;
        while ((len = isr.read(chars)) != -1) {
            System.out.println(new String(chars, 0, len));
        }
        isr.close();
    }
}
