package fr.java._0407_HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HashMapString {
    public static void main(String[] args) {

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.println("please scanner the words");
        String str = sc.next();

        /*
        for (char key : str.toCharArray()) {
            if (map.containsKey(key)) {
                Integer value = map.get(key);
                value++;
                map.put(key, value);
            } else {
                map.put(key, 1);
            }
        }
       */

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char key = chars[i];
            if (map.containsKey(key)) {
                Integer value = map.get(key);
                value++;
                map.put(key, value);
            } else {
                map.put(key, 1);
            }
        }

        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            System.out.println(entry.getKey().toString() + " : " + entry.getValue());
        }
    }
}
