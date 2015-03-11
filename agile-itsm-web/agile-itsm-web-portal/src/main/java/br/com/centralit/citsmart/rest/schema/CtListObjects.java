package br.com.centralit.citsmart.rest.schema;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.BaseEntityAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CtListObjects", propOrder = {"objetos"})
public class CtListObjects extends CtMessageResp {

    @XmlElement(name = "Objeto", required = true)
    @XmlJavaTypeAdapter(BaseEntityAdapter.class)
    protected List<BaseEntity> objetos;

    public List<BaseEntity> getObjetos() {
        if (objetos == null) {
            objetos = new ArrayList<>();
        }
        return objetos;
    }

    public void setObjetos(final List<BaseEntity> objetos) {
        this.objetos = objetos;
    }

}
