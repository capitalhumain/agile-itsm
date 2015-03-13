/**
 * CentralIT - CITSmart
 *
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AtividadesServicoContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class AtividadesServicoContratoBIDao extends CrudDaoDefaultImpl {

    public AtividadesServicoContratoBIDao() {
        super(Constantes.getValue("DATABASE_BI_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAtividadeServicoContrato", "idAtividadeServicoContrato", true, true, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("descricaoAtividade", "descricaoAtividade", false, false, false, false));
        listFields.add(new Field("obsAtividade", "obsAtividade", false, false, false, false));
        listFields.add(new Field("custoAtividade", "custoAtividade", false, false, false, false));
        listFields.add(new Field("complexidade", "complexidade", false, false, false, false));
        listFields.add(new Field("hora", "hora", false, false, false, false));
        listFields.add(new Field("quantidade", "quantidade", false, false, false, false));
        listFields.add(new Field("periodo", "periodo", false, false, false, false));
        listFields.add(new Field("formula", "formula", false, false, false, false));
        listFields.add(new Field("contabilizar", "contabilizar", false, false, false, false));
        listFields.add(new Field("idServicoContratoContabil", "idServicoContratoContabil", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("tipoCusto", "tipoCusto", false, false, false, false));
        listFields.add(new Field("idConexaoBI", "idConexaoBI", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "AtividadesServicoContrato";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AtividadesServicoContratoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
