/* Released under the GPL2. See license.txt for details. */
package br.com.centralit.nagios;

import java.util.ArrayList;
import java.util.List;

public class PerformanceDataPerElement {

    String lastCheckTime = "";
    final protected List<DataSource> dataSources = new ArrayList<DataSource>();

    public DataSource add(final String source) {
        for (final DataSource current : dataSources) {
            if (current.getDataSourceName().equals(source)) {
                return current;
            }
        }

        final DataSource newDataSource = new DataSource(source);
        dataSources.add(newDataSource);

        return newDataSource;
    }

    public void setDataSourceUnit(final String name, final String unit) {
        this.getDataSource(name).setUnit(unit);
    }

    public void setCheckTime(final String checkTime) {
        lastCheckTime = checkTime;
    }

    public DataSource add(final String source, final double value) {
        final DataSource newDataSource = this.add(source);

        newDataSource.add(value);

        return newDataSource;
    }

    public String getCheckTime() {
        return lastCheckTime;
    }

    public DataSource getDataSource(final String name) {
        for (final DataSource current : dataSources) {
            if (current.getDataSourceName().equals(name)) {
                return current;
            }
        }

        return null;
    }

    public List<DataSource> getAllDataSources() {
        return dataSources;
    }

}
