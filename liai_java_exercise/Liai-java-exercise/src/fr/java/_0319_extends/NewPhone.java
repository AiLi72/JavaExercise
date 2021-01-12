package fr.java._0319_extends;

public class NewPhone extends Phone {
    @Override
    public void show() {
        super.show();
        System.out.println("show photo");
        System.out.println("show name");
    }
}
