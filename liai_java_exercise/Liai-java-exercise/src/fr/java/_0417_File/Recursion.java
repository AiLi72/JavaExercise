package fr.java._0417_File;

/*
递归的注意事项：
1.要有结束的条件：每次执行同一个方法体，但是参数不同，总会有一个能停下来
2.递归的目的：获取下一个数字i-1
3.哪怕参数不同，也不能太多次，否则会栈内存溢出
 */
public class Recursion {
    public static void main(String[] args) {
        int jc = jc(5);
        System.out.println(jc);
    }

    public static int jc(int i) {
        if (i == 1) {
            return 1;
        }
        return i * jc(i - 1);
    }
}
