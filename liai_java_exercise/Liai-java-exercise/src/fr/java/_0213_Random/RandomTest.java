package fr.java._0213_Random;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random ran = new Random();
        int num = ran.nextInt();
        for (int i = 0; i < 100; i++) {
            int num2 = ran.nextInt(-99);
            System.out.println(num2);
        }
        System.out.println(num);


    }

}

