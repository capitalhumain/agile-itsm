package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AcordoNivelServicoDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

public class AcordoNivelServicoDao extends CrudDaoDefaultImpl {

    public AcordoNivelServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", true, true, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("idPrioridadePadrao", "idPrioridadePadrao", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("tituloSLA", "tituloSLA", false, false, false, false));
        listFields.add(new Field("disponibilidade", "disponibilidade", false, false, false, false));
        listFields.add(new Field("descricaoSLA", "descricaoSLA", false, false, false, false));
        listFields.add(new Field("escopoSLA", "escopoSLA", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("avaliarEm", "avaliarEm", false, false, false, false));
        listFields.add(new Field("tipo", "tipo", false, false, false, false));
        listFields.add(new Field("valorLimite", "valorLimite", false, false, false, false));
        listFields.add(new Field("detalheGlosa", "detalheGlosa", false, false, false, false));
        listFields.add(new Field("detalheLimiteGlosa", "detalheLimiteGlosa", false, false, false, false));
        listFields.add(new Field("unidadeValorLimite", "unidadeValorLimite", false, false, false, false));
        listFields.add(new Field("impacto", "impacto", false, false, false, false));
        listFields.add(new Field("urgencia", "urgencia", false, false, false, false));
        listFields.add(new Field("permiteMudarImpUrg", "permiteMudarImpUrg", false, false, false, false));
        // listFields.add(new Field("idFormula" ,"idFormula", false, false, false, false));
        listFields.add(new Field("contatos", "contatos", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("tempoAuto", "tempoAuto", false, false, false, false));
        listFields.add(new Field("idPrioridadeAuto1", "idPrioridadeAuto1", false, false, false, false));
        listFields.add(new Field("idGrupo1", "idGrupo1", false, false, false, false));
        listFields.add(new Field("criadoEm", "criadoEm", false, false, false, false));
        listFields.add(new Field("criadoPor", "criadoPor", false, false, false, false));
        listFields.add(new Field("modificadoEm", "modificadoEm", false, false, false, false));
        listFields.add(new Field("modificadoPor", "modificadoPor", false, false, false, false));
        listFields.add(new Field("idEmail", "idEmail", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "AcordoNivelServico";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AcordoNivelServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdServicoContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", parm));
        ordenacao.add(new Order("dataInicio"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdServicoContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdPrioridadePadrao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idPrioridadePadrao", "=", parm));
        ordenacao.add(new Order("dataInicio"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdPrioridadePadrao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idPrioridadePadrao", "=", parm));
        super.deleteByCondition(condicao);
    }

    public AcordoNivelServicoDTO findAtivoByIdServicoContrato(final Integer idServicoContrato, final String tipo) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", idServicoContrato));
        condicao.add(new Condition("tipo", "=", tipo));
        condicao.add(new Condition("situacao", "=", "A"));

        final Collection<AcordoNivelServicoDTO> col = super.findByCondition(condicao, null);
        if (col == null || col.size() == 0) {
            return null;
        }
        AcordoNivelServicoDTO result = null;
        for (final AcordoNivelServicoDTO acordoNivelServicoDto : col) {
            if ((acordoNivelServicoDto.getDataFim() == null || UtilDatas.getDataAtual().before(acordoNivelServicoDto.getDataFim()))
                    && (acordoNivelServicoDto.getDeleted() == null || acordoNivelServicoDto.getDeleted().equalsIgnoreCase("N"))) {
                result = acordoNivelServicoDto;
            }
        }
        return result;
    }

    /**
     * Método para retornar os serviços que possuem o SLA selecionado já copiado, para ser tratado evitando duplicação de SLA.
     *
     * @param titulo
     *            do SLA selecionado
     * @return retorna os serviços que possuem o SLA selecionado
     * @throws Exception
     * @author rodrigo.oliveira
     */
    public List<ServicoContratoDTO> buscaServicosComContrato(final String tituloSla) throws PersistenceException {

        final List parametro = new ArrayList<>();
        List resp = new ArrayList<>();

        final String sql = "SELECT DISTINCT idServicoContrato FROM " + this.getTableName()
                + " WHERE titulosla LIKE ? AND idservicocontrato IS NOT NULL ORDER BY idservicocontrato ";

        parametro.add(tituloSla);

        resp = this.execSQL(sql, parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idServicoContrato");

        final List<ServicoContratoDTO> listconvertion = engine.listConvertion(ServicoContratoDTO.class, resp, listRetorno);

        return listconvertion;
    }

    /**
     * Método para verificar se existe cadastrado um cadastro o mesmo nome.
     *
     * @param String
     *            tituloSLA
     * @return true se o nome exisite e false se não existir
     * @throws Exception
     * @author rodrigo.oliveira
     */
    public boolean verificaSeNomeExiste(final String tituloSLA) throws PersistenceException {

        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "SELECT idacordonivelservico FROM " + this.getTableName() + " WHERE titulosla = ? ";

        parametro.add(tituloSLA);

        list = this.execSQL(sql, parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna os SLAs que não possuem vinculação direta com ServicoContrato
     *
     */
    public List<AcordoNivelServicoDTO> findAcordosSemVinculacaoDireta() throws PersistenceException {
        List resp = new ArrayList<>();

        final Collection fields = this.getFields();
        final List listRetorno = new ArrayList<>();
        String campos = "";
        for (final Iterator it = fields.iterator(); it.hasNext();) {
            final Field field = (Field) it.next();
            if (!campos.trim().equalsIgnoreCase("")) {
                campos = campos + ",";
            }
            campos = campos + field.getFieldDB();
            listRetorno.add(field.getFieldClass());
        }

        final String sql = "SELECT " + campos + " FROM " + this.getTableName() + " WHERE idservicocontrato IS NULL ORDER BY titulosla ";

        resp = this.execSQL(sql, null);

        final Collection<AcordoNivelServicoDTO> col = this.listConvertion(AcordoNivelServicoDTO.class, resp, listRetorno);
        if (col == null || col.size() == 0) {
            return null;
        }
        final List<AcordoNivelServicoDTO> result = new ArrayList<AcordoNivelServicoDTO>();
        for (final AcordoNivelServicoDTO acordoNivelServicoDTO : col) {
            if (acordoNivelServicoDTO.getDeleted() == null || acordoNivelServicoDTO.getDeleted().equalsIgnoreCase("N")) {
                if (acordoNivelServicoDTO.getDataFim() == null || acordoNivelServicoDTO.getDataFim().after(UtilDatas.getDataAtual())) {
                    result.add(acordoNivelServicoDTO);
                }
            }
        }
        return result;

    }

    /**
     * Encontra o SLAs pelo ID
     *
     * @author euler.ramos
     */
    public List<AcordoNivelServicoDTO> findByIdAcordoSemVinculacaoDireta(final Integer id) throws PersistenceException {
        List resp = new ArrayList<>();

        final Collection fields = this.getFields();
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        String campos = "";
        for (final Iterator it = fields.iterator(); it.hasNext();) {
            final Field field = (Field) it.next();
            if (!campos.trim().equalsIgnoreCase("")) {
                campos = campos + ",";
            }
            campos = campos + field.getFieldDB();
            listRetorno.add(field.getFieldClass());
        }

        final String sql = "SELECT "
                + campos
                + " FROM "
                + this.getTableName()
                + " WHERE idacordonivelservico=? and (idservicocontrato IS NULL) and ((deleted is null) or (deleted = 'N') or (deleted = 'n')) ORDER BY titulosla ";
        parametro.add(id);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<AcordoNivelServicoDTO>() : result;
    }

    /**
     * Encontra o SLAs pelo Titulo
     *
     * @author euler.ramos
     */
    public List<AcordoNivelServicoDTO> findByTituloSLA(final String titulo) throws PersistenceException {
        List resp = new ArrayList<>();

        final Collection fields = this.getFields();
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        String campos = "";
        for (final Iterator it = fields.iterator(); it.hasNext();) {
            final Field field = (Field) it.next();
            if (!campos.trim().equalsIgnoreCase("")) {
                campos = campos + ",";
            }
            campos = campos + field.getFieldDB();
            listRetorno.add(field.getFieldClass());
        }

        final String sql = "SELECT " + campos + " FROM " + this.getTableName()
                + " WHERE titulosla=? and (idservicocontrato IS NULL) and ((deleted is null) or (deleted = 'N') or (deleted = 'n')) ORDER BY titulosla ";
        parametro.add(titulo);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<AcordoNivelServicoDTO>() : result;
    }

    public void updateTemposAcoes(final AcordoNivelServicoDTO acordoNivelServicoDTO) throws PersistenceException {
        final AcordoNivelServicoDTO acordoNivelServicoAux = new AcordoNivelServicoDTO();
        acordoNivelServicoAux.setIdGrupo1(acordoNivelServicoDTO.getIdGrupo1());
        acordoNivelServicoAux.setIdPrioridadeAuto1(acordoNivelServicoDTO.getIdPrioridadeAuto1());
        acordoNivelServicoAux.setTempoAuto(acordoNivelServicoDTO.getTempoAuto());
        acordoNivelServicoAux.setIdAcordoNivelServico(acordoNivelServicoDTO.getIdAcordoNivelServico());
        acordoNivelServicoAux.setDeleted(acordoNivelServicoDTO.getDeleted());
        super.updateNotNull(acordoNivelServicoAux);
    }

    /**
     * @param idServicoContrato
     * @param data
     * @throws PersistenceException
     * @author cledson.junior
     */
    public void updateAcordoNivelServico(final Integer idServicoContrato, final Date data) throws PersistenceException {
        final List parametros = new ArrayList<>();
        if (data != null) {
            parametros.add(data);
        } else {
            parametros.add(null);
        }
        parametros.add("y");
        parametros.add(idServicoContrato);
        final String sql = "UPDATE " + this.getTableName() + " SET datafim = ?, deleted = ? WHERE idServicoContrato = ?";
        this.execUpdate(sql, parametros.toArray());
    }

    public List<AcordoNivelServicoDTO> findIdEmailByIdSolicitacaoServico(final Integer idSolicitacaoServico) throws PersistenceException {

        final List parametro = new ArrayList<>();
        List resp = new ArrayList<>();

        final String sql = "SELECT DISTINCT idEmail FROM " + this.getTableName()
                + " inner join  solicitacaoservico on solicitacaoservico.idservicocontrato = acordonivelservico.idservicocontrato"
                + " AND solicitacaoservico.idsolicitacaoservico = " + idSolicitacaoServico;

        resp = this.execSQL(sql, parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idEmail");

        final List<AcordoNivelServicoDTO> listconvertion = engine.listConvertion(ServicoContratoDTO.class, resp, listRetorno);

        return listconvertion;
    }

    public AcordoNivelServicoDTO findByIdAcordoNivelServicoEServicoContrato(final Integer idAcordoNivelServico, final Integer idServicoContrato)
            throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "select idacordonivelservico, idservicocontrato from acordonivelservico where idacordonivelservico = ? and idservicocontrato = ?";
        parametro.add(idAcordoNivelServico);
        parametro.add(idServicoContrato);
        list = this.execSQL(sql, parametro.toArray());
        fields.add("idAcordoNivelServico");
        fields.add("idServicoContrato");

        if (list != null && !list.isEmpty()) {
            return (AcordoNivelServicoDTO) this.listConvertion(this.getBean(), list, fields).get(0);
        } else {
            return null;
        }
    }

}
