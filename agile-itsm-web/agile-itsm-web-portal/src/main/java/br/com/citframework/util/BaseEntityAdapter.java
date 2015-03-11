package br.com.citframework.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import br.com.agileitsm.model.support.BaseEntity;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BaseEntityAdapter extends XmlAdapter<String, BaseEntity> {

    @Override
    public String marshal(final BaseEntity obj) throws Exception {
        final XStream x = new XStream(new DomDriver("UTF-8"));
        return x.toXML(obj);
    }

    @Override
    public BaseEntity unmarshal(final String str) throws Exception {
        final XStream x = new XStream(new DomDriver("UTF-8"));
        return (BaseEntity) x.fromXML(str);
    }

}
