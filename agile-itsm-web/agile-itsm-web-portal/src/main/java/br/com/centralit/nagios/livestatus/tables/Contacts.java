/*****************************************************************************
 * Contacts.java -
 *
 * Copyright (c) 2014 Projeto citsmart (Contact: adenir.gomes@centralit.com.br)
 *
 * License:
 *****************************************************************************/
package br.com.centralit.nagios.livestatus.tables;

import java.util.Map;

/**
 * Class Contacts is the main class for obtain all columns of table "contacts"
 * from a Livestatus TCP-socket/file status.dat.
 *
 * @author Adenir Ribeiro Gomes
 */

public class Contacts extends LiveStatusBase {

    /**
     * Constructor of table Contacts
     *
     * @param path
     *            = "tcp://host:port" File : where path is the path to the file
     */
    public Contacts(final String path) {
        super(path);
        this.initializeMaps();
        tableName = "contacts";
    }

    /**
     * create the map for all columns description of table Contacts. Key=column name, Value=column description
     *
     */
    public final void initializeMaps() {
        mapComments.put("address1", "The additional field address1");
        mapComments.put("address2", "The additional field address2");
        mapComments.put("address3", "The additional field address3");
        mapComments.put("address4", "The additional field address4");
        mapComments.put("address5", "The additional field address5");
        mapComments.put("address6", "The additional field address6");
        mapComments.put("alias", "The full name of the contact");
        mapComments.put("can_submit_commands", "Wether the contact is allowed to submit commands (0/1)");
        mapComments.put("custom_variable_names", "A list of all custom variables of the contact");
        mapComments.put("custom_variable_values", "A list of the values of all custom variables of the contact");
        mapComments.put("custom_variables", "A dictionary of the custom variables");
        mapComments.put("email", "The email address of the contact");
        mapComments.put("host_notification_period", "The time period in which the contact will be notified about host problems");
        mapComments.put("host_notifications_enabled", "Wether the contact will be notified about host problems in general (0/1)");
        mapComments.put("in_host_notification_period", "Wether the contact is currently in his/her host notification period (0/1)");
        mapComments.put("in_service_notification_period", "Wether the contact is currently in his/her service notification period (0/1)");
        mapComments.put("modified_attributes", "A bitmask specifying which attributes have been modified");
        mapComments.put("modified_attributes_list", "A list of all modified attributes");
        mapComments.put("name", "The login name of the contact person");
        mapComments.put("pager", "The pager address of the contact");
        mapComments.put("service_notification_period", "The time period in which the contact will be notified about service problems");
        mapComments.put("service_notifications_enabled", "Wether the contact will be notified about service problems in general (0/1)");
    }

    /**
     * The additional field address1
     * 
     * @return returns the value of the "address1" column as string
     */
    public String Address1() {
        return this.getAsString("address1");
    }

    /**
     * The additional field address2
     * 
     * @return returns the value of the "address2" column as string
     */
    public String Address2() {
        return this.getAsString("address2");
    }

    /**
     * The additional field address3
     * 
     * @return returns the value of the "address3" column as string
     */
    public String Address3() {
        return this.getAsString("address3");
    }

    /**
     * The additional field address4
     * 
     * @return returns the value of the "address4" column as string
     */
    public String Address4() {
        return this.getAsString("address4");
    }

    /**
     * The additional field address5
     * 
     * @return returns the value of the "address5" column as string
     */
    public String Address5() {
        return this.getAsString("address5");
    }

    /**
     * The additional field address6
     * 
     * @return returns the value of the "address6" column as string
     */
    public String Address6() {
        return this.getAsString("address6");
    }

    /**
     * The full name of the contact
     * 
     * @return returns the value of the "alias" column as string
     */
    public String Alias() {
        return this.getAsString("alias");
    }

    /**
     * Wether the contact is allowed to submit commands (0/1)
     * 
     * @return returns the value of the "can_submit_commands" column as int
     */
    public int Can_submit_commands() throws NumberFormatException {
        return this.getAsInt("can_submit_commands");
    }

    /**
     * A list of all custom variables of the contact
     * 
     * @return returns the value of the "custom_variable_names" column as list
     */
    public String Custom_variable_names() {
        return this.getAsList("custom_variable_names");
    }

    /**
     * A list of the values of all custom variables of the contact
     * 
     * @return returns the value of the "custom_variable_values" column as list
     */
    public String Custom_variable_values() {
        return this.getAsList("custom_variable_values");
    }

