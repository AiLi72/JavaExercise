package fr.java._0417_File;

import java.io.File;
import java.io.IOException;

//创建File文件夹：把字符串封装为File对象。
public class FileClass {
    public static void main(String[] args){
        String s = File.pathSeparator;
        System.out.println(s); //路径分隔符。结果是：冒号。windows是分号，Linux也是冒号

        String s1 = File.separator;
        System.out.println(s1); //文件名称分隔符。结果是斜杠/。windows是反斜杠\,Linux也是正斜杠/

        //第一种方式创建文件夹或文件：
        //注意：我们不考虑路径的真假，路径可以以文件结尾（a.txt），也可以以文件夹结尾
        File f1 = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa.txt");
        System.out.println(f1);//打印了路径,说明重写了toString方法

        //第二种方式创建文件夹或文件，路径由两部分组成，更灵活：
        File f2 = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122","/aaa");
        System.out.println(f2); //c:/a.txt

        //第三种构造方法创建文件或文件夹，路径由两部分组成，其中parent位置可以使用File类，这样可以使用一些类的方法作为参数用来构造文件
        File parent = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122");
        File f3 = new File(parent,"/bbb/ccc/ddd");
        System.out.println(f3); //c:/java.class

        //第四种方法只能创建文件：不是构造方法，是内部方法
        //注意：createNewFile方法：如果文件不存在，则创建，并且返回true。⚠️没有参数（参数在构造对象时写过了）
        //当文件路径不对时，抛出异常
        try {
            boolean b = f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //第五种方式只能创建单级文件夹，如果文件夹不存在，则创建，并且返回true：
        //当路径不存在时，返回false，不抛出异常
        System.out.println(f2.mkdir());

        //第六种方式既能创建单级文件夹，又能创建多级文件夹，如果文件夹不存在，则创建，并且返回true：
        //当路径不存在时，返回false，不抛出异常
        System.out.println(f3.mkdirs());

        //注意：delete方法直接从硬盘删除，不走回收站
        System.out.println(f1.delete());

        //第一种遍历：
        // 把当前文件夹或目录的名字存在数组总，返回一个String类型的数组
        // 注意文件不可以遍历，会抛出空指针异常，目录不存在或者路径不是一个目录，也会抛出空指针异常
        for (String fileName : f1.list()) {
            System.out.println(fileName);
        }

        //第二种方式遍历：
        // 返回一个File类型的数组，根据构造方法中的路径，将当前目录下的所有文件或者文件夹，把他们封装成File对象，多个File对象存储到数组中
        for (File file : f3.listFiles()) {
            System.out.println(file);
        }


        /**
        ⚠️文件夹没有大小的概念，length方法只能获取文件的大小
        ⚠️判断是文件还是文件夹时不要看名字，而要看类型 .txt也可能是个文件夹的名字
         **/
    }
}
