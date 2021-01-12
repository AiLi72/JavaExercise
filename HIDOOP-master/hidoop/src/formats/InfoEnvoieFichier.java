package formats;

public class InfoEnvoieFichier {
	
    private char[] charPrecedent;
    int nbCharRetenu;
    boolean endOfFileReached;
    boolean firstfile;

    public InfoEnvoieFichier(char[] charPrecedent, int nbCharRetenu, boolean firstfile, boolean endOfFileReached) {
        this.charPrecedent = charPrecedent;
        this.nbCharRetenu = nbCharRetenu;
        this.endOfFileReached = endOfFileReached;
        this.firstfile = firstfile;
    }


    public char[] getCharPrecedent() {
        return charPrecedent;
    }

    public void setCharPrecedent(char[] charPrecedent) {
        this.charPrecedent = charPrecedent;
    }

    public int getNbCharRetenu() {
        return nbCharRetenu;
    }

    public void setNbCharRetenu(int nbCharRetenu) {
        this.nbCharRetenu = nbCharRetenu;
    }

    public boolean isEndOfFileReached() {
        return endOfFileReached;
    }

    public void setEndOfFileReached(boolean endOfFileReached) {
        this.endOfFileReached = endOfFileReached;
    }

    public boolean isFirstfile() {
        return firstfile;
    }
}
