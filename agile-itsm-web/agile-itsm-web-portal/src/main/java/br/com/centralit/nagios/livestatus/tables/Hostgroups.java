/*****************************************************************************
 * Hostgroups.java -
 *
 * Copyright (c) 2014 Projeto citsmart (Contact: adenir.gomes@centralit.com.br)
 *
 * License:
 *****************************************************************************/
package br.com.centralit.nagios.livestatus.tables;

import java.util.Map;

/**
 * Class Hostgroups is the main class for obtain all columns of table "hostgroups"
 * from a Livestatus TCP-socket/file status.dat.
 *
 * @author Adenir Ribeiro Gomes
 */

public class Hostgroups extends LiveStatusBase {

    /**
     * Constructor of table Hostgroups
     *
     * @param path
     *            = "tcp://host:port" File : where path is the path to the file
     */
    public Hostgroups(final String path) {
        super(path);
        this.initializeMaps();
        tableName = "hostgroups";
    }

    /**
     * create the map for all columns description of table Hostgroups. Key=column name, Value=column description
     *
     */
    public final void initializeMaps() {
        mapComments.put("action_url", "An optional URL to custom actions or information about the hostgroup");
        mapComments.put("alias", "An alias of the hostgroup");
        mapComments.put("members", "A list of all host names that are members of the hostgroup");
        mapComments.put("members_with_state", "A list of all host names that are members of the hostgroup together with state and has_been_checked");
        mapComments.put("name", "Name of the hostgroup");
        mapComments.put("notes", "Optional notes to the hostgroup");
        mapComments.put("notes_url", "An optional URL with further information about the hostgroup");
        mapComments.put("num_hosts", "The total number of hosts in the group");
        mapComments.put("num_hosts_down", "The number of hosts in the group that are down");
        mapComments.put("num_hosts_pending", "The number of hosts in the group that are pending");
        mapComments.put("num_hosts_unreach", "The number of hosts in the group that are unreachable");
        mapComments.put("num_hosts_up", "The number of hosts in the group that are up");
        mapComments.put("num_services", "The total number of services of hosts in this group");
        mapComments.put("num_services_crit", "The total number of services with the state CRIT of hosts in this group");
        mapComments.put("num_services_hard_crit", "The total number of services with the state CRIT of hosts in this group");
        mapComments.put("num_services_hard_ok", "The total number of services with the state OK of hosts in this group");
        mapComments.put("num_services_hard_unknown", "The total number of services with the state UNKNOWN of hosts in this group");
        mapComments.put("num_services_hard_warn", "The total number of services with the state WARN of hosts in this group");
        mapComments.put("num_services_ok", "The total number of services with the state OK of hosts in this group");
        mapComments.put("num_services_pending", "The total number of services with the state Pending of hosts in this group");
        mapComments.put("num_services_unknown", "The total number of services with the state UNKNOWN of hosts in this group");
        mapComments.put("num_services_warn", "The total number of services with the state WARN of hosts in this group");
        mapComments.put("worst_host_state", "The worst state of all of the groups' hosts (UP <= UNREACHABLE <= DOWN)");
        mapComments.put("worst_service_hard_state", "The worst state of all services that belong to a host of this group (OK <= WARN <= UNKNOWN <= CRIT)");
        mapComments.put("worst_service_state", "The worst state of all services that belong to a host of this group (OK <= WARN <= UNKNOWN <= CRIT)");
    }

    /**
     * An optional URL to custom actions or information about the hostgroup
     * 
     * @return returns the value of the "action_url" column as string
     */
    public String Action_url() {
        return this.getAsString("action_url");
    }

    /**
     * An alias of the hostgroup
     * 
     * @return returns the value of the "alias" column as string
     */
    public String Alias() {
        return this.getAsString("alias");
    }

    /**
     * A list of all host names that are members of the hostgroup
     * 
     * @return returns the value of the "members" column as list
     */
    public String Members() {
        return this.getAsList("members");
    }

