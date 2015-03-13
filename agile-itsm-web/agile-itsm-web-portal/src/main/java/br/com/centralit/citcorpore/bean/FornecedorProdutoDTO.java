package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class FornecedorProdutoDTO extends BaseEntity {

    private static final long serialVersionUID = -4527722170327910788L;

    private Integer idFornecedorProduto;

    private Integer idFornecedor;

    private Integer idTipoProduto;

    private Integer idMarca;

    private Date dataInicio;

    private Date dataFim;

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer parm) {
        idFornecedor = parm;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date parm) {
        dataInicio = parm;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date parm) {
        dataFim = parm;
    }

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(final Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(final Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdFornecedorProduto() {
        return idFornecedorProduto;
    }

    public void setIdFornecedorProduto(final Integer idFornecedorProduto) {
        this.idFornecedorProduto = idFornecedorProduto;
    }

}
