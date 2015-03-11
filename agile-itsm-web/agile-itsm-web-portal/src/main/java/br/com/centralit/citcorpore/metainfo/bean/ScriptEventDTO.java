package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ScriptEventDTO extends BaseEntity {
	private String name;
	private String description;
	
	public ScriptEventDTO(String nameParm, String descParm){
		this.setName(nameParm);
		this.setDescription(descParm);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
