package fr.java._0328_Collection_Integer_StringBuilder_System;

public class StringBuilderExercise {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1).append("hhj").append('中');
        System.out.println(stringBuilder);

        String str = "abc";
        StringBuilder stringBuilder1 = new StringBuilder(str);
        System.out.println(stringBuilder1);

        System.out.println(stringBuilder1.toString());


    }
}
