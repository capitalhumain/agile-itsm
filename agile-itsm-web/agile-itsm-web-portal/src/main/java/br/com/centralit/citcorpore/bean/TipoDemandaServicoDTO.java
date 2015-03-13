package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TipoDemandaServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 4413980874410791397L;

    private Integer idTipoDemandaServico;

    private String nomeTipoDemandaServico;

    private String classificacao;

    private String deleted;

    private Integer quantidade;

    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    public String getNomeTipoDemandaServico() {
        return nomeTipoDemandaServico;
    }

    public void setNomeTipoDemandaServico(final String nomeTipoDemandaServico) {
        this.nomeTipoDemandaServico = nomeTipoDemandaServico;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(final String classificacao) {
        this.classificacao = classificacao;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

}
