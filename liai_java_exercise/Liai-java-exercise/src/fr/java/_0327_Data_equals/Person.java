package fr.java._0327_Data_equals;

import java.io.Serializable;
import java.util.Objects;
//注意 ：静态的（带static关键字的）是不可以被序列化的

public class Person implements Comparable<Person>, Serializable  {

    //每次修改程序，都会重新生成一个.class文件和新的序列号，如果不重新进行序列化操作，则无法直接进行反序列化操作
    // 我们需要手动给程序添加一个固定的序列号，这样无论怎么修改程序，序列号都固定（L为long类型，数值任意）
    private static final long serialVersionUID = 1l;

    //为了字节码文件获取到特定修饰符的成员变量(关键字的范围由大到小)
    public String a;
    protected String b;
    String c; //啥也不写是default
    private String d;


    String name;
    int age;
    //static int age; 不能被序列化
    //private transient int age; transient关键字也不可以被序列化


    @Override
    public String toString() {
        return "Person{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //重写equals方法，可以用来根据成员变量比较对象是否相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    //重写compareTo方法：用来根据对象的年龄进行比较
    @Override
    public int compareTo(Person o) {
        return this.getAge() - o.getAge();
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //空参方法
    public void eat(){
        System.out.println("eat...");
    }
    //有参方法
    public  void eat(String food){
        System.out.println("eat"+food);
    }
}
