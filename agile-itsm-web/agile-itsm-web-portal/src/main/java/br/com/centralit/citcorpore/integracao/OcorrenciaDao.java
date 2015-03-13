package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.DadosEmailRegOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class OcorrenciaDao extends CrudDaoDefaultImpl {

    private static final String SQL_OCORRENCIA_DEMANDA = "SELECT O.ocorrencia, O.tipoOcorrencia, O.respostaOcorrencia, O.data, E.nome, O.idOcorrencia, O.idDemanda "
            + "FROM OCORRENCIAS O " + " INNER JOIN EMPREGADOS E on E.idEmpregado = O.idEmpregado " + "where idDemanda = ? order by data";

    public OcorrenciaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return OcorrenciaDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idOcorrencia", "idOcorrencia", true, true, false, false));
        listFields.add(new Field("idDemanda", "idDemanda", false, false, false, false));
        listFields.add(new Field("ocorrencia", "ocorrencia", false, false, false, false));
        listFields.add(new Field("tipoOcorrencia", "tipoOcorrencia", false, false, false, false));
        listFields.add(new Field("respostaOcorrencia", "respostaOcorrencia", false, false, false, false));
        listFields.add(new Field("data", "data", false, false, false, false));
        listFields.add(new Field("idEmpregado", "idEmpregado", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "OCORRENCIAS";
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

        final String sql = SQL_OCORRENCIA_DEMANDA;

        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("ocorrencia");
        listRetorno.add("tipoOcorrencia");
        listRetorno.add("respostaOcorrencia");
        listRetorno.add("data");
        listRetorno.add("nomeEmpregado");
        listRetorno.add("idOcorrencia");
        listRetorno.add("idDemanda");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return result;
    }

    public void updateResposta(final BaseEntity obj) throws PersistenceException {
        final OcorrenciaDTO ocorrencia = (OcorrenciaDTO) obj;
        final OcorrenciaDTO ocorrenciaUpdate = new OcorrenciaDTO();

        ocorrenciaUpdate.setIdOcorrencia(ocorrencia.getIdOcorrencia());
        ocorrenciaUpdate.setRespostaOcorrencia(ocorrencia.getRespostaOcorrencia());

        super.updateNotNull(ocorrenciaUpdate);
    }

    public Collection<OcorrenciaDTO> findByIdSolicitacao(final Integer idSolicitacao) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();
        sql.append("select ocorrencia from ocorrenciasolicitacao where idsolicitacaoservico = ?");
        parametro.add(idSolicitacao);

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("ocorrencia");

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, listRetorno);
        } else {
            return null;
        }
    }

    public OcorrenciaDTO findSiglaGrupoExecutorByIdSolicitacao(final Integer idSolicitacao) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();
        sql.append("select  sigla from ocorrenciasolicitacao o " + "inner join solicitacaoservico ss on o.idsolicitacaoservico = ss.idsolicitacaoservico "
                + "inner join servicocontrato sc on sc.idservicocontrato = ss.idservicocontrato " + "inner join grupo g on sc.idgrupoexecutor = g.idgrupo "
                + "where o.idsolicitacaoservico = ?");

        parametro.add(idSolicitacao);

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("sigla");

        if (list != null && !list.isEmpty()) {
            return (OcorrenciaDTO) this.listConvertion(this.getBean(), list, listRetorno).get(0);
        } else {
            return null;
        }
    }

    /**
     * @param idSolicitacaoServico
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public DadosEmailRegOcorrenciaDTO obterDadosResponsavelEmailRegOcorrencia(final Integer idSolicitacaoServico) throws PersistenceException {

        final StringBuilder query = new StringBuilder();

        query.append("select emp.idempregado,sol.idresponsavel,emp.email, emp.nome, sol.idgrupoatual from solicitacaoservico sol ");

        query.append("INNER JOIN empregados emp on emp.idempregado = sol.idresponsavel ");

        query.append("where sol.idsolicitacaoservico =? ");

        final List parametros = new ArrayList<>();

        parametros.add(idSolicitacaoServico);

        final List list = this.execSQL(query.toString(), parametros.toArray());

        final List listRetorno = new ArrayList<>();

        listRetorno.add("idEmpregado");

        listRetorno.add("idResponsavelAtual");

        listRetorno.add("email");

        listRetorno.add("nome");

        listRetorno.add("idGrupoAtual");

        if (list != null && !list.isEmpty()) {

            return (DadosEmailRegOcorrenciaDTO) this.listConvertion(DadosEmailRegOcorrenciaDTO.class, list, listRetorno).get(0);

        }
        return null;
    }

}
