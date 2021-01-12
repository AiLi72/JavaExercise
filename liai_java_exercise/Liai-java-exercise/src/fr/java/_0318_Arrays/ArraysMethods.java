package fr.java._0318_Arrays;

import java.util.Arrays;

public class ArraysMethods {
        public static void main(String[] args) {
            int [] array = {10,20,30};
            String intStr = Arrays.toString(array);
            System.out.println(intStr);

            int [] array2 = {1,4,6,7,9,8,};
            Arrays.sort(array2);
            System.out.println(Arrays.toString(array2));

            String [] array3 = {"2","3","5","w","d","f"};
            Arrays.sort(array3);

            //倒叙：array.forr
            for (int i = array3.length - 1; i >= 0; i--) {
                System.out.println(array3[i]);

            }
        }
    }