    /**
     * A dictionary of the custom variables
     * 
     * @return returns the value of the "custom_variables" column as dict
     */
    public String Custom_variables() {
        return this.getAsDict("custom_variables");
    }

    /**
     * The email address of the contact
     * 
     * @return returns the value of the "email" column as string
     */
    public String Email() {
        return this.getAsString("email");
    }

    /**
     * The time period in which the contact will be notified about host problems
     * 
     * @return returns the value of the "host_notification_period" column as string
     */
    public String Host_notification_period() {
        return this.getAsString("host_notification_period");
    }

    /**
     * Wether the contact will be notified about host problems in general (0/1)
     * 
     * @return returns the value of the "host_notifications_enabled" column as int
     */
    public int Host_notifications_enabled() throws NumberFormatException {
        return this.getAsInt("host_notifications_enabled");
    }

    /**
     * Wether the contact is currently in his/her host notification period (0/1)
     * 
     * @return returns the value of the "in_host_notification_period" column as int
     */
    public int In_host_notification_period() throws NumberFormatException {
        return this.getAsInt("in_host_notification_period");
    }

    /**
     * Wether the contact is currently in his/her service notification period (0/1)
     * 
     * @return returns the value of the "in_service_notification_period" column as int
     */
    public int In_service_notification_period() throws NumberFormatException {
        return this.getAsInt("in_service_notification_period");
    }

    /**
     * A bitmask specifying which attributes have been modified
     * 
     * @return returns the value of the "modified_attributes" column as int
     */
    public int Modified_attributes() throws NumberFormatException {
        return this.getAsInt("modified_attributes");
    }

    /**
     * A list of all modified attributes
     * 
     * @return returns the value of the "modified_attributes_list" column as list
     */
    public String Modified_attributes_list() {
        return this.getAsList("modified_attributes_list");
    }

    /**
     * The login name of the contact person
     * 
     * @return returns the value of the "name" column as string
     */
    public String Name() {
        return this.getAsString("name");
    }

    /**
     * The pager address of the contact
     * 
     * @return returns the value of the "pager" column as string
     */
    public String Pager() {
        return this.getAsString("pager");
    }

    /**
     * The time period in which the contact will be notified about service problems
     * 
     * @return returns the value of the "service_notification_period" column as string
     */
    public String Service_notification_period() {
        return this.getAsString("service_notification_period");
    }

    /**
     * Wether the contact will be notified about service problems in general (0/1)
     * 
     * @return returns the value of the "service_notifications_enabled" column as int
     */
    public int Service_notifications_enabled() throws NumberFormatException {
        return this.getAsInt("service_notifications_enabled");
    }

    /**
     * create the map for all columns of table Contacts. Key=column name, Value=column value
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

        this.addToHashtable("address1", this.Address1());
        this.addToHashtable("address2", this.Address2());
        this.addToHashtable("address3", this.Address3());
        this.addToHashtable("address4", this.Address4());
        this.addToHashtable("address5", this.Address5());
        this.addToHashtable("address6", this.Address6());
        this.addToHashtable("alias", this.Alias());
        this.addToHashtable("can_submit_commands", this.getAsString(this.Can_submit_commands()));
        this.addToHashtable("custom_variable_names", this.Custom_variable_names());
        this.addToHashtable("custom_variable_values", this.Custom_variable_values());
        this.addToHashtable("custom_variables", this.Custom_variables());
        this.addToHashtable("email", this.Email());
        this.addToHashtable("host_notification_period", this.Host_notification_period());
        this.addToHashtable("host_notifications_enabled", this.getAsString(this.Host_notifications_enabled()));
        this.addToHashtable("in_host_notification_period", this.getAsString(this.In_host_notification_period()));
        this.addToHashtable("in_service_notification_period", this.getAsString(this.In_service_notification_period()));
        this.addToHashtable("modified_attributes", this.getAsString(this.Modified_attributes()));
        this.addToHashtable("modified_attributes_list", this.Modified_attributes_list());
        this.addToHashtable("name", this.Name());
        this.addToHashtable("pager", this.Pager());
        this.addToHashtable("service_notification_period", this.Service_notification_period());
        this.addToHashtable("service_notifications_enabled", this.getAsString(this.Service_notifications_enabled()));
        return mapKeyValue;
    }

}
