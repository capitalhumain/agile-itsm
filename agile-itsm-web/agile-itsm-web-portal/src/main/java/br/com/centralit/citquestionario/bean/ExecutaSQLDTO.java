package br.com.centralit.citquestionario.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ExecutaSQLDTO extends BaseEntity {
	private Integer value;
	private String description;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
