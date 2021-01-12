package fr.java._0413_Sleep_WaitAndNotify;

//锁对象（自己创建，保证唯一）.wait了一个线程，这个锁对象需要另一个线程去notify第一个线程，实现了线程之间的交流
//保证等待和唤醒的线程只有一个执行，所以wait和notify都需要在同步代码块里
public class WaitAndNotify {
    public static void main(String[] args) {
        Object cuisineur = new Object();
        Object mangeur = new Object();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (mangeur) {
                        System.out.println(Thread.currentThread().getName() + "I want some noddles");
                        synchronized (cuisineur) {
                            cuisineur.notify();
                        }
                        try {
                            mangeur.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //唤醒之后的语句
                        System.out.println(Thread.currentThread().getName() + "thank you for the noddles");
                        System.out.println("=============");
                    }
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (cuisineur) {
                        try {
                            cuisineur.wait();
                            System.out.println(Thread.currentThread().getName()+"ok wait for 5 seconds");
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName() + "your noddles is ready! ");
                        synchronized (mangeur) {
                            mangeur.notify();
                        }
                    }
                }
            }
        }.start();
    }
}
