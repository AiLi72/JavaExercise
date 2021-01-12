package fr.java._0508_Annotation.resources;

//定义注解就是public @interface 注解名
//注解由 元注解 和 属性 组成

import fr.java._0508_Annotation.MyAnnotation2;
import fr.java._0508_Annotation.Person;

import java.lang.annotation.*;

//一，元注解：用JDK自带的注解去解释我们自己的注解

        //1.@Target: 描述注解作用的位置:以下分别为作用在类上，作用在构造参数上，作用在方法上
        @Target(value = {ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})

        //2.@Rentention: 表示注解存在的阶段：source,class,runtime三个阶段
        @Retention(RetentionPolicy.RUNTIME) //一般都是runtime：表示当前被描述的这个注解会被保存到class字节码文件中，并被JVM读取到

        //3.@Documented: 将来会被抽取到javadoc的文档中
        @Documented

        //4.@Inherited: 表示被这个注解修饰的类的子类会自动继承这个注解
        @Inherited

public @interface MyAnnotation {
 //二，注解的属性：

        //注解的属性(就是注解当中定义的抽象方法，他继承自一个接口)返回值只能有以下几类
        //1.基本数据类型
        public abstract int show01();
        String show02();
        String show03() default "world" ; //如果不想使用注解的时候赋值，那么我们可以给这个方法一个默认值default

        //2.枚举类型->需要定义一个Person类型的枚举类
        Person person();

        //3.注解类型->需要定义另一个注解
        MyAnnotation2 annotation();

        //4.以上所有类型的集合
        String[] string();
}
 //我们建立一个Student类看看如何使用注解