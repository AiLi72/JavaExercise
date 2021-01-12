package fr.java._0423_Reader_Writer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryCatch {
    public static void main(String[] args) throws FileNotFoundException {

        /*JDK7之后的改进：
   try(new流对象，new流对象，new流对象...){
   可能会产生异常的代码
          }catch(异常类变量 变量名){
    异常的处理逻辑
    }
    ⚠️这样的改进省略了finally中释放资源的繁琐，在try后面的括号里定义的流对象，在使用完之后会自动释放
 */
        try (FileInputStream fis = new FileInputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/bbb.txt");
             FileOutputStream fos = new FileOutputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/3/ccc.txt");) {

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read()) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        /*JDK9之后的改进：
        new流对象
        new流对象
        ...
        try(流对象名字;流对象名字;流对象名字...){
   可能会产生异常的代码
          }catch(异常类变量 变量名){
    异常的处理逻辑
    }
        //本身定义对象有异常，throws抛出
        FileInputStream fis = new FileInputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/1/bbb.txt");
        FileOutputStream fos = new FileOutputStream("/Users/liai/IdeaProjects/basic-code/liaiday0122/aaa/3/ccc.txt");){

            try (fis;fos) {
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = fis.read()) != -1) {
                    fos.write(bytes, 0, len);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
         */
    }
}
