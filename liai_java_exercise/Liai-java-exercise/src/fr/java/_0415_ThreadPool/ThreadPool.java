package fr.java._0415_ThreadPool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPool {
    public static void main(String[] args) {
        //用工厂类Executors中的静态方法创建一个具有固定数量的线程池，返回值是个接口的实现类，我们用接口来接收
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //创建一个类，实现Runnable接口
        //用ExecutorService中的submit方法把接口实现类传递进去
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());
        executorService.submit(new RunnableImpl());

        /*
        运行结果：
        线程池中的线程用完之后会自动归还
        pool-1-thread-1
        pool-1-thread-3
        pool-1-thread-2
        pool-1-thread-1
         */
    }
}

