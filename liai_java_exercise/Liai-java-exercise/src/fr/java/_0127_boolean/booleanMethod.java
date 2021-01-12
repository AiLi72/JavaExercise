package fr.java._0127_boolean;

public class booleanMethod {
    public static void main(String[] args) {
        System.out.println(isEqual(9,20));
    }
    public static boolean isEqual(int a,int b){
       // boolean same = a ==b;
       // boolean same = a==b? true:false;
/*        boolean same;
        if (a == b){
            same = true;
        }
        else {
           same = false;
        }
        return same ;*/
        return a==b;
    }
}
