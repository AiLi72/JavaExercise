package fr.java._0416_Lambda;

import fr.java._0327_Data_equals.Person;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaExercise {
    public static void main(String[] args) {

        //1.用匿名内部类的方法实现多线程，但还是过于冗余
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        //1.用lambda表达式,()代替了run函数，->指的是把{ }中的语句传递到run方法中，别忘记了new Thread后面还有个（）
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();

        //1.优化lambda表达式
        //参数列表中可以省略数据类型，只写参数名称，如果只有一个参数时可以什么都不写
        // 抽象方法中只有一行代码时，同时省略{}和return和分号
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        //2.使用匿名内部类调用invokeCook方法，打印"eat!"
        invokeCook(new Cook() {
            @Override
            public void makeFood() {
                System.out.println("eat!");
            }
        });

        //2.使用lambada格式调用invokeCook方法，打印"eat!"，注意invoke方法本身就有个（），在这个括号里写lambda表达式
        invokeCook(() -> {
            System.out.println("eat!");
        });

        //2.lambada表达式优化
        invokeCook(() -> System.out.println("eat!"));

        //3.使用普通方法对Person类的年龄进行排序
        Person[] array = {new Person("ai", 19),
                new Person("Adrien", 18)};

        Arrays.sort(array, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        for (Person person : array) {
            System.out.println(person);
        }

        //3.使用lambada格式对Person类的年龄进行排序
        Arrays.sort(array, (Person o1, Person o2) -> {
            return o1.getAge() - o2.getAge();
        });

        for (Person person : array) {
            System.out.println(person);
        }

        //3.lambada表达式优化
        Arrays.sort(array, (o1,o2) -> o1.getAge() - o2.getAge());

        for (Person person : array) {
            System.out.println(person);
        }

        //4.一般方法调用invokecalc方法计算两个值的和
        invokeCalc(120, 130, new Calculator() {
            @Override
            public int calc (int a, int b) {
                return a+b;
            }
        });

        //4.lambda方法调用
        invokeCalc(120,130,(int a,int b)->{
            return a+b;
        });

        //4.lambda优化
        invokeCalc(120,130,(a,b)-> a+b);

    }

    //2题当中的方法
    private static void invokeCook(Cook cook) {
        cook.makeFood();
    }

    //4题当中的方法
    private static void invokeCalc(int a, int b, Calculator calculator) {
        System.out.println(calculator.calc(a,b));
    }
}
