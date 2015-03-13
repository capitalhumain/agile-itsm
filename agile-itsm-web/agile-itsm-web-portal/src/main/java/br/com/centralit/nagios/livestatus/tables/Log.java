/*****************************************************************************
 * Log.java -
 *
 * Copyright (c) 2014 Projeto citsmart (Contact: adenir.gomes@centralit.com.br)
 *
 * License:
 *****************************************************************************/
package br.com.centralit.nagios.livestatus.tables;

import java.util.Date;
import java.util.Map;

/**
 * Class Log is the main class for obtain all columns of table "log"
 * from a Livestatus TCP-socket/file status.dat.
 *
 * @author Adenir Ribeiro Gomes
 */

public class Log extends LiveStatusBase {

    /**
     * Constructor of table Log
     *
     * @param path
     *            = "tcp://host:port" File : where path is the path to the file
     */
    public Log(final String path) {
        super(path);
        this.initializeMaps();
        tableName = "log";
    }

    /**
     * create the map for all columns description of table Log. Key=column name, Value=column description
     *
     */
    public final void initializeMaps() {
        mapComments.put("attempt", "The number of the check attempt");
        mapComments.put("class", "The class of the message as integer (0:info, 1:state, 2:program, 3:notification, 4:passive, 5:command)");
        mapComments.put("command_name", "The name of the command of the log entry (e.g. for notifications)");
        mapComments.put("comment", "A comment field used in various message types");
        mapComments.put("contact_name", "The name of the contact the log entry is about (might be empty)");
        mapComments.put("current_command_line", "The shell command line");
        mapComments.put("current_command_name", "The name of the command");
        mapComments.put("current_contact_address1", "The additional field address1");
        mapComments.put("current_contact_address2", "The additional field address2");
        mapComments.put("current_contact_address3", "The additional field address3");
        mapComments.put("current_contact_address4", "The additional field address4");
        mapComments.put("current_contact_address5", "The additional field address5");
        mapComments.put("current_contact_address6", "The additional field address6");
        mapComments.put("current_contact_alias", "The full name of the contact");
        mapComments.put("current_contact_can_submit_commands", "Wether the contact is allowed to submit commands (0/1)");
        mapComments.put("current_contact_custom_variable_names", "A list of all custom variables of the contact");
        mapComments.put("current_contact_custom_variable_values", "A list of the values of all custom variables of the contact");
        mapComments.put("current_contact_custom_variables", "A dictionary of the custom variables");
        mapComments.put("current_contact_email", "The email address of the contact");
        mapComments.put("current_contact_host_notification_period", "The time period in which the contact will be notified about host problems");
        mapComments.put("current_contact_host_notifications_enabled", "Wether the contact will be notified about host problems in general (0/1)");
        mapComments.put("current_contact_in_host_notification_period", "Wether the contact is currently in his/her host notification period (0/1)");
        mapComments.put("current_contact_in_service_notification_period", "Wether the contact is currently in his/her service notification period (0/1)");
        mapComments.put("current_contact_modified_attributes", "A bitmask specifying which attributes have been modified");
        mapComments.put("current_contact_modified_attributes_list", "A list of all modified attributes");
        mapComments.put("current_contact_name", "The login name of the contact person");
        mapComments.put("current_contact_pager", "The pager address of the contact");
        mapComments.put("current_contact_service_notification_period", "The time period in which the contact will be notified about service problems");
        mapComments.put("current_contact_service_notifications_enabled", "Wether the contact will be notified about service problems in general (0/1)");
        mapComments.put("current_host_accept_passive_checks", "Whether passive host checks are accepted (0/1)");
        mapComments.put("current_host_acknowledged", "Whether the current host problem has been acknowledged (0/1)");
        mapComments.put("current_host_acknowledgement_type", "Type of acknowledgement (0: none, 1: normal, 2: stick)");
        mapComments.put("current_host_action_url", "An optional URL to custom actions or information about this host");
        mapComments.put("current_host_action_url_expanded", "The same as action_url, but with the most important macros expanded");
        mapComments.put("current_host_active_checks_enabled", "Whether active checks are enabled for the host (0/1)");
        mapComments.put("current_host_address", "IP address");
        mapComments.put("current_host_alias", "An alias name for the host");
        mapComments.put("current_host_check_command", "Nagios command for active host check of this host");
        mapComments.put("current_host_check_command_expanded", "Nagios command for active host check of this host with the macros expanded");
        mapComments.put("current_host_check_flapping_recovery_notification", "Whether to check to send a recovery notification when flapping stops (0/1)");
        mapComments.put("current_host_check_freshness", "Whether freshness checks are activated (0/1)");
        mapComments.put("current_host_check_interval", "Number of basic interval lengths between two scheduled checks of the host");
        mapComments.put("current_host_check_options", "The current check option, forced, normal, freshness... (0-2)");
        mapComments.put("current_host_check_period", "Time period in which this host will be checked. If empty then the host will always be checked.");
        mapComments.put("current_host_check_type", "Type of check (0: active, 1: passive)");
        mapComments.put("current_host_checks_enabled", "Whether checks of the host are enabled (0/1)");
        mapComments.put("current_host_childs", "A list of all direct childs of the host");
        mapComments.put("current_host_comments", "A list of the ids of all comments of this host");
        mapComments.put("current_host_comments_with_extra_info", "A list of all comments of the host with id, author, comment, entry type and entry time");
        mapComments.put("current_host_comments_with_info", "A list of all comments of the host with id, author and comment");
        mapComments.put("current_host_contact_groups", "A list of all contact groups this host is in");
        mapComments.put("current_host_contacts", "A list of all contacts of this host, either direct or via a contact group");
        mapComments.put("current_host_current_attempt", "Number of the current check attempts");
        mapComments.put("current_host_current_notification_number", "Number of the current notification");
        mapComments.put("current_host_custom_variable_names", "A list of the names of all custom variables");
        mapComments.put("current_host_custom_variable_values", "A list of the values of the custom variables");
        mapComments.put("current_host_custom_variables", "A dictionary of the custom variables");
        mapComments.put("current_host_display_name", "Optional display name of the host - not used by Nagios' web interface");
        mapComments.put("current_host_downtimes", "A list of the ids of all scheduled downtimes of this host");
        mapComments.put("current_host_downtimes_with_info", "A list of the all scheduled downtimes of the host with id, author and comment");
        mapComments.put("current_host_event_handler", "Nagios command used as event handler");
        mapComments.put("current_host_event_handler_enabled", "Whether event handling is enabled (0/1)");
        mapComments.put("current_host_execution_time", "Time the host check needed for execution");
        mapComments.put("current_host_filename", "The value of the custom variable FILENAME");
        mapComments.put("current_host_first_notification_delay", "Delay before the first notification");
        mapComments.put("current_host_flap_detection_enabled", "Whether flap detection is enabled (0/1)");
        mapComments.put("current_host_groups", "A list of all host groups this host is in");
        mapComments.put("current_host_hard_state", "The effective hard state of the host (eliminates a problem in hard_state)");
        mapComments.put("current_host_has_been_checked", "Whether the host has already been checked (0/1)");
        mapComments.put("current_host_high_flap_threshold", "High threshold of flap detection");
        mapComments.put("current_host_icon_image", "The name of an image file to be used in the web pages");
        mapComments.put("current_host_icon_image_alt", "Alternative text for the icon_image");
        mapComments.put("current_host_icon_image_expanded", "The same as icon_image, but with the most important macros expanded");
        mapComments.put("current_host_in_check_period", "Whether this host is currently in its check period (0/1)");
        mapComments.put("current_host_in_notification_period", "Whether this host is currently in its notification period (0/1)");
        mapComments.put("current_host_in_service_period", "Whether this host is currently in its service period (0/1)");
        mapComments.put("current_host_initial_state", "Initial host state");
        mapComments.put("current_host_is_executing", "is there a host check currently running... (0/1)");
        mapComments.put("current_host_is_flapping", "Whether the host state is flapping (0/1)");
        mapComments.put("current_host_last_check", "Time of the last check (Unix timestamp)");
        mapComments.put("current_host_last_hard_state", "Last hard state");
        mapComments.put("current_host_last_hard_state_change", "Time of the last hard state change (Unix timestamp)");
        mapComments.put("current_host_last_notification", "Time of the last notification (Unix timestamp)");
        mapComments.put("current_host_last_state", "State before last state change");
        mapComments.put("current_host_last_state_change", "Time of the last state change - soft or hard (Unix timestamp)");
        mapComments.put("current_host_last_time_down", "The last time the host was DOWN (Unix timestamp)");
        mapComments.put("current_host_last_time_unreachable", "The last time the host was UNREACHABLE (Unix timestamp)");
        mapComments.put("current_host_last_time_up", "The last time the host was UP (Unix timestamp)");
        mapComments.put("current_host_latency", "Time difference between scheduled check time and actual check time");
        mapComments.put("current_host_long_plugin_output", "Complete output from check plugin");
        mapComments.put("current_host_low_flap_threshold", "Low threshold of flap detection");
        mapComments.put("current_host_max_check_attempts", "Max check attempts for active host checks");
        mapComments.put("current_host_modified_attributes", "A bitmask specifying which attributes have been modified");
        mapComments.put("current_host_modified_attributes_list", "A list of all modified attributes");
        mapComments.put("current_host_name", "Host name");
        mapComments.put("current_host_next_check", "Scheduled time for the next check (Unix timestamp)");
        mapComments.put("current_host_next_notification", "Time of the next notification (Unix timestamp)");
        mapComments.put("current_host_no_more_notifications", "Whether to stop sending notifications (0/1)");
        mapComments.put("current_host_notes", "Optional notes for this host");
        mapComments.put("current_host_notes_expanded", "The same as notes, but with the most important macros expanded");
        mapComments.put("current_host_notes_url", "An optional URL with further information about the host");
        mapComments.put("current_host_notes_url_expanded", "Same es notes_url, but with the most important macros expanded");
        mapComments.put("current_host_notification_interval", "Interval of periodic notification or 0 if its off");
        mapComments.put("current_host_notification_period",
                "Time period in which problems of this host will be notified. If empty then notification will be always");
        mapComments.put("current_host_notifications_enabled", "Whether notifications of the host are enabled (0/1)");
        mapComments.put("current_host_num_services", "The total number of services of the host");
        mapComments.put("current_host_num_services_crit", "The number of the host's services with the soft state CRIT");
        mapComments.put("current_host_num_services_hard_crit", "The number of the host's services with the hard state CRIT");
        mapComments.put("current_host_num_services_hard_ok", "The number of the host's services with the hard state OK");
        mapComments.put("current_host_num_services_hard_unknown", "The number of the host's services with the hard state UNKNOWN");
        mapComments.put("current_host_num_services_hard_warn", "The number of the host's services with the hard state WARN");
        mapComments.put("current_host_num_services_ok", "The number of the host's services with the soft state OK");
        mapComments.put("current_host_num_services_pending", "The number of the host's services which have not been checked yet (pending)");
        mapComments.put("current_host_num_services_unknown", "The number of the host's services with the soft state UNKNOWN");
        mapComments.put("current_host_num_services_warn", "The number of the host's services with the soft state WARN");
        mapComments.put("current_host_obsess_over_host", "The current obsess_over_host setting... (0/1)");
        mapComments.put("current_host_parents", "A list of all direct parents of the host");
        mapComments.put("current_host_pending_flex_downtime", "Whether a flex downtime is pending (0/1)");
        mapComments.put("current_host_percent_state_change", "Percent state change");
        mapComments.put("current_host_perf_data", "Optional performance data of the last host check");
        mapComments.put("current_host_plugin_output", "Output of the last host check");
        mapComments.put("current_host_pnpgraph_present", "Whether there is a PNP4Nagios graph present for this host (0/1)");
        mapComments.put("current_host_process_performance_data", "Whether processing of performance data is enabled (0/1)");
        mapComments.put("current_host_retry_interval", "Number of basic interval lengths between checks when retrying after a soft error");
        mapComments.put("current_host_scheduled_downtime_depth", "The number of downtimes this host is currently in");
        mapComments.put("current_host_service_period", "The name of the service period of the host");
        mapComments.put("current_host_services", "A list of all services of the host");
        mapComments.put("current_host_services_with_info", "A list of all services including detailed information about each service");
        mapComments.put("current_host_services_with_state", "A list of all services of the host together with state and has_been_checked");
        mapComments.put("current_host_staleness", "Staleness indicator for this host");
        mapComments.put("current_host_state", "The current state of the host (0: up, 1: down, 2: unreachable)");
        mapComments.put("current_host_state_type", "Type of the current state (0: soft, 1: hard)");
        mapComments.put("current_host_statusmap_image", "The name of in image file for the status map");
        mapComments.put("current_host_total_services", "The total number of services of the host");
        mapComments.put("current_host_worst_service_hard_state", "The worst hard state of all of the host's services (OK <= WARN <= UNKNOWN <= CRIT)");
        mapComments.put("current_host_worst_service_state", "The worst soft state of all of the host's services (OK <= WARN <= UNKNOWN <= CRIT)");
        mapComments.put("current_host_x_3d", "3D-Coordinates: X");
        mapComments.put("current_host_y_3d", "3D-Coordinates: Y");
        mapComments.put("current_host_z_3d", "3D-Coordinates: Z");
        mapComments.put("current_service_accept_passive_checks", "Whether the service accepts passive checks (0/1)");
        mapComments.put("current_service_acknowledged", "Whether the current service problem has been acknowledged (0/1)");
        mapComments.put("current_service_acknowledgement_type", "The type of the acknownledgement (0: none, 1: normal, 2: sticky)");
        mapComments.put("current_service_action_url", "An optional URL for actions or custom information about the service");
        mapComments.put("current_service_action_url_expanded", "The action_url with (the most important) macros expanded");
        mapComments.put("current_service_active_checks_enabled", "Whether active checks are enabled for the service (0/1)");
        mapComments.put("current_service_check_command", "Nagios command used for active checks");
        mapComments.put("current_service_check_command_expanded", "Nagios command used for active checks with the macros expanded");
        mapComments.put("current_service_check_freshness", "Whether freshness checks are activated (0/1)");
        mapComments.put("current_service_check_interval", "Number of basic interval lengths between two scheduled checks of the service");
        mapComments.put("current_service_check_options", "The current check option, forced, normal, freshness... (0/1)");
        mapComments.put("current_service_check_period", "The name of the check period of the service. It this is empty, the service is always checked.");
        mapComments.put("current_service_check_type", "The type of the last check (0: active, 1: passive)");
        mapComments.put("current_service_checks_enabled", "Whether active checks are enabled for the service (0/1)");
        mapComments.put("current_service_comments", "A list of all comment ids of the service");
        mapComments
                .put("current_service_comments_with_extra_info", "A list of all comments of the service with id, author, comment, entry type and entry time");
        mapComments.put("current_service_comments_with_info", "A list of all comments of the service with id, author and comment");
        mapComments.put("current_service_contact_groups", "A list of all contact groups this service is in");
        mapComments.put("current_service_contacts", "A list of all contacts of the service, either direct or via a contact group");
        mapComments.put("current_service_current_attempt", "The number of the current check attempt");
        mapComments.put("current_service_current_notification_number", "The number of the current notification");
        mapComments.put("current_service_custom_variable_names", "A list of the names of all custom variables of the service");
        mapComments.put("current_service_custom_variable_values", "A list of the values of all custom variable of the service");
        mapComments.put("current_service_custom_variables", "A dictionary of the custom variables");
        mapComments.put("current_service_description", "Description of the service (also used as key)");
        mapComments.put("current_service_display_name", "An optional display name (not used by Nagios standard web pages)");
        mapComments.put("current_service_downtimes", "A list of all downtime ids of the service");
        mapComments.put("current_service_downtimes_with_info", "A list of all downtimes of the service with id, author and comment");
        mapComments.put("current_service_event_handler", "Nagios command used as event handler");
        mapComments.put("current_service_event_handler_enabled", "Whether and event handler is activated for the service (0/1)");
        mapComments.put("current_service_execution_time", "Time the service check needed for execution");
        mapComments.put("current_service_first_notification_delay", "Delay before the first notification");
        mapComments.put("current_service_flap_detection_enabled", "Whether flap detection is enabled for the service (0/1)");
        mapComments.put("current_service_groups", "A list of all service groups the service is in");
        mapComments.put("current_service_has_been_checked", "Whether the service already has been checked (0/1)");
        mapComments.put("current_service_high_flap_threshold", "High threshold of flap detection");
        mapComments.put("current_service_icon_image", "The name of an image to be used as icon in the web interface");
        mapComments.put("current_service_icon_image_alt", "An alternative text for the icon_image for browsers not displaying icons");
        mapComments.put("current_service_icon_image_expanded", "The icon_image with (the most important) macros expanded");
        mapComments.put("current_service_in_check_period", "Whether the service is currently in its check period (0/1)");
        mapComments.put("current_service_in_notification_period", "Whether the service is currently in its notification period (0/1)");
        mapComments.put("current_service_in_service_period", "Whether this service is currently in its service period (0/1)");
        mapComments.put("current_service_initial_state", "The initial state of the service");
        mapComments.put("current_service_is_executing", "is there a service check currently running... (0/1)");
        mapComments.put("current_service_is_flapping", "Whether the service is flapping (0/1)");
        mapComments.put("current_service_last_check", "The time of the last check (Unix timestamp)");
        mapComments.put("current_service_last_hard_state", "The last hard state of the service");
        mapComments.put("current_service_last_hard_state_change", "The time of the last hard state change (Unix timestamp)");
        mapComments.put("current_service_last_notification", "The time of the last notification (Unix timestamp)");
        mapComments.put("current_service_last_state", "The last state of the service");
        mapComments.put("current_service_last_state_change", "The time of the last state change (Unix timestamp)");
        mapComments.put("current_service_last_time_critical", "The last time the service was CRITICAL (Unix timestamp)");
        mapComments.put("current_service_last_time_ok", "The last time the service was OK (Unix timestamp)");
        mapComments.put("current_service_last_time_unknown", "The last time the service was UNKNOWN (Unix timestamp)");
        mapComments.put("current_service_last_time_warning", "The last time the service was in WARNING state (Unix timestamp)");
        mapComments.put("current_service_latency", "Time difference between scheduled check time and actual check time");
        mapComments.put("current_service_long_plugin_output", "Unabbreviated output of the last check plugin");
        mapComments.put("current_service_low_flap_threshold", "Low threshold of flap detection");
        mapComments.put("current_service_max_check_attempts", "The maximum number of check attempts");
        mapComments.put("current_service_modified_attributes", "A bitmask specifying which attributes have been modified");
        mapComments.put("current_service_modified_attributes_list", "A list of all modified attributes");
        mapComments.put("current_service_next_check", "The scheduled time of the next check (Unix timestamp)");
        mapComments.put("current_service_next_notification", "The time of the next notification (Unix timestamp)");
        mapComments.put("current_service_no_more_notifications", "Whether to stop sending notifications (0/1)");
        mapComments.put("current_service_notes", "Optional notes about the service");
        mapComments.put("current_service_notes_expanded", "The notes with (the most important) macros expanded");
        mapComments.put("current_service_notes_url", "An optional URL for additional notes about the service");
        mapComments.put("current_service_notes_url_expanded", "The notes_url with (the most important) macros expanded");
        mapComments.put("current_service_notification_interval", "Interval of periodic notification or 0 if its off");
        mapComments.put("current_service_notification_period",
                "The name of the notification period of the service. It this is empty, service problems are always notified.");
        mapComments.put("current_service_notifications_enabled", "Whether notifications are enabled for the service (0/1)");
        mapComments.put("current_service_obsess_over_service", "Whether 'obsess_over_service' is enabled for the service (0/1)");
        mapComments.put("current_service_percent_state_change", "Percent state change");
        mapComments.put("current_service_perf_data", "Performance data of the last check plugin");
        mapComments.put("current_service_plugin_output", "Output of the last check plugin");
        mapComments.put("current_service_pnpgraph_present", "Whether there is a PNP4Nagios graph present for this service (0/1)");
        mapComments.put("current_service_process_performance_data", "Whether processing of performance data is enabled for the service (0/1)");
        mapComments.put("current_service_retry_interval", "Number of basic interval lengths between checks when retrying after a soft error");
        mapComments.put("current_service_scheduled_downtime_depth", "The number of scheduled downtimes the service is currently in");
        mapComments.put("current_service_service_period", "The name of the service period of the service");
        mapComments.put("current_service_staleness", "The staleness indicator for this service");
        mapComments.put("current_service_state", "The current state of the service (0: OK, 1: WARN, 2: CRITICAL, 3: UNKNOWN)");
        mapComments.put("current_service_state_type", "The type of the current state (0: soft, 1: hard)");
        mapComments.put("host_name", "The name of the host the log entry is about (might be empty)");
        mapComments.put("lineno", "The number of the line in the log file");
        mapComments.put("message", "The complete message line including the timestamp");
        mapComments.put("options", "The part of the message after the ':'");
        mapComments.put("plugin_output", "The output of the check, if any is associated with the message");
        mapComments.put("service_description", "The description of the service log entry is about (might be empty)");
        mapComments.put("state", "The state of the host or service in question");
        mapComments.put("state_type", "The type of the state (varies on different log classes)");
        mapComments.put("time", "Time of the log event (UNIX timestamp)");
        mapComments.put("type", "The type of the message (text before the colon), the message itself for info messages");
    }

