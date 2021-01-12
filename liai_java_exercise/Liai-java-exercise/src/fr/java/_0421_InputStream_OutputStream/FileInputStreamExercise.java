package fr.java._0421_InputStream_OutputStream;

import java.io.FileInputStream;
import java.io.IOException;

//InputStream:文件字节输入流，把硬盘中的数据，读取到内存中使用
public class FileInputStreamExercise {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/bbb.txt");
        //.read方法特殊：一次只读取一个元素并返回，同时将指针指向下一个元素，当下一个元素没有时，返回-1
        //⚠️强制类型转换（char）:括号的位置
        // System.out.println((char)fis.read());

        //【重点！】
        /*现在将读取语句改良：(固定写法)
        主要有三个作用：
        1.fis.read()读取一个元素,并将指针指向下一位【关键！】
        2.len=fis.read() 将读取到的元素赋值给len
        3.len=fis.read())!= -1进行判读
         */

        /* 正确写法：
        int len  = 0; 用来记录
        while ((len=fis.read())!= -1){
            System.out.println((char)len);
        }
         */

        /*
        ⚠️以下写法是错误的：没有对fis.read进行赋值直接就判断，那么判断的和输出的不是一个值
         int len  = fis.read();   // 这里先赋值给len一次
        while ((fis.read())!= -1){     // 到这里已经是第二次了
            System.out.println((char)len);
        }
         */

        /*
        一次读取多个字节：int read(byte[] b):从流中读取一定数量的字节，并将其储存在缓冲区数组b中
        1.int :返回值是读取到的字节的个数
        2.byte[]：用来存储读取到的多个的字节，起到缓冲的作用
        另外：为了阅读方便，使用String类中的方法将byte数组转换为字符串：String（byte[] b)
         */

        /*创建一个长度为两个字节的数组,作为储存字节的单位
        byte[] bytes = new byte[5];
        //⚠️输出fis.read得到的是int类型的一个数组，是得到字节的长度，要想知道读了什么，要输出这个数组
        fis.read(bytes);
        System.out.println(new String(bytes));
         */

        //我们用循环的方式来优化，以下为固定写法：
        int len = 0; //用来记录每次读取到的有效字节个数
        byte[] bytes = new byte[1024];//一般用1kb长度建立数组
        while((len=fis.read(bytes))!=-1){
            //System.out.println(new String(bytes)); //这样打印出来的数组长度为1024，后面的都浪费了
            //所以我们用一个方法，只读取数组的有效字节
            System.out.println(new String(bytes,0,len)); //offset为起始索引，len为长度
        }
        //释放资源
        fis.close();
    }
}
