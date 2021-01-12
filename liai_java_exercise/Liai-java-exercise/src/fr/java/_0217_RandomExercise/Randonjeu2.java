package fr.java._0217_RandomExercise;

import java.util.Random;
import java.util.Scanner;

public class Randonjeu2 {
    public static void main(String[] args) {
        Random r = new Random();
        int a = r.nextInt(100)+1;
        System.out.println(a);
        Scanner sc = new Scanner(System.in);
        System.out.println("commence à deviner");
        for (int i = 0; i < 5 ; i++) {
            int n = sc.nextInt();
            if (n < a){
                System.out.println("plus petit, devnine encore une fois,tu reste "+(4-i)+" fois");
            }else if(n > a ){
                System.out.println("plus grand ,devine encore une fois,tu reste "+(4-i)+" fois");
            }else{
                System.out.println("tu as réussi, tu as deviné "+(i+1)+" fois");
                break;
            }
        }
        System.out.println("le chiffre est"+ a);
    }
}
