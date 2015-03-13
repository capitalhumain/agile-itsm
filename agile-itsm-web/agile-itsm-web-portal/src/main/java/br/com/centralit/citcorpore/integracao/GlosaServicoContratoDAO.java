package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GlosaServicoContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class GlosaServicoContratoDAO extends CrudDaoDefaultImpl {

    public GlosaServicoContratoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idGlosaServicoContrato", "idGlosaServicoContrato", true, true, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("quantidadeGlosa", "quantidadeGlosa", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "GlosaServicoContrato";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return GlosaServicoContratoDTO.class;
    }

    public Collection findByIdServicoContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", parm));
        ordenacao.add(new Order("idGlosaServicoContrato"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection quantidadeGlosaServico(final Integer idServicoContrato) throws PersistenceException {

        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        sql.append("SELECT quantidadeglosa, idservicocontrato FROM " + this.getTableName() + " WHERE idservicocontrato = ? and datafim is null ");
        parametro.add(idServicoContrato);
        final List list = this.execSQL(sql.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("quantidadeGlosa");
        listRetorno.add("idServicoContrato");
        final List<GlosaServicoContratoDTO> result = engine.listConvertion(this.getBean(), list, listRetorno);

        return result;
    }

    public void atualizaQuantidadeGlosa(final Integer novaQuantidade, final Integer idServicoContrato) throws PersistenceException {

        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        sql.append("UPDATE " + this.getTableName().toLowerCase() + " SET quantidadeglosa = ? WHERE idservicocontrato = ? ");
        parametro.add(novaQuantidade);
        parametro.add(idServicoContrato);
        this.execUpdate(sql.toString(), parametro.toArray());

    }

}
