package fr.java._0420_FileFilter_FileNameFilter;

import java.io.File;
import java.io.FileFilter;

public class FileFilterImpl implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        //⚠️这一步很关键，如果文件名是个文件夹，我们也需要继续过滤
        if (pathname.isDirectory()){
            return true;
        }
        //用getname方法可以得到字符串类型的名字，顺便可以判断一下大小写
        return pathname.getName().toLowerCase().endsWith(".java");
    }
}
