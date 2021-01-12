package fr.java._0305_ArrayListExercise;

import java.util.ArrayList;

public class StudentTest {
    public static void main(String[] args) {
        String[] names = {"ai", "adrien", "jenny", "luis"};
        int[] ages = {19, 20, 30, 40};
        boolean[] isMale = {false, true, false, false};
        for (int i = 0; i < 4; i++) {
            Student stu = new Student(names[i], ages[i], isMale[i]);

            ArrayList<Student> list = new ArrayList<>();
            list.add(stu);

            for (int a = 0; a < list.size(); a++) {
            Student one = list.get(a);
                System.out.println(one.getNom()+one.getAge()+one.isMale());
            }
        }


    }


}
