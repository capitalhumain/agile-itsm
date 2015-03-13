/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.centralit.nagios.livestatus.tables;

/**
 *
 * @author adenir
 */
public class HostsBase extends LiveStatusBase {

    private final String TABLE = "hosts";

    public HostsBase(final String pPath) {
        super(pPath);
    }

    public String[] get_hosts() throws Exception {
        return this.execute_query(TABLE, "name", "").asArrayString();
    }

    public String[] hosts() throws Exception {
        return this.get_hosts();
    }

    public int state(final String host) throws Exception {
        return this.getAsInt(this.execute_query(TABLE, "state", "name = " + host).asString());
    }

    // os vários serviço do host
    public String[] services(final String host) throws Exception {
        return this.execute_query(TABLE, "services", "name = " + host).asString().split(LivestatusSeparator.SEP3());
    }

    // os vários serviço do host com informações
    public String[] services_with_info(final String host) throws Exception {
        return this.execute_query(TABLE, "services_with_info", "name = " + host).asArrayString();
    }

    // um serviço do host com informações
    public String service_with_info(final String host, final String service) throws Exception {
        for (final String ss : this.execute_query(TABLE, "services_with_info", "name = " + host).asArrayList()) {
            final String[] st = ss.split("|");
            if (service.equals(st[0])) {
                return ss;
            }
        }

        return "";
    }

    public int service_state(final String host, final String service) throws Exception {
        return this.getAsInt(this.service_with_info(host, service));
    }

}
