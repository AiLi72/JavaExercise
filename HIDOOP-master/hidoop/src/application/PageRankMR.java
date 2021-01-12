package application;

import formats.Format;
import formats.FormatReader;
import formats.FormatWriter;
import formats.KV;
import map.MapReduce;
import ordo.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class PageRankMR implements MapReduce {

    @Override
    public void map(FormatReader reader, FormatWriter writer) {
        Map<String, Double> hm = new HashMap<>();
        ArrayList<URL_PR_nbL> URLlist = new ArrayList<URL_PR_nbL>();

        KV kv;
        while ((kv = reader.read()) != null) {
            String[] line = kv.v.split(" ");

            String url = line[0]; // URL de la page

            double PR = Double.parseDouble(line[1]); // PR de la page

            for (int k = 2; k < line.length; k++) { // URL des liens de la page
                String lienURL = line[k];
                URLlist.add(new URL_PR_nbL(lienURL, PR, line.length - 2)); // On l'ajoute dans la liste avec le nombre de page en tout
            }
            hm.put(url, PR);
        }

        for (URL_PR_nbL url_pr_nbL : URLlist) writer.write(new KV(url_pr_nbL.getURL(), url_pr_nbL.getPR() + " " + url_pr_nbL.getNbL()));
    }

    @Override
    public void reduce(FormatReader reader, FormatWriter writer) {
        final double s = 0.85;

        ArrayList<URL_PR_nbL> list = new ArrayList<>();
        KV kv;

        // On ajoute toutes les lignes dans une liste afin que ce soit plus facile à traiter
        while ((kv = reader.read()) != null) {
            String[] line = kv.v.split(" ");
            String URLlk = line[0];
            double PRpi = Double.parseDouble(line[1]);
            int nbLpi = Integer.parseInt(line[2]);

            list.add(new URL_PR_nbL(URLlk, PRpi, nbLpi));
        }

        // Ep de la formule
        final double Ep = 1 / list.size();

        // On traite la somme de la formule
        HashMap<String, Double> URL_PR_Map = new HashMap<String, Double>();

        for (URL_PR_nbL url_pr_nbL : list) {
            if (URL_PR_Map.containsKey(url_pr_nbL.getURL())) { // Terme suivant
                double pr = URL_PR_Map.get(url_pr_nbL.getURL());
                pr += url_pr_nbL.getPR() / url_pr_nbL.getNbL();
                URL_PR_Map.replace(url_pr_nbL.getURL(), pr);
            } else { // Terme initial
                URL_PR_Map.put(url_pr_nbL.getURL(), url_pr_nbL.getPR() / url_pr_nbL.getNbL());
            }
        }

        // On finit la formule
        for (String key : URL_PR_Map.keySet()) {
            double pr = URL_PR_Map.get(key);
            pr *= s;
            pr += (1 - s) * Ep;
            URL_PR_Map.replace(key, pr);

            StringBuilder sb = new StringBuilder("");
            try {
                for(String link : findLinks(key)) { // On regarde les liens
                    sb.append(" ").append(link);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            writer.write(new KV(key, pr + sb.toString())); // Forme : URLpage PRpage URLlink...
        }



        for (String k : URL_PR_Map.keySet()) writer.write(new KV(k, URL_PR_Map.get(k).toString()));
    }

    public static void main(String args[]) {
        Job j = new Job();
        j.setInputFormat(Format.Type.KV);
        j.setOutputFormat(Format.Type.KV);
        j.setInputFname(args[0]);
        j.setOutputFname(args[0] + "-res");
        long t1 = System.currentTimeMillis();
        j.startJob(new PageRankMR());
        long t2 = System.currentTimeMillis();
        System.out.println("time in ms =" + (t2 - t1));
        System.exit(0);
    }


    private class URL_PR_nbL {

        private String URL; // URL de la page
        private double PR; // PageRank de la page parente
        private int nbL; // Nombre de liens

        public URL_PR_nbL(String URLlk, double PRpi, int nbLpi) {
            this.URL = URLlk;
            this.PR = PRpi;
            this.nbL = nbLpi;
        }

        public String getURL() {
            return URL;
        }

        public double getPR() {
            return PR;
        }

        public int getNbL() {
            return nbL;
        }

        @Override
        public String toString() {
            return "URL_PR_nbL{" +
                    "URL='" + URL + '\'' +
                    ", PR=" + PR +
                    ", nbL=" + nbL +
                    '}';
        }
    }

    private static Set<String> findLinks(String url) throws IOException {

        Set<String> links = new HashSet<>();

        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            links.add(element.attr("href"));
        }

        return links;

    }
}
