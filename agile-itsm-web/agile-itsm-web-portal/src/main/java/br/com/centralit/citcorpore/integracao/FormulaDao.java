package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.FormulaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class FormulaDao extends CrudDaoDefaultImpl {

    public FormulaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idFormula", "idFormula", true, true, false, false));
        listFields.add(new Field("identificador", "identificador", false, false, false, false));
        listFields.add(new Field("nome", "nome", false, false, false, false));
        listFields.add(new Field("conteudo", "conteudo", false, false, false, false));
        listFields.add(new Field("datacriacao", "datacriacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Formula";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return FormulaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdentificador(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("identificador", "=", parm));
        ordenacao.add(new Order("nome"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdentificadorAndIdDiferente(final String parm1, final String parm2) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("identificador", "=", parm1));
        condicao.add(new Condition("idFormula", "!=", Integer.parseInt(parm2)));
        ordenacao.add(new Order("nome"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdentificador(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("identificador", "=", parm));
        super.deleteByCondition(condicao);
    }

    public boolean verificarSeIdentificadorExiste(final FormulaDTO formula) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select identificador from " + this.getTableName() + "  where  identificador = ?");
        parametro.add(formula.getIdentificador());

        if (formula.getIdFormula() != null) {
            sql.append(" and idformula <> ? ");
            parametro.add(formula.getIdFormula());
        }
        list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean verificarSeNomeExiste(final FormulaDTO formula) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select nome from formula where  nome = ? ");
        parametro.add(formula.getNome());

        if (formula.getIdFormula() != null) {
            sql.append("and idformula <> ?");
            parametro.add(formula.getIdFormula());
        }
        list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

}
