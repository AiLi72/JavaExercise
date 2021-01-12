package fr.java._0321_Interface;

public class Laptop {

    public void powerOn (){
        System.out.println("laptop powers on");
    }
    public void powerOff (){
        System.out.println("laptop powers off");
    }
    public void useUsb (USB usb){
        usb.open();

        if (usb instanceof Clavier){
           Clavier usbClavier = (Clavier) usb;
           usbClavier.type();
        } else if (usb instanceof Sourie){
            Sourie usbSourie = (Sourie) usb;
            usbSourie.click();
        }
        usb.close();
    }
}
