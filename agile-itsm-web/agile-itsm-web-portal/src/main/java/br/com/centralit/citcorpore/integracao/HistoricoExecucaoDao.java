package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.HistoricoExecucaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class HistoricoExecucaoDao extends CrudDaoDefaultImpl {

    private static final String SQL_HISTORICO_DEMANDA = "SELECT H.data, H.situacao, H.detalhamento, H.hora, E.nome " + "FROM HISTORICOEXECUCAO H "
            + " INNER JOIN EXECUCAODEMANDA EX on EX.idExecucao = H.idExecucao " + " INNER JOIN EMPREGADOS E on E.idEmpregado = H.idEmpregadoExecutor "
            + "where EX.idDemanda = ? order by data";

    public HistoricoExecucaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return HistoricoExecucaoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idHistorico", "idHistorico", true, true, false, false));
        listFields.add(new Field("idExecucao", "idExecucao", false, false, false, false));
        listFields.add(new Field("data", "data", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("idEmpregadoExecutor", "idEmpregadoExecutor", false, false, false, false));
        listFields.add(new Field("detalhamento", "detalhamento", false, false, false, false));
        listFields.add(new Field("hora", "hora", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "HISTORICOEXECUCAO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection findByDemanda(final Integer idDemanda) throws PersistenceException {
        final Object[] objs = new Object[] {idDemanda};

        final String sql = SQL_HISTORICO_DEMANDA;

        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("data");
        listRetorno.add("situacao");
        listRetorno.add("detalhamento");
        listRetorno.add("hora");
        listRetorno.add("nomeEmpregado");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return result;
    }

}
