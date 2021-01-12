package fr.java._0410_Throw_throws_tryCatch;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ThrowsExercise {
    public static void main(String[] args) throws Exception {
        readFile("c://a.txt");
    }

        //Exception is the father of every kind of exception
      // IOException is the father of FileNotFindException
      private static void readFile(String file) throws Exception {
          // private static void readFile(String file) throws IOException {
        if (!file.equals("c://a.txt")){
            throw new FileNotFoundException("we can not find the file");
        }
        System.out.println("the tile is correct");

        if (!file.endsWith(".txt")){
            throw new IOException("the modele of the file is wrong");

        }    }
}
