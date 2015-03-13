package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ContratoQuestionariosDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ContratoQuestionariosDao extends CrudDaoDefaultImpl {

    private static final String SQL_LIST_BY_ID_CONTRATO_AND_ABA = "SELECT CONTRATOQUESTIONARIOS.IDCONTRATOQUESTIONARIO AS idContratoQuestionario, CONTRATOQUESTIONARIOS.IDQUESTIONARIO AS idQuestionario, CONTRATOQUESTIONARIOS.IDCONTRATO AS idContrato, "
            + "       CONTRATOQUESTIONARIOS.DATAQUESTIONARIO AS dataQuestionario, CONTRATOQUESTIONARIOS.IDPROFISSIONAL as idProfissional, CONTRATOQUESTIONARIOS.IDEMPRESA as idEmpresa, "
            + "       CONTRATOQUESTIONARIOS.ABA AS aba, CONTRATOQUESTIONARIOS.SITUACAO as situacao, QUESTIONARIO.NOMEQUESTIONARIO AS nomeQuestionario, CONTRATOQUESTIONARIOS.situacaoComplemento as situacaoComplemento "
            + "  FROM CONTRATOQUESTIONARIOS LEFT OUTER JOIN "
            + "       QUESTIONARIO ON CONTRATOQUESTIONARIOS.IDQUESTIONARIO = QUESTIONARIO.IDQUESTIONARIO "
            + " WHERE CONTRATOQUESTIONARIOS.IDCONTRATO = ? "
            + "   AND UPPER(CONTRATOQUESTIONARIOS.ABA) IN (#ABA#) "
            + " ORDER BY CONTRATOQUESTIONARIOS.DATAQUESTIONARIO DESC, IDCONTRATOQUESTIONARIO DESC";

    private static final String SQL_LIST_BY_ID_CONTRATO_AND_ABA_CRESCENTE = "SELECT CONTRATOQUESTIONARIOS.IDCONTRATOQUESTIONARIO AS idContratoQuestionario, CONTRATOQUESTIONARIOS.IDQUESTIONARIO AS idQuestionario, CONTRATOQUESTIONARIOS.IDCONTRATO AS idContrato, "
            + "       CONTRATOQUESTIONARIOS.DATAQUESTIONARIO AS dataQuestionario, CONTRATOQUESTIONARIOS.IDPROFISSIONAL as idProfissional, CONTRATOQUESTIONARIOS.IDEMPRESA as idEmpresa, "
            + "       CONTRATOQUESTIONARIOS.ABA AS aba, CONTRATOQUESTIONARIOS.SITUACAO as situacao, Profissionais.ConhecidoComo AS profissional, QUESTIONARIO.NOMEQUESTIONARIO AS nomeQuestionario, CONTRATOQUESTIONARIOS.situacaoComplemento as situacaoComplemento "
            + "  FROM CONTRATOQUESTIONARIOS INNER JOIN "
            + "       Profissionais ON CONTRATOQUESTIONARIOS.IDPROFISSIONAL = Profissionais.IdProfissional INNER JOIN "
            + "       QUESTIONARIO ON CONTRATOQUESTIONARIOS.IDQUESTIONARIO = QUESTIONARIO.IDQUESTIONARIO "
            + " WHERE CONTRATOQUESTIONARIOS.IDCONTRATO = ? "
            + "   AND UPPER(CONTRATOQUESTIONARIOS.ABA) IN (#ABA#) " + " ORDER BY CONTRATOQUESTIONARIOS.DATAQUESTIONARIO, IDCONTRATOQUESTIONARIO DESC";

    private static final String SQL_ULTIMO_BY_ID_CONTRATO_AND_ABA = "SELECT CONTRATOQUESTIONARIOS.IDCONTRATOQUESTIONARIO AS idContratoQuestionario, CONTRATOQUESTIONARIOS.IDQUESTIONARIO AS idQuestionario, CONTRATOQUESTIONARIOS.IDCONTRATO AS idContrato, "
            + "       CONTRATOQUESTIONARIOS.DATAQUESTIONARIO AS dataQuestionario, CONTRATOQUESTIONARIOS.IDPROFISSIONAL as idProfissional, CONTRATOQUESTIONARIOS.IDEMPRESA as idEmpresa, "
            + "       CONTRATOQUESTIONARIOS.ABA AS aba, CONTRATOQUESTIONARIOS.SITUACAO as situacao, Profissionais.ConhecidoComo AS profissional, QUESTIONARIO.NOMEQUESTIONARIO AS nomeQuestionario"
            + "  FROM CONTRATOQUESTIONARIOS INNER JOIN "
            + "       Profissionais ON CONTRATOQUESTIONARIOS.IDPROFISSIONAL = Profissionais.IdProfissional INNER JOIN "
            + "       QUESTIONARIO ON CONTRATOQUESTIONARIOS.IDQUESTIONARIO = QUESTIONARIO.IDQUESTIONARIO "
            + " WHERE CONTRATOQUESTIONARIOS.IDCONTRATO = ? "
            + "   AND CONTRATOQUESTIONARIOS.ABA IN (#ABA#) " + " ORDER BY IDCONTRATOQUESTIONARIO DESC, CONTRATOQUESTIONARIOS.DATAQUESTIONARIO DESC";

    private static final String SQL_ULTIMO_BY_ID_CONTRATO_AND_ABA_PERIODO = "SELECT CONTRATOQUESTIONARIOS.IDCONTRATOQUESTIONARIO AS idContratoQuestionario, CONTRATOQUESTIONARIOS.IDQUESTIONARIO AS idQuestionario, CONTRATOQUESTIONARIOS.IDCONTRATO AS idContrato, "
            + "       CONTRATOQUESTIONARIOS.DATAQUESTIONARIO AS dataQuestionario, CONTRATOQUESTIONARIOS.IDPROFISSIONAL as idProfissional, CONTRATOQUESTIONARIOS.IDEMPRESA as idEmpresa, "
            + "       CONTRATOQUESTIONARIOS.ABA AS aba, CONTRATOQUESTIONARIOS.SITUACAO as situacao, Profissionais.ConhecidoComo AS profissional, QUESTIONARIO.NOMEQUESTIONARIO AS nomeQuestionario"
            + "  FROM CONTRATOQUESTIONARIOS INNER JOIN "
            + "       Profissionais ON CONTRATOQUESTIONARIOS.IDPROFISSIONAL = Profissionais.IdProfissional INNER JOIN "
            + "       QUESTIONARIO ON CONTRATOQUESTIONARIOS.IDQUESTIONARIO = QUESTIONARIO.IDQUESTIONARIO "
            + " WHERE CONTRATOQUESTIONARIOS.IDCONTRATO = ? "
            + "   AND CONTRATOQUESTIONARIOS.ABA IN (#ABA#) "
            + "   AND CONTRATOQUESTIONARIOS.DATAQUESTIONARIO BETWEEN ? AND ? "
            + " ORDER BY IDCONTRATOQUESTIONARIO DESC, CONTRATOQUESTIONARIOS.DATAQUESTIONARIO DESC";

    private static final String SQL_LIST_BY_ID_CONTRATO_AND_QUEST = "SELECT CONTRATOQUESTIONARIOS.IDCONTRATOQUESTIONARIO AS idContratoQuestionario, CONTRATOQUESTIONARIOS.IDQUESTIONARIO AS idQuestionario, CONTRATOQUESTIONARIOS.IDCONTRATO AS idContrato, "
            + "       CONTRATOQUESTIONARIOS.DATAQUESTIONARIO AS dataQuestionario, CONTRATOQUESTIONARIOS.IDEMPRESA as idEmpresa, "
            + "       CONTRATOQUESTIONARIOS.ABA AS aba, CONTRATOQUESTIONARIOS.SITUACAO as situacao, QUESTIONARIO.NOMEQUESTIONARIO AS nomeQuestionario"
            + "  FROM CONTRATOQUESTIONARIOS INNER JOIN "
            + "       QUESTIONARIO ON CONTRATOQUESTIONARIOS.IDQUESTIONARIO = QUESTIONARIO.IDQUESTIONARIO "
            + " WHERE CONTRATOQUESTIONARIOS.idQuestionario = ? "
            + "   AND CONTRATOQUESTIONARIOS.idContrato = ? "
            + " ORDER BY CONTRATOQUESTIONARIOS.DATAQUESTIONARIO DESC, IDCONTRATOQUESTIONARIO DESC";

    private static final String SQL_LIST_BY_ID_REL_ANUAL_AND_ABA = "SELECT COUNT(*) " + "  FROM CONTRATOQUESTIONARIOS "
            + " WHERE CONTRATOQUESTIONARIOS.IDCONTRATO IN (SELECT IDCONTRATO FROM LOTACAO WHERE DATAFIM IS NULL #COMPLEMENTO#) "
            + "   AND CONTRATOQUESTIONARIOS.ABA = ? ";

    private static final String SQL_LIST_BY_ID_REL_ANUAL_AND_ABA_AND_PERIODO = "SELECT COUNT(DISTINCT IDCONTRATO) " + "  FROM CONTRATOQUESTIONARIOS "
            + " WHERE CONTRATOQUESTIONARIOS.IDCONTRATO IN (SELECT IDCONTRATO FROM LOTACAO WHERE DATAFIM IS NULL #COMPLEMENTO#) "
            + "   AND CONTRATOQUESTIONARIOS.ABA = ? AND CONTRATOQUESTIONARIOS.DATAQUESTIONARIO BETWEEN ? AND ? ";

    public ContratoQuestionariosDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    public Collection findByIdContratoAndAbaAndData(final Integer idContrato, final String aba, final Date data) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idContrato", "=", idContrato));
        condicao.add(new Condition("aba", "=", aba));
        condicao.add(new Condition("dataQuestionario", "=", data));
        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final List lista = new ArrayList<>();

        lista.add(new Field("idContratoQuestionario", "idContratoQuestionario", true, false, false, false));
        lista.add(new Field("idQuestionario", "idQuestionario", false, false, false, false));
        lista.add(new Field("idContrato", "idContrato", false, false, false, false));
        lista.add(new Field("dataQuestionario", "dataQuestionario", false, false, false, false));
        lista.add(new Field("idProfissional", "idProfissional", false, false, false, false));
        lista.add(new Field("idEmpresa", "idEmpresa", false, false, false, false));
        lista.add(new Field("aba", "aba", false, false, false, false));
        lista.add(new Field("situacao", "situacao", false, false, false, false));
        lista.add(new Field("situacaoComplemento", "situacaoComplemento", false, false, false, false));
        lista.add(new Field("datahoragrav", "datahoragrav", false, false, false, false));
        lista.add(new Field("migrado", "migrado", false, false, false, false));
        lista.add(new Field("conteudoImpresso", "conteudoImpresso", false, false, false, false));
        lista.add(new Field("idMigracao", "idMigracao", false, false, false, false));

        return lista;
    }

    @Override
    public String getTableName() {
        return "ContratoQuestionarios";
    }

    public static String getTableNameAssDigital() {
        return "ContratoQuestionarios";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ContratoQuestionariosDTO.class;
    }

    public Collection listByIdContratoAndAba(final Integer idContrato, final String aba) throws PersistenceException {
        /*
         * List list = new ArrayList<>();
         * list.add(new Order("dataQuestionario"));
         * ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
         * obj.setIdContrato(idContrato);
         * obj.setAba(aba);
         * return super.find(obj, list);
         */
        final Object[] objs = new Object[] {idContrato};

        String sql = SQL_LIST_BY_ID_CONTRATO_AND_ABA;

        String abaX = "";
        boolean bPrimeiro = true;
        final String[] abaAux = aba.split(",");
        if (abaAux != null && abaAux.length > 0) {
            for (final String element : abaAux) {
                if (!bPrimeiro) {
                    abaX += ",";
                }
                abaX += "'" + element + "'";
                bPrimeiro = false;
            }
        } else {
            abaX = "'" + aba + "'";
        }

        abaX = abaX.toUpperCase();
        sql = sql.replaceAll("\\#ABA\\#", abaX);

        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idContratoQuestionario");
        listRetorno.add("idQuestionario");
        listRetorno.add("idContrato");
        listRetorno.add("dataQuestionario");
        listRetorno.add("idProfissional");
        listRetorno.add("idEmpresa");
        listRetorno.add("aba");
        listRetorno.add("situacao");
        listRetorno.add("nomeQuestionario");
        listRetorno.add("situacaoComplemento");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public Collection listByIdContratoAndAbaOrdemCrescente(final Integer idContrato, final String aba) throws PersistenceException {
        /*
         * List list = new ArrayList<>();
         * list.add(new Order("dataQuestionario"));
         * ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
         * obj.setIdContrato(idContrato);
         * obj.setAba(aba);
         * return super.find(obj, list);
         */
        final Object[] objs = new Object[] {idContrato};

        String sql = SQL_LIST_BY_ID_CONTRATO_AND_ABA_CRESCENTE;

        String abaX = "";
        boolean bPrimeiro = true;
        final String[] abaAux = aba.split(",");
        if (abaAux != null && abaAux.length > 0) {
            for (final String element : abaAux) {
                if (!bPrimeiro) {
                    abaX += ",";
                }
                abaX += "'" + element + "'";
                bPrimeiro = false;
            }
        } else {
            abaX = "'" + aba + "'";
        }

        abaX = abaX.toUpperCase();
        sql = sql.replaceAll("\\#ABA\\#", abaX);

        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idContratoQuestionario");
        listRetorno.add("idQuestionario");
        listRetorno.add("idContrato");
        listRetorno.add("dataQuestionario");
        listRetorno.add("idEmpresa");
        listRetorno.add("aba");
        listRetorno.add("situacao");
        listRetorno.add("profissional");
        listRetorno.add("nomeQuestionario");
        listRetorno.add("situacaoComplemento");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public ContratoQuestionariosDTO getUltimoByIdContratoAndAba(final Integer idContrato, final String aba) throws PersistenceException {
        final Object[] objs = new Object[] {idContrato};

        String sql = SQL_ULTIMO_BY_ID_CONTRATO_AND_ABA;

        String abaX = "";
        boolean bPrimeiro = true;
        final String[] abaAux = aba.split(",");
        if (abaAux != null && abaAux.length > 0) {
            for (final String element : abaAux) {
                abaX += "'" + element + "'";
                if (!bPrimeiro) {
                    abaX += ",";
                }
                bPrimeiro = false;
            }
        } else {
            abaX = "'" + aba + "'";
        }

        sql = sql.replaceAll("\\#ABA\\#", abaX);

        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idContratoQuestionario");
        listRetorno.add("idQuestionario");
        listRetorno.add("idContrato");
        listRetorno.add("dataQuestionario");
        listRetorno.add("idEmpresa");
        listRetorno.add("aba");
        listRetorno.add("situacao");
        listRetorno.add("profissional");
        listRetorno.add("nomeQuestionario");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return (ContratoQuestionariosDTO) result.get(0);
    }

    public ContratoQuestionariosDTO getUltimoByIdContratoAndAbaAndPeriodo(final Integer idContrato, final String aba, final Date dataInicio, final Date dataFim)
            throws PersistenceException {
        final Object[] objs = new Object[] {idContrato, dataInicio, dataFim};

        String sql = SQL_ULTIMO_BY_ID_CONTRATO_AND_ABA_PERIODO;

        String abaX = "";
        boolean bPrimeiro = true;
        final String[] abaAux = aba.split(",");
        if (abaAux != null && abaAux.length > 0) {
            for (final String element : abaAux) {
                abaX += "'" + element + "'";
                if (!bPrimeiro) {
                    abaX += ",";
                }
                bPrimeiro = false;
            }
        } else {
            abaX = "'" + aba + "'";
        }

        sql = sql.replaceAll("\\#ABA\\#", abaX);

        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idContratoQuestionario");
        listRetorno.add("idQuestionario");
        listRetorno.add("idContrato");
        listRetorno.add("dataQuestionario");
        listRetorno.add("idEmpresa");
        listRetorno.add("aba");
        listRetorno.add("situacao");
        listRetorno.add("profissional");
        listRetorno.add("nomeQuestionario");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return (ContratoQuestionariosDTO) result.get(0);
    }

    public ContratoQuestionariosDTO getQuantidadeByIdDepEstabAndAba(final Integer idDepartamento, final Integer idEstabelecimento, final Integer idCargo,
            final String aba) throws PersistenceException {
        String sql = SQL_LIST_BY_ID_REL_ANUAL_AND_ABA;
        String complemento = "";
        final List lstParms = new ArrayList<>();
        if (idEstabelecimento != null) {
            complemento += " AND idEstabelecimento = ?";
            lstParms.add(idEstabelecimento);
        }
        if (idDepartamento != null) {
            complemento += " AND idDepartamento = ?";
            lstParms.add(idDepartamento);
        }
        if (idCargo != null) {
            complemento += " AND idCargo = ?";
            lstParms.add(idCargo);
        }
        sql = sql.replaceAll("#COMPLEMENTO#", complemento);

        lstParms.add(aba);
        final Object[] objs = lstParms.toArray();
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("qtde");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return (ContratoQuestionariosDTO) result.get(0);
    }

    public ContratoQuestionariosDTO getQuantidadeByIdDepEstabAndAbaAndPeriodo(final Integer idDepartamento, final Integer idEstabelecimento,
            final Integer idCargo, final String aba, final Date dataInicio, final Date dataFim) throws PersistenceException {
        String sql = SQL_LIST_BY_ID_REL_ANUAL_AND_ABA_AND_PERIODO;
        String complemento = "";
        final List lstParms = new ArrayList<>();
        if (idEstabelecimento != null) {
            complemento += " AND idEstabelecimento = ?";
            lstParms.add(idEstabelecimento);
        }
        if (idDepartamento != null) {
            complemento += " AND idDepartamento = ?";
            lstParms.add(idDepartamento);
        }
        if (idCargo != null) {
            complemento += " AND idCargo = ?";
            lstParms.add(idCargo);
        }
        sql = sql.replaceAll("#COMPLEMENTO#", complemento);

        lstParms.add(aba);
        lstParms.add(dataInicio);
        lstParms.add(dataFim);
        final Object[] objs = lstParms.toArray();
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("qtde");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return (ContratoQuestionariosDTO) result.get(0);
    }

    public ContratoQuestionariosDTO getQuantidadeByIdDepEstabAndAbaAndPeriodoFinalizados(final Integer idDepartamento, final Integer idEstabelecimento,
            final Integer idCargo, final String aba, final Date dataInicio, final Date dataFim) throws PersistenceException {
        String sql = SQL_LIST_BY_ID_REL_ANUAL_AND_ABA_AND_PERIODO;
        String complemento = "";
        final List lstParms = new ArrayList<>();
        if (idEstabelecimento != null) {
            complemento += " AND idEstabelecimento = ?";
            lstParms.add(idEstabelecimento);
        }
        if (idDepartamento != null) {
            complemento += " AND idDepartamento = ?";
            lstParms.add(idDepartamento);
        }
        if (idCargo != null) {
            complemento += " AND idCargo = ?";
            lstParms.add(idCargo);
        }
        complemento += " AND CONTRATOQUESTIONARIOS.situacao = 'F' ";

        sql = sql.replaceAll("#COMPLEMENTO#", complemento);

        lstParms.add(aba);
        lstParms.add(dataInicio);
        lstParms.add(dataFim);
        final Object[] objs = lstParms.toArray();
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("qtde");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return (ContratoQuestionariosDTO) result.get(0);
    }

    public ContratoQuestionariosDTO getQuantidadeByIdDepEstabFuncaoAndAbaAndPeriodo(final Integer idDepartamento, final Integer idEstabelecimento,
            final Integer idCargo, final Integer idFuncao, final String aba, final Date dataInicio, final Date dataFim) throws PersistenceException {
        String sql = SQL_LIST_BY_ID_REL_ANUAL_AND_ABA_AND_PERIODO;
        String complemento = "";
        final List lstParms = new ArrayList<>();
        if (idEstabelecimento != null) {
            complemento += " AND idEstabelecimento = ?";
            lstParms.add(idEstabelecimento);
        }
        if (idDepartamento != null) {
            complemento += " AND idDepartamento = ?";
            lstParms.add(idDepartamento);
        }
        if (idCargo != null) {
            complemento += " AND idCargo = ?";
            lstParms.add(idCargo);
        }
        if (idFuncao != null) {
            complemento += " AND idFuncao = ?";
            lstParms.add(idFuncao);
        }
        sql = sql.replaceAll("#COMPLEMENTO#", complemento);

        lstParms.add(aba);
        lstParms.add(dataInicio);
        lstParms.add(dataFim);
        final Object[] objs = lstParms.toArray();
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("qtde");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return (ContratoQuestionariosDTO) result.get(0);
    }

    public Collection listByIdContratoAndQuestionario(final Integer idQuestionario, final Integer idContrato) throws PersistenceException {
        final Object[] objs = new Object[] {idQuestionario, idContrato};
        final List lista = this.execSQL(SQL_LIST_BY_ID_CONTRATO_AND_QUEST, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idContratoQuestionario");
        listRetorno.add("idQuestionario");
        listRetorno.add("idContrato");
        listRetorno.add("dataQuestionario");
        listRetorno.add("idEmpresa");
        listRetorno.add("aba");
        listRetorno.add("situacao");
        listRetorno.add("nomeQuestionario");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public Collection listByIdContrato(final Integer idContrato) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("dataQuestionario"));
        list.add(new Order("aba"));
        final ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
        obj.setIdContrato(idContrato);
        return super.find(obj, list);
    }

    public Collection listByIdContratoOrderDecrescente(final Integer idContrato) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("dataQuestionario", Order.DESC));
        list.add(new Order("aba"));
        final ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
        obj.setIdContrato(idContrato);
        return super.find(obj, list);
    }

    public Collection listByIdContratoOrderIdDecrescente(final Integer idContrato) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idContratoQuestionario", Order.DESC));
        list.add(new Order("dataQuestionario", Order.DESC));
        list.add(new Order("aba"));

        final ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
        obj.setIdContrato(idContrato);
        return super.find(obj, list);
    }

    public void updateSituacaoComplemento(final Integer idPessQuest, final String situacaoComplemento) throws PersistenceException {
        final ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
        obj.setIdContratoQuestionario(idPessQuest);
        obj.setSituacaoComplemento(situacaoComplemento);
        super.updateNotNull(obj);
    }

    public void updateConteudoImpresso(final Integer idPessQuest, final String conteudoImpresso) throws PersistenceException {
        final ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
        obj.setIdContratoQuestionario(idPessQuest);
        obj.setConteudoImpresso(conteudoImpresso);
        super.updateNotNull(obj);
    }

}
