package fr.java._0414_WaitAndNotifyExercise;

public class Noddles {
    String meat;
    String vegetable;
    //设置包子初始值为没有
    boolean flag = false;

    public Noddles() {
    }

    public Noddles(String meat, String vegetable, boolean flag) {
        this.meat = meat;
        this.vegetable = vegetable;
        this.flag = flag;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public String getVegetable() {
        return vegetable;
    }

    public void setVegetable(String vegetable) {
        this.vegetable = vegetable;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
