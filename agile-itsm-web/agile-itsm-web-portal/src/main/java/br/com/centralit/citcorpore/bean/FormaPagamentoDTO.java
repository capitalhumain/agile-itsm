package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FormaPagamentoDTO extends BaseEntity {

    private Integer idFormaPagamento;
    private String nomeFormaPagamento;
    private String situacao;

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(final Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getNomeFormaPagamento() {
        return nomeFormaPagamento;
    }

    public void setNomeFormaPagamento(final String nomeFormaPagamento) {
        this.nomeFormaPagamento = nomeFormaPagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

}
