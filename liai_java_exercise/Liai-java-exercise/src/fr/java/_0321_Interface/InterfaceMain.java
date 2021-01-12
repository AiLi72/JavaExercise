package fr.java._0321_Interface;

public class InterfaceMain {

    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        laptop.powerOn();

        USB usbClavier = new Clavier();
        laptop.useUsb(usbClavier);

        Sourie usbSourie = new Sourie();
        laptop.useUsb(usbSourie);

        laptop.powerOff();

    }
}
