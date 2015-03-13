package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilStrings;

public class ProdutoContratoDTO extends BaseEntity {

    private Integer idProdutoContrato;
    private Integer idContrato;
    private String nomeProduto;

    private Double qtde;
    private String deleted;

    public Integer getIdProdutoContrato() {
        return idProdutoContrato;
    }

    public void setIdProdutoContrato(final Integer parm) {
        idProdutoContrato = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getNomeProdutoHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(nomeProduto));
    }

    public void setNomeProduto(final String parm) {
        nomeProduto = parm;
    }

    public Double getQtde() {
        return qtde;
    }

    public void setQtde(final Double qtde) {
        this.qtde = qtde;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
