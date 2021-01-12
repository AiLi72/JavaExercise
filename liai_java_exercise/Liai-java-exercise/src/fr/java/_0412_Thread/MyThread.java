package fr.java._0412_Thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println("run:"+i);
        }
    }
}
