package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioQuantitativoServicoAnaliticoDTO extends BaseEntity {
	private static final long serialVersionUID = 5769173299912237423L;
	private Collection<ServicoDTO> listaPorPeriodo;

	public Collection<ServicoDTO> getListaPorPeriodo() {
		return listaPorPeriodo;
	}

	public void setListaPorPeriodo(Collection<ServicoDTO> listaPorPeriodo) {
		this.listaPorPeriodo = listaPorPeriodo;
	}
}