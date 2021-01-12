package fr.java._0503_reflect;

//创建好文件字节码文件之后，获取其中的成员变量Filed
//获取成员变量就是为了赋值和查看值：get（），set()
import fr.java._0327_Data_equals.Person;
import java.lang.reflect.Field;

public class ReflectField {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //0.获取Class类文件
        Class<Person> personClass = Person.class;

        //1.获取所有public修饰的成员变量：Field[] getFields()
        Field[] fields = personClass.getFields();
        //⚠️iter是for each循环的快捷键️
        for (Field field : fields) {
            System.out.println(field); //输出：public java.lang.String fr.cours._0327_Data_equals.Person.a
        }
        System.out.println("-----------------");

        //2.获取的固定的public成员变量，输入名字，返回一个对象，这样就可以对这个成员变量进行赋值和获取值
        Field a = personClass.getField("a"); //注意a是个对象
        //获取a的值 get(Object obj),返回值也是个对象
        Person person = new Person();
        Object o = a.get(person);
        System.out.println(o); //null :字符串默认初始值null
        //设置a的值 void set(Object obj,Object value)
        a.set(person,"liai");
        System.out.println(person);
        System.out.println("-----------------");


        //3.获取全部成员变量: Field[] getDeclaredFields()
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
            //private static final long fr.cours._0327_Data_equals.Person.serialVersionUID
            //public java.lang.String fr.cours._0327_Data_equals.Person.a
            //protected java.lang.String fr.cours._0327_Data_equals.Person.b
            //java.lang.String fr.cours._0327_Data_equals.Person.c
            //private java.lang.String fr.cours._0327_Data_equals.Person.d
            //java.lang.String fr.cours._0327_Data_equals.Person.name
            //int fr.cours._0327_Data_equals.Person.age
        }
        System.out.println("-----------------");


        /** 4.获取任意修饰符修饰的成员变量，输入名字，返回一个对象，可以对这个对象进行赋值和获取值
                但是如果我们想要获取private修饰的成员变量时，会抛出异常：安全检查
                此时我们就需要忽略访问权限修饰符的安全检查
         */
        Field d = personClass.getDeclaredField("d");
        d.setAccessible(true); //暴力反射
        Object o1 = d.get(person); //之前的代码中已经创建过对象了
        System.out.println(o1); //null
        System.out.println("-----------------");


    }
}
