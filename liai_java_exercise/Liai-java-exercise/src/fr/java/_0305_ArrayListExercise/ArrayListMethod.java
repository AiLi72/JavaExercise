package fr.java._0305_ArrayListExercise;

import java.util.ArrayList;
import java.util.Random;

public class ArrayListMethod {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random r = new Random();
            int num = r.nextInt(100);
            list.add(num);
        }

        ArrayList<Integer> pairs = pair(list);
        for (int a = 0; a < pairs.size(); a++) {
            System.out.println(pairs.get(a));
        }
    }

    public static ArrayList<Integer> pair(ArrayList<Integer> l) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) % 2 == 0) {
                 list.add(l.get(i));

            }
        }
        return list;
    }

}


