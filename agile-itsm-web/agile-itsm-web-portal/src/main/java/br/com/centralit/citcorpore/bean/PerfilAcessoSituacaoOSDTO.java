package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PerfilAcessoSituacaoOSDTO extends BaseEntity {

    private Integer idPerfil;
    private Integer situacaoOs;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(final Integer parm) {
        idPerfil = parm;
    }

    public Integer getSituacaoOs() {
        return situacaoOs;
    }

    public void setSituacaoOs(final Integer parm) {
        situacaoOs = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

}
