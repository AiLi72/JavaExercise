package fr.java._0425_PropertiesArray;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesArray {
    public static void main(String[] args) throws IOException {
        //1.创建Properties集合对象
        Properties properties = new Properties();
        properties.setProperty("ai", "19");
        properties.setProperty("adrien", "20");
        //2.创建FileWriter对象
//        File file = new File("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/2/properties.txt");
//        file.createNewFile();
        FileWriter fw = new FileWriter("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/2/properties.txt");
        //3.把集合中的临时数据存储到硬盘中
        properties.store(fw, "save file");
        //4.释放资源(如果FileWriter使用的是匿名对象，则可以不用释放资源)
        fw.close();

        //现在再用Properties集合读取一个文件里的健值对
        //1.创建Properties集合对象（上面已经new过了）
        //2.与3.合并
        properties.load(new FileReader("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/2/properties.txt"));
        //4.使用匿名对象不需要释放资源

        //⚠️5.【重点！】遍历Properties集合
        //.stringPropertyNames()方法获取文件中所有的key,储存到一个set集合中，相当于之前的keySet方法
        Set<String> set = properties.stringPropertyNames();
        for (String key : set) {
            String value = properties.getProperty(key);
            System.out.println(key + value);

        }
    }
}
