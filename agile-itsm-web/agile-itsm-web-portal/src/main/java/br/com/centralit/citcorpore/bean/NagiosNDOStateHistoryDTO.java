package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class NagiosNDOStateHistoryDTO extends BaseEntity {

    private Integer statehistory_id;
    private Integer instance_id;
    private java.sql.Timestamp state_time;
    private Integer state_time_usec;
    private Integer object_id;
    private Integer state_change;
    private Integer state;
    private Integer state_type;
    private Integer current_check_attempt;
    private Integer max_check_attempts;
    private Integer last_state;
    private Integer last_hard_state;
    private String output;
    private String long_output;

    public Integer getStatehistory_id() {
        return statehistory_id;
    }

    public void setStatehistory_id(final Integer parm) {
        statehistory_id = parm;
    }

    public Integer getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(final Integer parm) {
        instance_id = parm;
    }

    public java.sql.Timestamp getState_time() {
        return state_time;
    }

    public void setState_time(final java.sql.Timestamp parm) {
        state_time = parm;
    }

    public Integer getState_time_usec() {
        return state_time_usec;
    }

    public void setState_time_usec(final Integer parm) {
        state_time_usec = parm;
    }

    public Integer getObject_id() {
        return object_id;
    }

    public void setObject_id(final Integer parm) {
        object_id = parm;
    }

    public Integer getState_change() {
        return state_change;
    }

    public void setState_change(final Integer parm) {
        state_change = parm;
    }

    public Integer getState() {
        return state;
    }

    public void setState(final Integer parm) {
        state = parm;
    }

    public Integer getState_type() {
        return state_type;
    }

    public void setState_type(final Integer parm) {
        state_type = parm;
    }

    public Integer getCurrent_check_attempt() {
        return current_check_attempt;
    }

    public void setCurrent_check_attempt(final Integer parm) {
        current_check_attempt = parm;
    }

    public Integer getMax_check_attempts() {
        return max_check_attempts;
    }

    public void setMax_check_attempts(final Integer parm) {
        max_check_attempts = parm;
    }

    public Integer getLast_state() {
        return last_state;
    }

    public void setLast_state(final Integer parm) {
        last_state = parm;
    }

    public Integer getLast_hard_state() {
        return last_hard_state;
    }

    public void setLast_hard_state(final Integer parm) {
        last_hard_state = parm;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(final String parm) {
        output = parm;
    }

    public String getLong_output() {
        return long_output;
    }

    public void setLong_output(final String parm) {
        long_output = parm;
    }

    public String getStatus() {
        if (this.getState() != null) {
            if (this.getState().intValue() == 2) { // CRITICAL
                return "DOWN";
            } else {
                return "UP";
            }
        }
        return "";
    }

}
