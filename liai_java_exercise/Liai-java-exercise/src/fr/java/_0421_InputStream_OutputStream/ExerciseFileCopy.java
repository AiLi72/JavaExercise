package fr.java._0421_InputStream_OutputStream;
//变读边写：复制的原理
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExerciseFileCopy {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/3/ccc.txt");
        file.createNewFile();

        FileInputStream fis = new FileInputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/bbb.txt");
        FileOutputStream fos = new FileOutputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/3/ccc.txt");

        /* 方法一：一次只读取一个字节
        int len = 0;
        while ((len = fis.read())!= -1) {
            fos.write((char)len);
        }
         */

        //方法二：使用byte[],读多少字节，写入多少字节
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read())!= -1) {
            //直接写入这个数组，不需要什么String类型转换
            fos.write(bytes,0,len);
        }
        //释放资源。先关写的，再关读的。因为如果写完了肯定读完了，反之不一定
        fos.close();
        fis.close();
    }
}
