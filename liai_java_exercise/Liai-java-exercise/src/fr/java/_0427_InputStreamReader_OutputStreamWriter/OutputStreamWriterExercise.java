package fr.java._0427_InputStreamReader_OutputStreamWriter;
//OutputStreamWriter：可以写指定字符集的字符 比如可以写GBK
import java.io.*;

public class OutputStreamWriterExercise {
    public static void main(String[] args) throws IOException {
        //第二个参数是指定的编码字符集，如果不写，默认是utf-8，显示你好，6个字节
        //OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/utf-8.txt"),"utf-8");
        //现在该用GBK编码，我们发现在IDEA中无法显示���，但是从电脑本身文件夹看是你好：4个字节
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/gbk.txt"),"gbk");
        osw.write("你好");
        osw.flush();
        osw.close();

    }
}
