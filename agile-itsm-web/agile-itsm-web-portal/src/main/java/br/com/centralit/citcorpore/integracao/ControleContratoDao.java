package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 *
 * @author pedro
 *
 */

public class ControleContratoDao extends CrudDaoDefaultImpl {

    public ControleContratoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idControleContrato", "idControleContrato", true, true, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("cliente", "cliente", false, false, false, false));
        listFields.add(new Field("numeroSubscricao", "numeroSubscricao", false, false, false, false));
        listFields.add(new Field("endereco", "endereco", false, false, false, false));
        listFields.add(new Field("contato", "contato", false, false, false, false));
        listFields.add(new Field("email", "email", false, false, false, false));
        listFields.add(new Field("telefone1", "telefone1", false, false, false, false));
        listFields.add(new Field("telefone2", "telefone2", false, false, false, false));
        listFields.add(new Field("tipoSubscricao", "tipoSubscricao", false, false, false, false));
        listFields.add(new Field("url", "url", false, false, false, false));
        listFields.add(new Field("login", "login", false, false, false, false));
        listFields.add(new Field("senha", "senha", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CONTROLECONTRATO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();

        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();

        return super.list(list);
    }

    @Override
    public Class getBean() {
        return ControleContratoDTO.class;
    }

}
