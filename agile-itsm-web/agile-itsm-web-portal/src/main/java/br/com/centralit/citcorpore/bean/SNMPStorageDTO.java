package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class SNMPStorageDTO extends BaseEntity {
	public static String FISICA = "F";
	public static String DISCO = "D";
	private String tipo;
	private String descricao;
	private Integer allocationUnits;
	private Long size;
	private Long used;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getAllocationUnits() {
		return allocationUnits;
	}
	public void setAllocationUnits(Integer allocationUnits) {
		this.allocationUnits = allocationUnits;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getUsed() {
		return used;
	}
	public void setUsed(Long used) {
		this.used = used;
	}
}
