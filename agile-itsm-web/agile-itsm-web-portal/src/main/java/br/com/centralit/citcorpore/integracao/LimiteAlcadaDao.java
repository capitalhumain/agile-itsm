package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LimiteAlcadaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class LimiteAlcadaDao extends CrudDaoDefaultImpl {

    public LimiteAlcadaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idLimiteAlcada", "idLimiteAlcada", true, true, false, false));
        listFields.add(new Field("idAlcada", "idAlcada", false, false, false, false));
        listFields.add(new Field("idGrupo", "idGrupo", false, false, false, false));
        listFields.add(new Field("tipoLimite", "tipoLimite", false, false, false, false));
        listFields.add(new Field("abrangenciaCentroCusto", "abrangenciaCentroCusto", false, false, false, false));
        listFields.add(new Field("limiteItemUsoInterno", "limiteItemUsoInterno", false, false, false, false));
        listFields.add(new Field("limiteMensalUsoInterno", "limiteMensalUsoInterno", false, false, false, false));
        listFields.add(new Field("limiteItemAtendCliente", "limiteItemAtendCliente", false, false, false, false));
        listFields.add(new Field("limiteMensalAtendCliente", "limiteMensalAtendCliente", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "LimiteAlcada";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LimiteAlcadaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdAlcada(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAlcada", "=", parm));
        ordenacao.add(new Order("tipoLimite"));
        ordenacao.add(new Order("limiteItemUsoInterno"));
        return super.findByCondition(condicao, ordenacao);
    }

    public boolean verificarSeAlcadaPossuiLimite(final Integer idAlcada) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final String sql = "SELECT idalcada FROM " + this.getTableName() + " WHERE idalcada = ? AND situacao <> 'I' ";
        parametro.add(idAlcada);
        final List lista = this.execSQL(sql.toString(), parametro.toArray());
        if (lista != null && !lista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void removerPorIdAlcada(final Integer idAlcada) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAlcada", "=", idAlcada));
        this.deleteByCondition(condicao);
    }

    /**
     *
     * @param idGrupo
     * @return Collection
     * @throws Exception
     */
    public Collection findByIdGrupo(final Integer idGrupo) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idGrupo", "=", idGrupo));
        ordenacao.add(new Order("idGrupo"));
        return super.findByCondition(condicao, ordenacao);
    }

}
