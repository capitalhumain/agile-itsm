package br.com.centralit.citged.integracao;

import java.util.HashMap;

import br.com.agileitsm.model.support.BaseEntity;

public abstract class ControleGEDExternoDao {
	public abstract BaseEntity create(BaseEntity dto, HashMap infoAdicional) throws Exception;
	public abstract void update(BaseEntity dto, HashMap infoAdicional) throws Exception;
}
