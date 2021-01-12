package tests;

import application.Count;
import application.MyMapReduce;
import config.Project;
import formats.Format;
import hdfs.client.HdfsClient;
import outils.DummmyFileCreate;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static outils.DummmyFileCreate.createDummyFiles;

public class PerformancesTest {

    public static void main(String[] args) {

        DummmyFileCreate.DummyFile[] dummyFiles = DummmyFileCreate.DummyFile.values();
        System.out.println(args[0]);
        if(args[0].equals("createfiles") || args[0].equals("complete")) {
            System.out.println(Arrays.toString(args));
            try{
                System.out.println("try");
                createDummyFiles(Project.DUMMY_FILES_PATH);
            } catch (IOException e) {
                System.out.println("erreur");
                e.printStackTrace();
            }
        }

        long t1;
        long t2;
        long t3 = 0;

        t1 = System.currentTimeMillis();
        if(args[1].equals("hdfs") || args[0].equals("hdfs") || args[0].equals("complete")) {
            for(DummmyFileCreate.DummyFile dummyFile : dummyFiles) {
                t2 = System.currentTimeMillis();
                HdfsClient.HdfsWrite(Format.Type.LINE, Project.DUMMY_FILES_PATH + dummyFile.getName(), dummyFile.getName(), 1);
                t3 = System.currentTimeMillis();
                System.out.println("Temps d'écriture dans hdfs de " + dummyFile.getName() +" = "+(t3-t2)+" ms\n");
            }
            System.out.println("Temps total d'écriture dans hdfs des fichiers de test = "+(t3-t1)+" ms\n");
        }


        long[] countTime = new long[Array.getLength(dummyFiles)];
        int i = 0;
        for(DummmyFileCreate.DummyFile dummyFile : dummyFiles) {
            countTime[i] = application.Count.countExec(Project.DUMMY_FILES_PATH + dummyFile.getName());
            System.out.println("Temps pour compter le fichier " + dummyFile.getName() + " = " + countTime[i] + " ms\n");
            i++;
        }
        System.out.println(Arrays.toString(countTime));

        //long[] myMapReduceTime = new long[Array.getLength(dummyFiles)];
        int j = 0;
        for(DummmyFileCreate.DummyFile dummyFile : dummyFiles) {
            countTime[j] = application.MyMapReduce.mapReduceExec(dummyFile.getName());
            System.out.println("Temps pour compter le fichier " + dummyFile.getName() + " dans hdfs = " + countTime[j] + " ms\n");
            j++;
        }
        System.out.println(Arrays.toString(countTime));
    }
}
