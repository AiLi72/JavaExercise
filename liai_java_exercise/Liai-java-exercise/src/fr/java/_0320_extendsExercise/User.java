package fr.java._0320_extendsExercise;

public class User {
    private String name;
    private double MoneyRest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoneyRest() {
        return MoneyRest;
    }

    public void setMoneyRest(double moneyRest) {
        MoneyRest = moneyRest;
    }

    public User(String name,double MoneyRest){
        this.name = name;
        this.MoneyRest = MoneyRest;
    }
    public void show (){
        System.out.println("my name is : "+ name +" i have money of "+ MoneyRest);
    }
}
