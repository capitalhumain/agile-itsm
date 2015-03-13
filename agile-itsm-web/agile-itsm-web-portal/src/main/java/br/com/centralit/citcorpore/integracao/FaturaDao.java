package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.FaturaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class FaturaDao extends CrudDaoDefaultImpl {

    public FaturaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idFatura", "idFatura", true, true, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("dataInicial", "dataInicial", false, false, false, false));
        listFields.add(new Field("dataFinal", "dataFinal", false, false, false, false));
        listFields.add(new Field("descricaoFatura", "descricaoFatura", false, false, false, false));
        listFields.add(new Field("valorCotacaoMoeda", "valorCotacaoMoeda", false, false, false, false));
        listFields.add(new Field("dataCriacao", "dataCriacao", false, false, false, false));
        listFields.add(new Field("dataUltModificacao", "dataUltModificacao", false, false, false, false));
        listFields.add(new Field("valorPrevistoSomaOS", "valorPrevistoSomaOS", false, false, false, false));
        listFields.add(new Field("valorSomaGlosasOS", "valorSomaGlosasOS", false, false, false, false));
        listFields.add(new Field("valorExecutadoSomaOS", "valorExecutadoSomaOS", false, false, false, false));
        listFields.add(new Field("observacao", "observacao", false, false, false, false));
        listFields.add(new Field("aprovacaoGestor", "aprovacaoGestor", false, false, false, false));
        listFields.add(new Field("aprovacaoFiscal", "aprovacaoFiscal", false, false, false, false));
        listFields.add(new Field("saldoPrevisto", "saldoPrevisto", false, false, false, false));
        listFields.add(new Field("situacaoFatura", "situacaoFatura", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Fatura";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return FaturaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idContrato", "=", parm));
        ordenacao.add(new Order("idGlosaOS"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idContrato", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdContratoAndPeriodoAndSituacao(final Integer idContrato, final Date dataInicio, final Date dataFim, final String[] situacao)
            throws PersistenceException {
        final List lstParametros = new ArrayList<>();
        String sql = "SELECT idFatura, descricaoFatura, dataInicial, dataFinal, valorCotacaoMoeda, dataCriacao, situacaoFatura, valorPrevistoSomaOS, valorSomaGlosasOS, valorExecutadoSomaOS, ";
        sql = sql + " (SELECT COUNT(*) FROM FaturaOS fatOs WHERE fatOs.idFatura = F.idFatura) AS qtdeOS ";
        sql = sql + " FROM " + this.getTableName() + " F ";
        sql = sql + "WHERE idContrato = ? ";
        lstParametros.add(idContrato);
        if (situacao != null || dataInicio != null || dataFim != null) {
            if (dataInicio != null && dataFim != null) {
                sql = sql + "AND ";
                sql = sql + " (dataInicial BETWEEN ? AND ?) ";
                lstParametros.add(dataInicio);
                lstParametros.add(dataFim);
            } else {
                if (dataInicio != null) {
                    sql = sql + "AND ";
                    sql = sql + " (dataInicial = ?) ";
                    lstParametros.add(dataInicio);
                }
                if (dataFim != null) {
                    sql = sql + "AND ";
                    sql = sql + " (dataFinal = ?) ";
                    lstParametros.add(dataFim);
                }
            }
            if (situacao != null) {
                sql = sql + "AND ";
                sql = sql + " (situacaoFatura IN (";
                String strSqlAux = "";
                for (final String element : situacao) {
                    if (!strSqlAux.equalsIgnoreCase("")) {
                        strSqlAux = strSqlAux + ",";
                    }
                    strSqlAux = strSqlAux + "'" + element + "'";
                }
                sql = sql + strSqlAux;
                sql = sql + ")) ";
            }
        }
        sql = sql + "ORDER BY dataInicial, dataFinal, idFatura ";
        final Object[] parametros = lstParametros.toArray();
        final List lstDados = super.execSQL(sql, parametros);

        final List fields = new ArrayList<>();
        fields.add("idFatura");
        fields.add("descricaoFatura");
        fields.add("dataInicial");
        fields.add("dataFinal");
        fields.add("valorCotacaoMoeda");
        fields.add("dataCriacao");
        fields.add("situacaoFatura");
        fields.add("valorPrevistoSomaOS");
        fields.add("valorSomaGlosasOS");
        fields.add("valorExecutadoSomaOS");
        fields.add("qtdeOS");

        return super.listConvertion(FaturaDTO.class, lstDados, fields);
    }

    public void updateSituacao(final Integer idFatura, final String situacao, final String aprovacaoGestor, final String aprovacaoFiscal)
            throws PersistenceException {
        final FaturaDTO faturaDto = new FaturaDTO();
        faturaDto.setIdFatura(idFatura);
        faturaDto.setSituacaoFatura(situacao);
        faturaDto.setAprovacaoGestor(aprovacaoGestor);
        faturaDto.setAprovacaoFiscal(aprovacaoFiscal);
        super.updateNotNull(faturaDto);
    }

}
