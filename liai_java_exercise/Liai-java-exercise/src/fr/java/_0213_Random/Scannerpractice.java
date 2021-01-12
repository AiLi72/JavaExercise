package fr.java._0213_Random;

import java.util.Scanner;

public class Scannerpractice {
    public static void main(String[] args) {
        // int num = new Scanner(System.in).nextInt();
        //method(new Scanner(System.in));
        //Scanner sc = new Scanner(System.in);
        //method(sc);
        Scanner sc = method();
        int num = sc.nextInt();
        System.out.println(num);
    }

    public static Scanner method() {
        //System.out.println("请输入数字");
        //int num = new Scanner(System.in).nextInt();
        //return num;
        return new Scanner(System.in);
    }
}
