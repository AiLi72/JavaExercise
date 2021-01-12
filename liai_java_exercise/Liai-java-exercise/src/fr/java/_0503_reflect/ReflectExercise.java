package fr.java._0503_reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 案例介绍：要求不改变Java类的任何代码，就创建任意的对象，实现其中的任意方法
 * 实现步骤：
 * 1.将需要创建的对象的全类名和需要执行的方法定义在配置文件中(new一个文件，随便起名，这个文件就是配置文件)
 * 2.在程序中加载读取配置文件
 * 3.使用反射技术加载类文件进内存
 * 4.创建对象
 * 5.执行方法
 *
 */
public class ReflectExercise {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
//1.加载配置文件
        //创建Properties类对象，用load方法把文件加载进内存（以字节的形式）
        Properties properties = new Properties();
       //加载配置文件，转换为一个集合 ->
        //获取当前字节码文件的类加载器（先获取当前类的字节码文件，再获取类加载器），把当前的类加载进内存
        ReflectExercise.class.getClassLoader();
        //classLoader这个类加载器可以找到所有的Java文件，包括我们需要的额配置文件
        //获取class目录下的配置文件，直接返回资源对应的字节流
        InputStream is = ReflectExercise.class.getResourceAsStream("pro.properties");
        properties.load(is); //把字节流加载进内存
//2. 获取配置文件中定义的数据
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
//3.加载该类进内存
        Class<?> aClass = Class.forName(className);//forName方法创建字节码文件
//4.创建对象（相当于得到了构造器）
        Object obj = aClass.newInstance();// 加载谁就创建谁的对象->简化版的空参数构造器
//5.获取方法对象(已经获取到了方法的名字，假设无参)
        Method method = aClass.getMethod(methodName);
//6.执行方法
        method.invoke(obj);
    }
}
