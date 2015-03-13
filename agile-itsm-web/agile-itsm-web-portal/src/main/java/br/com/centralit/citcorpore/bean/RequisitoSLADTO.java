package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RequisitoSLADTO extends BaseEntity {

    private Integer idRequisitoSLA;
    private Integer idEmpregado;
    private String assunto;
    private String detalhamento;
    private String situacao;
    private java.sql.Date requisitadoEm;
    private java.sql.Date criadoEm;
    private java.sql.Date modificadoEm;
    private String criadoPor;
    private String modificadoPor;
    private String deleted;
    private java.sql.Date dataVinculacao;

    public Integer getIdRequisitoSLA() {
        return idRequisitoSLA;
    }

    public void setIdRequisitoSLA(final Integer parm) {
        idRequisitoSLA = parm;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer parm) {
        idEmpregado = parm;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(final String parm) {
        assunto = parm;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String parm) {
        detalhamento = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public java.sql.Date getRequisitadoEm() {
        return requisitadoEm;
    }

    public void setRequisitadoEm(final java.sql.Date parm) {
        requisitadoEm = parm;
    }

    public java.sql.Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(final java.sql.Date parm) {
        criadoEm = parm;
    }

    public java.sql.Date getModificadoEm() {
        return modificadoEm;
    }

    public void setModificadoEm(final java.sql.Date parm) {
        modificadoEm = parm;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(final String parm) {
        criadoPor = parm;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(final String parm) {
        modificadoPor = parm;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

    public java.sql.Date getDataVinculacao() {
        return dataVinculacao;
    }

    public void setDataVinculacao(final java.sql.Date dataVinculacao) {
        this.dataVinculacao = dataVinculacao;
    }

}
