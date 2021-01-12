package formats;

import java.io.*;

public interface Format extends FormatReader, FormatWriter, Serializable {
    public enum Type { LINE, KV };
    public enum OpenMode { R, W };

	public void open(OpenMode mode);
	public void close();
	public void write(String text) throws IOException;
	public InfoEnvoieFichier sendFilePart(PrintWriter pw, InfoEnvoieFichier ief, int nbChar) throws IOException;
	public void sendFile(PrintWriter pw);
	public void receiveFile(BufferedReader reader) throws IOException;
	public long getIndex();
	public String getFname();
	public void setFname(String fname);

}
