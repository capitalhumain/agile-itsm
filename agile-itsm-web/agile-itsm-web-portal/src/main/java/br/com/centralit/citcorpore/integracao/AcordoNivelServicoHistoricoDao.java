package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AcordoNivelServicoHistoricoDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class AcordoNivelServicoHistoricoDao extends CrudDaoDefaultImpl {

    public AcordoNivelServicoHistoricoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idacordonivelservico_hist", "idAcordoNivelServico_Hist", true, true, false, false));
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", false, false, false, false));
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
        listFields.add(new Field("criadoEm", "criadoEm", false, false, false, false));
        listFields.add(new Field("criadoPor", "criadoPor", false, false, false, false));
        listFields.add(new Field("modificadoEm", "modificadoEm", false, false, false, false));
        listFields.add(new Field("modificadoPor", "modificadoPor", false, false, false, false));
        listFields.add(new Field("conteudodados", "conteudodados", false, false, false, false));
        // listFields.add(new Field("idFormula" ,"idFormula", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "acordonivelservico_hist";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AcordoNivelServicoHistoricoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdAcordoNivelServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", parm));
        ordenacao.add(new Order("idAcordoNivelServico_Hist"));
        return super.findByCondition(condicao, ordenacao);
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

    public AcordoNivelServicoHistoricoDTO findAtivoByIdServicoContrato(final Integer idServicoContrato, final String tipo) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", idServicoContrato));
        condicao.add(new Condition("tipo", "=", tipo));

        final Collection<AcordoNivelServicoHistoricoDTO> col = super.findByCondition(condicao, null);
        if (col == null || col.size() == 0) {
            return null;
        }
        AcordoNivelServicoHistoricoDTO result = null;
        for (final AcordoNivelServicoHistoricoDTO acordoNivelServicoHistoricoDTO : col) {
            if (acordoNivelServicoHistoricoDTO.getDataFim() == null
                    && (acordoNivelServicoHistoricoDTO.getDeleted() == null || acordoNivelServicoHistoricoDTO.getDeleted().equalsIgnoreCase("N"))) {
                result = acordoNivelServicoHistoricoDTO;
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
     * Retorna os SLAs que nao possuem vinculacao direta com ServicoContrato
     *
     */
    public List<AcordoNivelServicoHistoricoDTO> findAcordosSemVinculacaoDireta() throws PersistenceException {
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

        final List<AcordoNivelServicoHistoricoDTO> listconvertion = engine.listConvertion(AcordoNivelServicoHistoricoDTO.class, resp, listRetorno);

        return listconvertion;
    }

}
