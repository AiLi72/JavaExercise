package fr.java._0410_Throw_throws_tryCatch;

import java.io.IOException;

public class TryCatch {
    public static void main(String[] args) {
        try {
            readFile("c://a.txt");
        } catch (IOException e) {
            System.out.println("wrong2");
        }finally {
            System.out.println("资源释放");//一定会执行的内容
        }
    }

    private static void readFile(String file) throws IOException {
        if (file.equals("c://a.txt")){
                throw new IOException("wrong");
            }
        System.out.println("correct");
        }
    }
