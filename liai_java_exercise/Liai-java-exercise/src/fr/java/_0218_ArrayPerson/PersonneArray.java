package fr.java._0218_ArrayPerson;

public class PersonneArray {
    public static void main(String[] args) {
        Personne[] array = new Personne[3];
        Personne one = new Personne("adrien",20,true);
        Personne two = new Personne("liai",18,false);
        Personne three = new Personne("aili",15,false);

        array[0] = one ;
        array [1]= two ;
        array [2]= three;

        one.setAge(19);

        System.out.println(array[0].getAge()+array[0].getName()+array[0].isMale());

    }

}
