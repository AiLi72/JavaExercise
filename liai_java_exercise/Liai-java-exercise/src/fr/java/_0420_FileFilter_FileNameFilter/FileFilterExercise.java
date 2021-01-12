package fr.java._0420_FileFilter_FileNameFilter;
/*
文件过滤器：选择性的打印文件或文件夹名
⚠️过滤器是在调用.listFiles时用的，根据fileFilter中的accept方法（我们自己重写）把符合要求的目录放入list中
fileFilter是个接口，需要接口的实现类才能实现，在调用listFiles时把这个实现类对象传递进去
 */

import java.io.File;

public class FileFilterExercise {
    public static void main(String[] args) {
        File f = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa");
        getAllFiles(f);
    }

    /*普通方法：创建接口的实现类
    private static void getAllFiles(File file) {
        //FileFilter是listFiles调用的
        for (File f : file.listFiles(new FileFilterImpl())) {
            if (f.isDirectory()) {
                getAllFiles(f);
            } else {
                System.out.println(f);
            }
        }
    }

     */

    //只有当接口中只有一个抽象方法时才可以使用lambada表达式
    // 对Impl进行优化，使用lambada表达式，同时省略参数类型  {};和return
    private static void getAllFiles(File file) {
        //FileFilter是listFiles调用的
        for (File f : file.listFiles((pathname) ->

                pathname.isDirectory() || pathname.getName().toLowerCase().endsWith(".java")
        )) { if (f.isDirectory()) {
                getAllFiles(f);
            } else {
                System.out.println(f);
            }
        }
    }
}
