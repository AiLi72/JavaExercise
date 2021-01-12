package fr.java._0305_ArrayListExercise;

import java.util.ArrayList;
import java.util.Random;

public class ArrayTest {
    public static void main(String[] args) {
        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i < 6; i ++){
            list.add(r.nextInt(32)+1);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


    }
}
