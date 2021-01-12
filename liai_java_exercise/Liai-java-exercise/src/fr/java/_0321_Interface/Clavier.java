package fr.java._0321_Interface;

public class Clavier implements USB {
    @Override
    public void open() {
        System.out.println("open the clavier");
    }

    @Override
    public void close() {
        System.out.println("close the clavier");
    }

    public void type (){
        System.out.println("clvier types");
    }
}
