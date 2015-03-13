package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.MarcoPagamentoPrjDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class MarcoPagamentoPrjDao extends CrudDaoDefaultImpl {

    public MarcoPagamentoPrjDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idMarcoPagamentoPrj", "idMarcoPagamentoPrj", true, true, false, false));
        listFields.add(new Field("idProjeto", "idProjeto", false, false, false, false));
        listFields.add(new Field("nomeMarcoPag", "nomeMarcoPag", false, false, false, false));
        listFields.add(new Field("dataPrevisaoPag", "dataPrevisaoPag", false, false, false, false));
        listFields.add(new Field("valorPagamento", "valorPagamento", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("dataUltAlteracao", "dataUltAlteracao", false, false, false, false));
        listFields.add(new Field("horaUltAlteracao", "horaUltAlteracao", false, false, false, false));
        listFields.add(new Field("usuarioUltAlteracao", "usuarioUltAlteracao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "marcopagamentoprj";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return MarcoPagamentoPrjDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProjeto", "=", parm));
        ordenacao.add(new Order("dataPrevisaoPag"));
        ordenacao.add(new Order("nomeMarcoPag"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProjeto", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByIdProjetoNotIn(final Integer idProjetoParm, final Collection col) throws PersistenceException {
        String sql = "DELETE FROM " + this.getTableName() + " WHERE IDPROJETO = ? AND idMarcoPagamentoPrj NOT IN (0";
        String ids = "";
        if (col != null) {
            for (final Iterator it = col.iterator(); it.hasNext();) {
                final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = (MarcoPagamentoPrjDTO) it.next();
                ids += ", " + marcoPagamentoPrjDTO.getIdMarcoPagamentoPrj();
            }
        }
        sql += ids + ")";
        super.execUpdate(sql, new Object[] {idProjetoParm});
    }

}
