package fr.java._0328_Collection_Integer_StringBuilder_System;

public class IntegerExercise {
    public static void main(String[] args) {
//        Integer integer1 = new Integer(12);
//        Integer integer2 = new Integer("2");
//        System.out.println(integer1+integer2);

        Integer integer1 = Integer.valueOf(3);//把基本类型的数据添加到包装类中
        System.out.println(integer1);

        int a = integer1.intValue();//从包装类中取出基本类型的数据

        //以下是把基本类型转换为字符串
        int n1 = 100;

        String str1 = 100+"";
        System.out.println(str1+200);

        String str2 = String.valueOf(100);

        String str3 = Integer.toString(100);

        //以下是把字符串变为基本类型
        int n2 = Integer.parseInt(str1);

        double n3 = Double.parseDouble(str2);
        System.out.println(n3);
    }
}
