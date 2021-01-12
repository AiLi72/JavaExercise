package fr.java._0318_Arrays;

public class Student {
    private String name;
    private boolean isMale;
    private int age ;
    private int id ;
    static String room;
    private static int idCounter  = 0;

    public Student() {
        this.id = idCounter++;
    }

    public Student(String name, boolean isMale, int age) {
        this.name = name;
        this.isMale = isMale;
        this.age = age;
        this.id = ++idCounter;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String getRoom() {
        return room;
    }

    public static void setRoom(String room) {
        Student.room = room;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Student.idCounter = idCounter;
    }
}
