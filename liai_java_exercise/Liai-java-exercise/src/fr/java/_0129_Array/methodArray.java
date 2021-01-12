package fr.java._0129_Array;

public class methodArray {
    public static void main(String[] args) {
        System.out.println(add(30,40,50));
        }
    public static int[] add (int a,int b,int c){
        int sum = a+b+c;
        int arg = sum/3;
        int[] array03 = {sum,arg};
        return array03;
    }
}
