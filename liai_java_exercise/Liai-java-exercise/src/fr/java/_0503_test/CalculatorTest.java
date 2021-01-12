package fr.java._0503_test;

import fr.java._0503_junit.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试包单独成包，在要测试内容之外，并且测试类的名字：被测试类Test
 * 直接在测试方法的左边点绿色的三角，只运行某一个方法，不用创建main方法运行了
 * 在执行所有测试方法之前都要执行的代码@Before:用于一些申请资源或者重复代码
 * 在执行所有测试方法之后都要执行的代码@After:用于资源释放
 */

public class CalculatorTest {
    @Before  //写在方法外面，类里面
    public void init() {
        System.out.println("init");
    }

    @After
    public void close() {
        System.out.println("close");
    }

    @Test //倒入@Test环境，注意这个注解在类里，不在类外，在方法外
    public void testAdd() {//约定俗成：测试方法的名字：test被测试的方法
        //1.创建要测试的对象
        Calculator c = new Calculator();
        //2.调用方法
        int result = c.add(1, 2);
        //3.断言(期待的值与实际的值)
        Assert.assertEquals(3, result); //如果结果是绿色就说明测试成功，不用我们用肉眼看输出的值了
    }

    @Test //如果测试多个方法，那我们也要创建多个方法
    public void testSub() {
        Calculator c = new Calculator();
        int result = c.sub(1, 2);
        //3.断言(期待的值与实际的值)
        Assert.assertEquals(-1, result);
    }
}