    /**
     * The number of the check attempt
     * 
     * @return returns the value of the "attempt" column as int
     */
    public int Attempt() throws NumberFormatException {
        return this.getAsInt("attempt");
    }

    /**
     * The class of the message as integer (0:info, 1:state, 2:program, 3:notification, 4:passive, 5:command)
     * 
     * @return returns the value of the "class" column as int
     */
    public int Class() throws NumberFormatException {
        return this.getAsInt("class");
    }

    /**
     * The name of the command of the log entry (e.g. for notifications)
     * 
     * @return returns the value of the "command_name" column as string
     */
    public String Command_name() {
        return this.getAsString("command_name");
    }

    /**
     * A comment field used in various message types
     * 
     * @return returns the value of the "comment" column as string
     */
    public String Comment() {
        return this.getAsString("comment");
    }

    /**
     * The name of the contact the log entry is about (might be empty)
     * 
     * @return returns the value of the "contact_name" column as string
     */
    public String Contact_name() {
        return this.getAsString("contact_name");
    }

    /**
     * The shell command line
     * 
     * @return returns the value of the "current_command_line" column as string
     */
    public String Current_command_line() {
        return this.getAsString("current_command_line");
    }

    /**
     * The name of the command
     * 
     * @return returns the value of the "current_command_name" column as string
     */
    public String Current_command_name() {
        return this.getAsString("current_command_name");
    }

    /**
     * The additional field address1
     * 
     * @return returns the value of the "current_contact_address1" column as string
     */
    public String Current_contact_address1() {
        return this.getAsString("current_contact_address1");
    }

    /**
     * The additional field address2
     * 
     * @return returns the value of the "current_contact_address2" column as string
     */
    public String Current_contact_address2() {
        return this.getAsString("current_contact_address2");
    }

    /**
     * The additional field address3
     * 
     * @return returns the value of the "current_contact_address3" column as string
     */
    public String Current_contact_address3() {
        return this.getAsString("current_contact_address3");
    }

    /**
     * The additional field address4
     * 
     * @return returns the value of the "current_contact_address4" column as string
     */
    public String Current_contact_address4() {
        return this.getAsString("current_contact_address4");
    }

    /**
     * The additional field address5
     * 
     * @return returns the value of the "current_contact_address5" column as string
     */
    public String Current_contact_address5() {
        return this.getAsString("current_contact_address5");
    }

    /**
     * The additional field address6
     * 
     * @return returns the value of the "current_contact_address6" column as string
     */
    public String Current_contact_address6() {
        return this.getAsString("current_contact_address6");
    }

    /**
     * The full name of the contact
     * 
     * @return returns the value of the "current_contact_alias" column as string
     */
    public String Current_contact_alias() {
        return this.getAsString("current_contact_alias");
    }

    /**
     * Wether the contact is allowed to submit commands (0/1)
     * 
     * @return returns the value of the "current_contact_can_submit_commands" column as int
     */
    public int Current_contact_can_submit_commands() throws NumberFormatException {
        return this.getAsInt("current_contact_can_submit_commands");
    }

    /**
     * A list of all custom variables of the contact
     * 
     * @return returns the value of the "current_contact_custom_variable_names" column as list
     */
    public String Current_contact_custom_variable_names() {
        return this.getAsList("current_contact_custom_variable_names");
    }

