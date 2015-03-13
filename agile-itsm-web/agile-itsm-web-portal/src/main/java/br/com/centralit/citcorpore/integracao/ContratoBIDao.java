package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ContratoBIDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 * @author Thays
 *
 */
public class ContratoBIDao extends CrudDaoDefaultImpl {

    public ContratoBIDao() {
        super(Constantes.getValue("DATABASE_BI_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idContrato", "idContrato", true, true, false, false));
        listFields.add(new Field("idCliente", "idCliente", false, false, false, false));
        listFields.add(new Field("numero", "numero", false, false, false, false));
        listFields.add(new Field("objeto", "objeto", false, false, false, false));
        listFields.add(new Field("dataContrato", "dataContrato", false, false, false, false));
        listFields.add(new Field("dataFimContrato", "dataFimContrato", false, false, false, false));
        listFields.add(new Field("valorEstimado", "valorEstimado", false, false, false, false));
        listFields.add(new Field("tipoTempoEstimado", "tipoTempoEstimado", false, false, false, false));
        listFields.add(new Field("tempoEstimado", "tempoEstimado", false, false, false, false));
        listFields.add(new Field("tipo", "tipo", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("idMoeda", "idMoeda", false, false, false, false));
        listFields.add(new Field("cotacaoMoeda", "cotacaoMoeda", false, false, false, false));
        listFields.add(new Field("idFornecedor", "idFornecedor", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("idgruposolicitante", "idGrupoSolicitante", false, false, false, false));
        listFields.add(new Field("idconexaobi", "idConexaoBI", false, false, false, false));
        listFields.add(new Field("idsuperintendente", "idSuperintendente", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "Contratos";
    }

    @Override
    public Collection list() throws PersistenceException {
        final Collection colFinal = new ArrayList<>();
        final Collection col = super.list("numero");
        for (final Iterator it = col.iterator(); it.hasNext();) {
            final ContratoBIDTO contratoDto = (ContratoBIDTO) it.next();
            if (contratoDto.getDeleted() == null || contratoDto.getDeleted().equalsIgnoreCase("n")) {
                colFinal.add(contratoDto);
            }
        }
        return colFinal;
    }

    @Override
    public Class getBean() {
        return ContratoBIDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
