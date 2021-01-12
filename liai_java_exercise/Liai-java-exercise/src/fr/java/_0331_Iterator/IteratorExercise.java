package fr.java._0331_Iterator;

import java.util.ArrayList;

public class IteratorExercise {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("ai1");
        arrayList.add("ai2");
        arrayList.add("ai3");
        arrayList.add("ai4");
        arrayList.add("ai7");

        //用Iterator接口接收实现类，是多态
       // Iterator<String> it = arrayList.iterator();
        //不知道具体次数用while，先判断集合内是否有元素，有的话打印输出
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }

        //增强for循环
        for (String str: arrayList) {
            System.out.println(str);
        }
    }
}
