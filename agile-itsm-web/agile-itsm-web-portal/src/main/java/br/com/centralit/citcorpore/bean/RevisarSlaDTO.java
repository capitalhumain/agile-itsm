package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RevisarSlaDTO extends BaseEntity {

    private Integer idRevisarSla;
    private Integer idAcordoNivelServico;
    private java.sql.Date dataRevisao;
    private String detalheRevisao;
    private String observacao;
    private String deleted;

    public Integer getIdRevisarSla() {
        return idRevisarSla;
    }

    public void setIdRevisarSla(final Integer idRevisarSla) {
        this.idRevisarSla = idRevisarSla;
    }

    public Integer getIdAcordoNivelServico() {
        return idAcordoNivelServico;
    }

    public void setIdAcordoNivelServico(final Integer idAcordoNivelServico) {
        this.idAcordoNivelServico = idAcordoNivelServico;
    }

    public java.sql.Date getDataRevisao() {
        return dataRevisao;
    }

    public void setDataRevisao(final java.sql.Date dataRevisao) {
        this.dataRevisao = dataRevisao;
    }

    public String getDetalheRevisao() {
        return detalheRevisao;
    }

    public void setDetalheRevisao(final String detalheRevisao) {
        this.detalheRevisao = detalheRevisao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