    /**
     * A list of the values of all custom variables of the contact
     * 
     * @return returns the value of the "current_contact_custom_variable_values" column as list
     */
    public String Current_contact_custom_variable_values() {
        return this.getAsList("current_contact_custom_variable_values");
    }

    /**
     * A dictionary of the custom variables
     * 
     * @return returns the value of the "current_contact_custom_variables" column as dict
     */
    public String Current_contact_custom_variables() {
        return this.getAsDict("current_contact_custom_variables");
    }

    /**
     * The email address of the contact
     * 
     * @return returns the value of the "current_contact_email" column as string
     */
    public String Current_contact_email() {
        return this.getAsString("current_contact_email");
    }

    /**
     * The time period in which the contact will be notified about host problems
     * 
     * @return returns the value of the "current_contact_host_notification_period" column as string
     */
    public String Current_contact_host_notification_period() {
        return this.getAsString("current_contact_host_notification_period");
    }

    /**
     * Wether the contact will be notified about host problems in general (0/1)
     * 
     * @return returns the value of the "current_contact_host_notifications_enabled" column as int
     */
    public int Current_contact_host_notifications_enabled() throws NumberFormatException {
        return this.getAsInt("current_contact_host_notifications_enabled");
    }

    /**
     * Wether the contact is currently in his/her host notification period (0/1)
     * 
     * @return returns the value of the "current_contact_in_host_notification_period" column as int
     */
    public int Current_contact_in_host_notification_period() throws NumberFormatException {
        return this.getAsInt("current_contact_in_host_notification_period");
    }

    /**
     * Wether the contact is currently in his/her service notification period (0/1)
     * 
     * @return returns the value of the "current_contact_in_service_notification_period" column as int
     */
    public int Current_contact_in_service_notification_period() throws NumberFormatException {
        return this.getAsInt("current_contact_in_service_notification_period");
    }

    /**
     * A bitmask specifying which attributes have been modified
     * 
     * @return returns the value of the "current_contact_modified_attributes" column as int
     */
    public int Current_contact_modified_attributes() throws NumberFormatException {
        return this.getAsInt("current_contact_modified_attributes");
    }

    /**
     * A list of all modified attributes
     * 
     * @return returns the value of the "current_contact_modified_attributes_list" column as list
     */
    public String Current_contact_modified_attributes_list() {
        return this.getAsList("current_contact_modified_attributes_list");
    }

    /**
     * The login name of the contact person
     * 
     * @return returns the value of the "current_contact_name" column as string
     */
    public String Current_contact_name() {
        return this.getAsString("current_contact_name");
    }

    /**
     * The pager address of the contact
     * 
     * @return returns the value of the "current_contact_pager" column as string
     */
    public String Current_contact_pager() {
        return this.getAsString("current_contact_pager");
    }

    /**
     * The time period in which the contact will be notified about service problems
     * 
     * @return returns the value of the "current_contact_service_notification_period" column as string
     */
    public String Current_contact_service_notification_period() {
        return this.getAsString("current_contact_service_notification_period");
    }

    /**
     * Wether the contact will be notified about service problems in general (0/1)
     * 
     * @return returns the value of the "current_contact_service_notifications_enabled" column as int
     */
    public int Current_contact_service_notifications_enabled() throws NumberFormatException {
        return this.getAsInt("current_contact_service_notifications_enabled");
    }

    /**
     * Whether passive host checks are accepted (0/1)
     * 
     * @return returns the value of the "current_host_accept_passive_checks" column as int
     */
    public int Current_host_accept_passive_checks() throws NumberFormatException {
        return this.getAsInt("current_host_accept_passive_checks");
    }

    /**
     * Whether the current host problem has been acknowledged (0/1)
     * 
     * @return returns the value of the "current_host_acknowledged" column as int
     */
    public int Current_host_acknowledged() throws NumberFormatException {
        return this.getAsInt("current_host_acknowledged");
    }

    /**
     * Type of acknowledgement (0: none, 1: normal, 2: stick)
     * 
     * @return returns the value of the "current_host_acknowledgement_type" column as int
     */
    public int Current_host_acknowledgement_type() throws NumberFormatException {
        return this.getAsInt("current_host_acknowledgement_type");
    }

    /**
     * An optional URL to custom actions or information about this host
     * 
     * @return returns the value of the "current_host_action_url" column as string
     */
    public String Current_host_action_url() {
        return this.getAsString("current_host_action_url");
    }

    /**
     * The same as action_url, but with the most important macros expanded
     * 
     * @return returns the value of the "current_host_action_url_expanded" column as string
     */
    public String Current_host_action_url_expanded() {
        return this.getAsString("current_host_action_url_expanded");
    }

    /**
     * Whether active checks are enabled for the host (0/1)
     * 
     * @return returns the value of the "current_host_active_checks_enabled" column as int
     */
    public int Current_host_active_checks_enabled() throws NumberFormatException {
        return this.getAsInt("current_host_active_checks_enabled");
    }

    /**
     * IP address
     * 
     * @return returns the value of the "current_host_address" column as string
     */
    public String Current_host_address() {
        return this.getAsString("current_host_address");
    }

    /**
     * An alias name for the host
     * 
     * @return returns the value of the "current_host_alias" column as string
     */
    public String Current_host_alias() {
        return this.getAsString("current_host_alias");
    }

    /**
     * Nagios command for active host check of this host
     * 
     * @return returns the value of the "current_host_check_command" column as string
     */
    public String Current_host_check_command() {
        return this.getAsString("current_host_check_command");
    }

    /**
     * Nagios command for active host check of this host with the macros expanded
     * 
     * @return returns the value of the "current_host_check_command_expanded" column as string
     */
    public String Current_host_check_command_expanded() {
        return this.getAsString("current_host_check_command_expanded");
    }

    /**
     * Whether to check to send a recovery notification when flapping stops (0/1)
     * 
     * @return returns the value of the "current_host_check_flapping_recovery_notification" column as int
     */
    public int Current_host_check_flapping_recovery_notification() throws NumberFormatException {
        return this.getAsInt("current_host_check_flapping_recovery_notification");
    }

    /**
     * Whether freshness checks are activated (0/1)
     * 
     * @return returns the value of the "current_host_check_freshness" column as int
     */
    public int Current_host_check_freshness() throws NumberFormatException {
        return this.getAsInt("current_host_check_freshness");
    }

    /**
     * Number of basic interval lengths between two scheduled checks of the host
     * 
     * @return returns the value of the "current_host_check_interval" column as float
     */
    public float Current_host_check_interval() throws NumberFormatException {
        return this.getAsFloat("current_host_check_interval");
    }

    /**
     * The current check option, forced, normal, freshness... (0-2)
     * 
     * @return returns the value of the "current_host_check_options" column as int
     */
    public int Current_host_check_options() throws NumberFormatException {
        return this.getAsInt("current_host_check_options");
    }

    /**
     * Time period in which this host will be checked. If empty then the host will always be checked.
     * 
     * @return returns the value of the "current_host_check_period" column as string
     */
    public String Current_host_check_period() {
        return this.getAsString("current_host_check_period");
    }

    /**
     * Type of check (0: active, 1: passive)
     * 
     * @return returns the value of the "current_host_check_type" column as int
     */
    public int Current_host_check_type() throws NumberFormatException {
        return this.getAsInt("current_host_check_type");
    }

    /**
     * Whether checks of the host are enabled (0/1)
     * 
     * @return returns the value of the "current_host_checks_enabled" column as int
     */
    public int Current_host_checks_enabled() throws NumberFormatException {
        return this.getAsInt("current_host_checks_enabled");
    }

    /**
     * A list of all direct childs of the host
     * 
     * @return returns the value of the "current_host_childs" column as list
     */
    public String Current_host_childs() {
        return this.getAsList("current_host_childs");
    }

    /**
     * A list of the ids of all comments of this host
     * 
     * @return returns the value of the "current_host_comments" column as list
     */
    public String Current_host_comments() {
        return this.getAsList("current_host_comments");
    }

    /**
     * A list of all comments of the host with id, author, comment, entry type and entry time
     * 
     * @return returns the value of the "current_host_comments_with_extra_info" column as list
     */
    public String Current_host_comments_with_extra_info() {
        return this.getAsList("current_host_comments_with_extra_info");
    }

    /**
     * A list of all comments of the host with id, author and comment
     * 
     * @return returns the value of the "current_host_comments_with_info" column as list
     */
    public String Current_host_comments_with_info() {
        return this.getAsList("current_host_comments_with_info");
    }

    /**
     * A list of all contact groups this host is in
     * 
     * @return returns the value of the "current_host_contact_groups" column as list
     */
    public String Current_host_contact_groups() {
        return this.getAsList("current_host_contact_groups");
    }

    /**
     * A list of all contacts of this host, either direct or via a contact group
     * 
     * @return returns the value of the "current_host_contacts" column as list
     */
    public String Current_host_contacts() {
        return this.getAsList("current_host_contacts");
    }

    /**
     * Number of the current check attempts
     * 
     * @return returns the value of the "current_host_current_attempt" column as int
     */
    public int Current_host_current_attempt() throws NumberFormatException {
        return this.getAsInt("current_host_current_attempt");
    }

    /**
     * Number of the current notification
     * 
     * @return returns the value of the "current_host_current_notification_number" column as int
     */
    public int Current_host_current_notification_number() throws NumberFormatException {
        return this.getAsInt("current_host_current_notification_number");
    }

    /**
     * A list of the names of all custom variables
     * 
     * @return returns the value of the "current_host_custom_variable_names" column as list
     */
    public String Current_host_custom_variable_names() {
        return this.getAsList("current_host_custom_variable_names");
    }

    /**
     * A list of the values of the custom variables
     * 
     * @return returns the value of the "current_host_custom_variable_values" column as list
     */
    public String Current_host_custom_variable_values() {
        return this.getAsList("current_host_custom_variable_values");
    }

    /**
     * A dictionary of the custom variables
     * 
     * @return returns the value of the "current_host_custom_variables" column as dict
     */
    public String Current_host_custom_variables() {
        return this.getAsDict("current_host_custom_variables");
    }

    /**
     * Optional display name of the host - not used by Nagios' web interface
     * 
     * @return returns the value of the "current_host_display_name" column as string
     */
    public String Current_host_display_name() {
        return this.getAsString("current_host_display_name");
    }

    /**
     * A list of the ids of all scheduled downtimes of this host
     * 
     * @return returns the value of the "current_host_downtimes" column as list
     */
    public String Current_host_downtimes() {
        return this.getAsList("current_host_downtimes");
    }

    /**
     * A list of the all scheduled downtimes of the host with id, author and comment
     * 
     * @return returns the value of the "current_host_downtimes_with_info" column as list
     */
    public String Current_host_downtimes_with_info() {
        return this.getAsList("current_host_downtimes_with_info");
    }

    /**
     * Nagios command used as event handler
     * 
     * @return returns the value of the "current_host_event_handler" column as string
     */
    public String Current_host_event_handler() {
        return this.getAsString("current_host_event_handler");
    }

    /**
     * Whether event handling is enabled (0/1)
     * 
     * @return returns the value of the "current_host_event_handler_enabled" column as int
     */
    public int Current_host_event_handler_enabled() throws NumberFormatException {
        return this.getAsInt("current_host_event_handler_enabled");
    }

    /**
     * Time the host check needed for execution
     * 
     * @return returns the value of the "current_host_execution_time" column as float
     */
    public float Current_host_execution_time() throws NumberFormatException {
        return this.getAsFloat("current_host_execution_time");
    }

    /**
     * The value of the custom variable FILENAME
     * 
     * @return returns the value of the "current_host_filename" column as string
     */
    public String Current_host_filename() {
        return this.getAsString("current_host_filename");
    }

    /**
     * Delay before the first notification
     * 
     * @return returns the value of the "current_host_first_notification_delay" column as float
     */
    public float Current_host_first_notification_delay() throws NumberFormatException {
        return this.getAsFloat("current_host_first_notification_delay");
    }

    /**
     * Whether flap detection is enabled (0/1)
     * 
     * @return returns the value of the "current_host_flap_detection_enabled" column as int
     */
    public int Current_host_flap_detection_enabled() throws NumberFormatException {
        return this.getAsInt("current_host_flap_detection_enabled");
    }

