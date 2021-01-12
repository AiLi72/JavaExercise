package fr.java._0420_FileFilter_FileNameFilter;

import java.io.File;

public class FileNameFilterExercise {
    public static void main(String[] args) {
        File f = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa");
        getAllFiles(f);
    }

    /*
    private static void getAllFiles(File f) {
        File[] files = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //我们没办法通过name看出来是不是文件夹，所以要把File类的名字和name组合起来
                return new File(dir, name).isDirectory() || name.toLowerCase().endsWith(".java");
            }
        });
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFiles(file);
            } else {
                System.out.println(file);
            }
        }
    }

     */
    //lambada表达式简化
    private static void getAllFiles(File f) {
        File[] files = f.listFiles((dir, name)->
            new File(dir, name).isDirectory() || name.toLowerCase().endsWith(".java")
        );
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFiles(file);
            } else {
                System.out.println(file);
            }
        }
    }
}
