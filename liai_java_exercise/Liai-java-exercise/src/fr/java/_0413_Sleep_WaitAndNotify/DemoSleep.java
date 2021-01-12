package fr.java._0413_Sleep_WaitAndNotify;

    //模拟秒表
public class DemoSleep {
    public static void main(String[] args) {
        for (int i = 1; i <60 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}