    /**
     * A list of all host groups this host is in
     * 
     * @return returns the value of the "current_host_groups" column as list
     */
    public String Current_host_groups() {
        return this.getAsList("current_host_groups");
    }

    /**
     * The effective hard state of the host (eliminates a problem in hard_state)
     * 
     * @return returns the value of the "current_host_hard_state" column as int
     */
    public int Current_host_hard_state() throws NumberFormatException {
        return this.getAsInt("current_host_hard_state");
    }

    /**
     * Whether the host has already been checked (0/1)
     * 
     * @return returns the value of the "current_host_has_been_checked" column as int
     */
    public int Current_host_has_been_checked() throws NumberFormatException {
        return this.getAsInt("current_host_has_been_checked");
    }

    /**
     * High threshold of flap detection
     * 
     * @return returns the value of the "current_host_high_flap_threshold" column as float
     */
    public float Current_host_high_flap_threshold() throws NumberFormatException {
        return this.getAsFloat("current_host_high_flap_threshold");
    }

    /**
     * The name of an image file to be used in the web pages
     * 
     * @return returns the value of the "current_host_icon_image" column as string
     */
    public String Current_host_icon_image() {
        return this.getAsString("current_host_icon_image");
    }

    /**
     * Alternative text for the icon_image
     * 
     * @return returns the value of the "current_host_icon_image_alt" column as string
     */
    public String Current_host_icon_image_alt() {
        return this.getAsString("current_host_icon_image_alt");
    }

    /**
     * The same as icon_image, but with the most important macros expanded
     * 
     * @return returns the value of the "current_host_icon_image_expanded" column as string
     */
    public String Current_host_icon_image_expanded() {
        return this.getAsString("current_host_icon_image_expanded");
    }

    /**
     * Whether this host is currently in its check period (0/1)
     * 
     * @return returns the value of the "current_host_in_check_period" column as int
     */
    public int Current_host_in_check_period() throws NumberFormatException {
        return this.getAsInt("current_host_in_check_period");
    }

    /**
     * Whether this host is currently in its notification period (0/1)
     * 
     * @return returns the value of the "current_host_in_notification_period" column as int
     */
    public int Current_host_in_notification_period() throws NumberFormatException {
        return this.getAsInt("current_host_in_notification_period");
    }

    /**
     * Whether this host is currently in its service period (0/1)
     * 
     * @return returns the value of the "current_host_in_service_period" column as int
     */
    public int Current_host_in_service_period() throws NumberFormatException {
        return this.getAsInt("current_host_in_service_period");
    }

    /**
     * Initial host state
     * 
     * @return returns the value of the "current_host_initial_state" column as int
     */
    public int Current_host_initial_state() throws NumberFormatException {
        return this.getAsInt("current_host_initial_state");
    }

    /**
     * is there a host check currently running... (0/1)
     * 
     * @return returns the value of the "current_host_is_executing" column as int
     */
    public int Current_host_is_executing() throws NumberFormatException {
        return this.getAsInt("current_host_is_executing");
    }

    /**
     * Whether the host state is flapping (0/1)
     * 
     * @return returns the value of the "current_host_is_flapping" column as int
     */
    public int Current_host_is_flapping() throws NumberFormatException {
        return this.getAsInt("current_host_is_flapping");
    }

    /**
     * Time of the last check (Unix timestamp)
     * 
     * @return returns the value of the "current_host_last_check" column as time
     */
    public Date Current_host_last_check() throws NumberFormatException {
        return this.getAsTime("current_host_last_check");
    }

    /**
     * Last hard state
     * 
     * @return returns the value of the "current_host_last_hard_state" column as int
     */
    public int Current_host_last_hard_state() throws NumberFormatException {
        return this.getAsInt("current_host_last_hard_state");
    }

    /**
     * Time of the last hard state change (Unix timestamp)
     * 
     * @return returns the value of the "current_host_last_hard_state_change" column as time
     */
    public Date Current_host_last_hard_state_change() throws NumberFormatException {
        return this.getAsTime("current_host_last_hard_state_change");
    }

    /**
     * Time of the last notification (Unix timestamp)
     * 
     * @return returns the value of the "current_host_last_notification" column as time
     */
    public Date Current_host_last_notification() throws NumberFormatException {
        return this.getAsTime("current_host_last_notification");
    }

    /**
     * State before last state change
     * 
     * @return returns the value of the "current_host_last_state" column as int
     */
    public int Current_host_last_state() throws NumberFormatException {
        return this.getAsInt("current_host_last_state");
    }

    /**
     * Time of the last state change - soft or hard (Unix timestamp)
     * 
     * @return returns the value of the "current_host_last_state_change" column as time
     */
    public Date Current_host_last_state_change() throws NumberFormatException {
        return this.getAsTime("current_host_last_state_change");
    }

    /**
     * The last time the host was DOWN (Unix timestamp)
     * 
     * @return returns the value of the "current_host_last_time_down" column as time
     */
    public Date Current_host_last_time_down() throws NumberFormatException {
        return this.getAsTime("current_host_last_time_down");
    }

    /**
     * The last time the host was UNREACHABLE (Unix timestamp)
     * 
     * @return returns the value of the "current_host_last_time_unreachable" column as time
     */
    public Date Current_host_last_time_unreachable() throws NumberFormatException {
        return this.getAsTime("current_host_last_time_unreachable");
    }

    /**
     * The last time the host was UP (Unix timestamp)
     * 
     * @return returns the value of the "current_host_last_time_up" column as time
     */
    public Date Current_host_last_time_up() throws NumberFormatException {
        return this.getAsTime("current_host_last_time_up");
    }

    /**
     * Time difference between scheduled check time and actual check time
     * 
     * @return returns the value of the "current_host_latency" column as float
     */
    public float Current_host_latency() throws NumberFormatException {
        return this.getAsFloat("current_host_latency");
    }

    /**
     * Complete output from check plugin
     * 
     * @return returns the value of the "current_host_long_plugin_output" column as string
     */
    public String Current_host_long_plugin_output() {
        return this.getAsString("current_host_long_plugin_output");
    }

    /**
     * Low threshold of flap detection
     * 
     * @return returns the value of the "current_host_low_flap_threshold" column as float
     */
    public float Current_host_low_flap_threshold() throws NumberFormatException {
        return this.getAsFloat("current_host_low_flap_threshold");
    }

    /**
     * Max check attempts for active host checks
     * 
     * @return returns the value of the "current_host_max_check_attempts" column as int
     */
    public int Current_host_max_check_attempts() throws NumberFormatException {
        return this.getAsInt("current_host_max_check_attempts");
    }

    /**
     * A bitmask specifying which attributes have been modified
     * 
     * @return returns the value of the "current_host_modified_attributes" column as int
     */
    public int Current_host_modified_attributes() throws NumberFormatException {
        return this.getAsInt("current_host_modified_attributes");
    }

    /**
     * A list of all modified attributes
     * 
     * @return returns the value of the "current_host_modified_attributes_list" column as list
     */
    public String Current_host_modified_attributes_list() {
        return this.getAsList("current_host_modified_attributes_list");
    }

    /**
     * Host name
     * 
     * @return returns the value of the "current_host_name" column as string
     */
    public String Current_host_name() {
        return this.getAsString("current_host_name");
    }

    /**
     * Scheduled time for the next check (Unix timestamp)
     * 
     * @return returns the value of the "current_host_next_check" column as time
     */
    public Date Current_host_next_check() throws NumberFormatException {
        return this.getAsTime("current_host_next_check");
    }

    /**
     * Time of the next notification (Unix timestamp)
     * 
     * @return returns the value of the "current_host_next_notification" column as time
     */
    public Date Current_host_next_notification() throws NumberFormatException {
        return this.getAsTime("current_host_next_notification");
    }

    /**
     * Whether to stop sending notifications (0/1)
     * 
     * @return returns the value of the "current_host_no_more_notifications" column as int
     */
    public int Current_host_no_more_notifications() throws NumberFormatException {
        return this.getAsInt("current_host_no_more_notifications");
    }

    /**
     * Optional notes for this host
     * 
     * @return returns the value of the "current_host_notes" column as string
     */
    public String Current_host_notes() {
        return this.getAsString("current_host_notes");
    }

    /**
     * The same as notes, but with the most important macros expanded
     * 
     * @return returns the value of the "current_host_notes_expanded" column as string
     */
    public String Current_host_notes_expanded() {
        return this.getAsString("current_host_notes_expanded");
    }

    /**
     * An optional URL with further information about the host
     * 
     * @return returns the value of the "current_host_notes_url" column as string
     */
    public String Current_host_notes_url() {
        return this.getAsString("current_host_notes_url");
    }

    /**
     * Same es notes_url, but with the most important macros expanded
     * 
     * @return returns the value of the "current_host_notes_url_expanded" column as string
     */
    public String Current_host_notes_url_expanded() {
        return this.getAsString("current_host_notes_url_expanded");
    }

    /**
     * Interval of periodic notification or 0 if its off
     * 
     * @return returns the value of the "current_host_notification_interval" column as float
     */
    public float Current_host_notification_interval() throws NumberFormatException {
        return this.getAsFloat("current_host_notification_interval");
    }

    /**
     * Time period in which problems of this host will be notified. If empty then notification will be always
     * 
     * @return returns the value of the "current_host_notification_period" column as string
     */
    public String Current_host_notification_period() {
        return this.getAsString("current_host_notification_period");
    }

    /**
     * Whether notifications of the host are enabled (0/1)
     * 
     * @return returns the value of the "current_host_notifications_enabled" column as int
     */
    public int Current_host_notifications_enabled() throws NumberFormatException {
        return this.getAsInt("current_host_notifications_enabled");
    }

    /**
     * The total number of services of the host
     * 
     * @return returns the value of the "current_host_num_services" column as int
     */
    public int Current_host_num_services() throws NumberFormatException {
        return this.getAsInt("current_host_num_services");
    }

