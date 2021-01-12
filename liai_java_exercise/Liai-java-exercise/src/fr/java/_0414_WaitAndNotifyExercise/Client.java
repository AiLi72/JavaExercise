package fr.java._0414_WaitAndNotifyExercise;

public class Client extends Thread {
    private Noddles noddles;

    public Client(Noddles noddles) {
        this.noddles = noddles;
    }

    //重写run方法，实现吃包子
    @Override
    public void run() {
        while (true) {
            synchronized (noddles) {
                //不用判断如果有面条的话怎么办，有的话其实就当于是唤醒之后的程序
                if (noddles.flag == false) {
                    System.out.println("Client: I want some noddles,please!");
                    try {
                        noddles.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //唤醒之后的程序
                System.out.println("Client: thank you for the noddles of " + noddles.meat + noddles.vegetable + "!");
                noddles.flag = false;
                //唤醒restaurant继续做
                noddles.notify();
            }
        }
    }
}
