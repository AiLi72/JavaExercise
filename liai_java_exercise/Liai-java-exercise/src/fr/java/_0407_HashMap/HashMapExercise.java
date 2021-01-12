package fr.java._0407_HashMap;

import fr.java._0327_Data_equals.Person;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExercise {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("liai",18);
        map.put("adrien",20);
//        map.remove("liai");
//        System.out.println(map.get("liai"));
//        System.out.println(map);

        //两种方式遍历hachmap
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String name = iterator.next();
             System.out.println(name+map.get(name));
        }
        System.out.println("================");

        for (String key:map.keySet()){
            System.out.println(key+ map.get(key));
        }
        System.out.println("================");

        //另外两种方式遍历（entry对象）
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator1 = entries.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, Integer> entry = iterator1.next();
            System.out.println(entry.getKey()+entry.getValue());
            System.out.println("================");

            for (Map.Entry<String, Integer> entry1 : entries){
                System.out.println(entry1.getKey()+entry1.getValue());
            }
            System.out.println("================");

            HashMap<Person,String> map1 = new HashMap<>();
            map1.put(new Person("ai",19),"china");
            map1.put(new Person("ai",19),"china");
            map1.put(new Person("adrien",20),"france");
            map1.put(new Person("adrien",19),"france");

            Set<Map.Entry<Person, String>> entries1 = map1.entrySet();
            for (Map.Entry<Person, String> entry1:entries1){
                System.out.println(entry1.getKey()+entry1.getValue());
            }

        }
    }
}
