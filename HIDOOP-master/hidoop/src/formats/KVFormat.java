package formats;

import config.Project;

import java.io.*;
import java.util.List;

public class KVFormat implements Format {
    private static final long serialVersionUID = 1L;

    private String fname;
    private KV kv;

    private transient BufferedReader br;
    private transient BufferedWriter bw;
    private transient long index = 0;
    private transient Format.OpenMode mode;

    public KVFormat(String fname) {
        this.fname = fname;
    }

    public void open(OpenMode mode) {
        try {
            this.mode = mode;
            this.kv = new KV();
            switch (mode) {
            case R:
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
                break;
            case W:
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname)));
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            switch (mode) {
            case R:
                br.close();
                break;
            case W:
                bw.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String text) throws IOException {
        bw.write(text);
        bw.flush();
    }

    @Override
    public InfoEnvoieFichier sendFilePart(PrintWriter pw, InfoEnvoieFichier ief, int nbChar) throws IOException {

        int nbCharRetenu = ief.getNbCharRetenu();
        char[] charPrecedent = ief.getCharPrecedent();
        boolean endOfFileReached = false;
        int nbCharLu;

        if((nbCharLu = br.read(charPrecedent, nbCharRetenu, nbChar-nbCharRetenu)) != nbChar-nbCharRetenu) {
            endOfFileReached = true;
            for(int i = 0; i < nbCharLu+nbCharRetenu; i++) {
                if(ief.isFirstfile()) {
                    pw.print(charPrecedent[i]);
                } else if (i != 0) {
                    pw.print(charPrecedent[i]);
                }
            }
        } else {
            int count = nbChar-1;
            while(charPrecedent[count] != '\n') {
                count--;
            }
            for(int i = 0; i <= count; i++) {
                if(ief.isFirstfile()) {
                    pw.print(charPrecedent[i]);
                } else if (i != 0) {
                    pw.print(charPrecedent[i]);
                }
            }
            nbCharRetenu = nbChar-count;
            for(int i = 0; i < nbCharRetenu; i++) {
                charPrecedent[i] = charPrecedent[i+count];
            }
        }
        pw.flush();
        return new InfoEnvoieFichier(charPrecedent, nbCharRetenu, false, endOfFileReached);
    }

    @Override
    public void sendFile(PrintWriter pw){
        char c;
        int i;
        try {
            while((i = br.read()) != -1){
                c = (char) i;
                pw.write(c);
            }
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveFile(BufferedReader reader) throws IOException {
        char c;
        int i;
        while((i = reader.read()) != -1){
            c = (char) i;
            bw.write(c);
        }
        bw.flush();
    }


    public KV read() {
        try {
            while (true) {
                String l = br.readLine();
                if (l == null) return null;
                index += l.length();
                String[] tokens = l.split(KV.SEPARATOR);
                if (tokens.length != 2) continue;
                kv.k = tokens[0];
                kv.v = tokens[1];
                return kv;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(KV record) {
        try {
            String s = record.k+KV.SEPARATOR+record.v;
            bw.write(s, 0, s.length());
            bw.newLine();
            bw.flush();
            index += s.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public long getIndex() {
        return index;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
