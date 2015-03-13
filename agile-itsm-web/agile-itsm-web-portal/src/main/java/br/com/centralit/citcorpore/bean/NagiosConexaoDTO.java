package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class NagiosConexaoDTO extends BaseEntity {

    private Integer idNagiosConexao;
    private String nome;
    private String nomeJNDI;
    private String criadoPor;
    private String modificadoPor;
    private java.sql.Date dataCriacao;
    private java.sql.Date ultModificacao;

    public Integer getIdNagiosConexao() {
        return idNagiosConexao;
    }

    public void setIdNagiosConexao(final Integer parm) {
        idNagiosConexao = parm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String parm) {
        nome = parm;
    }

    public String getNomeJNDI() {
        return nomeJNDI;
    }

    public void setNomeJNDI(final String parm) {
        nomeJNDI = parm;
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

    public java.sql.Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final java.sql.Date parm) {
        dataCriacao = parm;
    }

    public java.sql.Date getUltModificacao() {
        return ultModificacao;
    }

    public void setUltModificacao(final java.sql.Date parm) {
        ultModificacao = parm;
    }

}
