package fr.java._0501_FunctionInterface;
//函数式接口：只有一个抽象方法的接口
//我们可以用注解来查看是否编译成功：是否有且只有一个抽象方法（但是可以有其他的方法）

@FunctionalInterface //注解
public interface  MyFunctionInterface {
    public abstract void method();  // 可以省略前两个关键字
}
