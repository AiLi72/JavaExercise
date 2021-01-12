package formats;
import config.Project;

import java.io.*;


public class LineFormat implements Format {
    private static final long serialVersionUID = 1L;

    private String fname;
    private KV kv;

    private transient LineNumberReader lnr;
    private transient BufferedWriter bw;
    private transient long index = 0;
    private transient Format.OpenMode mode;


    public LineFormat(String fname) {
        this.fname = fname;
    }

    public void open(Format.OpenMode mode) {
        try {
            this.mode = mode;
            this.kv = new KV();
            switch (mode) {
            case R:
                lnr = new LineNumberReader(new InputStreamReader(new FileInputStream(fname)));
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
                lnr.close();
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

        if((nbCharLu = lnr.read(charPrecedent, nbCharRetenu, nbChar-nbCharRetenu)) != nbChar-nbCharRetenu) {
            endOfFileReached = true;
            for(int i = 0; i < nbCharLu+nbCharRetenu; i++) {
            	pw.print(charPrecedent[i]);
            }
        } else {
        	int count = nbChar-1;
            while(count > 0 && charPrecedent[count] != ' ' && charPrecedent[count] != '\n') {
                count--;
            }
            for(int i = 0; i < count; i++) {
                pw.print(charPrecedent[i]);
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
            while((i = lnr.read()) != -1){
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
            kv.k = Integer.toString(lnr.getLineNumber());
            kv.v = lnr.readLine();
            if (kv.v == null) return null;
            index += kv.v.length();
            return kv;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(KV record) {
        try {
            bw.write(record.v, 0, record.v.length());
            bw.newLine();
            index += record.v.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LineNumberReader getLnr() {
        return lnr;
    }

    public void setLnr(LineNumberReader lnr) {
        this.lnr = lnr;
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
