package fr.java._0220_ArrayList;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("123");
        list.add("456");
        boolean success = list.add("789");
        System.out.println(success);
        System.out.println(list);

        String name = list.get(2);
        System.out.println(name);
        System.out.println(list.get(1));

        list.remove(2);
        System.out.println(list);
        String nameRemoved = list.remove(1);
        System.out.println(nameRemoved);

        int num = list.size();
        System.out.println(num);

        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }

     ArrayList <Integer> list2 = new ArrayList<>();
            list2.add(1);
            list2.add(2);
            list2.add(3);
        System.out.println(list2);


    }
}
