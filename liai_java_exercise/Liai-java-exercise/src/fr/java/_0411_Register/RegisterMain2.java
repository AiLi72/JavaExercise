package fr.java._0411_Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//⚠️当定义的异常类继承RunTimeException的时候，就相当于交给了虚拟机处理，我们在程序中可以不用处理

public class RegisterMain2 {
    public static void main(String[] args) {
        System.out.println("please import your user name");
        String str = new Scanner(System.in).next();
        nameCompare(str);
    }

    private static void nameCompare(String name)  {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "ai", "adrien", "aili", "adrien le roux", "jenny");
        for (String name2 : list) {
            if (name.equals(name2)) {
                throw new RegisterException("your name is already used");
            }
        }
        System.out.println("your name is registered");
        list.add(name);
    }
}