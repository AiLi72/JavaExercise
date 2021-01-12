package fr.java._0414_WaitAndNotifyExercise;

public class Restaurant extends Thread {

    //需要在成员位置创建一个Noodles类型的noddles
    private Noddles noddles;

    //⚠️创建带参构造方法，把noddles当作参数传递进来，就可以联系上两者了
    public Restaurant(Noddles noddles) {
        this.noddles = noddles;
    }

    //重写run方法，实现做包子
    @Override
    public void run() {
        int cont = 1;
        while (true) {
            //先进到锁中，再进行判断
            synchronized (noddles) {
                if (noddles.flag == true) {
                    //如果有面条，restaurant等待，注意不用判断如果没有面条的话，没有的话就做，相当于被唤醒的之后的程序
                    try {
                        noddles.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒之后的程序
                System.out.println("restaurant : I'm starting to make noddles,please wait 3 seconds");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //开始做面条
                if (cont % 2 == 0) {
                    noddles.meat = "beaf";
                    noddles.vegetable = "tomato";
                } else {
                    noddles.meat = "pork";
                    noddles.vegetable = "potato";
                }
                cont++;
                noddles.flag = true;
                //唤醒吃面条的人
                noddles.notify();
                System.out.println("restaurant: the noddles are ready ,please eat");
            }
        }
    }
}