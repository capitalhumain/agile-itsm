package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class UnidadesAccServicosDTO extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 907067099262037701L;
	
	private Integer idUnidade;
	private Integer idServico;
	private String nomeServico;
	private String detalheServico;
	
	public Integer getIdUnidade(){
		return this.idUnidade;
	}
	public void setIdUnidade(Integer parm){
		this.idUnidade = parm;
	}
	public Integer getIdServico(){
		return this.idServico;
	}
	public void setIdServico(Integer parm){
		this.idServico = parm;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public String getDetalheServico() {
		return detalheServico;
	}
	public void setDetalheServico(String detalheServico) {
		this.detalheServico = detalheServico;
	}

}
