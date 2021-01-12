package fr.java._0320_extendsExercise;

import java.util.ArrayList;
import java.util.Random;

public class Member extends User {

    public Member(String name, double MoneyRest) {
        super(name, MoneyRest);
    }

    public void recieve (ArrayList<Integer> arrayList){
        int index = new Random().nextInt(arrayList.size());
        super.setMoneyRest(super.getMoneyRest()+arrayList.remove(index));
    }
}
