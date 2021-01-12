package outils;

import config.Project;
import formats.Format;
import formats.KVFormat;
import formats.LineFormat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.HashMap;

public class DummmyFileCreate {

    private static void usage() {
        System.out.println("Usage: java DummyFileCreate <name> <folderpath> <size> <format>");
    }

    public enum DummyFile {
        DUMMY_1MO("1MO", 1),
        DUMMY_10MO("10MO", 10),
        DUMMY_100MO("100MO", 100),
        DUMMY_500MO("500MO", 500),
        DUMMY_1GO("1GO", 1000),
        DUMMY_5GO("5GO", 5000),
        DUMMY_10GO("10GO",10000);

        private String name = "";
        private int size;
        private LineFormat file;
        private boolean closed;

        DummyFile(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public LineFormat getFile() {
            return file;
        }

        public void setFile(LineFormat file) {
            this.file = file;
        }

        public void open() {
            this.file.open(Format.OpenMode.W);
        }

        public void setClosed(boolean closed) {
            this.closed = closed;
        }

        public boolean isClosed() {
            return this.closed;
        }

        public void write(String str) throws IOException {
            this.file.write(str);
        }

        public void close() {
            this.file.close();
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void createDummyFiles(String path) throws IOException {
        File folder = new File(path);
        boolean folderCreated = folder.mkdirs();
        if(!folderCreated) {
            return;
        }
        DummyFile[] dummyFiles = DummyFile.values();
        for(DummyFile dummyFile : dummyFiles) {
            dummyFile.setFile(new LineFormat(path + dummyFile.getName()));
            dummyFile.open();
            dummyFile.setClosed(false);
        }

        int n = Array.getLength(dummyFiles);
        String text = DummyText.DUMMY_TEXT;
        long size = 0;

        while(n > 0) {
            if(text.length() < 96559104) {
                text = text + text;
                System.out.println(text.length());
            }
            size += text.length() / (1024*1024);
            for(DummyFile dummyFile : dummyFiles) {
                if((dummyFile.getSize()) > size) {
                    dummyFile.write(text);
                } else {
                    if(!dummyFile.isClosed()) {
                        dummyFile.close();
                        dummyFile.setClosed(true);
                        n--;
                        System.out.println(dummyFile.getName());
                        System.out.println(size);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length != 4) {
            usage();
            return;
        }
        String filename = args[0];
        String path = args[1];
        long size = Long.parseLong(args[2], 10);
        Format file;
        Format.Type fmt;

        if (args[3].equals("line")) fmt = Format.Type.LINE;
        else if(args[3].equals("kv")) fmt = Format.Type.KV;
        else { usage(); return;}

        String localFSDestFname = path + "/" + filename;
        String dummyString;
        switch (fmt) {
            case KV:
                file = new KVFormat(localFSDestFname);
                file.open(Format.OpenMode.W);
                dummyString = "test<->2\nshell<->5\npythagore<->4\n";
                break;
            case LINE:
                file = new LineFormat(localFSDestFname);
                file.open(Format.OpenMode.W);
                dummyString = "test taille fichier et c'est très long donc il faut que je rajoute des caractères car on incrémente i avec la taille du dummyString";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fmt);
        }
        int rep = 2;
        for(long i = 0; i < size; i = i + dummyString.length() + (rep-2) * dummyString.length()) {
            if(dummyString.length() <= 1073741823) {
                file.write(dummyString);
                dummyString = dummyString + dummyString;
            } else {
                for(int j = 0; j < rep; j++)
                    file.write(dummyString);
                    rep = rep * 2;
            }

        }
        file.close();

    }
}