    /**
     * A list of all host names that are members of the hostgroup together with state and has_been_checked
     * 
     * @return returns the value of the "members_with_state" column as list
     */
    public String Members_with_state() {
        return this.getAsList("members_with_state");
    }

    /**
     * Name of the hostgroup
     * 
     * @return returns the value of the "name" column as string
     */
    public String Name() {
        return this.getAsString("name");
    }

    /**
     * Optional notes to the hostgroup
     * 
     * @return returns the value of the "notes" column as string
     */
    public String Notes() {
        return this.getAsString("notes");
    }

    /**
     * An optional URL with further information about the hostgroup
     * 
     * @return returns the value of the "notes_url" column as string
     */
    public String Notes_url() {
        return this.getAsString("notes_url");
    }

    /**
     * The total number of hosts in the group
     * 
     * @return returns the value of the "num_hosts" column as int
     */
    public int Num_hosts() throws NumberFormatException {
        return this.getAsInt("num_hosts");
    }

    /**
     * The number of hosts in the group that are down
     * 
     * @return returns the value of the "num_hosts_down" column as int
     */
    public int Num_hosts_down() throws NumberFormatException {
        return this.getAsInt("num_hosts_down");
    }

    /**
     * The number of hosts in the group that are pending
     * 
     * @return returns the value of the "num_hosts_pending" column as int
     */
    public int Num_hosts_pending() throws NumberFormatException {
        return this.getAsInt("num_hosts_pending");
    }

    /**
     * The number of hosts in the group that are unreachable
     * 
     * @return returns the value of the "num_hosts_unreach" column as int
     */
    public int Num_hosts_unreach() throws NumberFormatException {
        return this.getAsInt("num_hosts_unreach");
    }

    /**
     * The number of hosts in the group that are up
     * 
     * @return returns the value of the "num_hosts_up" column as int
     */
    public int Num_hosts_up() throws NumberFormatException {
        return this.getAsInt("num_hosts_up");
    }

    /**
     * The total number of services of hosts in this group
     * 
     * @return returns the value of the "num_services" column as int
     */
    public int Num_services() throws NumberFormatException {
        return this.getAsInt("num_services");
    }

    /**
     * The total number of services with the state CRIT of hosts in this group
     * 
     * @return returns the value of the "num_services_crit" column as int
     */
    public int Num_services_crit() throws NumberFormatException {
        return this.getAsInt("num_services_crit");
    }

    /**
     * The total number of services with the state CRIT of hosts in this group
     * 
     * @return returns the value of the "num_services_hard_crit" column as int
     */
    public int Num_services_hard_crit() throws NumberFormatException {
        return this.getAsInt("num_services_hard_crit");
    }

    /**
     * The total number of services with the state OK of hosts in this group
     * 
     * @return returns the value of the "num_services_hard_ok" column as int
     */
    public int Num_services_hard_ok() throws NumberFormatException {
        return this.getAsInt("num_services_hard_ok");
    }

    /**
     * The total number of services with the state UNKNOWN of hosts in this group
     * 
     * @return returns the value of the "num_services_hard_unknown" column as int
     */
    public int Num_services_hard_unknown() throws NumberFormatException {
        return this.getAsInt("num_services_hard_unknown");
    }

    /**
     * The total number of services with the state WARN of hosts in this group
     * 
     * @return returns the value of the "num_services_hard_warn" column as int
     */
    public int Num_services_hard_warn() throws NumberFormatException {
        return this.getAsInt("num_services_hard_warn");
    }

    /**
     * The total number of services with the state OK of hosts in this group
     * 
     * @return returns the value of the "num_services_ok" column as int
     */
    public int Num_services_ok() throws NumberFormatException {
        return this.getAsInt("num_services_ok");
    }

    /**
     * The total number of services with the state Pending of hosts in this group
     * 
     * @return returns the value of the "num_services_pending" column as int
     */
    public int Num_services_pending() throws NumberFormatException {
        return this.getAsInt("num_services_pending");
    }

