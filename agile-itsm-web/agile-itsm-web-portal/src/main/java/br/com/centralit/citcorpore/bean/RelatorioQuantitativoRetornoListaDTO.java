package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioQuantitativoRetornoListaDTO extends BaseEntity {

    private static final long serialVersionUID = 5769173299912237423L;

    private Collection<RelatorioQuantitativoRetornoDTO> listaPorPeriodo;

    public Collection<RelatorioQuantitativoRetornoDTO> getListaPorPeriodo() {
        return listaPorPeriodo;
    }

    public void setListaPorPeriodo(final Collection<RelatorioQuantitativoRetornoDTO> listaPorPeriodo) {
        this.listaPorPeriodo = listaPorPeriodo;
    }

}
