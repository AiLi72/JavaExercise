package fr.java._0508_Annotation;

import fr.java._0508_Annotation.resources.MyAnnotation;

//使用新建的注解，需要传递参数进去，如果不传或者类型不匹配会报错
@MyAnnotation2
@MyAnnotation(show01 = 1, show02 = "hello", person = Person.p1, annotation = @MyAnnotation2,string = {"a", "b"})
class Student {

}
