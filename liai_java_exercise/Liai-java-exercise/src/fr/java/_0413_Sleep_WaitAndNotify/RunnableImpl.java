package fr.java._0413_Sleep_WaitAndNotify;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableImpl implements Runnable {

    private /*static用于方法三*/ int ticket = 100;

    // 在成员位置，用多态创建一个ReentrantLock对象，该对象继承了Lock接口，用于方法四
    Lock lock = new ReentrantLock();

    /* 解决线程安全问题方法一：
    创建一个锁对象（这个对象可以用this替代，指的就是我们建的RunnableImpl实现类对象），用同步代码块完成

    Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            //同步代码块 ⚠️ 同步代码块要包含if语句，否则还是会出现-1的情况
            synchronized (obj) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "----->" + ticket);
                    ticket--;
                }
            }
        }
    }

     */

    /*方法二：定义一个同步方法,在返回值前加入synchronized，在run方法中调用这个方法

    @Override
    public void run() {
        //run方法中别忘了循环
        while (true){
            sellTicket();
        }
    }

    private synchronized void sellTicket() {
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "----->" + ticket);
            ticket--;
        }
    }

     */

    /*第三种方法：静态方法 在方法二的基础上加上static，同时int变量也需要用static来修饰
     静态方法的锁对象：本类的class属性---》class 文件对象

    @Override
    public void run() {
        while (true) {
            sellTicketStatic();
        }
    }

    public static void sellTicketStatic() {
        synchronized (Runnable.class) {
            if (ticket > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "----->" + ticket);
                ticket--;
            }
        }
    }

     */

    /*第四种方法：lock锁 ：先在成员位置new一个ReentrantLock对象，再在共享代码前后加上lock与unlock

    @Override
    public void run() {
        while (true) {

            lock.lock();
            if (ticket > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "----->" + ticket);
                ticket--;
            }

            lock.unlock();
        }
    }

     */

    //方法四的优化：无论是否出现异常，都把锁释放掉
    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (ticket > 0) {
                try {
                    Thread.sleep(10);

                    //把程序后续语句放到try中
                    System.out.println(Thread.currentThread().getName() + "----->" + ticket);
                    ticket--;
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    //把解锁语句放到finally中：无论程序是否出现异常，都会释放锁
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}


