package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SituacaoLiberacaoMudancaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 *
 * @author geber.costa
 *
 */
public class SituacaoLiberacaoMudancaDAO extends CrudDaoDefaultImpl {

    public SituacaoLiberacaoMudancaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {

        return null;
    }

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idSituacaoLiberacaoMudanca", "idSituacaoLiberacaoMudanca", true, true, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "situacaoliberacaomudanca";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("situacao"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return SituacaoLiberacaoMudancaDTO.class;
    }

    /**
     * Retorna lista de status de usuário.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public boolean consultarSituacoes(final SituacaoLiberacaoMudancaDTO situacaoDto) throws Exception {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "select idsituacaoliberacaomudanca from " + this.getTableName() + "  where  situacao = ? ";

        if (situacaoDto.getIdSituacaoLiberacaoMudanca() != null) {
            sql += " and idsituacaoliberacaomudanca <> " + situacaoDto.getIdSituacaoLiberacaoMudanca();
        }

        parametro.add(situacaoDto.getSituacao());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validaInsert(final SituacaoLiberacaoMudancaDTO obj) {

        return false;

    }

    public Collection findByNomeSituacao(final SituacaoLiberacaoMudancaDTO situacaoDto) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("situacao", "=", situacaoDto.getSituacao()));
        ordenacao.add(new Order("situacao"));
        return super.findByCondition(condicao, ordenacao);
    }

    // geber.costa
    public Collection<SituacaoLiberacaoMudancaDTO> listAll() throws ServiceException, Exception {

        final StringBuilder sb = new StringBuilder();
        final List listRetorno = new ArrayList<>();

        sb.append("SELECT situacao ");
        sb.append("FROM situacaoliberacaomudanca ");
        sb.append("WHERE situacao is not null ");

        final List lista = this.execSQL(sb.toString(), null);
        listRetorno.add("situacao");

        final List listaSolicitacoes = engine.listConvertion(this.getBean(), lista, listRetorno);

        return listaSolicitacoes;

    }

}
