package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaRiscoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class RequisicaoMudancaRiscoDao extends CrudDaoDefaultImpl {

    public RequisicaoMudancaRiscoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idrequisicaomudancarisco", "idRequisicaoMudancaRisco", true, true, false, false));
        listFields.add(new Field("idrequisicaomudanca", "idRequisicaoMudanca", false, false, false, false));
        listFields.add(new Field("idrisco", "idRisco", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {

        return null;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "requisicaomudancarisco";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("idrequisicaomudancarisco");
    }

    @Override
    public Class getBean() {
        return RequisicaoMudancaRiscoDTO.class;
    }

    @Override
    public int deleteByCondition(final List<Condition> condicao) throws PersistenceException {
        return super.deleteByCondition(condicao);
    }

    public RequisicaoMudancaRiscoDTO restoreByChaveComposta(final RequisicaoMudancaRiscoDTO dto) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();

        condicoes.add(new Condition("idRequisicaoMudanca", "=", dto.getIdRequisicaoMudanca()));
        condicoes.add(new Condition("idRisco", "=", dto.getIdRisco()));

        final ArrayList<RequisicaoMudancaRiscoDTO> retorno = (ArrayList<RequisicaoMudancaRiscoDTO>) super.findByCondition(condicoes, null);
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

    public Collection findByIdRequisicaoMudanca(final Integer parm) throws Exception {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT rm.idrisco as idrisco, nomerisco, detalhamento FROM risco r JOIN requisicaomudancarisco rm ON r.idrisco = rm.idrisco WHERE rm.idrequisicaomudanca = ? ORDER BY rm.idrisco ");
        parametro.add(parm);
        list = this.execSQL(sql.toString(), parametro.toArray());
        fields.add("idRisco");
        fields.add("nomeRisco");
        fields.add("detalhamento");
        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, fields);
        } else {
            return null;
        }
    }

    public Collection findByIdRequisicaoMudancaEDataFim(final Integer parm) throws Exception {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT rm.idrisco as idrisco, rm.idrequisicaomudancarisco, nomerisco, detalhamento FROM risco r JOIN requisicaomudancarisco rm ON r.idrisco = rm.idrisco WHERE rm.idrequisicaomudanca = ? and rm.datafim is null ORDER BY rm.idrisco ");
        parametro.add(parm);
        list = this.execSQL(sql.toString(), parametro.toArray());
        fields.add("idRisco");
        fields.add("idRequisicaoMudancaRisco");
        fields.add("nomeRisco");
        fields.add("detalhamento");
        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, fields);
        } else {
            return null;
        }
    }

    public Collection listByIdHistoricoMudanca(final Integer parm) throws Exception {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT rm.idrisco as idrisco, rm.idrequisicaomudancarisco, nomerisco, detalhamento " + "FROM risco r "
                + "JOIN requisicaomudancarisco rm ON r.idrisco = rm.idrisco "
                + "inner join ligacao_mud_hist_risco ligrisc on ligrisc.idrequisicaomudancarisco = rm.idrequisicaomudancarisco "
                + "WHERE ligrisc.idhistoricomudanca = ? ORDER BY rm.idrisco ");
        parametro.add(parm);
        list = this.execSQL(sql.toString(), parametro.toArray());
        fields.add("idRisco");
        fields.add("idRequisicaoMudancaRisco");
        fields.add("nomeRisco");
        fields.add("detalhamento");
        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, fields);
        }
        return null;
    }

}
