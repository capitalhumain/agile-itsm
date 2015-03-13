package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class NagiosNDOObjectDTO extends BaseEntity {

    private Integer object_id;
    private Integer instance_id;
    private Integer objecttype_id;
    private String name1;
    private String name2;
    private Integer is_active;

    public Integer getObject_id() {
        return object_id;
    }

    public void setObject_id(final Integer parm) {
        object_id = parm;
    }

    public Integer getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(final Integer parm) {
        instance_id = parm;
    }

    public Integer getObjecttype_id() {
        return objecttype_id;
    }

    public void setObjecttype_id(final Integer parm) {
        objecttype_id = parm;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(final String parm) {
        name1 = parm;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(final String parm) {
        name2 = parm;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(final Integer parm) {
        is_active = parm;
    }

}
