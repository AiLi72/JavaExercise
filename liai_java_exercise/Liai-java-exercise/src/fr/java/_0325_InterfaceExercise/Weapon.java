package fr.java._0325_InterfaceExercise;

public class Weapon {
    String name;
    int kind;

    public Weapon() {
    }

    public Weapon(String name, int kind) {
        this.name = name;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
