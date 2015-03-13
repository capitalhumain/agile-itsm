/* Released under the GPL2. See license.txt for details. */
package br.com.centralit.nagios;

public class DataInfo {

    double min, max, avg, sd;
    int n;

    public DataInfo(final double min, final double max, final double avg, final double sd, final int n) {
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.sd = sd;
        this.n = n;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getAvg() {
        return avg;
    }

    public double getSd() {
        return sd;
    }

    public int getN() {
        return n;
    }

}
