package fr.java._0417_File;
//通过递归打印所有的文件或文件夹名
//通过递归的思想打印一个目录下所有的文件夹，如果这个文件夹下还有文件，继续打印

import java.io.File;

public class FileRecursion {
    public static void main(String[] args) {

        getAllFiles(new File("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa"));

    }

    //先遍历，对遍历得到的元素再进行判断，而不是先判断
    public static void getAllFiles(File file) {
        System.out.println(file); //为的是一上来先把看到的文件名都打印了，之后再一个个看哪个还是文件夹
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getAllFiles(f);
            }else {
                System.out.println(f);
            }
        }

    }
}
