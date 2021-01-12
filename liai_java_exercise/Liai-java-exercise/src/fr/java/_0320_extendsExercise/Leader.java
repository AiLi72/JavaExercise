package fr.java._0320_extendsExercise;

import java.util.ArrayList;

public class Leader extends User {

    public Leader (String name, double MoneyRest) {
        super (name, MoneyRest);
    }

    public ArrayList<Double> send (double sendMoney,int numWorker){
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < numWorker; i++) {
            arrayList.add(sendMoney / numWorker);
        }
        super.setMoneyRest(super.getMoneyRest()-sendMoney);
        return arrayList;
    }
}
