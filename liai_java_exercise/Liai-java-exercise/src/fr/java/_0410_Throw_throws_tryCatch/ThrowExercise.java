package fr.java._0410_Throw_throws_tryCatch;

import java.util.Objects;

public class ThrowExercise {
    public static void main(String[] args) {
        //int[] array = new int[]{1, 2, 3};
        int[] array = null;
        int i = method(array, 3);
        System.out.println(i);
    }

    private static int method(int[] array, int index) {
        Objects.requireNonNull(array,"为空");
        if (index > array.length-1 || index < 0 ){
            throw new IndexOutOfBoundsException("溢出");
        }
        return array[index];
    }
}
