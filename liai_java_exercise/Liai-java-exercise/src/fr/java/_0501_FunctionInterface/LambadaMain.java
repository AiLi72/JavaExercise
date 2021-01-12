package fr.java._0501_FunctionInterface;

public class LambadaMain {
    //函数式接口的使用一：当作方法的参数传递进去
    public static void show(MyFunctionInterface myInterface) {
        myInterface.method(); //这个方法式调用接口中的方法
    }

    public static void main(String[] args) {
        //1. 方法的参数是一个接口，所以我们可以传递接口的实现类对象
        show(new MyfunctionInterfaceImpl());
        //2.方法是一个参数，所以我们可以传递接口的匿名内部类
        show(new MyFunctionInterface() {
            @Override
            public void method() {
                System.out.println("接口的匿名内部类");
            }
        });
        //3.使用lambada表达式（没有参数，括号就空着，只有一行语句，省略打括号和分号）
        show(()-> System.out.println("lambada表达式"));
    }
}
