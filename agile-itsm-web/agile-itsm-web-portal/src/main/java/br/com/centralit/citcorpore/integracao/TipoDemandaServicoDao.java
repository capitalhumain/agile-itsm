package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AnexoBaseConhecimentoDTO;
import br.com.centralit.citcorpore.bean.TipoDemandaServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class TipoDemandaServicoDao extends CrudDaoDefaultImpl {

    public TipoDemandaServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return TipoDemandaServicoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idTipoDemandaServico", "idTipoDemandaServico", true, true, false, false));
        listFields.add(new Field("nomeTipoDemandaServico", "nomeTipoDemandaServico", false, false, false, false));
        listFields.add(new Field("classificacao", "classificacao", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "tipodemandaservico";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeTipoDemandaServico"));
        return super.list(list);
    }

    public Collection<TipoDemandaServicoDTO> listSolicitacoes() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("classificacao", "<>", "O"));
        condicao.add(new Condition("deleted", "is", null));
        condicao.add(new Condition(Condition.OR, "deleted", "<>", "Y"));
        ordenacao.add(new Order("nomeTipoDemandaServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna lista de Tipo Demanda por nome.
     *
     * @return Collection
     * @throws Exception
     */
    public Collection findByNome(final TipoDemandaServicoDTO tipoDemandaServicoDTO) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("nomeTipoDemandaServico", "=", tipoDemandaServicoDTO.getNomeTipoDemandaServico()));
        ordenacao.add(new Order("nomeTipoDemandaServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * @author euler.ramos
     * @param tipoDemandaServicoDTO
     * @return
     * @throws Exception
     */
    public Collection findByClassificacao(final String classificacao) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        sql.append("select * from tipodemandaservico");

        if (classificacao != null && classificacao.length() > 0) {
            sql.append(" where (classificacao in (" + classificacao + "))");
        }
        sql.append(" order by idtipodemandaservico ");
        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), null);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idTipoDemandaServico");
        listRetorno.add("nomeTipoDemandaServico");
        listRetorno.add("classificacao");
        listRetorno.add("deleted");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result == null ? new ArrayList<AnexoBaseConhecimentoDTO>() : result;
    }

    /**
     * @see br.com.centralit.citcorpore.negocio.TipoDemandaService#validarExclusaoVinculada(HashMap)
     *
     * @author Ezequiel
     */
    public List validarExclusaoVinculada(final Map mapFields) throws PersistenceException {

        final StringBuilder query = new StringBuilder();

        final Integer idTipoDemanda = Integer.valueOf(mapFields.get("IDTIPODEMANDASERVICO").toString());

        query.append(" SELECT COUNT(IDSERVICO) as quantidade");

        query.append(" FROM SERVICO s ");

        query.append(" INNER JOIN TIPODEMANDASERVICO tp ON tp.idtipodemandaservico = s.idtipodemandaservico");

        query.append(" WHERE s.idtipodemandaservico = " + idTipoDemanda);

        final List parametro = new ArrayList<>();

        final List list = this.execSQL(query.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();

        listRetorno.add("quantidade");

        return engine.listConvertion(this.getBean(), list, listRetorno);

    }

}
