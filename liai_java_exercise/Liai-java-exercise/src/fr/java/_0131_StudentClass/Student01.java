package fr.java._0131_StudentClass;

public class Student01 {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.age = 18;
        stu.name = "王";
        System.out.println(stu.age);
        System.out.println(stu.name);
        stu.eat();
        stu.study();

    }
}
