package fr.java._0428_ObjectStream;
// 序列化：将对象写入文件中：
//⚠️Person类需要 implement Serializable（序列化接口）
import fr.java._0327_Data_equals.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamExercise {
    public static void main(String[] args) throws IOException {
        Person one = new Person("ai",20);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/person.txt"));
        //注意写入的方法是 writeOobject（），但是写入后的内容如果我们直接打开是乱码
        oos.writeObject(one);
        oos.close();
    }
}
