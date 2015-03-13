package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PerfilAcessoSituacaoOSDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class PerfilAcessoSituacaoOSDao extends CrudDaoDefaultImpl {

    public PerfilAcessoSituacaoOSDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idPerfil", "idPerfil", true, false, false, false));
        listFields.add(new Field("situacaoOs", "situacaoOs", true, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "PerfilAcessoSituacaoOS";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return PerfilAcessoSituacaoOSDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Retorna PerfilAcessoSituacaoOS ativos por idPerfilAcesso.
     *
     * @param idPerfilAcesso
     * @return
     * @throws Exception
     */
    public Collection findByIdPerfil(final Integer idPerfilAcesso) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idPerfil", "=", idPerfilAcesso));
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("situacaoOs"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdPerfil(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idPerfil", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findBySituacaoOs(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("situacaoOs", "=", parm));
        ordenacao.add(new Order("idPerfil"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteBySituacaoOs(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("situacaoOs", "=", parm));
        super.deleteByCondition(condicao);
    }

}
