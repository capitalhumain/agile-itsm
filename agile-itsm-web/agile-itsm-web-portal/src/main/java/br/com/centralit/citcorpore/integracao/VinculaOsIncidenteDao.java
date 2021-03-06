package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.metainfo.bean.VinculoVisaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 *
 * @author rodrigo.oliveira
 *
 */
public class VinculaOsIncidenteDao extends CrudDaoDefaultImpl {

    public VinculaOsIncidenteDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idOS", "idOS", true, false, false, false));
        listFields.add(new Field("idSolicitacaoServico", "idSolicitacaoServico", true, false, false, false));
        listFields.add(new Field("idAtividadesOS", "idAtividadesOS", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "VinculaOsIncidente";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idOS"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return VinculoVisaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdOS(final Integer idOS) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idOS", "=", idOS));
        ordenacao.add(new Order("idSolicitacaoServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdAtividadeOS(final Integer idAtividadesOS) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAtividadesOS", "=", idAtividadesOS));
        ordenacao.add(new Order("idSolicitacaoServico"));
        final Collection resp = super.findByCondition(condicao, ordenacao);
        if (resp != null) {
            return resp;
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteByIdOs(final Integer idOs) throws PersistenceException {
        final List parametros = new ArrayList<>();
        final String sql = "DELETE FROM " + this.getTableName() + " WHERE idOS = ? ";
        parametros.add(idOs);
        this.execUpdate(sql, parametros.toArray());
    }

    public void deleteByIdAtividadeOS(final Integer idAtividadeOS) throws PersistenceException {
        final List parametros = new ArrayList<>();
        final String sql = "DELETE FROM " + this.getTableName() + " WHERE idAtividadesOS = ? ";
        parametros.add(idAtividadeOS);
        this.execUpdate(sql, parametros.toArray());
    }

    public boolean verificaServicoSelecionado(final Integer idServicoContratoContabil) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idServicoContratoContabil));
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idOS"));
        final Collection resp = super.findByCondition(condicao, ordenacao);
        if (resp != null && resp.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificaServicoJaVinculado(final Integer idOS, final Integer idServicoContratoContabil) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idOS", "<>", idOS));
        condicao.add(new Condition("idSolicitacaoServico", "=", idServicoContratoContabil));
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idOS"));
        final Collection resp = super.findByCondition(condicao, ordenacao);
        if (resp != null && resp.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
