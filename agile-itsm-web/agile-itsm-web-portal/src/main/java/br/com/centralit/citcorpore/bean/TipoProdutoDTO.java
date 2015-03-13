package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TipoProdutoDTO extends BaseEntity {

    private Integer idTipoProduto;
    private Integer idCategoria;
    private Integer idUnidadeMedida;
    private String nomeProduto;
    private String situacao;
    private String aceitaRequisicao;
    private String nomeUnidadeMedida;
    private String nomeCategoria;
    private String siglaUnidMedida;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Integer parm) {
        idCategoria = parm;
    }

    public Integer getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(final Integer parm) {
        idUnidadeMedida = parm;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(final String parm) {
        nomeProduto = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getAceitaRequisicao() {
        return aceitaRequisicao;
    }

    public void setAceitaRequisicao(final String parm) {
        aceitaRequisicao = parm;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getSiglaUnidMedida() {
        return siglaUnidMedida;
    }

    public void setSiglaUnidMedida(final String siglaUnidMedida) {
        this.siglaUnidMedida = siglaUnidMedida;
    }

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(final Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getNomeUnidadeMedida() {
        return nomeUnidadeMedida;
    }

    public void setNomeUnidadeMedida(final String nomeUnidadeMedida) {
        this.nomeUnidadeMedida = nomeUnidadeMedida;
    }

}
