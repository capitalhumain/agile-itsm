package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CausaIncidenteDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.SQLConfig;

public class CausaIncidenteDao extends CrudDaoDefaultImpl {

    public CausaIncidenteDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idCausaIncidente", "idCausaIncidente", true, true, false, false));
        listFields.add(new Field("idCausaIncidentePai", "idCausaIncidentePai", false, false, false, false));
        listFields.add(new Field("descricaoCausa", "descricaoCausa", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "CausaIncidente";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idCausaIncidente"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return CausaIncidenteDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findSemPai() throws PersistenceException {

        String sql = "SELECT idCausaIncidente, idCausaIncidentePai, descricaoCausa, dataInicio, dataFim FROM causaincidente WHERE idCausaIncidentePai IS NULL AND dataFim IS NULL AND ";
        if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.POSTGRESQL)) {
            sql += "(UPPER(deleted) IS NULL OR UPPER(deleted) = 'N') ";
        } else if (CITCorporeUtil.SGBD_PRINCIPAL.equalsIgnoreCase(SQLConfig.SQLSERVER)) {
            sql += "(deleted IS NULL OR deleted = 'N') ";
        } else {
            sql += "(deleted IS NULL OR deleted = 'N') ";
        }
        sql += "ORDER BY descricaoCausa ";

        final List colDados = this.execSQL(sql, null);
        if (colDados != null) {
            final List fields = new ArrayList<>();
            fields.add("idCausaIncidente");
            fields.add("idCausaIncidentePai");
            fields.add("descricaoCausa");
            fields.add("dataInicio");
            fields.add("dataFim");
            return this.listConvertion(CausaIncidenteDTO.class, colDados, fields);
        }
        return null;
    }

    public Collection findByIdPai(final Integer idCausaIncidentePaiParm) throws PersistenceException {
        String sql = "SELECT idCausaIncidente, idCausaIncidentePai, descricaoCausa, dataInicio, dataFim FROM causaincidente "
                + "WHERE idCausaIncidentePai = ? AND dataFim IS NULL AND ";
        if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.POSTGRESQL)) {
            sql += "(UPPER(deleted) IS NULL OR UPPER(deleted) = 'N') ";
        } else if (CITCorporeUtil.SGBD_PRINCIPAL.equalsIgnoreCase(SQLConfig.SQLSERVER)) {
            sql += "(deleted IS NULL OR deleted = 'N') ";
        } else {
            sql += "(deleted IS NULL OR deleted = 'N') ";
        }
        sql += "ORDER BY descricaoCausa ";

        final List colDados = this.execSQL(sql, new Object[] {idCausaIncidentePaiParm});
        if (colDados != null) {
            final List fields = new ArrayList<>();
            fields.add("idCausaIncidente");
            fields.add("idCausaIncidentePai");
            fields.add("descricaoCausa");
            fields.add("dataInicio");
            fields.add("dataFim");
            return this.listConvertion(CausaIncidenteDTO.class, colDados, fields);
        }
        return null;
    }

    public Collection findByIdCausaIncidentePai(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCausaIncidentePai", "=", parm));
        ordenacao.add(new Order("descricaoCausa"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCausaIncidentePai(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCausaIncidentePai", "=", parm));
        super.deleteByCondition(condicao);
    }

    /**
     * Retorna uma lista de causa de acordo com o parametro passado
     *
     * @param descricaoCausa
     * @return
     * @throws Exception
     * @author thays.araujo
     */
    public Collection listaCausaByDescricaoCausa(String descricaoCausa) throws PersistenceException {

        final List fields = new ArrayList<>();
        if (descricaoCausa == null) {
            descricaoCausa = "";
        }

        String text = descricaoCausa;
        text = text.replaceAll("´`^''-+=", "");
        descricaoCausa = text;
        descricaoCausa = "%" + descricaoCausa + "%";
        final List parametros = new ArrayList<>();

        parametros.add(descricaoCausa);

        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT idcausaincidente , descricaoCausa");
        sql.append(" FROM causaincidente ");

        if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.POSTGRESQL)) {
            sql.append("where  descricaoCausa ilike ? ");
        } else {
            sql.append("where  UPPER(descricaoCausa) like UPPER(?) ");
        }

        sql.append(" ORDER BY descricaoCausa");

        final List listaR = this.execSQL(sql.toString(), parametros.toArray());

        fields.add("idCausaIncidente");
        fields.add("descricaoCausa");

        return this.listConvertion(CausaIncidenteDTO.class, listaR, fields);
    }

    public Collection listaCausasAtivas() throws PersistenceException {
        final List fields = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT idcausaincidente, descricaoCausa FROM causaincidente WHERE datafim is NULL AND (deleted is NULL OR deleted = 'n') ");

        final List listaR = this.execSQL(sql.toString(), null);

        fields.add("idCausaIncidente");
        fields.add("descricaoCausa");

        return this.listConvertion(CausaIncidenteDTO.class, listaR, fields);
    }

}
