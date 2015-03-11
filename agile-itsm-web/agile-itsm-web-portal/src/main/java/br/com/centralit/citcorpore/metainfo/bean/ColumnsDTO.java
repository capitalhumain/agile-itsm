package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ColumnsDTO extends BaseEntity {
	private String[] coluna;

	public String[] getColuna() {
		return coluna;
	}

	public void setColuna(String[] coluna) {
		this.coluna = coluna;
	}
}
