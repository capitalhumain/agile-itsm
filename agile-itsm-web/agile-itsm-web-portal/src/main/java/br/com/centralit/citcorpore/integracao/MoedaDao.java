package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.MoedaDTO;
import br.com.citframework.dto.Usuario;
import br.com.citframework.excecao.InvalidTransactionControler;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.util.Constantes;

public class MoedaDao extends CrudDaoDefaultImpl {

    public MoedaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    public MoedaDao(final TransactionControler tc, final Usuario usuario) throws InvalidTransactionControler {
        super(tc, usuario);

    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idMoeda", "idMoeda", true, true, false, false));
        listFields.add(new Field("nomeMoeda", "nomeMoeda", false, false, false, false));
        listFields.add(new Field("usarCotacao", "usarCotacao", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "MOEDAS";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeMoeda"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return MoedaDTO.class;
    }

    /**
     * Verifica se existe uma moeda com o mesmo nome já cadastrada
     * Se existir retorna 'true', se nao existir retorna 'false';
     *
     * @param moedaDTO
     * @return estaCadastrato
     * @throws PersistenceException
     */
    public boolean verificaSeCadastrado(final MoedaDTO moedaDTO) throws PersistenceException {
        boolean estaCadastrato;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idMoeda from " + this.getTableName() + "  where  nomeMoeda = ? and datafim is null  ");
        parametro.add(moedaDTO.getNomeMoeda());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            estaCadastrato = true;
        } else {
            estaCadastrato = false;
        }
        return estaCadastrato;
    }

    /**
     * Verifica se existe relacionamento de moeda com outras tabelas.
     * Se existir retorna 'true', se nao existir retorna 'false';
     *
     * @param moedaDTO
     * @throws PersistenceException
     */
    public boolean verificaRelacionamento(final MoedaDTO moedaDTO) throws PersistenceException {
        boolean estaRelacionado;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idcontrato from contratos where  idMoeda = ? and deleted is null ");
        parametro.add(moedaDTO.getIdMoeda());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            estaRelacionado = true;
        } else {
            estaRelacionado = false;
        }
        return estaRelacionado;
    }

    public Collection findAtivos() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("dataFim", "IS", null));
        ordenacao.add(new Order("nomeMoeda"));
        return super.findByCondition(condicao, ordenacao);
    }

}
