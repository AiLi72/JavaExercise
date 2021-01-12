package fr.java._0213_Random;

import java.util.Random;
import java.util.Scanner;

public class RandomPractice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字范围最大值");
        int n = sc.nextInt();

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int num = r.nextInt(n);
            System.out.println("生成的数字是"+num);
            System.out.println((num+1));
        }

    }
}
