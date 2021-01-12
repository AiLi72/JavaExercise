package fr.java._0201_PhoneObjet;

public class Phone {
    String name;
    int price;
    String color;
    //注意：成员方法没有static关键字
    public void call(String nom){
        System.out.println("给"+nom+"打电话");
    }
    public void messageSend (int num){
        System.out.println("电话号码是"+num);
    }
}
