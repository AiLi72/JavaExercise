package fr.java._0423_Reader_Writer;
//文件字符输出流：以字符的方式写入缓冲区，用flush方法写入硬盘
import java.io.FileWriter;
import java.io.IOException;

public class FileWiterExercise {
    public static void main(String[] args) throws IOException {
        //开启续写和换行功能
        FileWriter fw = new FileWriter("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/aaa.txt",true);

        //注意此时的write方法只是将字符写到缓冲区中，此时有一个将字符转换为字节的过程
        //方法一：写入字符串
        for (int i = 0; i <10 ; i++) {
            fw.write("李艾"+"\r");
        }
        //方法二：写入一部分字符串
        //方法三：写入单个字符
        //方法四：写入char[]
        char[] chars = {'a','b','c','d','e','f'};
        fw.write(chars);
        //方法五：写入char[]一部分字符

        //需要用flush方法把缓冲区的字节储存到内存中，flush过后输出流还存在
        fw.flush();
        //如果 没有flush，那么程序会在close方法前自动进行flush，但是close过后输出流不再存在
        fw.close();
    }
}
