package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoEvtMonDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class SolicitacaoServicoEvtMonDao extends CrudDaoDefaultImpl {

    public SolicitacaoServicoEvtMonDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idSolicitacaoServico", "idSolicitacaoServico", true, false, false, false));
        listFields.add(new Field("idEventoMonitoramento", "idEventoMonitoramento", true, false, false, false));
        listFields.add(new Field("idRecurso", "idRecurso", false, false, false, false));
        listFields.add(new Field("nomeHost", "nomeHost", false, false, false, false));
        listFields.add(new Field("nomeService", "nomeService", false, false, false, false));
        listFields.add(new Field("infoAdd", "infoAdd", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "SolicitacaoServicoEvtMon";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return SolicitacaoServicoEvtMonDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdSolicitacao(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        ordenacao.add(new Order("idEventoMonitoramento"));

        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdRecursoAndSolicitacaoAberta(final Integer idRecurso) throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add(idRecurso);
        parametros.add(idRecurso);
        String sql = "select " + this.getNamesFieldsStr() + " from " + this.getTableName();
        sql += " where idRecurso = ? and idSolicitacaoServico in (select idSolicitacaoServico from solicitacaoservico where idsolicitacaoservico in (select idSolicitacaoServico from "
                + this.getTableName() + " where idRecurso = ?) and UPPER(situacao) <> 'FECHADA')";
        final List lstDados = super.execSQL(sql, parametros.toArray());
        return super.listConvertion(this.getBean(), lstDados, this.getListNamesFieldClass());
    }

}
