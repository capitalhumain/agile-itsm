package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ProblemaItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idProblemaItemConfiguracao;
    private Integer idProblema;
    private Integer idItemConfiguracao;
    private String descricaoProblema;

    // campos que não estão no banco
    private String nomeItemConfiguracao;

    public Integer getIdProblemaItemConfiguracao() {
        return idProblemaItemConfiguracao;
    }

    public void setIdProblemaItemConfiguracao(final Integer idProblemaItemConfiguracao) {
        this.idProblemaItemConfiguracao = idProblemaItemConfiguracao;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(final String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getNomeItemConfiguracao() {
        return nomeItemConfiguracao;
    }

    public void setNomeItemConfiguracao(final String nomeItemConfiguracao) {
        this.nomeItemConfiguracao = nomeItemConfiguracao;
    }

}
