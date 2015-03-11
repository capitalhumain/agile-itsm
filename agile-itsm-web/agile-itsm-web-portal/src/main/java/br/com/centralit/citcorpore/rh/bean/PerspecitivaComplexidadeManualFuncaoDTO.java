package br.com.centralit.citcorpore.rh.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PerspecitivaComplexidadeManualFuncaoDTO extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private AtribuicaoResponsabilidadeDTO atribuicaoResponsabilidadeDto;

	public AtribuicaoResponsabilidadeDTO getAtribuicaoResponsabilidadeDto() {
		return atribuicaoResponsabilidadeDto;
	}

	public void setAtribuicaoResponsabilidadeDto(
			AtribuicaoResponsabilidadeDTO atribuicaoResponsabilidadeDto) {
		this.atribuicaoResponsabilidadeDto = atribuicaoResponsabilidadeDto;
	}
	
	
	
}
