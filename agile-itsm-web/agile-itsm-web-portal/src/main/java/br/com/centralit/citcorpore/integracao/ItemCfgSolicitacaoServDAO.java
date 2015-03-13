package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ItemCfgSolicitacaoServDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ItemCfgSolicitacaoServDAO extends CrudDaoDefaultImpl {

    public ItemCfgSolicitacaoServDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idsolicitacaoservico", "idsolicitacaoservico", false, false, false, false));
        listFields.add(new Field("idItemConfiguracao", "idItemConfiguracao", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    public ItemCfgSolicitacaoServDTO findByIdItemConfiguracaoEidsolicitacaoservico(final Integer idItemConfiguracao, final Integer idsolicitacaoservico)
            throws PersistenceException {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();
        condicoes.add(new Condition("idItemConfiguracao", "=", idItemConfiguracao));
        condicoes.add(new Condition("idsolicitacaoservico", "=", idsolicitacaoservico));

        final ArrayList<ItemCfgSolicitacaoServDTO> lista = (ArrayList<ItemCfgSolicitacaoServDTO>) this.findByCondition(condicoes, null);

        if (lista != null) {
            return lista.get(0);
        }

        return null;
    }

    /**
     * Verifica se existe outro item igual criado.
     * Se existir retorna 'true', senao retorna 'false';
     */
    public boolean verificaSeCadastrado(final ItemCfgSolicitacaoServDTO itemDTO) throws PersistenceException {
        boolean estaCadastrado;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select * from " + this.getTableName() + " where idItemConfiguracao = ? and idsolicitacaoservico = ?  ");
        parametro.add(itemDTO.getIdItemConfiguracao());
        parametro.add(itemDTO.getIdSolicitacaoServico());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            estaCadastrado = true;
        } else {
            estaCadastrado = false;
        }
        return estaCadastrado;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ItemCfgSolicitacaoServ";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ItemCfgSolicitacaoServDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdItemConfiguracao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idItemConfiguracao", "=", parm));
        ordenacao.add(new Order("idItemConfiguracao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdSolicitacaoServico(final Integer parm) throws PersistenceException {

        final List parametro = new ArrayList<>();
        final List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select sol.idSolicitacaoservico, sol.iditemconfiguracao, item.identificacao, item.iditemconfiguracaopai from itemcfgsolicitacaoserv sol ");
        sql.append("inner join itemconfiguracao item on item.iditemconfiguracao = sol.iditemconfiguracao ");
        sql.append("where sol.idsolicitacaoservico = ? ");
        parametro.add(parm);

        final List retorno = this.execSQL(sql.toString(), parametro.toArray());

        list.add("idsolicitacaoservico");
        list.add("idItemConfiguracao");
        list.add("identificacao");
        list.add("idItemConfiguracaoPai");

        return this.listConvertion(ItemCfgSolicitacaoServDTO.class, retorno, list);
    }

    public void deleteByIdItemConfiguracao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idItemConfiguracao", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByIdSolicitacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idsolicitacaoservico", "=", parm));
        super.deleteByCondition(condicao);
    }

}
