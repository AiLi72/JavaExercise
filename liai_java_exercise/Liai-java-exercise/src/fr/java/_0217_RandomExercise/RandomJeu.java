package fr.java._0217_RandomExercise;

import java.util.Random;
import java.util.Scanner;

public class RandomJeu {
    public static void main(String[] args) {
        Random r = new Random();
        int a = r.nextInt(100)+1;
        Scanner sc = new Scanner(System.in);
        System.out.println("commence a deviner");
        int n = sc.nextInt();
        int i = 1;
        while (a !=n ){
            if (n > a) {
                System.out.println("plus grand");
            } else {
                System.out.println("plus petit");
            }
            i++;
            System.out.println("devinez encore");
             n = sc.nextInt();

        }
        System.out.println("tu as réussi! tu as deviné "+i+" fois");
    }
}
