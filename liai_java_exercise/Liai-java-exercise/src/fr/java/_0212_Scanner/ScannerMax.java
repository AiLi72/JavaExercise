package fr.java._0212_Scanner;

import java.util.Scanner;

public class ScannerMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println(method(a,b,c));

    }
    public static int method (int a,int b,int c){
        int max;
        if (b>a){
            if (c>b){
           max = c;
            }else {
                max = b;
            }
            }
            else if (c>a){
            max = c;
        }else {
            max = a ;
        }
        return max;
    }
}
