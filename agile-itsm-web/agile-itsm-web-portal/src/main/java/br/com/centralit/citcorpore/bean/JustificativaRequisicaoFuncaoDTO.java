package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class JustificativaRequisicaoFuncaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Integer idJustificativa;
    private String descricao;
    private String situacao;

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

}