    /**
     * The total number of services with the state UNKNOWN of hosts in this group
     * 
     * @return returns the value of the "num_services_unknown" column as int
     */
    public int Num_services_unknown() throws NumberFormatException {
        return this.getAsInt("num_services_unknown");
    }

    /**
     * The total number of services with the state WARN of hosts in this group
     * 
     * @return returns the value of the "num_services_warn" column as int
     */
    public int Num_services_warn() throws NumberFormatException {
        return this.getAsInt("num_services_warn");
    }

    /**
     * The worst state of all of the groups' hosts (UP <= UNREACHABLE <= DOWN)
     * 
     * @return returns the value of the "worst_host_state" column as int
     */
    public int Worst_host_state() throws NumberFormatException {
        return this.getAsInt("worst_host_state");
    }

    /**
     * The worst state of all services that belong to a host of this group (OK <= WARN <= UNKNOWN <= CRIT)
     * 
     * @return returns the value of the "worst_service_hard_state" column as int
     */
    public int Worst_service_hard_state() throws NumberFormatException {
        return this.getAsInt("worst_service_hard_state");
    }

    /**
     * The worst state of all services that belong to a host of this group (OK <= WARN <= UNKNOWN <= CRIT)
     * 
     * @return returns the value of the "worst_service_state" column as int
     */
    public int Worst_service_state() throws NumberFormatException {
        return this.getAsInt("worst_service_state");
    }

    /**
     * create the map for all columns of table Hostgroups. Key=column name, Value=column value
     *
     * @param table
     *            LiveStatus table
     * @param filter
     *            filter to applay for this table
     * @return Map<String, String>
     */

    @Override
    public Map<String, String> asArrayString(final String table, final String filter) throws NumberFormatException {
        mapKeyValue.clear();
        this.setMapObjects(table, filter);

        this.addToHashtable("action_url", this.Action_url());
        this.addToHashtable("alias", this.Alias());
        this.addToHashtable("members", this.Members());
        this.addToHashtable("members_with_state", this.Members_with_state());
        this.addToHashtable("name", this.Name());
        this.addToHashtable("notes", this.Notes());
        this.addToHashtable("notes_url", this.Notes_url());
        this.addToHashtable("num_hosts", this.getAsString(this.Num_hosts()));
        this.addToHashtable("num_hosts_down", this.getAsString(this.Num_hosts_down()));
        this.addToHashtable("num_hosts_pending", this.getAsString(this.Num_hosts_pending()));
        this.addToHashtable("num_hosts_unreach", this.getAsString(this.Num_hosts_unreach()));
        this.addToHashtable("num_hosts_up", this.getAsString(this.Num_hosts_up()));
        this.addToHashtable("num_services", this.getAsString(this.Num_services()));
        this.addToHashtable("num_services_crit", this.getAsString(this.Num_services_crit()));
        this.addToHashtable("num_services_hard_crit", this.getAsString(this.Num_services_hard_crit()));
        this.addToHashtable("num_services_hard_ok", this.getAsString(this.Num_services_hard_ok()));
        this.addToHashtable("num_services_hard_unknown", this.getAsString(this.Num_services_hard_unknown()));
        this.addToHashtable("num_services_hard_warn", this.getAsString(this.Num_services_hard_warn()));
        this.addToHashtable("num_services_ok", this.getAsString(this.Num_services_ok()));
        this.addToHashtable("num_services_pending", this.getAsString(this.Num_services_pending()));
        this.addToHashtable("num_services_unknown", this.getAsString(this.Num_services_unknown()));
        this.addToHashtable("num_services_warn", this.getAsString(this.Num_services_warn()));
        this.addToHashtable("worst_host_state", this.getAsString(this.Worst_host_state()));
        this.addToHashtable("worst_service_hard_state", this.getAsString(this.Worst_service_hard_state()));
        this.addToHashtable("worst_service_state", this.getAsString(this.Worst_service_state()));
        return mapKeyValue;
    }

}
