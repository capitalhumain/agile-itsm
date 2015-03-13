/* Released under the GPL2. See license.txt for details. */
package br.com.centralit.nagios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    final protected int maxN = 1000;
    final protected String dataSourceName;
    final protected List<Double> data = new ArrayList<Double>();
    protected String unit = "";

    public DataSource(final String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public DataSource(final String dataSourceName, final String fileName) throws Exception {
        final BufferedReader in = new BufferedReader(new FileReader(fileName));

        String[] values = null;
        if (in != null) {
            final String stringAux = in.readLine();
            if (stringAux != null) {
                values = stringAux.split("\\|");
            }
        }
        if (values != null) {
            for (final String value : values) {
                this.add(Double.valueOf(value));
            }
        }

        in.close();

        this.dataSourceName = dataSourceName;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void add(final double value) {
        data.add(value);

        if (data.size() > maxN) {
            data.remove(0);
        }
    }

    public List<Double> getData() {
        return data;
    }

    public DataInfo getStats() {
        final int n = data.size();
        if (n == 0) {
            return null;
        }

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double total = 0.0, sdval = 0.0, avg = 0.0, sd = 0.0;

        for (int index = 0; index < n; index++) {
            final double value = data.get(index);
            min = Math.min(min, value);
            max = Math.max(max, value);
            total += value;
            sdval += Math.pow(value, 2.0);
        }

        if (n != 0) {
            avg = total / n;
        }

        sd = Math.sqrt(sdval / n - Math.pow(avg, 2.0));

        return new DataInfo(min, max, avg, sd, n);
    }

    public List<Double> getValues() {
        return data;
    }

    public void dump(final String fileName) throws Exception {
        boolean first = true;
        final BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        final StringBuilder output = new StringBuilder();

        for (final Double currentData : data) {
            if (first) {
                first = false;
            } else {
                output.append("|");
            }

            output.append("" + currentData);
        }

        out.write(output.toString(), 0, output.length());
        out.newLine();

        out.close();
    }

}
