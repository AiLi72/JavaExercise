package fr.java._0320_extendsExercise;

import java.util.ArrayList;

public class DemonMain {

    public static void main(String[] args) {

        Leader leader = new Leader("jenny",100);

        Worker worker1 = new Worker("AI",100);
        Worker worker2 = new Worker("Adrien",20);

        leader.show();
        worker1.show();
        worker2.show();
        System.out.println("============================");

        ArrayList<Double> arrayList = leader.send(50,2);
        worker1.receive(arrayList);
        worker2.receive(arrayList);

        leader.show();
        worker1.show();
        worker2.show();
        System.out.println("======================");

        Maneger maneger = new Maneger("Maneger",200);

        Member one = new Member("one",0);
        Member two = new Member("two",0);
        Member three = new Member("three",0);
        maneger.show();
        one.show();
        two.show();
        three.show();
        System.out.println("===============");

        ArrayList<Integer> arrayList1 = maneger.send(50,3);
        one.recieve(arrayList1);
        two.recieve(arrayList1);
        three.recieve(arrayList1);

        maneger.show();
        one.show();
        two.show();
        three.show();

    }
}

