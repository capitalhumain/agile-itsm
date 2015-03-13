package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ExcecaoEmpregadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class ExcecaoEmpregadoDao extends CrudDaoDefaultImpl {

    public ExcecaoEmpregadoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ExcecaoEmpregadoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDEVENTO", "idEvento", true, false, false, false));
        listFields.add(new Field("IDEMPREGADO", "idEmpregado", true, false, false, false));
        listFields.add(new Field("IDGRUPO", "idGrupo", false, false, false, false));
        listFields.add(new Field("IDUNIDADE", "idUnidade", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "EXCECAOEMPREGADO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection<ExcecaoEmpregadoDTO> listByIdEventoIdGrupo(final Integer idEvento, final Integer idGrupo) throws PersistenceException {
        final String sql = "select e.idempregado, e.nome from " + this.getTableName()
                + " ee join empregados e on e.idempregado = ee.idempregado where ee.idevento = ? and ee.idgrupo = ?";
        final List dados = this.execSQL(sql, new Object[] {idEvento, idGrupo});
        final List fields = new ArrayList<>();
        fields.add("idEmpregado");
        fields.add("nomeEmpregado");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public Collection<ExcecaoEmpregadoDTO> listByIdEventoIdUnidade(final Integer idEvento, final Integer idUnidade) throws PersistenceException {
        final String sql = "select e.idempregado, e.nome from " + this.getTableName()
                + " ee join empregados e on e.idempregado = ee.idempregado where ee.idevento = ? and ee.idunidade = ?";
        final List dados = this.execSQL(sql, new Object[] {idEvento, idUnidade});
        final List fields = new ArrayList<>();
        fields.add("idEmpregado");
        fields.add("nomeEmpregado");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public void deleteByIdEvento(final Integer idEvento) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition(Condition.AND, "idEvento", "=", idEvento));
        super.deleteByCondition(lstCondicao);
    }

}
