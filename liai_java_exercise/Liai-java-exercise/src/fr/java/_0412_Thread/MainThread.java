package fr.java._0412_Thread;

public class MainThread {
    public static void main(String[] args) {

        /* 第一种方式创建多线程：MyThread子类继承父类Thread

        MyThread  myThread = new MyThread();
        myThread.start();
        for (int i = 0; i <10 ; i++) {
            System.out.println("main:"+i);
        }
         */


        /* 第二种方式：RunnableIMpl实现Runnable接口

       RunnableImpl runnable = new RunnableImpl();
        new Thread(runnable).start();

        */

       /*  第三种：用匿名内部类的方式简化代码，子类方式

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();  // ⚠️ start别忘了
        */


       /* 第四种：用匿名内部类的方式简化代码，接口方式 */

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        new Thread(r).start(); // ⚠️ 这里别忘记newThread



       /*第五种：更为简化的接口方式

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        */
    }
}
