package fr.java._0405_Collections;

import fr.java._0327_Data_equals.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CollectionsExercise {
    public static void main(String[] args) {
        Person one  = new Person("liai",20);
        Person two  = new Person("adrien",18);
        Person three  = new Person("tommy",17);
        ArrayList<Person> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,one,two,three);
        Collections.sort(arrayList);
        System.out.println(arrayList.toString());

        Collections.sort(arrayList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(arrayList.toString());

    }

}
