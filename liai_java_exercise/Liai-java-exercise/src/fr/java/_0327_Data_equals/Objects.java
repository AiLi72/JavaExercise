package fr.java._0327_Data_equals;

public class Objects {
    public static void main(String[] args) {
       String s1 = "abc";
       String s2 = "abc";
       Person p1 = new Person("Ai",20);
       Person p2 = new Person("Adrien",19);
        boolean b = java.util.Objects.equals(p1, s2);
        System.out.println(b);
    }

}
