package fr.java._0423_Reader_Writer;

import java.io.FileWriter;
import java.io.IOException;

public class TryCatchFinally {
    public static void main(String[] args) {
        //提高了fw的阈值，使其可以作用在finally的打括号中
        //并且我们要使用fw,需要给他定义一个初值，以便如果创建对象失败时，还是有值
        FileWriter fw = null;
        try {
            fw = new FileWriter("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/aaa.txt",true);

            for (int i = 0; i <10 ; i++) {
                fw.write("liai"+"\r");
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            //把一定要执行的语句放在finally中：资源释放
            // 但是如果创建对象失败的话，初值null会造成空指针异常，所以我们在这里进行一个空指针的判断
            if (fw!=null){
                //同时 close方法本身存在异常
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
