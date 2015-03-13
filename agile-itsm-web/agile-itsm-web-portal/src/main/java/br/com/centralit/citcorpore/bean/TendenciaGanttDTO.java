package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author euler.ramos
 * Guarda as informações para a geração do gráfico de Gantt
 */
public class TendenciaGanttDTO extends BaseEntity{

	private static final long serialVersionUID = -4822323995573414L;
	
	private Date data;
	private Integer qtde;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

}