package fr.java._0201_PhoneObjet;

public class Two {
    public static void main(String[] args) {
       Phone two = method();
        System.out.println(two.name);
        System.out.println(two.price);
        System.out.println(two.color);
        two.messageSend(77777);
    }
    public static Phone method (){
        Phone phone2 = new Phone();
        phone2.name = "sony";
        phone2.price = 8888;
        phone2.color = "pink";
        phone2.call("lucy");
        phone2.messageSend(88888);
        return phone2;
    }
}
