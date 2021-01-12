package fr.java._0428_ObjectStream;
//反序列化：从文件中读取对象

import fr.java._0327_Data_equals.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//要抛出的异常除了IOException之外还有ClassNotFoundException
public class ObjectInputStreamExercise {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/person.txt"));
        Object o = ois.readObject();
        System.out.println(o);
        //或者用强制类型转换的方式来输出：
        System.out.println((Person) o);
    }
}
