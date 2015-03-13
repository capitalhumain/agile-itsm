package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ProdutoTarefaLinBaseProjDTO extends BaseEntity {

    private Integer idTarefaLinhaBaseProjeto;
    private Integer idProdutoContrato;

    public Integer getIdTarefaLinhaBaseProjeto() {
        return idTarefaLinhaBaseProjeto;
    }

    public void setIdTarefaLinhaBaseProjeto(final Integer parm) {
        idTarefaLinhaBaseProjeto = parm;
    }

    public Integer getIdProdutoContrato() {
        return idProdutoContrato;
    }

    public void setIdProdutoContrato(final Integer parm) {
        idProdutoContrato = parm;
    }

}
