package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FaturaOSBIDTO extends BaseEntity {

    private Integer idFatura;
    private Integer idOs;
    private Integer idConexaoBI;

    public Integer getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(final Integer parm) {
        idFatura = parm;
    }

    public Integer getIdOs() {
        return idOs;
    }

    public void setIdOs(final Integer parm) {
        idOs = parm;
    }

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

}
