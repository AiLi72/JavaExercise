package fr.java._0503_reflect;

//获取字节码对象中的构造方法Constructor
//构造器其实就是用来创建对象的，我们得到了构造方法，就可以创建对象了：newInstance(）

import fr.java._0327_Data_equals.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectConstructor {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //0.获取Class类文件
        Class<Person> personClass = Person.class;

        //1.获取所有的构造方法 ： Constructor<?>[] getConstructors()
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
            //打印结果：public fr.cours._0327_Data_equals.Person()
            //public fr.cours._0327_Data_equals.Person(java.lang.String,int)
        }
        System.out.println("---------------");

        //2.获取指定类型的构造方法getConstructor(）：有参数或无参数-->取决于有参构造还是无参构造
           //1.获取有参构造方法：参数是类！
            Constructor<Person> constructor1 = personClass.getConstructor(String.class, int.class);
            System.out.println(constructor1);//public fr.cours._0327_Data_equals.Person(java.lang.String,int)
            //构造对象 newInstance(参数为成员变量的值)
            Person person = constructor1.newInstance("liai", 20);
            System.out.println(person);//Person{a='null', b='null', c='null', d='null', name='liai', age=20}
            System.out.println("---------------");

            //2.获取无参构造方法：参数为空
            Constructor<Person> constructor2 = personClass.getConstructor();
            System.out.println(constructor2);
            //构造对象 newInstance(空参)
             person = constructor2.newInstance();
            System.out.println(person);//Person{a='null', b='null', c='null', d='null', name='null', age=0}
            System.out.println("---------------");
                //简化空参构造器：直接用Class类的方法：省略了上述先获得构造方法的过程
                Person person1 = personClass.newInstance();
                System.out.println(person1);//Person{a='null', b='null', c='null', d='null', name='null', age=0}
                System.out.println("---------------");

        //3.获取所有的构造方法（包括私有的），同时可以传递参数，同时需要用到暴力反射
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor); //结果和1一样，因为Person类中没有私有构造方法
        }
    }
}
