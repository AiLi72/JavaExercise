package fr.java._0414_WaitAndNotifyExercise;

public class WaitAndNotifyMain {
    public static void main(String[] args) {
        Noddles noddles = new Noddles();
        new Restaurant(noddles).start();
        new Client(noddles).start();
    }
}
