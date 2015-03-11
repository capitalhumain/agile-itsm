package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ProdutoTarefaLinBaseProjDTO extends BaseEntity {
	private Integer idTarefaLinhaBaseProjeto;
	private Integer idProdutoContrato;

	public Integer getIdTarefaLinhaBaseProjeto(){
		return this.idTarefaLinhaBaseProjeto;
	}
	public void setIdTarefaLinhaBaseProjeto(Integer parm){
		this.idTarefaLinhaBaseProjeto = parm;
	}

	public Integer getIdProdutoContrato(){
		return this.idProdutoContrato;
	}
	public void setIdProdutoContrato(Integer parm){
		this.idProdutoContrato = parm;
	}

}
