package fr.java._0129_Array;
// 找最小值
public class array02 {
    public static void main(String[] args) {
        int[] array ={5,6,7,8,9,10,11};
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (min>array[i]){
                min = array[i];
            }
        }
        System.out.println(min);
    }
}
