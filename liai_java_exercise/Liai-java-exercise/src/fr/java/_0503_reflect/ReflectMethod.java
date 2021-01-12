package fr.java._0503_reflect;

//获取字节码对象中的其他方法Method
//获取方法的目的就是使用方法：invoke(Object obj,Object ...args)
import fr.java._0327_Data_equals.Person;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethod {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //0.获取Class类文件
        Class<Person> personClass = Person.class;

        //1.获取所有public方法：注意：除了类本身带的方法我们还会得到继承来自Object类中的方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            //获取方法名 getName()
            String name = method.getName();
            System.out.println(name);
        }
        System.out.println("-------------");

        //2.获取public修饰的指定方法名称和参数的方法：参数要传递String类型的名称和Class类型的参数
        Method eat_method = personClass.getMethod("eat");//空参
        Person person = new Person();
        eat_method.invoke(person);//eat...

        Method eat_method2 = personClass.getMethod("eat", String.class);//有一个String类型的参数
        eat_method2.invoke(person,"meat");//传递真实的参数 //eatmeat
        System.out.println("-------------");

        //3.获取所有方法，,如果有私有方法，还需要用到暴力反射
        Method[] declaredMethods = personClass.getDeclaredMethods();
        //...不再赘述

        //获取类名
        String name = personClass.getName();
        System.out.println(name); //fr.cours._0327_Data_equals.Person
    }
}
