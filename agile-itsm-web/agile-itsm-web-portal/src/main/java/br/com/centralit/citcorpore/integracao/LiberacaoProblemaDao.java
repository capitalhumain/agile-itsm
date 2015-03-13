package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LiberacaoProblemaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class LiberacaoProblemaDao extends CrudDaoDefaultImpl {

    public LiberacaoProblemaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idLiberacao", "idLiberacao", false, false, false, false));
        listFields.add(new Field("idProblema", "idProblema", true, false, false, false));
        listFields.add(new Field("idhistoricoliberacao", "idHistoricoLiberacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "LiberacaoProblema";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LiberacaoProblemaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdLiberacao(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idLiberacao", "=", parm));
        ordenacao.add(new Order("idProblema"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdLiberacao(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idLiberacao", "=", parm));
        super.deleteByCondition(condicao);
    }

    public ArrayList<LiberacaoProblemaDTO> listByIdRequisicaoLiberacao(final Integer idrequisicaoliberacao) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idLiberacao", "=", idrequisicaoliberacao));

        return (ArrayList<LiberacaoProblemaDTO>) super.findByCondition(condicoes, null);
    }

    public ArrayList<LiberacaoProblemaDTO> listByIdHistorico(final Integer idHistoricoLiberacao) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idHistoricoLiberacao", "=", idHistoricoLiberacao));

        return (ArrayList<LiberacaoProblemaDTO>) super.findByCondition(condicoes, null);
    }
}
