package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class OrigemAtendimentoDTO extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Integer idOrigem;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	
	public Integer getIdOrigem(){
		return this.idOrigem;
	}
	public void setIdOrigem(Integer parm){
		this.idOrigem = parm;
	}

	public String getDescricao(){
		return this.descricao;
	}
	public void setDescricao(String parm){
		this.descricao = parm;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
}
