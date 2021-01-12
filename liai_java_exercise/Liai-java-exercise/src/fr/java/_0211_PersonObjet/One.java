package fr.java._0211_PersonObjet;

public class One {
    public static void main(String[] args) {
        Person person = new Person("Ai", 22);
        person.setMale(false);
        String sex ;
        if (person.isMale()){
            sex="garcon";
        }else
            sex="fille";
        person.setMoney(900);
        System.out.println(person.getName()+person.getAge()+sex+person.getMoney());
    }
}
