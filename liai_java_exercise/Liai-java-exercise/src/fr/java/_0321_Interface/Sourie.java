package fr.java._0321_Interface;

public class Sourie implements USB {
    @Override
    public void open() {
        System.out.println("open the sourie");
    }

    @Override
    public void close() {
        System.out.println("close the sourie");
    }

    public void click (){
        System.out.println("sourie clicks");
    }
}
