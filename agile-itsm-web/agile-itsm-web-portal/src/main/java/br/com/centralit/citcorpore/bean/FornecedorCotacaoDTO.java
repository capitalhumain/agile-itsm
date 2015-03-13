package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FornecedorCotacaoDTO extends BaseEntity {

    private Integer idCotacao;
    private Integer idFornecedor;

    public Integer getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(final Integer parm) {
        idCotacao = parm;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer parm) {
        idFornecedor = parm;
    }

}
