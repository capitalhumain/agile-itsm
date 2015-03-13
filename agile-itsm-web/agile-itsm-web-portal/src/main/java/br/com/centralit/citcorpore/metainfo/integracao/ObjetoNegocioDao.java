package br.com.centralit.citcorpore.metainfo.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.metainfo.bean.ObjetoNegocioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ObjetoNegocioDao extends CrudDaoDefaultImpl {

    public ObjetoNegocioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final List<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idObjetoNegocio", "idObjetoNegocio", true, true, false, false));
        listFields.add(new Field("nomeObjetoNegocio", "nomeObjetoNegocio", false, false, false, false));
        listFields.add(new Field("nomeTabelaDB", "nomeTabelaDB", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ObjetoNegocio";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List colOrder = new ArrayList<>();
        colOrder.add(new Order("nomeObjetoNegocio"));
        return super.list(colOrder);
    }

    public Collection listAtivos() throws Exception {
        final List colCondicao = new ArrayList<>();
        final List colOrder = new ArrayList<>();

        colCondicao.add(new Condition("situacao", "=", "A"));

        colOrder.add(new Order("nomeObjetoNegocio"));
        return super.findByCondition(colCondicao, colOrder);
    }

    @Override
    public Class getBean() {
        return ObjetoNegocioDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByNomeTabelaDB(final String nomeTabelaDBParm) throws Exception {
        final List colCondicao = new ArrayList<>();
        final List colOrder = new ArrayList<>();

        String nomeTabelaDBAux = nomeTabelaDBParm;
        if (nomeTabelaDBAux == null) {
            return null;
        }

        nomeTabelaDBAux = nomeTabelaDBAux.toUpperCase();

        colCondicao.add(new Condition("nomeTabelaDB", "=", nomeTabelaDBAux));

        colOrder.add(new Order("idObjetoNegocio"));
        return super.findByCondition(colCondicao, colOrder);
    }

    public ObjetoNegocioDTO findByNomeObjetoNegocio(final String nomeObjetoNegocio) throws Exception {
        final List colCondicao = new ArrayList<>();
        final List colOrder = new ArrayList<>();

        String nomeObjetoNegocioAux = nomeObjetoNegocio;
        if (nomeObjetoNegocioAux == null) {
            return null;
        }

        nomeObjetoNegocioAux = nomeObjetoNegocioAux.toUpperCase();

        colCondicao.add(new Condition("nomeObjetoNegocio", "=", nomeObjetoNegocioAux));

        colOrder.add(new Order("idObjetoNegocio"));
        final Collection result = super.findByCondition(colCondicao, colOrder);
        if (result != null && !result.isEmpty()) {
            return (ObjetoNegocioDTO) ((List) result).get(0);
        }
        return null;
    }

    public ObjetoNegocioDTO getByNomeTabelaDB(final String nomeObjetoNegocio) throws Exception {
        final List colCondicao = new ArrayList<>();
        final List colOrder = new ArrayList<>();

        String nomeObjetoNegocioAux = nomeObjetoNegocio;
        if (nomeObjetoNegocioAux == null) {
            return null;
        }

        nomeObjetoNegocioAux = nomeObjetoNegocioAux.toUpperCase();

        colCondicao.add(new Condition("nomeTabelaDB", "=", nomeObjetoNegocioAux));

        colOrder.add(new Order("idObjetoNegocio"));
        final Collection result = super.findByCondition(colCondicao, colOrder);
        if (result != null && !result.isEmpty()) {
            return (ObjetoNegocioDTO) ((List) result).get(0);
        }
        return null;
    }

}
