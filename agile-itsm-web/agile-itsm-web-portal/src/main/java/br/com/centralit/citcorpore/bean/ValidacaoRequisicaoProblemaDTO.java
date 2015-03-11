package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * 
 * @author geber.costa
 * 
 */

@SuppressWarnings("serial")
public class ValidacaoRequisicaoProblemaDTO extends BaseEntity {
	private Integer idValidacaoRequisicaoProblema;
	private String observacaoProblema;
	private Date dataInicio;
	private Date dataFim;
	private Integer  idProblema ;

	public Integer getIdValidacaoRequisicaoProblema() {
		return idValidacaoRequisicaoProblema;
	}
	
	public void setIdValidacaoRequisicaoProblema(Integer idValidacaoRequisicaoProblema) {
		this.idValidacaoRequisicaoProblema = idValidacaoRequisicaoProblema;
	}

	public String getObservacaoProblema() {
		return observacaoProblema;
	}

	public void setObservacaoProblema(String observacaoProblema) {
		this.observacaoProblema = observacaoProblema;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getIdProblema() {
		return idProblema;
	}

	public void setIdProblema(Integer idProblema) {
		this.idProblema = idProblema;
	}

}
