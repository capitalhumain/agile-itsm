package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class SNMPStorageDTO extends BaseEntity {

    public static String FISICA = "F";
    public static String DISCO = "D";
    private String tipo;
    private String descricao;
    private Integer allocationUnits;
    private Long size;
    private Long used;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Integer getAllocationUnits() {
        return allocationUnits;
    }

    public void setAllocationUnits(final Integer allocationUnits) {
        this.allocationUnits = allocationUnits;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(final Long size) {
        this.size = size;
    }

    public Long getUsed() {
        return used;
    }

    public void setUsed(final Long used) {
        this.used = used;
    }

}
