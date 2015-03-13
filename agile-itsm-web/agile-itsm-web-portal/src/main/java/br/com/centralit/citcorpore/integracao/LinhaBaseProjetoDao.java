package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LinhaBaseProjetoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class LinhaBaseProjetoDao extends CrudDaoDefaultImpl {

    public LinhaBaseProjetoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idLinhaBaseProjeto", "idLinhaBaseProjeto", true, true, false, false));
        listFields.add(new Field("idProjeto", "idProjeto", false, false, false, false));
        listFields.add(new Field("dataLinhaBase", "dataLinhaBase", false, false, false, false));
        listFields.add(new Field("horaLinhaBase", "horaLinhaBase", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("dataUltAlteracao", "dataUltAlteracao", false, false, false, false));
        listFields.add(new Field("horaUltAlteracao", "horaUltAlteracao", false, false, false, false));
        listFields.add(new Field("usuarioUltAlteracao", "usuarioUltAlteracao", false, false, false, false));
        listFields.add(new Field("justificativaMudanca", "justificativaMudanca", false, false, false, false));
        listFields.add(new Field("dataSolMudanca", "dataSolMudanca", false, false, false, false));
        listFields.add(new Field("horaSolMudanca", "horaSolMudanca", false, false, false, false));
        listFields.add(new Field("usuarioSolMudanca", "usuarioSolMudanca", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "LinhaBaseProjeto";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LinhaBaseProjetoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProjeto", "=", parm));
        ordenacao.add(new Order("idLinhaBaseProjeto", Order.DESC));
        ordenacao.add(new Order("dataLinhaBase", Order.DESC));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProjeto", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void inativaLinhasBaseAnteriorByIdProjeto(final Integer idProjetoParm) throws PersistenceException {
        final String sql = "UPDATE " + this.getTableName() + " SET situacao = '" + LinhaBaseProjetoDTO.INATIVO + "' where idProjeto = ?";
        super.execUpdate(sql, new Object[] {idProjetoParm});
    }

}
