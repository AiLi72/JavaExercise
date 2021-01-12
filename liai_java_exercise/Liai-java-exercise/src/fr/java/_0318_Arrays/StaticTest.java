package fr.java._0318_Arrays;

public class StaticTest {
    public static void main(String[] args) {
        Student one = new Student("adrien", true, 20);
        Student two = new Student("Ai", false, 18);
        one.setRoom("100教室");
        two.room = "200教室";
        System.out.println(one.getName()+one.getAge()+one.isMale()+one.getId()+one.getRoom());
        System.out.println(two.getName()+two.getAge()+two.isMale()+two.getId()+two.getRoom());
    }
}
