package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RelEscalonamentoProblemaDto;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class RelEscalonamentoProblemaDao extends CrudDaoDefaultImpl {

    public RelEscalonamentoProblemaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {

        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<Field>();
        listFields.add(new Field("idProblema", "idProblema", false, false, false, false));
        listFields.add(new Field("idEscalonamento", "idEscalonamento", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {

        return "relEscalonamentoProblema";
    }

    @Override
    public Collection list() throws PersistenceException {

        return null;
    }

    @Override
    public Class getBean() {

        return RelEscalonamentoProblemaDto.class;
    }

    /**
     * Verifica se existe relacionamento. Se existir retorna 'true', senao retorna 'false';
     *
     * @param
     * @return exists
     * @throws Exception
     */
    public boolean temRelacionamentoSolicitacaoEscalonamento(final Integer idProblema, final Integer idEscalonamento) throws PersistenceException {
        boolean exists;
        final List<Integer> parametro = new ArrayList<Integer>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select * from " + this.getTableName() + " where idProblema = ? and idEscalonamento = ?  ");
        parametro.add(idProblema);
        parametro.add(idEscalonamento);
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            exists = true;
        } else {
            exists = false;
        }
        return exists;
    }

}
