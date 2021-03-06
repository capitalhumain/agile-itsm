package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.NagiosConexaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class NagiosConexaoDao extends CrudDaoDefaultImpl {

    public NagiosConexaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idNagiosConexao", "idNagiosConexao", true, true, false, false));
        listFields.add(new Field("nome", "nome", false, false, false, false));
        listFields.add(new Field("nomeJNDI", "nomeJNDI", false, false, false, false));
        listFields.add(new Field("criadoPor", "criadoPor", false, false, false, false));
        listFields.add(new Field("modificadoPor", "modificadoPor", false, false, false, false));
        listFields.add(new Field("dataCriacao", "dataCriacao", false, false, false, false));
        listFields.add(new Field("ultModificacao", "ultModificacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "NagiosConexao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("nome");
    }

    @Override
    public Class getBean() {
        return NagiosConexaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
