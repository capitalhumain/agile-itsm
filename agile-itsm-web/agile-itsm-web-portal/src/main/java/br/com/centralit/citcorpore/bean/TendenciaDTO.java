package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author euler.ramos Guardará o resultado da análise de tendências de lançamento de solicitações de serviço
 */
public class TendenciaDTO extends BaseEntity {

    private static final long serialVersionUID = -42994999516790078L;

    private Integer id;
    private String descricao;
    private Integer qtdeCritica;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdeCritica() {
        return qtdeCritica;
    }

    public void setQtdeCritica(final Integer qtdeCritica) {
        this.qtdeCritica = qtdeCritica;
    }

}
