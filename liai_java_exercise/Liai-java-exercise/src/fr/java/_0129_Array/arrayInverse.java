package fr.java._0129_Array;

public class arrayInverse {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,8};
        for (int i = 0; i < (array.length)/2; i++) {
            int x = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = x;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }

    }

}
