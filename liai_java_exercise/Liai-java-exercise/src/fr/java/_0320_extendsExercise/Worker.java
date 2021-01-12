package fr.java._0320_extendsExercise;

import java.util.ArrayList;
import java.util.Random;

public class Worker extends User {

    public Worker(String name, double MoneyRest) {
        super(name, MoneyRest);
    }

    public void receive (ArrayList<Double> arrayList){
        int index = new Random().nextInt(arrayList.size());
        super.setMoneyRest(super.getMoneyRest()+arrayList.get(index));

    }

}
