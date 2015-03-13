package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ConhecimentoSolicitacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ConhecimentoSolicitacaoDao extends CrudDaoDefaultImpl {

    public ConhecimentoSolicitacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDSOLICITACAOSERVICO", "idSolicitacaoServico", true, false, false, false));
        listFields.add(new Field("IDBASECONHECIMENTO", "idBaseConhecimento", true, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CONHECIMENTOSOLICITACAOSERVICO";
    }

    @Override
    public Class<ConhecimentoSolicitacaoDTO> getBean() {
        return ConhecimentoSolicitacaoDTO.class;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public void deleteByidSolicitacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByIdBaseConhecimento(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idBaseConhecimento", "=", parm));
        super.deleteByCondition(condicao);
    }

    public boolean seCadastrada(final ConhecimentoSolicitacaoDTO conhecimentoSolicitacaoDTO) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idBaseConhecimento", "=", conhecimentoSolicitacaoDTO.getIdBaseConhecimento()));
        condicao.add(new Condition("idSolicitacaoServico", "=", conhecimentoSolicitacaoDTO.getIdSolicitacaoServico()));
        final Collection<ConhecimentoSolicitacaoDTO> collection = super.findByCondition(condicao, ordenacao);
        if (collection == null || collection.size() == 0) {
            return false;
        } else {
            return !collection.isEmpty();
        }
    }

    public Collection findByIdBaseConhecimento(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idBaseConhecimento", "=", parm));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByidSolicitacaoServico(final Integer idsolicitacaoservico) throws PersistenceException {

        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "select conhecimentosolicitacaoservico.idsolicitacaoservico,baseconhecimento.idbaseconhecimento, baseconhecimento.titulo "
                + "from baseconhecimento inner join conhecimentosolicitacaoservico on baseconhecimento.idbaseconhecimento = conhecimentosolicitacaoservico.idbaseconhecimento "
                + "where conhecimentosolicitacaoservico.idsolicitacaoservico = ?";
        parametro.add(idsolicitacaoservico);

        list = this.execSQL(sql, parametro.toArray());
        fields.add("idSolicitacaoServico");
        fields.add("idBaseConhecimento");
        fields.add("titulo");

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(ConhecimentoSolicitacaoDTO.class, list, fields);
        } else {
            return null;
        }
    }

    public void deleteByIdSolictacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        super.deleteByCondition(condicao);
    }

}
