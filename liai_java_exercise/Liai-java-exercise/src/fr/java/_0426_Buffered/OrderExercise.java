package fr.java._0426_Buffered;
//题目要求：在aaa.txt中有乱序的出师表，把他按照顺序正序排列
//思想：用hashmap分别保存每行的行号和内容作为K，V。遍历这个map,直接是按照K从小到大排列的
import java.io.*;
import java.util.HashMap;

public class OrderExercise {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/3/ccc.txt"), 1024);
        BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/bbb.txt"), 1024);
        HashMap<Integer, String> map = new HashMap<>();
        String line;
        while ((line = br.readLine()) != null) {
            //重点！split方法是用来分割的，因为句号是个特殊符号，我们放在两个反斜杠后面
            String[] split = line.split("\\.");
            map.put(Integer.parseInt(split[0]), split[1]);
        }
        for (Integer key : map.keySet()) {
            String value = map.get(key);
            line = key + "." + value;
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}
