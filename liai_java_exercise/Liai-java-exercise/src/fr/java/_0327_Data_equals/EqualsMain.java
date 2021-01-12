package fr.java._0327_Data_equals;

import java.util.ArrayList;

public class EqualsMain {
    public static void main(String[] args) {
        Person p1 = new Person("Ai",18);
        Person p2 = new Person("Ai",18);

        System.out.println(p1.toString());
        System.out.println(p1.equals(p2));

        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println(p1.equals(arrayList));
    }
}
