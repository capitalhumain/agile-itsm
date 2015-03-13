package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ValorAjusteGlosaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author rodrigo.oliveira
 *
 */
public class ValorAjusteGlosaDAO extends CrudDaoDefaultImpl {

    public ValorAjusteGlosaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idServicoContrato", "idServicoContrato", true, false, false, false));
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", true, false, false, false));
        listFields.add(new Field("quantidadeFalhas", "quantidadeFalhas", false, false, false, false));
        listFields.add(new Field("valorAjuste", "valorAjuste", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ValorAjusteGlosa";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ValorAjusteGlosaDTO.class;
    }

    public Collection findByIdServicoContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", parm));
        ordenacao.add(new Order("idAcordoNivelServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdServicoContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdAcordoNivelServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", parm));
        ordenacao.add(new Order("idServicoContrato"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdAcordoNivelServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection consultaQuantidadesPorAcordoEServicoContrato(final Integer idServicoContrato, final Integer idAcordoNivelServico)
            throws PersistenceException {

        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        sql.append("SELECT quantidadefalhas, valorajuste FROM " + this.getTableName()
                + " WHERE idservicocontrato = ? and idacordonivelservico = ? ORDER BY quantidadefalhas ");
        parametro.add(idServicoContrato);
        parametro.add(idAcordoNivelServico);
        final List list = this.execSQL(sql.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("quantidadeFalhas");
        listRetorno.add("valorAjuste");
        final List<ValorAjusteGlosaDTO> result = engine.listConvertion(this.getBean(), list, listRetorno);

        return result;
    }

    /**
     * @param idServicoContrato
     * @throws PersistenceException
     * @author cledson.junior
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void updateValorAjusteGlosa(final Integer idServicoContrato) throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add("y");
        parametros.add(idServicoContrato);
        final String sql = "UPDATE " + this.getTableName() + " SET deleted = ? WHERE idServicoContrato = ?";
        this.execUpdate(sql, parametros.toArray());
    }

}
