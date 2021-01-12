package fr.java._0328_Collection_Integer_StringBuilder_System;

import java.util.Arrays;

public class SystemExercise {
    public static void main(String[] args) {
//        long l1 = System.currentTimeMillis();
//
//        for (int i = 0; i < 9999 ; i++) {
//            System.out.println(i);
//
//        }
//        long l2 = System.currentTimeMillis();
//        System.out.println(l2-l1);
//
       int[] src = {1,2,3,4,5};
       int[] dest = {6,7,8,9,10};
       System.arraycopy(src,0,dest,0,3);
        System.out.println(Arrays.toString(dest));
        System.out.println("abc".toUpperCase().toLowerCase());
    }
}
