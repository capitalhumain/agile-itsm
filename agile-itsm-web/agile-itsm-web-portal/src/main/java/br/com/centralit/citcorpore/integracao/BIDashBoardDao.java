package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BIDashBoardDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class BIDashBoardDao extends CrudDaoDefaultImpl {

    public BIDashBoardDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idDashBoard", "idDashBoard", true, true, false, false));
        listFields.add(new Field("nomeDashBoard", "nomeDashBoard", false, false, false, false));
        listFields.add(new Field("tipo", "tipo", false, false, false, false));
        listFields.add(new Field("identificacao", "identificacao", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("idUsuario", "idUsuario", false, false, false, false));
        listFields.add(new Field("parametros", "parametros", false, false, false, false));
        listFields.add(new Field("naoAtualizBase", "naoAtualizBase", false, false, false, false));
        listFields.add(new Field("tempoRefresh", "tempoRefresh", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "BI_DashBoard";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("situacao", "=", "A"));
        ordenacao.add(new Order("nomeDashBoard"));
        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public Class getBean() {
        return BIDashBoardDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public BaseEntity getByIdentificacao(final String ident) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("identificacao", "=", ident));
        condicao.add(new Condition("situacao", "=", "A"));
        ordenacao.add(new Order("idDashBoard"));
        final Collection col = super.findByCondition(condicao, ordenacao);
        if (col != null) {
            for (final Iterator it = col.iterator(); it.hasNext();) {
                final BaseEntity ret = (BaseEntity) it.next();
                return ret;
            }
        }
        return null;
    }

}
