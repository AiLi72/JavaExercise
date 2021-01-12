package fr.java._0308_String;

public class StringTest {
    public static void main(String[] args) {
        char [] charArray  = { 'A','B','C'};
        String str1  = new String(charArray);
        String str2 = "ABC";
        String str3 = "abcjhjhidisknnj";
        System.out.println(str1.equals(str2));
        System.out.println(str1.equalsIgnoreCase(str3));
        int length = str3.length();
        System.out.println(length);
        String str4 = str3.concat(str1);
        System.out.println(str4);
        char ch = str3.charAt(1);
        System.out.println(ch);
        int index = str3.indexOf("lll");
        System.out.println(index);
        char[] chars = str3.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            System.out.println(chars[i]);
            byte[] bytes = str3.getBytes();
            for (int i1 = 0; i1 < bytes.length; i1++) {
                System.out.println(bytes[i1]);
            }
            String replace = str3.replace("l", "r");
            System.out.println(str4);
            System.out.println("---------------------");
            String[] ls = str3.split("j");
            for (int i1 = 0; i1 < ls.length; i1++) {
                System.out.println(ls[i1]);
            }

        }
    }
}
