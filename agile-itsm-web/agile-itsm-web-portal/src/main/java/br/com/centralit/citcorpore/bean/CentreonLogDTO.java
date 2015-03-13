package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CentreonLogDTO extends BaseEntity {

    private Integer log_id;
    private Long ctime;
    private String host_name;
    private String service_description;
    private String status;
    private String output;
    private String notification_cmd;
    private String notification_contact;
    private String type;
    private Integer retry;
    private Integer msg_type;
    private Integer instance;

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(final Integer parm) {
        log_id = parm;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(final String parm) {
        host_name = parm;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(final String parm) {
        service_description = parm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String parm) {
        status = parm;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(final String parm) {
        output = parm;
    }

    public String getNotification_cmd() {
        return notification_cmd;
    }

    public void setNotification_cmd(final String parm) {
        notification_cmd = parm;
    }

    public String getNotification_contact() {
        return notification_contact;
    }

    public void setNotification_contact(final String parm) {
        notification_contact = parm;
    }

    public String getType() {
        return type;
    }

    public void setType(final String parm) {
        type = parm;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(final Integer parm) {
        retry = parm;
    }

    public Integer getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(final Integer parm) {
        msg_type = parm;
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(final Integer parm) {
        instance = parm;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(final Long ctime) {
        this.ctime = ctime;
    }

}
