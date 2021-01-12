package fr.java._0305_ArrayListExercise;

public class Student {
    private String nom;
    private int age;
    private boolean isMale;

    public Student(String nom, int age, boolean isMale) {
        this.nom = nom;
        this.age = age;
        this.isMale = isMale;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMale(boolean male) {
        isMale = male;
    }


}
