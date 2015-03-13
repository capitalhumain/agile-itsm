package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TipoOSDTO extends BaseEntity {

    private Integer idClassificacaoOS;
    private Integer idContrato;
    private String descricao;
    private String detalhamento;

    public Integer getIdClassificacaoOS() {
        return idClassificacaoOS;
    }

    public void setIdClassificacaoOS(final Integer parm) {
        idClassificacaoOS = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String parm) {
        detalhamento = parm;
    }

}
