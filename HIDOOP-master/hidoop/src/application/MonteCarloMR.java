package application;

import formats.Format;
import formats.FormatReader;
import formats.FormatWriter;
import formats.KV;
import map.MapReduce;
import ordo.Job;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MonteCarloMR implements MapReduce {
    private static final Long serialVersionUID = 1L;

    public void map(FormatReader reader, FormatWriter writer) {

        KV kv;

        int nbPointsInside = 0;
        int nbPointsTotal = 0;

        while ((kv = reader.read()) != null) {
            String point = kv.v;
            // on récupère les coordonnées
            String[] coordonnees = point.split("#");
            if (point.length() == 0) {
                continue;
            } else {
                double x = Double.parseDouble(coordonnees[0]);
                double y = Double.parseDouble(coordonnees[1]);

                // on teste si le point est dans le disque de rayon 1 et de centre 0,0
                if ((x * x + y * y) <= 1) {
                    nbPointsInside++;
                }

                nbPointsTotal++;
            }
        }

        writer.write(new KV("inside ", String.valueOf(nbPointsInside) + "\n"));
        writer.write(new KV("outside ", String.valueOf(nbPointsTotal-nbPointsInside) + "\n"));
    }

    public void reduce(FormatReader reader, FormatWriter writer) {
        KV kv;

        int nbPointsInside = 0;
        int nbPointOutside = 0;

        while ((kv = reader.read()) != null) {
            if(kv.k.equals("inside")) {
                nbPointsInside += Integer.parseInt(kv.v);
            } else if(kv.k.equals("outside")) {
                nbPointOutside += Integer.parseInt(kv.v);
            }
        }

        // on calcule Pi
        double estimationPiFinale = 4*(((double) nbPointsInside) /((double) nbPointOutside+nbPointsInside));
        writer.write(new KV("pi ", String.valueOf(estimationPiFinale)));

    }

    public static void main(String args[]) {

        Job j = new Job();
        System.out.println("Combien de points souhaitez vous ?");
        Scanner sc = new Scanner(System.in);
        int nbPoints = sc.nextInt();

        j.setInputFormat(Format.Type.LINE);
        j.setOutputFormat(Format.Type.KV);
        j.setInputFname("sourceMonteCarlo.txt");
        j.setOutputFname(args[0] + "-res");

        HaltonSequenceGenerator.generateFileWithPoints(j.getInputFname(), nbPoints);

        long t1 = System.currentTimeMillis();
        j.startJob(new PageRankMR());
        long t2 = System.currentTimeMillis();
        System.out.println("time in ms =" + (t2 - t1));

        System.exit(0);
    }


    private static class HaltonSequence {

        final int[] P = {2, 3};
        final int[] K = {63, 40};

        private long index;
        private double[] x;
        private double[][] q;
        private int[][] d;

        /**
         * Initialize to H(startindex),
         * so the sequence begins with H(startindex+1).
         */
        HaltonSequence(long startindex) {
            index = startindex;
            x = new double[K.length];
            q = new double[K.length][];
            d = new int[K.length][];
            for (int i = 0; i < K.length; i++) {
                q[i] = new double[K[i]];
                d[i] = new int[K[i]];
            }

            for (int i = 0; i < K.length; i++) {
                long k = index;
                x[i] = 0;

                for (int j = 0; j < K[i]; j++) {
                    q[i][j] = (j == 0 ? 1.0 : q[i][j - 1]) / P[i];
                    d[i][j] = (int) (k % P[i]);
                    k = (k - d[i][j]) / P[i];
                    x[i] += d[i][j] * q[i][j];
                }
            }
        }

        /**
         * Compute next point.
         * Assume the current point is H(index).
         * Compute H(index+1).
         *
         * @return a 2-dimensional point with coordinates in [0,1)^2
         */
        double[] nextPoint() {
            index++;
            for (int i = 0; i < K.length; i++) {
                for (int j = 0; j < K[i]; j++) {
                    d[i][j]++;
                    x[i] += q[i][j];
                    if (d[i][j] < P[i]) {
                        break;
                    }
                    d[i][j] = 0;
                    x[i] -= (j == 0 ? 1.0 : q[i][j - 1]);
                }
            }
            return x;
        }
    }

    private static class HaltonSequenceGenerator {

        public static void generateFileWithPoints(String filename, int nbPoints) {
            try {
                HaltonSequence halton = new HaltonSequence(1);
                File fichier = new File(filename);
                FileWriter writer = new FileWriter(fichier);

                for (int i = 0; i <= nbPoints; i++) {
                    double[] point = halton.nextPoint();
                    writer.write(String.valueOf(point[0]) + "#" + String.valueOf(point[1]) + "\n");
                }
                writer.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }


}