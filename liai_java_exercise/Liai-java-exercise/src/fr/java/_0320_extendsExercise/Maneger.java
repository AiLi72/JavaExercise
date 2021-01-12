package fr.java._0320_extendsExercise;

import java.util.ArrayList;
import java.util.Random;

public class Maneger extends User {
    public Maneger(String name, double MoneyRest) {
        super(name, MoneyRest);
    }
    public ArrayList<Integer> send (int sendMoney, int numMembers){
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random r = new Random();
        int currentMoney = sendMoney;

        for (int i = 0; i < numMembers; i++) {
            int moneyGiven = r.nextInt(currentMoney-(numMembers-i-1))+1;
            currentMoney -= moneyGiven;
            arrayList.add(moneyGiven);
        }
        super.setMoneyRest(super.getMoneyRest()-sendMoney);
        return arrayList;
    }
}
