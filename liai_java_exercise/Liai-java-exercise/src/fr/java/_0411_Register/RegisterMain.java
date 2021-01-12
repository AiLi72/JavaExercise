package fr.java._0411_Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//⚠️当异常类继承的是Exception的时候，我们需要自己处理异常，处理完之后程序不会停下来

public class RegisterMain {
    public static void main(String[] args) throws RegisterException {
        System.out.println("please import your user name");
        String str = new Scanner(System.in).next();
        try {
            nameCompare(str);
        } catch (RegisterException e) {
            System.out.println("your name is already used");
        }

    }

    private static void nameCompare(String name) throws RegisterException {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "ai", "adrien", "aili", "adrien le roux", "jenny");
        for (String name2 : list) {
            if (name.equals(name2)) {
                throw new RegisterException();
            }
        }
            System.out.println("your name is registered");
            list.add(name);
    }
}
