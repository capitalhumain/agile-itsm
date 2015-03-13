package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AcordoServicoContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

public class AcordoServicoContratoDao extends CrudDaoDefaultImpl {

    public AcordoServicoContratoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAcordoServicoContrato", "idAcordoServicoContrato", true, true, false, false));
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", false, false, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("dataCriacao", "dataCriacao", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("dataUltAtualiz", "dataUltAtualiz", false, false, false, false));
        listFields.add(new Field("idRecurso", "idRecurso", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("habilitado", "habilitado", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "AcordoServicoContrato";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AcordoServicoContratoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdAcordoNivelServico(final Integer idAcordoNivelServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        ordenacao.add(new Order("idServicoContrato"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdAcordoServicoContrato(final Integer idAcordoServicoContrato) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoServicoContrato", "=", idAcordoServicoContrato));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdServicoContrato(final Integer idServicoContrato) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", idServicoContrato));
        ordenacao.add(new Order("idAcordoNivelServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    public AcordoServicoContratoDTO findAtivoByIdServicoContrato(final Integer idServicoContrato, final String tipo) throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add(idServicoContrato);
        parametros.add(tipo);

        final List fields = new ArrayList<>();
        fields.add("idAcordoServicoContrato");
        fields.add("idAcordoNivelServico");
        fields.add("idServicoContrato");
        fields.add("dataCriacao");
        fields.add("dataInicio");
        fields.add("dataFim");
        fields.add("dataUltAtualiz");
        fields.add("deleted");

        final String sql = "SELECT A.idacordoservicocontrato, A.idacordonivelservico, A.idservicocontrato, A.dataCriacao, A.dataInicio, A.dataFim, A.dataUltAtualiz, A.deleted FROM "
                + this.getTableName()
                + " A INNER JOIN acordonivelservico B ON A.idacordonivelservico = B.idacordonivelservico AND B.idservicocontrato IS NULL "
                + "WHERE A.idservicocontrato = ? AND B.tipo = ? AND (A.deleted IS NULL or UPPER(A.deleted) = 'N') AND (B.deleted IS NULL or UPPER(B.deleted) = 'N') AND A.habilitado = 'S' ";
        final List colDados = this.execSQL(sql, parametros.toArray());
        final Collection<AcordoServicoContratoDTO> col = this.listConvertion(AcordoServicoContratoDTO.class, colDados, fields);
        if (col == null || col.size() == 0) {
            return null;
        }
        AcordoServicoContratoDTO result = null;
        for (final AcordoServicoContratoDTO acordoNivelServicoDto : col) {
            if ((acordoNivelServicoDto.getDataFim() == null || UtilDatas.getDataAtual().before(acordoNivelServicoDto.getDataFim()))
                    && (acordoNivelServicoDto.getDeleted() == null || acordoNivelServicoDto.getDeleted().equalsIgnoreCase("N"))) {
                result = acordoNivelServicoDto;
            }
        }
        return result;
    }

    public void deleteByIdServicoContrato(final Integer idServicoContrato) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", idServicoContrato));
        super.deleteByCondition(condicao);
    }

    /**
     * Verifica se existe relacionamento na base de dados entre a ANS e o Contrato
     *
     * @author flavio.santana
     * @param idAcordoNivelServico
     * @param idContrato
     * @return
     * @throws Exception
     */
    public boolean existeAcordoServicoContrato(final Integer idAcordoNivelServico, final Integer idContrato) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List fields = new ArrayList<>();
        final List parametros = new ArrayList<>();
        sql.append("select ascn.idAcordoNivelServico from acordoServicoContrato ascn ");
        sql.append("inner join servicocontrato sc on sc.idservicocontrato = ascn.idservicocontrato ");
        sql.append("inner join contratos c on c.idcontrato =  sc.idcontrato ");
        sql.append("where ascn.idAcordoNivelServico = ? and c.idcontrato = ? ");

        fields.add("idAcordoNivelServico");
        parametros.add(idAcordoNivelServico);
        parametros.add(idContrato);

        final Collection<AcordoServicoContratoDTO> obj = this.execSQL(sql.toString(), parametros.toArray());
        return obj != null && !obj.isEmpty() ? true : false;
    }

    public Collection<AcordoServicoContratoDTO> findByIdAcordoNivelServicoIdServicoContrato(final Integer idAcordoNivelServico, final Integer idServicoContrato)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<Condition>();
        final List<Order> ordenacao = new ArrayList<Order>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        condicao.add(new Condition("idServicoContrato", "=", idServicoContrato));
        return super.findByCondition(condicao, ordenacao);
    }

    public List<AcordoServicoContratoDTO> listAtivoByIdServicoContrato(final Integer idAcordoServicoContrato, final Integer idServicoContrato, final String tipo)
            throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add(idAcordoServicoContrato);
        parametros.add(idServicoContrato);
        parametros.add(tipo);

        final List fields = new ArrayList<>();
        fields.add("idAcordoServicoContrato");
        fields.add("idAcordoNivelServico");
        fields.add("idServicoContrato");
        fields.add("dataCriacao");
        fields.add("dataInicio");
        fields.add("dataFim");
        fields.add("dataUltAtualiz");
        fields.add("deleted");

        final String sql = "SELECT A.idacordoservicocontrato, A.idacordonivelservico, A.idservicocontrato, A.dataCriacao, A.dataInicio, A.dataFim, A.dataUltAtualiz, A.deleted FROM "
                + this.getTableName()
                + " A INNER JOIN acordonivelservico B ON A.idacordonivelservico = B.idacordonivelservico AND B.idservicocontrato IS NULL "
                + "WHERE A.idAcordoServicoContrato <> ? and A.idservicocontrato = ? AND B.tipo = ? AND (A.deleted IS NULL or UPPER(A.deleted) = 'N') AND (B.deleted IS NULL or UPPER(B.deleted) = 'N') ";
        final List colDados = this.execSQL(sql, parametros.toArray());
        final List<AcordoServicoContratoDTO> col = this.listConvertion(AcordoServicoContratoDTO.class, colDados, fields);
        if (col == null || col.size() == 0) {
            return null;
        }
        return col;
    }

    public List<AcordoServicoContratoDTO> listAtivoByIdServicoContrato(final Integer idServicoContrato, final String tipo) throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add(idServicoContrato);
        parametros.add(tipo);

        final List fields = new ArrayList<>();
        fields.add("idAcordoServicoContrato");
        fields.add("idAcordoNivelServico");
        fields.add("idServicoContrato");
        fields.add("dataCriacao");
        fields.add("dataInicio");
        fields.add("dataFim");
        fields.add("dataUltAtualiz");
        fields.add("deleted");

        final String sql = "SELECT A.idacordoservicocontrato, A.idacordonivelservico, A.idservicocontrato, A.dataCriacao, A.dataInicio, A.dataFim, A.dataUltAtualiz, A.deleted FROM "
                + this.getTableName()
                + " A INNER JOIN acordonivelservico B ON A.idacordonivelservico = B.idacordonivelservico AND B.idservicocontrato IS NULL "
                + "WHERE A.idservicocontrato = ? AND B.tipo = ? AND (A.deleted IS NULL or UPPER(A.deleted) = 'N') AND (B.deleted IS NULL or UPPER(B.deleted) = 'N') ";
        final List colDados = this.execSQL(sql, parametros.toArray());
        final List<AcordoServicoContratoDTO> col = this.listConvertion(AcordoServicoContratoDTO.class, colDados, fields);
        if (col == null || col.size() == 0) {
            return null;
        }
        return col;
    }

    /**
     * Retorna o ANS do Contrato
     *
     * @author flavio.santana
     * @param idAcordoNivelServico
     * @param idContrato
     * @return
     * @throws Exception
     */
    public List<AcordoServicoContratoDTO> findBylistByIdAcordoNivelServicoAndContrato(final Integer idAcordoNivelServico, final Integer idContrato)
            throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List fields = new ArrayList<>();
        final List parametros = new ArrayList<>();
        sql.append("select ascn.idAcordoServicoContrato from " + this.getTableName() + " ascn ");
        sql.append("inner join servicocontrato sc on sc.idservicocontrato = ascn.idservicocontrato ");
        sql.append("inner join contratos c on c.idcontrato =  sc.idcontrato ");
        sql.append("where ascn.idAcordoNivelServico = ? and c.idcontrato = ? ");

        fields.add("idAcordoServicoContrato");
        parametros.add(idAcordoNivelServico);
        parametros.add(idContrato);

        final List colDados = this.execSQL(sql.toString(), parametros.toArray());
        return this.listConvertion(AcordoServicoContratoDTO.class, colDados, fields);
    }

}
