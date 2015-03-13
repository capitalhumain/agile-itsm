package br.com.centralit.citquestionario.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citquestionario.bean.QuestaoQuestionarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class QuestaoQuestionarioDao extends CrudDaoDefaultImpl {

    private static final String SQL_LIST_BY_ID_GRUPO_QUESTIONARIO = "SELECT idQuestaoQuestionario, idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, " + "valorPermitido1, valorPermitido2, idImagem "
            + "FROM QUESTAOQUESTIONARIO " + "WHERE idGrupoQuestionario = ? AND idQuestaoAgrupadora is null " + "ORDER BY idGrupoQuestionario, sequenciaQuestao";

    private static final String SQL_LIST_BY_ID_GRUPO_QUESTIONARIO_COM_AGRUPADORA = "SELECT idQuestaoQuestionario, idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, " + "valorPermitido1, valorPermitido2, idImagem "
            + "FROM QUESTAOQUESTIONARIO " + "WHERE idGrupoQuestionario = ? " + "ORDER BY idGrupoQuestionario, sequenciaQuestao";

    private static final String SQL_LIST_BY_ID_QUESTAO_AGRUPADORA = "SELECT idQuestaoQuestionario, idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, " + "valorPermitido1, valorPermitido2, idImagem "
            + "FROM QUESTAOQUESTIONARIO " + "WHERE idQuestaoAgrupadora = ? AND tipoQuestao not in ('3','4','5') " + "ORDER BY sequenciaQuestao";

    private static final String SQL_LIST_CABECALHOS_LINHA = "SELECT idQuestaoQuestionario, idGrupoQuestionario, idQuestaoAgrupadora, "
            + "tituloQuestaoQuestionario, tipoQuestao, tipo, sequenciaQuestao " + "FROM QUESTAOQUESTIONARIO "
            + "WHERE idQuestaoAgrupadora = ? AND tipoQuestao = '4' " + "ORDER BY sequenciaQuestao";

    private static final String SQL_LIST_CABECALHOS_COLUNA = "SELECT idQuestaoQuestionario, idGrupoQuestionario, idQuestaoAgrupadora, "
            + "tituloQuestaoQuestionario, tipoQuestao, tipo, sequenciaQuestao " + "FROM QUESTAOQUESTIONARIO "
            + "WHERE idQuestaoAgrupadora = ? AND tipoQuestao = '5' " + "ORDER BY sequenciaQuestao";

    private static final String SQL_LIST_BY_SIGLA = "SELECT idQuestaoQuestionario, idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, " + "valorPermitido1, valorPermitido2, idImagem "
            + "FROM QUESTAOQUESTIONARIO " + "WHERE sigla = ? " + "  AND idQuestaoOrigem <> ? " + "  AND idQuestaoQuestionario <> ? ";

    private static final String SQL_LIST_BY_SIGLA_AND_IDQUESTIONARIO = "SELECT idQuestaoQuestionario, Q.idGrupoQuestionario idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, "
            + "valorPermitido1, valorPermitido2, idImagem "
            + "FROM QUESTAOQUESTIONARIO Q, GRUPOQUESTIONARIO G "
            + "WHERE Q.idGrupoQuestionario = G.idGrupoQuestionario"
            + "  AND sigla = ? "
            + "  AND G.idQuestionario = ? ";

    private static final String SQL_LIST_BY_TIPOQUESTAO_AND_IDQUESTIONARIO = "SELECT idQuestaoQuestionario, Q.idGrupoQuestionario idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, "
            + "valorPermitido1, valorPermitido2, idImagem "
            + "FROM QUESTAOQUESTIONARIO Q, GRUPOQUESTIONARIO G "
            + "WHERE Q.idGrupoQuestionario = G.idGrupoQuestionario"
            + "  AND Q.tipoquestao = ? "
            + "  AND G.idQuestionario = ? ";

    private static final String SQL_LIST_BY_TIPO_AND_IDQUESTIONARIO = "SELECT idQuestaoQuestionario, Q.idGrupoQuestionario idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, "
            + "valorPermitido1, valorPermitido2, idImagem "
            + "FROM QUESTAOQUESTIONARIO Q, GRUPOQUESTIONARIO G "
            + "WHERE Q.idGrupoQuestionario = G.idGrupoQuestionario"
            + "  AND Q.tipo = ? "
            + "  AND G.idQuestionario = ? ";

    private static final String SQL_LIST_BY_TIPO_AND_IDQUESTAO = "SELECT "
            + "Q.idQuestaoQuestionario, Q.idGrupoQuestionario idGrupoQuestionario, idQuestaoAgrupadora, "
            + "idQuestaoOrigem, tituloQuestaoQuestionario, tipoQuestao, sequenciaQuestao, "
            + "valorDefault, textoInicial, tamanho, decimais, tipo, infoResposta, valoresReferencia, unidade, "
            + "obrigatoria, ponderada, qtdeLinhas, qtdeColunas, cabecalhoLinhas, cabecalhoColunas, nomeListagem, ultimoValor, "
            + "idSubQuestionario, abaResultSubForm, sigla, imprime, calculada, editavel, "
            + "valorPermitido1, valorPermitido2, idImagem, idRespostaItemQuestionario, dataQuestionario "
            + "FROM RespostaItemQuestionario RIQ inner join ContratoQuestionarios PQ ON RIQ.idIdentificadorResposta = PQ.idContratoQuestionario "
            + "inner join QuestaoQuestionario Q on Q.idQuestaoQuestionario = RIQ.idQuestaoQuestionario " + "WHERE PQ.idContrato = ? AND Q.idQuestaoOrigem = ? ";

    public QuestaoQuestionarioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<QuestaoQuestionarioDTO> find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idQuestaoQuestionario", "idQuestaoQuestionario", true, true, false, false));
        listFields.add(new Field("idGrupoQuestionario", "idGrupoQuestionario", false, false, false, false));
        listFields.add(new Field("idQuestaoAgrupadora", "idQuestaoAgrupadora", false, false, false, false));
        listFields.add(new Field("idQuestaoOrigem", "idQuestaoOrigem", false, false, false, false));
        listFields.add(new Field("tipo", "tipo", false, false, false, false));
        listFields.add(new Field("tituloQuestaoQuestionario", "tituloQuestaoQuestionario", false, false, false, false));
        listFields.add(new Field("tipoQuestao", "tipoQuestao", false, false, false, false));
        listFields.add(new Field("sequenciaQuestao", "sequenciaQuestao", false, false, false, false));
        listFields.add(new Field("valorDefault", "valorDefault", false, false, false, false));
        listFields.add(new Field("textoInicial", "textoInicial", false, false, false, false));
        listFields.add(new Field("tamanho", "tamanho", false, false, false, false));
        listFields.add(new Field("decimais", "decimais", false, false, false, false));
        listFields.add(new Field("infoResposta", "infoResposta", false, false, false, false));
        listFields.add(new Field("unidade", "unidade", false, false, false, false));
        listFields.add(new Field("valoresReferencia", "valoresReferencia", false, false, false, false));
        listFields.add(new Field("obrigatoria", "obrigatoria", false, false, false, false));
        listFields.add(new Field("ponderada", "ponderada", false, false, false, false));
        listFields.add(new Field("qtdeLinhas", "qtdeLinhas", false, false, false, false));
        listFields.add(new Field("qtdeColunas", "qtdeColunas", false, false, false, false));
        listFields.add(new Field("cabecalhoLinhas", "cabecalhoLinhas", false, false, false, false));
        listFields.add(new Field("cabecalhoColunas", "cabecalhoColunas", false, false, false, false));
        listFields.add(new Field("nomeListagem", "nomeListagem", false, false, false, false));
        listFields.add(new Field("ultimoValor", "ultimoValor", false, false, false, false));
        listFields.add(new Field("idSubQuestionario", "idSubQuestionario", false, false, false, false));
        listFields.add(new Field("abaResultSubForm", "abaResultSubForm", false, false, false, false));
        listFields.add(new Field("sigla", "sigla", false, false, false, false));
        listFields.add(new Field("imprime", "imprime", false, false, false, false));
        listFields.add(new Field("calculada", "calculada", false, false, false, false));
        listFields.add(new Field("editavel", "editavel", false, false, false, false));
        listFields.add(new Field("valorPermitido1", "valorPermitido1", false, false, false, false));
        listFields.add(new Field("valorPermitido2", "valorPermitido2", false, false, false, false));
        listFields.add(new Field("idImagem", "idImagem", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "QuestaoQuestionario";
    }

    @Override
    public Collection<QuestaoQuestionarioDTO> list() throws PersistenceException {
        return null;
    }

    @Override
    public Class<QuestaoQuestionarioDTO> getBean() {
        return QuestaoQuestionarioDTO.class;
    }

    public int deleteByIdGrupoQuestionario(final Integer idGrupoQuestionario) throws PersistenceException {
        final Collection<QuestaoQuestionarioDTO> colQuestoes = this.findByIdGrupoQuestionario(idGrupoQuestionario);
        if (colQuestoes != null) {
            final OpcaoRespostaQuestionarioDao opcaoRespostaDao = new OpcaoRespostaQuestionarioDao();
            opcaoRespostaDao.setTransactionControler(this.getTransactionControler());
            for (final QuestaoQuestionarioDTO questaoDto : colQuestoes) {
                opcaoRespostaDao.deleteByIdQuestaoQuestionario(questaoDto.getIdQuestaoQuestionario());
            }
        }
        final Condition cond = new Condition("idGrupoQuestionario", "=", idGrupoQuestionario);
        final List lstCond = new ArrayList<>();

        lstCond.add(cond);
        return super.deleteByCondition(lstCond);
    }

    public Collection findByIdGrupoQuestionario(final Integer idGrupoQuestionario) throws PersistenceException {
        final List<Order> listOrder = new ArrayList<>();
        listOrder.add(new Order("idQuestaoQuestionario"));
        final List<Condition> listCond = new ArrayList<>();
        listCond.add(new Condition("idGrupoQuestionario", "=", idGrupoQuestionario));
        return super.findByCondition(listCond, listOrder);
    }

    public Collection listByIdGrupoQuestionario(final Integer idGrupoQuestionario) throws PersistenceException {
        final Object[] objs = new Object[] {idGrupoQuestionario};
        final List lista = this.execSQL(SQL_LIST_BY_ID_GRUPO_QUESTIONARIO, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public Collection listByIdGrupoQuestionarioComAgrupadoras(final Integer idGrupoQuestionario) throws PersistenceException {
        final Object[] objs = new Object[] {idGrupoQuestionario};
        final List lista = this.execSQL(SQL_LIST_BY_ID_GRUPO_QUESTIONARIO_COM_AGRUPADORA, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public Collection listByIdQuestaoAgrupadora(final Integer idQuestaoAgrupadora) throws PersistenceException {
        final Object[] objs = new Object[] {idQuestaoAgrupadora};
        final List lista = this.execSQL(SQL_LIST_BY_ID_QUESTAO_AGRUPADORA, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    private Collection listCabecalhos(final Integer idQuestaoAgrupadora, final String SQL) throws PersistenceException {

        final Object[] objs = new Object[] {idQuestaoAgrupadora};
        final List lista = this.execSQL(SQL, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("tipo");
        listRetorno.add("sequenciaQuestao");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public Collection listCabecalhosLinha(final Integer idQuestaoAgrupadora) throws PersistenceException {
        return this.listCabecalhos(idQuestaoAgrupadora, SQL_LIST_CABECALHOS_LINHA);
    }

    public Collection listCabecalhosColuna(final Integer idQuestaoAgrupadora) throws PersistenceException {
        return this.listCabecalhos(idQuestaoAgrupadora, SQL_LIST_CABECALHOS_COLUNA);
    }

    public Collection listBySiglaAndIdQuestao(final String sigla, final Integer idQuestaoOrigem, final Integer idQuestao) throws PersistenceException {
        final Object[] objs = new Object[] {sigla, idQuestaoOrigem, idQuestao};
        final List lista = this.execSQL(SQL_LIST_BY_SIGLA, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public QuestaoQuestionarioDTO findBySiglaAndIdQuestionario(final String sigla, final Integer idQuestionario) throws PersistenceException {
        final Object[] objs = new Object[] {sigla, idQuestionario};
        final List lista = this.execSQL(SQL_LIST_BY_SIGLA_AND_IDQUESTIONARIO, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        final QuestaoQuestionarioDTO objRetorno = (QuestaoQuestionarioDTO) result.get(0);
        return objRetorno;
    }

    public Collection listByTipoQuestaoAndIdQuestionario(final String tipoQuestao, final Integer idQuestionario) throws PersistenceException {
        final Object[] objs = new Object[] {tipoQuestao, idQuestionario};
        final List lista = this.execSQL(SQL_LIST_BY_TIPOQUESTAO_AND_IDQUESTIONARIO, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");

        return engine.listConvertion(this.getBean(), lista, listRetorno);
    }

    public Collection listByTipoAndIdQuestionario(final String tipo, final Integer idQuestionario) throws PersistenceException {
        final Object[] objs = new Object[] {tipo, idQuestionario};
        final List lista = this.execSQL(SQL_LIST_BY_TIPO_AND_IDQUESTIONARIO, objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");

        return engine.listConvertion(this.getBean(), lista, listRetorno);
    }

    public Collection listByIdQuestaoAndContrato(final Integer idQuestao, final Integer idContrato) throws PersistenceException {
        final Object[] objs = new Object[] {idContrato, idQuestao};
        final List lista = this.execSQL(SQL_LIST_BY_TIPO_AND_IDQUESTAO + " ORDER BY dataQuestionario DESC", objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");
        listRetorno.add("idRespostaItemQuestionario");
        listRetorno.add("dataRegistro");

        return engine.listConvertion(this.getBean(), lista, listRetorno);
    }

    public Collection listByIdQuestaoAndContratoOrderDataASC(final Integer idQuestao, final Integer idContrato) throws PersistenceException {
        final Object[] objs = new Object[] {idContrato, idQuestao};
        final List lista = this.execSQL(SQL_LIST_BY_TIPO_AND_IDQUESTAO + " ORDER BY dataQuestionario ASC", objs);

        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("idQuestaoQuestionario");
        listRetorno.add("idGrupoQuestionario");
        listRetorno.add("idQuestaoAgrupadora");
        listRetorno.add("idQuestaoOrigem");
        listRetorno.add("tituloQuestaoQuestionario");
        listRetorno.add("tipoQuestao");
        listRetorno.add("sequenciaQuestao");
        listRetorno.add("valorDefault");
        listRetorno.add("textoInicial");
        listRetorno.add("tamanho");
        listRetorno.add("decimais");
        listRetorno.add("tipo");
        listRetorno.add("infoResposta");
        listRetorno.add("valoresReferencia");
        listRetorno.add("unidade");
        listRetorno.add("obrigatoria");
        listRetorno.add("ponderada");
        listRetorno.add("qtdeLinhas");
        listRetorno.add("qtdeColunas");
        listRetorno.add("cabecalhoLinhas");
        listRetorno.add("cabecalhoColunas");
        listRetorno.add("nomeListagem");
        listRetorno.add("ultimoValor");
        listRetorno.add("idSubQuestionario");
        listRetorno.add("abaResultSubForm");
        listRetorno.add("sigla");
        listRetorno.add("imprime");
        listRetorno.add("calculada");
        listRetorno.add("editavel");
        listRetorno.add("valorPermitido1");
        listRetorno.add("valorPermitido2");
        listRetorno.add("idImagem");
        listRetorno.add("idRespostaItemQuestionario");
        listRetorno.add("dataRegistro");

        return engine.listConvertion(this.getBean(), lista, listRetorno);
    }

}
