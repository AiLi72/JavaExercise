package fr.java._0428_ObjectStream;
//用集合的形式在文件中写入或读取 对象：一次可以写入或读取多个对象
import fr.java._0327_Data_equals.Person;

import java.io.*;
import java.util.ArrayList;

public class ObjectStreamArray {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person one = new Person("ai", 10);
        Person two = new Person("aii", 20);
        Person three = new Person("aiii", 30);

        ArrayList<Person> arrayList = new ArrayList<>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/person.txt"));
        oos.writeObject(arrayList);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/person.txt"));
        Object o = ois.readObject(); //此时的o其实是个集合
        //注意此时需要吧Object类型的集合强转成Arraylist集合，不然无法遍历
        ArrayList<Person> list2 = (ArrayList<Person>) o;

        for (Person p : list2) {
            System.out.println(p);
        }
        oos.close();
        ois.close();

    }
}
