package fr.java._0201_PhoneObjet;

public class One {
    public static void main(String[] args) {
        Phone phone1 = new Phone();

        phone1.color = "black";
        phone1.name = "iphone";
        phone1.price = 90000;

        method(phone1);
    }
    public static void method (Phone param){
        System.out.println(param.color);
        System.out.println(param.name);
        System.out.println(param.price);
        param.call("tom");
        param.messageSend(999990);
    }
}
