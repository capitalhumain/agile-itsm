package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.ProblemaRelacionadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class ProblemaRelacionadoDao extends CrudDaoDefaultImpl {

    public ProblemaRelacionadoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idproblema", "idProblema", false, false, false, false));
        listFields.add(new Field("idproblemarelacionado", "idProblemaRelacionado", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "problemarelacionado";
    }

    public void deleteByIdProblema(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProblema", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdProblema(final Integer idProblema) throws PersistenceException {
        final List listRetorno = new ArrayList<>();
        final List paramentro = new ArrayList<>();

        final String sql = " select pro.idproblema, pro.titulo, pro.dataHoraInicio,  "
                + " pro.idProprietario, pro.status, pro.descricao, pro.causaRaiz,  "
                + " pro.solucaoContorno, pro.solucaoDefinitiva   from problema pro where pro.idproblema in (select idproblemarelacionado from problemarelacionado where idproblema = ? ) ";

        paramentro.add(idProblema);
        final List list = this.execSQL(sql, paramentro.toArray());
        listRetorno.add("idProblema");
        listRetorno.add("titulo");
        listRetorno.add("dataHoraInicio");
        listRetorno.add("idProprietario");
        listRetorno.add("status");
        listRetorno.add("descricao");
        listRetorno.add("causaRaiz");
        listRetorno.add("solucaoContorno");
        listRetorno.add("solucaoDefinitiva");
        return this.listConvertion(ProblemaDTO.class, list, listRetorno);
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ProblemaRelacionadoDTO.class;
    }

}
