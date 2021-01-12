package fr.java._0310_StringExercise;

import java.util.Scanner;

public class StringExercice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入");
        String str = sc.next();
        char[] chars = str.toCharArray();
        compte(chars);

    }

    public static void compte(char[] chars) {

        int numchiffres = 0;
        int numlettremas = 0;
        int numlettremus = 0;
        for (int i = 0; i < chars.length; i++) {
            if (47 < chars[i] && chars[i] < 58) {
                numchiffres += 1;
            } else if (64 <chars[i] && chars[i] < 91) {
                numlettremas += 1;
            } else if (96 < chars[i] && chars[i] < 123) {
                numlettremus += 1;
            }
        }

        System.out.println("chiffres有"+numchiffres+"个");
        System.out.println("mas有"+numlettremas+"个");
        System.out.println("mus有"+numlettremus+"个");
        return ;

    }
}


