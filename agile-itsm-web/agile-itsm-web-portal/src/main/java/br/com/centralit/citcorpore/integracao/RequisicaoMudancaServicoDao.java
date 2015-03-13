package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class RequisicaoMudancaServicoDao extends CrudDaoDefaultImpl {

    public RequisicaoMudancaServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idrequisicaomudancaservico", "idRequisicaoMudancaServico", true, true, false, false));
        listFields.add(new Field("idrequisicaomudanca", "idRequisicaoMudanca", false, false, false, false));
        listFields.add(new Field("idservico", "idServico", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "requisicaomudancaservico";
    }

    @Override
    public Class getBean() {
        return RequisicaoMudancaServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return super.find(arg0, null);
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("idrequisicaomudancaservico");
    }

    @Override
    public int deleteByCondition(final List<Condition> condicao) throws PersistenceException {
        return super.deleteByCondition(condicao);
    }

    public ArrayList<RequisicaoMudancaServicoDTO> listByIdRequisicaoMudanca(final Integer idRequisicaoMudanca) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idRequisicaoMudanca", "=", idRequisicaoMudanca));

        return (ArrayList<RequisicaoMudancaServicoDTO>) super.findByCondition(condicoes, null);
    }

    /**
     * Retorna o item de relacionamento específico sem a chave primária da tabela.
     * Uma espécie de consulta por chave composta.
     *
     * @param dto
     * @return
     * @throws Exception
     * @throws ServiceException
     */
    public RequisicaoMudancaServicoDTO restoreByChaveComposta(final RequisicaoMudancaServicoDTO dto) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idRequisicaoMudanca", "=", dto.getIdRequisicaoMudanca()));
        condicoes.add(new Condition("idServico", "=", dto.getIdServico()));

        final ArrayList<RequisicaoMudancaServicoDTO> retorno = (ArrayList<RequisicaoMudancaServicoDTO>) super.findByCondition(condicoes, null);
        if (retorno != null) {
            return retorno.get(0);
        }
        return null;
    }

    public void deleteByIdRequisicaoMudanca(final Integer idRequisicaoMudanca) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idRequisicaoMudanca", "=", idRequisicaoMudanca));

        super.deleteByCondition(condicoes);
    }

    public Collection findByIdMudancaEDataFim(final Integer idRequisicaoMudanca) throws Exception {
        final List fields = new ArrayList<>();

        final String sql = " select idrequisicaomudancaservico,idrequisicaomudanca,idservico,datafim from requisicaomudancaservico where  idrequisicaomudanca = ? and datafim is null";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoMudanca});

        fields.add("idRequisicaoMudancaServico");
        fields.add("idRequisicaoMudanca");
        fields.add("idServico");
        fields.add("dataFim");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

    public Collection listByIdHistoricoMudanca(final Integer idRequisicaoMudanca) throws Exception {
        final List fields = new ArrayList<>();

        final String sql = " select distinct rs.idrequisicaomudancaservico, rs.idrequisicaomudanca, rs.idservico,datafim "
                + "from requisicaomudancaservico rs "
                + "inner join ligacao_mud_hist_se ligser on ligser.idrequisicaomudancaservico = rs.idrequisicaomudancaservico "
                + "where  ligser.idhistoricomudanca = ?";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoMudanca});

        fields.add("idRequisicaoMudancaServico");
        fields.add("idRequisicaoMudanca");
        fields.add("idServico");
        fields.add("dataFim");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

}