    /**
     * The number of the host's services with the soft state CRIT
     * 
     * @return returns the value of the "current_host_num_services_crit" column as int
     */
    public int Current_host_num_services_crit() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_crit");
    }

    /**
     * The number of the host's services with the hard state CRIT
     * 
     * @return returns the value of the "current_host_num_services_hard_crit" column as int
     */
    public int Current_host_num_services_hard_crit() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_hard_crit");
    }

    /**
     * The number of the host's services with the hard state OK
     * 
     * @return returns the value of the "current_host_num_services_hard_ok" column as int
     */
    public int Current_host_num_services_hard_ok() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_hard_ok");
    }

    /**
     * The number of the host's services with the hard state UNKNOWN
     * 
     * @return returns the value of the "current_host_num_services_hard_unknown" column as int
     */
    public int Current_host_num_services_hard_unknown() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_hard_unknown");
    }

    /**
     * The number of the host's services with the hard state WARN
     * 
     * @return returns the value of the "current_host_num_services_hard_warn" column as int
     */
    public int Current_host_num_services_hard_warn() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_hard_warn");
    }

    /**
     * The number of the host's services with the soft state OK
     * 
     * @return returns the value of the "current_host_num_services_ok" column as int
     */
    public int Current_host_num_services_ok() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_ok");
    }

    /**
     * The number of the host's services which have not been checked yet (pending)
     * 
     * @return returns the value of the "current_host_num_services_pending" column as int
     */
    public int Current_host_num_services_pending() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_pending");
    }

    /**
     * The number of the host's services with the soft state UNKNOWN
     * 
     * @return returns the value of the "current_host_num_services_unknown" column as int
     */
    public int Current_host_num_services_unknown() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_unknown");
    }

    /**
     * The number of the host's services with the soft state WARN
     * 
     * @return returns the value of the "current_host_num_services_warn" column as int
     */
    public int Current_host_num_services_warn() throws NumberFormatException {
        return this.getAsInt("current_host_num_services_warn");
    }

    /**
     * The current obsess_over_host setting... (0/1)
     * 
     * @return returns the value of the "current_host_obsess_over_host" column as int
     */
    public int Current_host_obsess_over_host() throws NumberFormatException {
        return this.getAsInt("current_host_obsess_over_host");
    }

    /**
     * A list of all direct parents of the host
     * 
     * @return returns the value of the "current_host_parents" column as list
     */
    public String Current_host_parents() {
        return this.getAsList("current_host_parents");
    }

    /**
     * Whether a flex downtime is pending (0/1)
     * 
     * @return returns the value of the "current_host_pending_flex_downtime" column as int
     */
    public int Current_host_pending_flex_downtime() throws NumberFormatException {
        return this.getAsInt("current_host_pending_flex_downtime");
    }

    /**
     * Percent state change
     * 
     * @return returns the value of the "current_host_percent_state_change" column as float
     */
    public float Current_host_percent_state_change() throws NumberFormatException {
        return this.getAsFloat("current_host_percent_state_change");
    }

    /**
     * Optional performance data of the last host check
     * 
     * @return returns the value of the "current_host_perf_data" column as string
     */
    public String Current_host_perf_data() {
        return this.getAsString("current_host_perf_data");
    }

    /**
     * Output of the last host check
     * 
     * @return returns the value of the "current_host_plugin_output" column as string
     */
    public String Current_host_plugin_output() {
        return this.getAsString("current_host_plugin_output");
    }

    /**
     * Whether there is a PNP4Nagios graph present for this host (0/1)
     * 
     * @return returns the value of the "current_host_pnpgraph_present" column as int
     */
    public int Current_host_pnpgraph_present() throws NumberFormatException {
        return this.getAsInt("current_host_pnpgraph_present");
    }

    /**
     * Whether processing of performance data is enabled (0/1)
     * 
     * @return returns the value of the "current_host_process_performance_data" column as int
     */
    public int Current_host_process_performance_data() throws NumberFormatException {
        return this.getAsInt("current_host_process_performance_data");
    }

    /**
     * Number of basic interval lengths between checks when retrying after a soft error
     * 
     * @return returns the value of the "current_host_retry_interval" column as float
     */
    public float Current_host_retry_interval() throws NumberFormatException {
        return this.getAsFloat("current_host_retry_interval");
    }

    /**
     * The number of downtimes this host is currently in
     * 
     * @return returns the value of the "current_host_scheduled_downtime_depth" column as int
     */
    public int Current_host_scheduled_downtime_depth() throws NumberFormatException {
        return this.getAsInt("current_host_scheduled_downtime_depth");
    }

    /**
     * The name of the service period of the host
     * 
     * @return returns the value of the "current_host_service_period" column as string
     */
    public String Current_host_service_period() {
        return this.getAsString("current_host_service_period");
    }

    /**
     * A list of all services of the host
     * 
     * @return returns the value of the "current_host_services" column as list
     */
    public String Current_host_services() {
        return this.getAsList("current_host_services");
    }

    /**
     * A list of all services including detailed information about each service
     * 
     * @return returns the value of the "current_host_services_with_info" column as list
     */
    public String Current_host_services_with_info() {
        return this.getAsList("current_host_services_with_info");
    }

    /**
     * A list of all services of the host together with state and has_been_checked
     * 
     * @return returns the value of the "current_host_services_with_state" column as list
     */
    public String Current_host_services_with_state() {
        return this.getAsList("current_host_services_with_state");
    }

    /**
     * Staleness indicator for this host
     * 
     * @return returns the value of the "current_host_staleness" column as float
     */
    public float Current_host_staleness() throws NumberFormatException {
        return this.getAsFloat("current_host_staleness");
    }

    /**
     * The current state of the host (0: up, 1: down, 2: unreachable)
     * 
     * @return returns the value of the "current_host_state" column as int
     */
    public int Current_host_state() throws NumberFormatException {
        return this.getAsInt("current_host_state");
    }

    /**
     * Type of the current state (0: soft, 1: hard)
     * 
     * @return returns the value of the "current_host_state_type" column as int
     */
    public int Current_host_state_type() throws NumberFormatException {
        return this.getAsInt("current_host_state_type");
    }

    /**
     * The name of in image file for the status map
     * 
     * @return returns the value of the "current_host_statusmap_image" column as string
     */
    public String Current_host_statusmap_image() {
        return this.getAsString("current_host_statusmap_image");
    }

    /**
     * The total number of services of the host
     * 
     * @return returns the value of the "current_host_total_services" column as int
     */
    public int Current_host_total_services() throws NumberFormatException {
        return this.getAsInt("current_host_total_services");
    }

    /**
     * The worst hard state of all of the host's services (OK <= WARN <= UNKNOWN <= CRIT)
     * 
     * @return returns the value of the "current_host_worst_service_hard_state" column as int
     */
    public int Current_host_worst_service_hard_state() throws NumberFormatException {
        return this.getAsInt("current_host_worst_service_hard_state");
    }

    /**
     * The worst soft state of all of the host's services (OK <= WARN <= UNKNOWN <= CRIT)
     * 
     * @return returns the value of the "current_host_worst_service_state" column as int
     */
    public int Current_host_worst_service_state() throws NumberFormatException {
        return this.getAsInt("current_host_worst_service_state");
    }

    /**
     * 3D-Coordinates: X
     * 
     * @return returns the value of the "current_host_x_3d" column as float
     */
    public float Current_host_x_3d() throws NumberFormatException {
        return this.getAsFloat("current_host_x_3d");
    }

    /**
     * 3D-Coordinates: Y
     * 
     * @return returns the value of the "current_host_y_3d" column as float
     */
    public float Current_host_y_3d() throws NumberFormatException {
        return this.getAsFloat("current_host_y_3d");
    }

    /**
     * 3D-Coordinates: Z
     * 
     * @return returns the value of the "current_host_z_3d" column as float
     */
    public float Current_host_z_3d() throws NumberFormatException {
        return this.getAsFloat("current_host_z_3d");
    }

    /**
     * Whether the service accepts passive checks (0/1)
     * 
     * @return returns the value of the "current_service_accept_passive_checks" column as int
     */
    public int Current_service_accept_passive_checks() throws NumberFormatException {
        return this.getAsInt("current_service_accept_passive_checks");
    }

    /**
     * Whether the current service problem has been acknowledged (0/1)
     * 
     * @return returns the value of the "current_service_acknowledged" column as int
     */
    public int Current_service_acknowledged() throws NumberFormatException {
        return this.getAsInt("current_service_acknowledged");
    }

    /**
     * The type of the acknownledgement (0: none, 1: normal, 2: sticky)
     * 
     * @return returns the value of the "current_service_acknowledgement_type" column as int
     */
    public int Current_service_acknowledgement_type() throws NumberFormatException {
        return this.getAsInt("current_service_acknowledgement_type");
    }

    /**
     * An optional URL for actions or custom information about the service
     * 
     * @return returns the value of the "current_service_action_url" column as string
     */
    public String Current_service_action_url() {
        return this.getAsString("current_service_action_url");
    }

    /**
     * The action_url with (the most important) macros expanded
     * 
     * @return returns the value of the "current_service_action_url_expanded" column as string
     */
    public String Current_service_action_url_expanded() {
        return this.getAsString("current_service_action_url_expanded");
    }

    /**
     * Whether active checks are enabled for the service (0/1)
     * 
     * @return returns the value of the "current_service_active_checks_enabled" column as int
     */
    public int Current_service_active_checks_enabled() throws NumberFormatException {
        return this.getAsInt("current_service_active_checks_enabled");
    }

    /**
     * Nagios command used for active checks
     * 
     * @return returns the value of the "current_service_check_command" column as string
     */
    public String Current_service_check_command() {
        return this.getAsString("current_service_check_command");
    }

    /**
     * Nagios command used for active checks with the macros expanded
     * 
     * @return returns the value of the "current_service_check_command_expanded" column as string
     */
    public String Current_service_check_command_expanded() {
        return this.getAsString("current_service_check_command_expanded");
    }

    /**
     * Whether freshness checks are activated (0/1)
     * 
     * @return returns the value of the "current_service_check_freshness" column as int
     */
    public int Current_service_check_freshness() throws NumberFormatException {
        return this.getAsInt("current_service_check_freshness");
    }

    /**
     * Number of basic interval lengths between two scheduled checks of the service
     * 
     * @return returns the value of the "current_service_check_interval" column as float
     */
    public float Current_service_check_interval() throws NumberFormatException {
        return this.getAsFloat("current_service_check_interval");
    }

    /**
     * The current check option, forced, normal, freshness... (0/1)
     * 
     * @return returns the value of the "current_service_check_options" column as int
     */
    public int Current_service_check_options() throws NumberFormatException {
        return this.getAsInt("current_service_check_options");
    }

    /**
     * The name of the check period of the service. It this is empty, the service is always checked.
     * 
     * @return returns the value of the "current_service_check_period" column as string
     */
    public String Current_service_check_period() {
        return this.getAsString("current_service_check_period");
    }

    /**
     * The type of the last check (0: active, 1: passive)
     * 
     * @return returns the value of the "current_service_check_type" column as int
     */
    public int Current_service_check_type() throws NumberFormatException {
        return this.getAsInt("current_service_check_type");
    }

    /**
     * Whether active checks are enabled for the service (0/1)
     * 
     * @return returns the value of the "current_service_checks_enabled" column as int
     */
    public int Current_service_checks_enabled() throws NumberFormatException {
        return this.getAsInt("current_service_checks_enabled");
    }

    /**
     * A list of all comment ids of the service
     * 
     * @return returns the value of the "current_service_comments" column as list
     */
    public String Current_service_comments() {
        return this.getAsList("current_service_comments");
    }

    /**
     * A list of all comments of the service with id, author, comment, entry type and entry time
     * 
     * @return returns the value of the "current_service_comments_with_extra_info" column as list
     */
    public String Current_service_comments_with_extra_info() {
        return this.getAsList("current_service_comments_with_extra_info");
    }

    /**
     * A list of all comments of the service with id, author and comment
     * 
     * @return returns the value of the "current_service_comments_with_info" column as list
     */
    public String Current_service_comments_with_info() {
        return this.getAsList("current_service_comments_with_info");
    }

    /**
     * A list of all contact groups this service is in
     * 
     * @return returns the value of the "current_service_contact_groups" column as list
     */
    public String Current_service_contact_groups() {
        return this.getAsList("current_service_contact_groups");
    }

    /**
     * A list of all contacts of the service, either direct or via a contact group
     * 
     * @return returns the value of the "current_service_contacts" column as list
     */
    public String Current_service_contacts() {
        return this.getAsList("current_service_contacts");
    }

    /**
     * The number of the current check attempt
     * 
     * @return returns the value of the "current_service_current_attempt" column as int
     */
    public int Current_service_current_attempt() throws NumberFormatException {
        return this.getAsInt("current_service_current_attempt");
    }

    /**
     * The number of the current notification
     * 
     * @return returns the value of the "current_service_current_notification_number" column as int
     */
    public int Current_service_current_notification_number() throws NumberFormatException {
        return this.getAsInt("current_service_current_notification_number");
    }

    /**
     * A list of the names of all custom variables of the service
     * 
     * @return returns the value of the "current_service_custom_variable_names" column as list
     */
    public String Current_service_custom_variable_names() {
        return this.getAsList("current_service_custom_variable_names");
    }

    /**
     * A list of the values of all custom variable of the service
     * 
     * @return returns the value of the "current_service_custom_variable_values" column as list
     */
    public String Current_service_custom_variable_values() {
        return this.getAsList("current_service_custom_variable_values");
    }

    /**
     * A dictionary of the custom variables
     * 
     * @return returns the value of the "current_service_custom_variables" column as dict
     */
    public String Current_service_custom_variables() {
        return this.getAsDict("current_service_custom_variables");
    }

    /**
     * Description of the service (also used as key)
     * 
     * @return returns the value of the "current_service_description" column as string
     */
    public String Current_service_description() {
        return this.getAsString("current_service_description");
    }

    /**
     * An optional display name (not used by Nagios standard web pages)
     * 
     * @return returns the value of the "current_service_display_name" column as string
     */
    public String Current_service_display_name() {
        return this.getAsString("current_service_display_name");
    }

    /**
     * A list of all downtime ids of the service
     * 
     * @return returns the value of the "current_service_downtimes" column as list
     */
    public String Current_service_downtimes() {
        return this.getAsList("current_service_downtimes");
    }

    /**
     * A list of all downtimes of the service with id, author and comment
     * 
     * @return returns the value of the "current_service_downtimes_with_info" column as list
     */
    public String Current_service_downtimes_with_info() {
        return this.getAsList("current_service_downtimes_with_info");
    }

    /**
     * Nagios command used as event handler
     * 
     * @return returns the value of the "current_service_event_handler" column as string
     */
    public String Current_service_event_handler() {
        return this.getAsString("current_service_event_handler");
    }

    /**
     * Whether and event handler is activated for the service (0/1)
     * 
     * @return returns the value of the "current_service_event_handler_enabled" column as int
     */
    public int Current_service_event_handler_enabled() throws NumberFormatException {
        return this.getAsInt("current_service_event_handler_enabled");
    }

    /**
     * Time the service check needed for execution
     * 
     * @return returns the value of the "current_service_execution_time" column as float
     */
    public float Current_service_execution_time() throws NumberFormatException {
        return this.getAsFloat("current_service_execution_time");
    }

    /**
     * Delay before the first notification
     * 
     * @return returns the value of the "current_service_first_notification_delay" column as float
     */
    public float Current_service_first_notification_delay() throws NumberFormatException {
        return this.getAsFloat("current_service_first_notification_delay");
    }

    /**
     * Whether flap detection is enabled for the service (0/1)
     * 
     * @return returns the value of the "current_service_flap_detection_enabled" column as int
     */
    public int Current_service_flap_detection_enabled() throws NumberFormatException {
        return this.getAsInt("current_service_flap_detection_enabled");
    }

    /**
     * A list of all service groups the service is in
     * 
     * @return returns the value of the "current_service_groups" column as list
     */
    public String Current_service_groups() {
        return this.getAsList("current_service_groups");
    }

    /**
     * Whether the service already has been checked (0/1)
     * 
     * @return returns the value of the "current_service_has_been_checked" column as int
     */
    public int Current_service_has_been_checked() throws NumberFormatException {
        return this.getAsInt("current_service_has_been_checked");
    }

    /**
     * High threshold of flap detection
     * 
     * @return returns the value of the "current_service_high_flap_threshold" column as float
     */
    public float Current_service_high_flap_threshold() throws NumberFormatException {
        return this.getAsFloat("current_service_high_flap_threshold");
    }

    /**
     * The name of an image to be used as icon in the web interface
     * 
     * @return returns the value of the "current_service_icon_image" column as string
     */
    public String Current_service_icon_image() {
        return this.getAsString("current_service_icon_image");
    }

    /**
     * An alternative text for the icon_image for browsers not displaying icons
     * 
     * @return returns the value of the "current_service_icon_image_alt" column as string
     */
    public String Current_service_icon_image_alt() {
        return this.getAsString("current_service_icon_image_alt");
    }

    /**
     * The icon_image with (the most important) macros expanded
     * 
     * @return returns the value of the "current_service_icon_image_expanded" column as string
     */
    public String Current_service_icon_image_expanded() {
        return this.getAsString("current_service_icon_image_expanded");
    }

    /**
     * Whether the service is currently in its check period (0/1)
     * 
     * @return returns the value of the "current_service_in_check_period" column as int
     */
    public int Current_service_in_check_period() throws NumberFormatException {
        return this.getAsInt("current_service_in_check_period");
    }

    /**
     * Whether the service is currently in its notification period (0/1)
     * 
     * @return returns the value of the "current_service_in_notification_period" column as int
     */
    public int Current_service_in_notification_period() throws NumberFormatException {
        return this.getAsInt("current_service_in_notification_period");
    }

    /**
     * Whether this service is currently in its service period (0/1)
     * 
     * @return returns the value of the "current_service_in_service_period" column as int
     */
    public int Current_service_in_service_period() throws NumberFormatException {
        return this.getAsInt("current_service_in_service_period");
    }

    /**
     * The initial state of the service
     * 
     * @return returns the value of the "current_service_initial_state" column as int
     */
    public int Current_service_initial_state() throws NumberFormatException {
        return this.getAsInt("current_service_initial_state");
    }

    /**
     * is there a service check currently running... (0/1)
     * 
     * @return returns the value of the "current_service_is_executing" column as int
     */
    public int Current_service_is_executing() throws NumberFormatException {
        return this.getAsInt("current_service_is_executing");
    }

    /**
     * Whether the service is flapping (0/1)
     * 
     * @return returns the value of the "current_service_is_flapping" column as int
     */
    public int Current_service_is_flapping() throws NumberFormatException {
        return this.getAsInt("current_service_is_flapping");
    }

    /**
     * The time of the last check (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_check" column as time
     */
    public Date Current_service_last_check() throws NumberFormatException {
        return this.getAsTime("current_service_last_check");
    }

    /**
     * The last hard state of the service
     * 
     * @return returns the value of the "current_service_last_hard_state" column as int
     */
    public int Current_service_last_hard_state() throws NumberFormatException {
        return this.getAsInt("current_service_last_hard_state");
    }

    /**
     * The time of the last hard state change (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_hard_state_change" column as time
     */
    public Date Current_service_last_hard_state_change() throws NumberFormatException {
        return this.getAsTime("current_service_last_hard_state_change");
    }

    /**
     * The time of the last notification (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_notification" column as time
     */
    public Date Current_service_last_notification() throws NumberFormatException {
        return this.getAsTime("current_service_last_notification");
    }

    /**
     * The last state of the service
     * 
     * @return returns the value of the "current_service_last_state" column as int
     */
    public int Current_service_last_state() throws NumberFormatException {
        return this.getAsInt("current_service_last_state");
    }

    /**
     * The time of the last state change (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_state_change" column as time
     */
    public Date Current_service_last_state_change() throws NumberFormatException {
        return this.getAsTime("current_service_last_state_change");
    }

    /**
     * The last time the service was CRITICAL (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_time_critical" column as time
     */
    public Date Current_service_last_time_critical() throws NumberFormatException {
        return this.getAsTime("current_service_last_time_critical");
    }

    /**
     * The last time the service was OK (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_time_ok" column as time
     */
    public Date Current_service_last_time_ok() throws NumberFormatException {
        return this.getAsTime("current_service_last_time_ok");
    }

    /**
     * The last time the service was UNKNOWN (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_time_unknown" column as time
     */
    public Date Current_service_last_time_unknown() throws NumberFormatException {
        return this.getAsTime("current_service_last_time_unknown");
    }

    /**
     * The last time the service was in WARNING state (Unix timestamp)
     * 
     * @return returns the value of the "current_service_last_time_warning" column as time
     */
    public Date Current_service_last_time_warning() throws NumberFormatException {
        return this.getAsTime("current_service_last_time_warning");
    }

    /**
     * Time difference between scheduled check time and actual check time
     * 
     * @return returns the value of the "current_service_latency" column as float
     */
    public float Current_service_latency() throws NumberFormatException {
        return this.getAsFloat("current_service_latency");
    }

    /**
     * Unabbreviated output of the last check plugin
     * 
     * @return returns the value of the "current_service_long_plugin_output" column as string
     */
    public String Current_service_long_plugin_output() {
        return this.getAsString("current_service_long_plugin_output");
    }

    /**
     * Low threshold of flap detection
     * 
     * @return returns the value of the "current_service_low_flap_threshold" column as float
     */
    public float Current_service_low_flap_threshold() throws NumberFormatException {
        return this.getAsFloat("current_service_low_flap_threshold");
    }

    /**
     * The maximum number of check attempts
     * 
     * @return returns the value of the "current_service_max_check_attempts" column as int
     */
    public int Current_service_max_check_attempts() throws NumberFormatException {
        return this.getAsInt("current_service_max_check_attempts");
    }

    /**
     * A bitmask specifying which attributes have been modified
     * 
     * @return returns the value of the "current_service_modified_attributes" column as int
     */
    public int Current_service_modified_attributes() throws NumberFormatException {
        return this.getAsInt("current_service_modified_attributes");
    }

    /**
     * A list of all modified attributes
     * 
     * @return returns the value of the "current_service_modified_attributes_list" column as list
     */
    public String Current_service_modified_attributes_list() {
        return this.getAsList("current_service_modified_attributes_list");
    }

    /**
     * The scheduled time of the next check (Unix timestamp)
     * 
     * @return returns the value of the "current_service_next_check" column as time
     */
    public Date Current_service_next_check() throws NumberFormatException {
        return this.getAsTime("current_service_next_check");
    }

    /**
     * The time of the next notification (Unix timestamp)
     * 
     * @return returns the value of the "current_service_next_notification" column as time
     */
    public Date Current_service_next_notification() throws NumberFormatException {
        return this.getAsTime("current_service_next_notification");
    }

    /**
     * Whether to stop sending notifications (0/1)
     * 
     * @return returns the value of the "current_service_no_more_notifications" column as int
     */
    public int Current_service_no_more_notifications() throws NumberFormatException {
        return this.getAsInt("current_service_no_more_notifications");
    }

    /**
     * Optional notes about the service
     * 
     * @return returns the value of the "current_service_notes" column as string
     */
    public String Current_service_notes() {
        return this.getAsString("current_service_notes");
    }

    /**
     * The notes with (the most important) macros expanded
     * 
     * @return returns the value of the "current_service_notes_expanded" column as string
     */
    public String Current_service_notes_expanded() {
        return this.getAsString("current_service_notes_expanded");
    }

    /**
     * An optional URL for additional notes about the service
     * 
     * @return returns the value of the "current_service_notes_url" column as string
     */
    public String Current_service_notes_url() {
        return this.getAsString("current_service_notes_url");
    }

    /**
     * The notes_url with (the most important) macros expanded
     * 
     * @return returns the value of the "current_service_notes_url_expanded" column as string
     */
    public String Current_service_notes_url_expanded() {
        return this.getAsString("current_service_notes_url_expanded");
    }

    /**
     * Interval of periodic notification or 0 if its off
     * 
     * @return returns the value of the "current_service_notification_interval" column as float
     */
    public float Current_service_notification_interval() throws NumberFormatException {
        return this.getAsFloat("current_service_notification_interval");
    }

    /**
     * The name of the notification period of the service. It this is empty, service problems are always notified.
     * 
     * @return returns the value of the "current_service_notification_period" column as string
     */
    public String Current_service_notification_period() {
        return this.getAsString("current_service_notification_period");
    }

    /**
     * Whether notifications are enabled for the service (0/1)
     * 
     * @return returns the value of the "current_service_notifications_enabled" column as int
     */
    public int Current_service_notifications_enabled() throws NumberFormatException {
        return this.getAsInt("current_service_notifications_enabled");
    }

    /**
     * Whether 'obsess_over_service' is enabled for the service (0/1)
     * 
     * @return returns the value of the "current_service_obsess_over_service" column as int
     */
    public int Current_service_obsess_over_service() throws NumberFormatException {
        return this.getAsInt("current_service_obsess_over_service");
    }

    /**
     * Percent state change
     * 
     * @return returns the value of the "current_service_percent_state_change" column as float
     */
    public float Current_service_percent_state_change() throws NumberFormatException {
        return this.getAsFloat("current_service_percent_state_change");
    }

    /**
     * Performance data of the last check plugin
     * 
     * @return returns the value of the "current_service_perf_data" column as string
     */
    public String Current_service_perf_data() {
        return this.getAsString("current_service_perf_data");
    }

    /**
     * Output of the last check plugin
     * 
     * @return returns the value of the "current_service_plugin_output" column as string
     */
    public String Current_service_plugin_output() {
        return this.getAsString("current_service_plugin_output");
    }

    /**
     * Whether there is a PNP4Nagios graph present for this service (0/1)
     * 
     * @return returns the value of the "current_service_pnpgraph_present" column as int
     */
    public int Current_service_pnpgraph_present() throws NumberFormatException {
        return this.getAsInt("current_service_pnpgraph_present");
    }

    /**
     * Whether processing of performance data is enabled for the service (0/1)
     * 
     * @return returns the value of the "current_service_process_performance_data" column as int
     */
    public int Current_service_process_performance_data() throws NumberFormatException {
        return this.getAsInt("current_service_process_performance_data");
    }

    /**
     * Number of basic interval lengths between checks when retrying after a soft error
     * 
     * @return returns the value of the "current_service_retry_interval" column as float
     */
    public float Current_service_retry_interval() throws NumberFormatException {
        return this.getAsFloat("current_service_retry_interval");
    }

    /**
     * The number of scheduled downtimes the service is currently in
     * 
     * @return returns the value of the "current_service_scheduled_downtime_depth" column as int
     */
    public int Current_service_scheduled_downtime_depth() throws NumberFormatException {
        return this.getAsInt("current_service_scheduled_downtime_depth");
    }

    /**
     * The name of the service period of the service
     * 
     * @return returns the value of the "current_service_service_period" column as string
     */
    public String Current_service_service_period() {
        return this.getAsString("current_service_service_period");
    }

    /**
     * The staleness indicator for this service
     * 
     * @return returns the value of the "current_service_staleness" column as float
     */
    public float Current_service_staleness() throws NumberFormatException {
        return this.getAsFloat("current_service_staleness");
    }

    /**
     * The current state of the service (0: OK, 1: WARN, 2: CRITICAL, 3: UNKNOWN)
     * 
     * @return returns the value of the "current_service_state" column as int
     */
    public int Current_service_state() throws NumberFormatException {
        return this.getAsInt("current_service_state");
    }

    /**
     * The type of the current state (0: soft, 1: hard)
     * 
     * @return returns the value of the "current_service_state_type" column as int
     */
    public int Current_service_state_type() throws NumberFormatException {
        return this.getAsInt("current_service_state_type");
    }

    /**
     * The name of the host the log entry is about (might be empty)
     * 
     * @return returns the value of the "host_name" column as string
     */
    public String Host_name() {
        return this.getAsString("host_name");
    }

    /**
     * The number of the line in the log file
     * 
     * @return returns the value of the "lineno" column as int
     */
    public int Lineno() throws NumberFormatException {
        return this.getAsInt("lineno");
    }

    /**
     * The complete message line including the timestamp
     * 
     * @return returns the value of the "message" column as string
     */
    public String Message() {
        return this.getAsString("message");
    }

    /**
     * The part of the message after the ':'
     * 
     * @return returns the value of the "options" column as string
     */
    public String Options() {
        return this.getAsString("options");
    }

    /**
     * The output of the check, if any is associated with the message
     * 
     * @return returns the value of the "plugin_output" column as string
     */
    public String Plugin_output() {
        return this.getAsString("plugin_output");
    }

    /**
     * The description of the service log entry is about (might be empty)
     * 
     * @return returns the value of the "service_description" column as string
     */
    public String Service_description() {
        return this.getAsString("service_description");
    }

    /**
     * The state of the host or service in question
     * 
     * @return returns the value of the "state" column as int
     */
    public int State() throws NumberFormatException {
        return this.getAsInt("state");
    }

    /**
     * The type of the state (varies on different log classes)
     * 
     * @return returns the value of the "state_type" column as string
     */
    public String State_type() {
        return this.getAsString("state_type");
    }

    /**
     * Time of the log event (UNIX timestamp)
     * 
     * @return returns the value of the "time" column as time
     */
    public Date Time() throws NumberFormatException {
        return this.getAsTime("time");
    }

    /**
     * The type of the message (text before the colon), the message itself for info messages
     * 
     * @return returns the value of the "type" column as string
     */
    public String Type() {
        return this.getAsString("type");
    }

    /**
     * create the map for all columns of table Log. Key=column name, Value=column value
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

        this.addToHashtable("attempt", this.getAsString(this.Attempt()));
        this.addToHashtable("class", this.getAsString(this.Class()));
        this.addToHashtable("command_name", this.Command_name());
        this.addToHashtable("comment", this.Comment());
        this.addToHashtable("contact_name", this.Contact_name());
        this.addToHashtable("current_command_line", this.Current_command_line());
        this.addToHashtable("current_command_name", this.Current_command_name());
        this.addToHashtable("current_contact_address1", this.Current_contact_address1());
        this.addToHashtable("current_contact_address2", this.Current_contact_address2());
        this.addToHashtable("current_contact_address3", this.Current_contact_address3());
        this.addToHashtable("current_contact_address4", this.Current_contact_address4());
        this.addToHashtable("current_contact_address5", this.Current_contact_address5());
        this.addToHashtable("current_contact_address6", this.Current_contact_address6());
        this.addToHashtable("current_contact_alias", this.Current_contact_alias());
        this.addToHashtable("current_contact_can_submit_commands", this.getAsString(this.Current_contact_can_submit_commands()));
        this.addToHashtable("current_contact_custom_variable_names", this.Current_contact_custom_variable_names());
        this.addToHashtable("current_contact_custom_variable_values", this.Current_contact_custom_variable_values());
        this.addToHashtable("current_contact_custom_variables", this.Current_contact_custom_variables());
        this.addToHashtable("current_contact_email", this.Current_contact_email());
        this.addToHashtable("current_contact_host_notification_period", this.Current_contact_host_notification_period());
        this.addToHashtable("current_contact_host_notifications_enabled", this.getAsString(this.Current_contact_host_notifications_enabled()));
        this.addToHashtable("current_contact_in_host_notification_period", this.getAsString(this.Current_contact_in_host_notification_period()));
        this.addToHashtable("current_contact_in_service_notification_period", this.getAsString(this.Current_contact_in_service_notification_period()));
        this.addToHashtable("current_contact_modified_attributes", this.getAsString(this.Current_contact_modified_attributes()));
        this.addToHashtable("current_contact_modified_attributes_list", this.Current_contact_modified_attributes_list());
        this.addToHashtable("current_contact_name", this.Current_contact_name());
        this.addToHashtable("current_contact_pager", this.Current_contact_pager());
        this.addToHashtable("current_contact_service_notification_period", this.Current_contact_service_notification_period());
        this.addToHashtable("current_contact_service_notifications_enabled", this.getAsString(this.Current_contact_service_notifications_enabled()));
        this.addToHashtable("current_host_accept_passive_checks", this.getAsString(this.Current_host_accept_passive_checks()));
        this.addToHashtable("current_host_acknowledged", this.getAsString(this.Current_host_acknowledged()));
        this.addToHashtable("current_host_acknowledgement_type", this.getAsString(this.Current_host_acknowledgement_type()));
        this.addToHashtable("current_host_action_url", this.Current_host_action_url());
        this.addToHashtable("current_host_action_url_expanded", this.Current_host_action_url_expanded());
        this.addToHashtable("current_host_active_checks_enabled", this.getAsString(this.Current_host_active_checks_enabled()));
        this.addToHashtable("current_host_address", this.Current_host_address());
        this.addToHashtable("current_host_alias", this.Current_host_alias());
        this.addToHashtable("current_host_check_command", this.Current_host_check_command());
        this.addToHashtable("current_host_check_command_expanded", this.Current_host_check_command_expanded());
        this.addToHashtable("current_host_check_flapping_recovery_notification", this.getAsString(this.Current_host_check_flapping_recovery_notification()));
        this.addToHashtable("current_host_check_freshness", this.getAsString(this.Current_host_check_freshness()));
        this.addToHashtable("current_host_check_interval", this.getAsString(this.Current_host_check_interval()));
        this.addToHashtable("current_host_check_options", this.getAsString(this.Current_host_check_options()));
        this.addToHashtable("current_host_check_period", this.Current_host_check_period());
        this.addToHashtable("current_host_check_type", this.getAsString(this.Current_host_check_type()));
        this.addToHashtable("current_host_checks_enabled", this.getAsString(this.Current_host_checks_enabled()));
        this.addToHashtable("current_host_childs", this.Current_host_childs());
        this.addToHashtable("current_host_comments", this.Current_host_comments());
        this.addToHashtable("current_host_comments_with_extra_info", this.Current_host_comments_with_extra_info());
        this.addToHashtable("current_host_comments_with_info", this.Current_host_comments_with_info());
        this.addToHashtable("current_host_contact_groups", this.Current_host_contact_groups());
        this.addToHashtable("current_host_contacts", this.Current_host_contacts());
        this.addToHashtable("current_host_current_attempt", this.getAsString(this.Current_host_current_attempt()));
        this.addToHashtable("current_host_current_notification_number", this.getAsString(this.Current_host_current_notification_number()));
        this.addToHashtable("current_host_custom_variable_names", this.Current_host_custom_variable_names());
        this.addToHashtable("current_host_custom_variable_values", this.Current_host_custom_variable_values());
        this.addToHashtable("current_host_custom_variables", this.Current_host_custom_variables());
        this.addToHashtable("current_host_display_name", this.Current_host_display_name());
        this.addToHashtable("current_host_downtimes", this.Current_host_downtimes());
        this.addToHashtable("current_host_downtimes_with_info", this.Current_host_downtimes_with_info());
        this.addToHashtable("current_host_event_handler", this.Current_host_event_handler());
        this.addToHashtable("current_host_event_handler_enabled", this.getAsString(this.Current_host_event_handler_enabled()));
        this.addToHashtable("current_host_execution_time", this.getAsString(this.Current_host_execution_time()));
        this.addToHashtable("current_host_filename", this.Current_host_filename());
        this.addToHashtable("current_host_first_notification_delay", this.getAsString(this.Current_host_first_notification_delay()));
        this.addToHashtable("current_host_flap_detection_enabled", this.getAsString(this.Current_host_flap_detection_enabled()));
        this.addToHashtable("current_host_groups", this.Current_host_groups());
        this.addToHashtable("current_host_hard_state", this.getAsString(this.Current_host_hard_state()));
        this.addToHashtable("current_host_has_been_checked", this.getAsString(this.Current_host_has_been_checked()));
        this.addToHashtable("current_host_high_flap_threshold", this.getAsString(this.Current_host_high_flap_threshold()));
        this.addToHashtable("current_host_icon_image", this.Current_host_icon_image());
        this.addToHashtable("current_host_icon_image_alt", this.Current_host_icon_image_alt());
        this.addToHashtable("current_host_icon_image_expanded", this.Current_host_icon_image_expanded());
        this.addToHashtable("current_host_in_check_period", this.getAsString(this.Current_host_in_check_period()));
        this.addToHashtable("current_host_in_notification_period", this.getAsString(this.Current_host_in_notification_period()));
        this.addToHashtable("current_host_in_service_period", this.getAsString(this.Current_host_in_service_period()));
        this.addToHashtable("current_host_initial_state", this.getAsString(this.Current_host_initial_state()));
        this.addToHashtable("current_host_is_executing", this.getAsString(this.Current_host_is_executing()));
        this.addToHashtable("current_host_is_flapping", this.getAsString(this.Current_host_is_flapping()));
        this.addToHashtable("current_host_last_check", this.getAsString(this.Current_host_last_check()));
        this.addToHashtable("current_host_last_hard_state", this.getAsString(this.Current_host_last_hard_state()));
        this.addToHashtable("current_host_last_hard_state_change", this.getAsString(this.Current_host_last_hard_state_change()));
        this.addToHashtable("current_host_last_notification", this.getAsString(this.Current_host_last_notification()));
        this.addToHashtable("current_host_last_state", this.getAsString(this.Current_host_last_state()));
        this.addToHashtable("current_host_last_state_change", this.getAsString(this.Current_host_last_state_change()));
        this.addToHashtable("current_host_last_time_down", this.getAsString(this.Current_host_last_time_down()));
        this.addToHashtable("current_host_last_time_unreachable", this.getAsString(this.Current_host_last_time_unreachable()));
        this.addToHashtable("current_host_last_time_up", this.getAsString(this.Current_host_last_time_up()));
        this.addToHashtable("current_host_latency", this.getAsString(this.Current_host_latency()));
        this.addToHashtable("current_host_long_plugin_output", this.Current_host_long_plugin_output());
        this.addToHashtable("current_host_low_flap_threshold", this.getAsString(this.Current_host_low_flap_threshold()));
        this.addToHashtable("current_host_max_check_attempts", this.getAsString(this.Current_host_max_check_attempts()));
        this.addToHashtable("current_host_modified_attributes", this.getAsString(this.Current_host_modified_attributes()));
        this.addToHashtable("current_host_modified_attributes_list", this.Current_host_modified_attributes_list());
        this.addToHashtable("current_host_name", this.Current_host_name());
        this.addToHashtable("current_host_next_check", this.getAsString(this.Current_host_next_check()));
        this.addToHashtable("current_host_next_notification", this.getAsString(this.Current_host_next_notification()));
        this.addToHashtable("current_host_no_more_notifications", this.getAsString(this.Current_host_no_more_notifications()));
        this.addToHashtable("current_host_notes", this.Current_host_notes());
        this.addToHashtable("current_host_notes_expanded", this.Current_host_notes_expanded());
        this.addToHashtable("current_host_notes_url", this.Current_host_notes_url());
        this.addToHashtable("current_host_notes_url_expanded", this.Current_host_notes_url_expanded());
        this.addToHashtable("current_host_notification_interval", this.getAsString(this.Current_host_notification_interval()));
        this.addToHashtable("current_host_notification_period", this.Current_host_notification_period());
        this.addToHashtable("current_host_notifications_enabled", this.getAsString(this.Current_host_notifications_enabled()));
        this.addToHashtable("current_host_num_services", this.getAsString(this.Current_host_num_services()));
        this.addToHashtable("current_host_num_services_crit", this.getAsString(this.Current_host_num_services_crit()));
        this.addToHashtable("current_host_num_services_hard_crit", this.getAsString(this.Current_host_num_services_hard_crit()));
        this.addToHashtable("current_host_num_services_hard_ok", this.getAsString(this.Current_host_num_services_hard_ok()));
        this.addToHashtable("current_host_num_services_hard_unknown", this.getAsString(this.Current_host_num_services_hard_unknown()));
        this.addToHashtable("current_host_num_services_hard_warn", this.getAsString(this.Current_host_num_services_hard_warn()));
        this.addToHashtable("current_host_num_services_ok", this.getAsString(this.Current_host_num_services_ok()));
        this.addToHashtable("current_host_num_services_pending", this.getAsString(this.Current_host_num_services_pending()));
        this.addToHashtable("current_host_num_services_unknown", this.getAsString(this.Current_host_num_services_unknown()));
        this.addToHashtable("current_host_num_services_warn", this.getAsString(this.Current_host_num_services_warn()));
        this.addToHashtable("current_host_obsess_over_host", this.getAsString(this.Current_host_obsess_over_host()));
        this.addToHashtable("current_host_parents", this.Current_host_parents());
        this.addToHashtable("current_host_pending_flex_downtime", this.getAsString(this.Current_host_pending_flex_downtime()));
        this.addToHashtable("current_host_percent_state_change", this.getAsString(this.Current_host_percent_state_change()));
        this.addToHashtable("current_host_perf_data", this.Current_host_perf_data());
        this.addToHashtable("current_host_plugin_output", this.Current_host_plugin_output());
        this.addToHashtable("current_host_pnpgraph_present", this.getAsString(this.Current_host_pnpgraph_present()));
        this.addToHashtable("current_host_process_performance_data", this.getAsString(this.Current_host_process_performance_data()));
        this.addToHashtable("current_host_retry_interval", this.getAsString(this.Current_host_retry_interval()));
        this.addToHashtable("current_host_scheduled_downtime_depth", this.getAsString(this.Current_host_scheduled_downtime_depth()));
        this.addToHashtable("current_host_service_period", this.Current_host_service_period());
        this.addToHashtable("current_host_services", this.Current_host_services());
        this.addToHashtable("current_host_services_with_info", this.Current_host_services_with_info());
        this.addToHashtable("current_host_services_with_state", this.Current_host_services_with_state());
        this.addToHashtable("current_host_staleness", this.getAsString(this.Current_host_staleness()));
        this.addToHashtable("current_host_state", this.getAsString(this.Current_host_state()));
        this.addToHashtable("current_host_state_type", this.getAsString(this.Current_host_state_type()));
        this.addToHashtable("current_host_statusmap_image", this.Current_host_statusmap_image());
        this.addToHashtable("current_host_total_services", this.getAsString(this.Current_host_total_services()));
        this.addToHashtable("current_host_worst_service_hard_state", this.getAsString(this.Current_host_worst_service_hard_state()));
        this.addToHashtable("current_host_worst_service_state", this.getAsString(this.Current_host_worst_service_state()));
        this.addToHashtable("current_host_x_3d", this.getAsString(this.Current_host_x_3d()));
        this.addToHashtable("current_host_y_3d", this.getAsString(this.Current_host_y_3d()));
        this.addToHashtable("current_host_z_3d", this.getAsString(this.Current_host_z_3d()));
        this.addToHashtable("current_service_accept_passive_checks", this.getAsString(this.Current_service_accept_passive_checks()));
        this.addToHashtable("current_service_acknowledged", this.getAsString(this.Current_service_acknowledged()));
        this.addToHashtable("current_service_acknowledgement_type", this.getAsString(this.Current_service_acknowledgement_type()));
        this.addToHashtable("current_service_action_url", this.Current_service_action_url());
        this.addToHashtable("current_service_action_url_expanded", this.Current_service_action_url_expanded());
        this.addToHashtable("current_service_active_checks_enabled", this.getAsString(this.Current_service_active_checks_enabled()));
        this.addToHashtable("current_service_check_command", this.Current_service_check_command());
        this.addToHashtable("current_service_check_command_expanded", this.Current_service_check_command_expanded());
        this.addToHashtable("current_service_check_freshness", this.getAsString(this.Current_service_check_freshness()));
        this.addToHashtable("current_service_check_interval", this.getAsString(this.Current_service_check_interval()));
        this.addToHashtable("current_service_check_options", this.getAsString(this.Current_service_check_options()));
        this.addToHashtable("current_service_check_period", this.Current_service_check_period());
        this.addToHashtable("current_service_check_type", this.getAsString(this.Current_service_check_type()));
        this.addToHashtable("current_service_checks_enabled", this.getAsString(this.Current_service_checks_enabled()));
        this.addToHashtable("current_service_comments", this.Current_service_comments());
        this.addToHashtable("current_service_comments_with_extra_info", this.Current_service_comments_with_extra_info());
        this.addToHashtable("current_service_comments_with_info", this.Current_service_comments_with_info());
        this.addToHashtable("current_service_contact_groups", this.Current_service_contact_groups());
        this.addToHashtable("current_service_contacts", this.Current_service_contacts());
        this.addToHashtable("current_service_current_attempt", this.getAsString(this.Current_service_current_attempt()));
        this.addToHashtable("current_service_current_notification_number", this.getAsString(this.Current_service_current_notification_number()));
        this.addToHashtable("current_service_custom_variable_names", this.Current_service_custom_variable_names());
        this.addToHashtable("current_service_custom_variable_values", this.Current_service_custom_variable_values());
        this.addToHashtable("current_service_custom_variables", this.Current_service_custom_variables());
        this.addToHashtable("current_service_description", this.Current_service_description());
        this.addToHashtable("current_service_display_name", this.Current_service_display_name());
        this.addToHashtable("current_service_downtimes", this.Current_service_downtimes());
        this.addToHashtable("current_service_downtimes_with_info", this.Current_service_downtimes_with_info());
        this.addToHashtable("current_service_event_handler", this.Current_service_event_handler());
        this.addToHashtable("current_service_event_handler_enabled", this.getAsString(this.Current_service_event_handler_enabled()));
        this.addToHashtable("current_service_execution_time", this.getAsString(this.Current_service_execution_time()));
        this.addToHashtable("current_service_first_notification_delay", this.getAsString(this.Current_service_first_notification_delay()));
        this.addToHashtable("current_service_flap_detection_enabled", this.getAsString(this.Current_service_flap_detection_enabled()));
        this.addToHashtable("current_service_groups", this.Current_service_groups());
        this.addToHashtable("current_service_has_been_checked", this.getAsString(this.Current_service_has_been_checked()));
        this.addToHashtable("current_service_high_flap_threshold", this.getAsString(this.Current_service_high_flap_threshold()));
        this.addToHashtable("current_service_icon_image", this.Current_service_icon_image());
        this.addToHashtable("current_service_icon_image_alt", this.Current_service_icon_image_alt());
        this.addToHashtable("current_service_icon_image_expanded", this.Current_service_icon_image_expanded());
        this.addToHashtable("current_service_in_check_period", this.getAsString(this.Current_service_in_check_period()));
        this.addToHashtable("current_service_in_notification_period", this.getAsString(this.Current_service_in_notification_period()));
        this.addToHashtable("current_service_in_service_period", this.getAsString(this.Current_service_in_service_period()));
        this.addToHashtable("current_service_initial_state", this.getAsString(this.Current_service_initial_state()));
        this.addToHashtable("current_service_is_executing", this.getAsString(this.Current_service_is_executing()));
        this.addToHashtable("current_service_is_flapping", this.getAsString(this.Current_service_is_flapping()));
        this.addToHashtable("current_service_last_check", this.getAsString(this.Current_service_last_check()));
        this.addToHashtable("current_service_last_hard_state", this.getAsString(this.Current_service_last_hard_state()));
        this.addToHashtable("current_service_last_hard_state_change", this.getAsString(this.Current_service_last_hard_state_change()));
        this.addToHashtable("current_service_last_notification", this.getAsString(this.Current_service_last_notification()));
        this.addToHashtable("current_service_last_state", this.getAsString(this.Current_service_last_state()));
        this.addToHashtable("current_service_last_state_change", this.getAsString(this.Current_service_last_state_change()));
        this.addToHashtable("current_service_last_time_critical", this.getAsString(this.Current_service_last_time_critical()));
        this.addToHashtable("current_service_last_time_ok", this.getAsString(this.Current_service_last_time_ok()));
        this.addToHashtable("current_service_last_time_unknown", this.getAsString(this.Current_service_last_time_unknown()));
        this.addToHashtable("current_service_last_time_warning", this.getAsString(this.Current_service_last_time_warning()));
        this.addToHashtable("current_service_latency", this.getAsString(this.Current_service_latency()));
        this.addToHashtable("current_service_long_plugin_output", this.Current_service_long_plugin_output());
        this.addToHashtable("current_service_low_flap_threshold", this.getAsString(this.Current_service_low_flap_threshold()));
        this.addToHashtable("current_service_max_check_attempts", this.getAsString(this.Current_service_max_check_attempts()));
        this.addToHashtable("current_service_modified_attributes", this.getAsString(this.Current_service_modified_attributes()));
        this.addToHashtable("current_service_modified_attributes_list", this.Current_service_modified_attributes_list());
        this.addToHashtable("current_service_next_check", this.getAsString(this.Current_service_next_check()));
        this.addToHashtable("current_service_next_notification", this.getAsString(this.Current_service_next_notification()));
        this.addToHashtable("current_service_no_more_notifications", this.getAsString(this.Current_service_no_more_notifications()));
        this.addToHashtable("current_service_notes", this.Current_service_notes());
        this.addToHashtable("current_service_notes_expanded", this.Current_service_notes_expanded());
        this.addToHashtable("current_service_notes_url", this.Current_service_notes_url());
        this.addToHashtable("current_service_notes_url_expanded", this.Current_service_notes_url_expanded());
        this.addToHashtable("current_service_notification_interval", this.getAsString(this.Current_service_notification_interval()));
        this.addToHashtable("current_service_notification_period", this.Current_service_notification_period());
        this.addToHashtable("current_service_notifications_enabled", this.getAsString(this.Current_service_notifications_enabled()));
        this.addToHashtable("current_service_obsess_over_service", this.getAsString(this.Current_service_obsess_over_service()));
        this.addToHashtable("current_service_percent_state_change", this.getAsString(this.Current_service_percent_state_change()));
        this.addToHashtable("current_service_perf_data", this.Current_service_perf_data());
        this.addToHashtable("current_service_plugin_output", this.Current_service_plugin_output());
        this.addToHashtable("current_service_pnpgraph_present", this.getAsString(this.Current_service_pnpgraph_present()));
        this.addToHashtable("current_service_process_performance_data", this.getAsString(this.Current_service_process_performance_data()));
        this.addToHashtable("current_service_retry_interval", this.getAsString(this.Current_service_retry_interval()));
        this.addToHashtable("current_service_scheduled_downtime_depth", this.getAsString(this.Current_service_scheduled_downtime_depth()));
        this.addToHashtable("current_service_service_period", this.Current_service_service_period());
        this.addToHashtable("current_service_staleness", this.getAsString(this.Current_service_staleness()));
        this.addToHashtable("current_service_state", this.getAsString(this.Current_service_state()));
        this.addToHashtable("current_service_state_type", this.getAsString(this.Current_service_state_type()));
        this.addToHashtable("host_name", this.Host_name());
        this.addToHashtable("lineno", this.getAsString(this.Lineno()));
        this.addToHashtable("message", this.Message());
        this.addToHashtable("options", this.Options());
        this.addToHashtable("plugin_output", this.Plugin_output());
        this.addToHashtable("service_description", this.Service_description());
        this.addToHashtable("state", this.getAsString(this.State()));
        this.addToHashtable("state_type", this.State_type());
        this.addToHashtable("time", this.getAsString(this.Time()));
        this.addToHashtable("type", this.Type());
        return mapKeyValue;
    }

}
