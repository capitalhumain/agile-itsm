package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.OrigemAtendimentoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class OrigemAtendimentoDao extends CrudDaoDefaultImpl {

    public OrigemAtendimentoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idOrigem", "idOrigem", true, true, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "OrigemAtendimento";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idOrigem"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return OrigemAtendimentoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Retorna lista de status de usuário.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public boolean consultarOrigemAtendimentoAtivos(final OrigemAtendimentoDTO obj) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "select idorigem From " + this.getTableName() + "  where  descricao = ?   and dataFim is null ";

        if (obj.getIdOrigem() != null) {
            sql += " and idorigem <> " + obj.getIdOrigem();
        }

        parametro.add(obj.getDescricao());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Collection<OrigemAtendimentoDTO> listarTodosAtivos() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("descricao"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * @author euler.ramos
     *         Busca a Origem de Atendimento filtrando pela descrição passada como parâmetro
     * @param descricao
     * @return
     */
    public OrigemAtendimentoDTO buscarOrigemAtendimento(final String descricao) {
        List result;
        try {
            final List parametro = new ArrayList<>();
            List resp = new ArrayList<>();
            final List listRetorno = new ArrayList<>();

            listRetorno.add("idOrigem");
            listRetorno.add("descricao");
            listRetorno.add("dataInicio");
            listRetorno.add("dataFim");

            final String sql = "select idOrigem,descricao,dataInicio,dataFim From " + this.getTableName() + "  where  descricao = ?   and dataFim is null ";
            parametro.add(descricao);

            resp = this.execSQL(sql.toString(), parametro.toArray());
            result = engine.listConvertion(OrigemAtendimentoDTO.class, resp, listRetorno);
        } catch (final PersistenceException e) {
            e.printStackTrace();
            result = null;
        } catch (final Exception e) {
            e.printStackTrace();
            result = null;
        }
        return (OrigemAtendimentoDTO) (result == null || result.size() <= 0 ? new OrigemAtendimentoDTO() : result.get(0));
    }

}
