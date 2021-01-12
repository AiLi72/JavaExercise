package fr.java._0503_reflect;

import fr.java._0327_Data_equals.Person;

/**
 *反射：框架（半成品软件）设计的灵魂---将类的各个组成部分封装成对象
 * * 好处：可以在程序运行过程总操作这些对象；可以解藕，提高程序的可扩展性
 *
 * java代码在计算机中经历的三个阶段（以创建对象为例）
 * *1 .Java文件编译成为.class文件：resource源代码阶段-->
 * *2. 将.class文件封装成Class类对象：Class类对象阶段-->
 * *3. 创建出对象：Runtime运行时阶段
 *
 * 获取Class对象的三种方式（无论用哪一种方式获取，同一个字节码文件.class文件只会被加载一次,获取到的class对象都是同一个）：
 * *1.Class.forName("全类名")：将字节码文件加载进内存，返回class对象-->多用于配置文件，将类名定义在配置文件中：读取文件，加载类
 * *2.类名.class:通过类名的属性class获取-->多用于传递参数
 * *3.对象.getClass():getClass()这个方法在Object类中定义--->多用于对象获取字节码文件
 *
 */
public class Reflect {

    public static void main(String[] args) throws Exception {
        //1.全类名：包名+类名：就是一打开类中import的内容复制过来就行
        Class<?> aClass = Class.forName("fr.java._0327_Data_equals.Person");
        //2.
        Class<Person> personClass = Person.class;
        //3.
        Person person = new Person();
        Class<? extends Person> aClass1 = person.getClass();

        System.out.println(aClass); // 输出： class fr.cours._0327_Data_equals.Person
    }
}
