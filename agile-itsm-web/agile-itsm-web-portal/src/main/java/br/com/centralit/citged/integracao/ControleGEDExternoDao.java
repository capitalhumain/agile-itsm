package br.com.centralit.citged.integracao;

import java.util.HashMap;

import br.com.agileitsm.model.support.BaseEntity;

public abstract class ControleGEDExternoDao {

    public abstract BaseEntity create(final BaseEntity dto, final HashMap infoAdicional) throws Exception;

    public abstract void update(final BaseEntity dto, final HashMap infoAdicional) throws Exception;

}
