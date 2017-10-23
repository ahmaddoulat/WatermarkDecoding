/*
 * Created on Jan 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Mohamed Shehab
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Stats {

    public int getBit(double[] dataset, double c, double T) {

        double count;

        count = (double) countTail(dataset, c) / ((double) dataset.length);

        int value = 0;
        if (count >= T) {
            value = 1;
        }
        return value;
    }

    public int countTail(double[] dataset, double c) {
        double mean;
        double std;
        double ref;
        int count, i;

        mean = findmean(dataset);
        std = findstd(dataset, mean);
        ref = mean + c * std;
        count = 0;

        for (i = 0; i < dataset.length; i++) {
            if (dataset[i] > ref) {
                count = count + 1;
            }
        }
        return count;
    }

    public double findmean(double[] dataset) {
        int i;
        double value = 0.0;

        for (i = 0; i < dataset.length; i++) {
            value = value + dataset[i];
        }
        value = value / dataset.length;
        return value;
    }

    public double findstd(double[] dataset, double mean) {
        int i;
        double value = 0.0;

        for (i = 0; i < dataset.length; i++) {
            value = value + Math.pow(dataset[i] - mean, 2);
        }
        value = Math.sqrt(value / dataset.length);
        return value;
    }

    public int getdist(String s1, String s2) {
        int count;
        int i;
        count = 0;
        for (i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
