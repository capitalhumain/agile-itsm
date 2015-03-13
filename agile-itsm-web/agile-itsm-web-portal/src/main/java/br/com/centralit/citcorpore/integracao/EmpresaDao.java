package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.EmpresaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author rosana.godinho
 *
 */
public class EmpresaDao extends CrudDaoDefaultImpl {

    public EmpresaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idEmpresa", "idEmpresa", true, true, false, false));
        listFields.add(new Field("NomeEmpresa", "nomeEmpresa", false, false, false, false));
        listFields.add(new Field("DataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("DataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("detalhamento", "detalhamento", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "EMPRESA";
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("nomeEmpresa"));
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeEmpresa"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return EmpresaDTO.class;
    }

    public boolean jaExisteRegistroComMesmoNome(final EmpresaDTO empresa) throws PersistenceException {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();
        condicoes.add(new Condition("nomeEmpresa", "=", empresa.getNomeEmpresa()));
        condicoes.add(new Condition("dataFim", "is", null));
        // condicoes.add(new Condition("idEmpresa", "<>", empresa.getIdEmpresa()));
        Collection retorno = null;
        retorno = super.findByCondition(condicoes, null);
        if (retorno != null) {
            if (retorno.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
