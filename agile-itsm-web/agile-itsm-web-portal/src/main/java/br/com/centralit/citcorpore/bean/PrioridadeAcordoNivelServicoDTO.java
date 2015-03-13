package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PrioridadeAcordoNivelServicoDTO extends BaseEntity {

    private Integer idUnidade;
    private Integer idAcordoNivelServico;
    private Integer idPrioridade;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(final Integer parm) {
        idUnidade = parm;
    }

    public Integer getIdAcordoNivelServico() {
        return idAcordoNivelServico;
    }

    public void setIdAcordoNivelServico(final Integer parm) {
        idAcordoNivelServico = parm;
    }

    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(final Integer parm) {
        idPrioridade = parm;
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
