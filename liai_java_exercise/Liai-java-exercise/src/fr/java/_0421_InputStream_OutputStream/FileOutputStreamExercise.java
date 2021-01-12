package fr.java._0421_InputStream_OutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileOutputStreamExercise {
//OutputStream：文件字节输出流：由内存写入硬盘的aaa.txt中
    public static void main(String[] args) throws IOException {

        /*
        File file = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/aaa.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        //第一种创建输出流的方式：每运行一次都会重新创建一个文件，将当前文件覆盖
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/aaa.txt");

            //写入内容的方法一，依次写入
            fos.write(100);
            fos.write(49);
            fos.write(49);
            fos.write(48);

            //方法二：用byte[]数组，一次写入多个，但是这时候要写ASCII码
            byte[] bytes1 = {65,66,67,68};
            fos.write(bytes1,0,4);

            //方法三，直接写入字符串，再将字符串转化为byte数组，再写入
            byte[] bytes = "hello".getBytes();
            fos.write(bytes);

            //⚠️Arrays.toString方法可以查看数组全部元素，如果直接打印数组名称，得到的是数组的地址值
            System.out.println(Arrays.toString(bytes));

            //释放内存
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //第二种创建输出流的方式：【续写】（直接在原文件上继续写，不创建新文件）与【换行】(mac系统是\r,windows是/r/n,linux是\n)
        FileOutputStream fos2 = new FileOutputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/bbb.txt",true);
        for (int i = 0; i <10; i++) {
            fos2.write("hello".getBytes());
            //换行也是字符
            fos2.write("\r".getBytes());
        }
        fos.close();
    }
}


