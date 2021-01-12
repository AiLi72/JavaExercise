package fr.java._0211_PersonObjet;

public class Person {
    private String name;
    private int age;
    private boolean male;
    private int money;

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setAge(int num) {
        if (num < 100 && num > 0) {
            age = num;
        } else
            System.out.println("数据错误");
    }

    public int getAge() {
        return age;
    }
    public void setMale(boolean b){
        male=b;
    }
    public boolean isMale(){
        return male;
    }
    public void setMoney(int num2){
        money = num2;
    }
    public int getMoney(){
        return money;
    }
    public Person (String name,int age){
        this.name = name ;
        this.age = age ;
        this.money = 100;
    }
    public Person (){}
}
