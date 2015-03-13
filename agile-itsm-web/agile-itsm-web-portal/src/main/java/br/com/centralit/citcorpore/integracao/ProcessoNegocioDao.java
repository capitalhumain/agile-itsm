package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ProcessoNegocioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ProcessoNegocioDao extends CrudDaoDefaultImpl {

    public ProcessoNegocioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idProcessoNegocio", "idProcessoNegocio", true, true, false, false));
        listFields.add(new Field("idGrupoExecutor", "idGrupoExecutor", false, false, false, false));
        listFields.add(new Field("idGrupoAdministrador", "idGrupoAdministrador", false, false, false, false));
        listFields.add(new Field("nomeProcessoNegocio", "nomeProcessoNegocio", false, false, false, false));
        listFields.add(new Field("permissaoSolicitacao", "permissaoSolicitacao", false, false, false, false));
        listFields.add(new Field("percDispensaNovaAprovacao", "percDispensaNovaAprovacao", false, false, false, false));
        listFields.add(new Field("permiteAprovacaoNivelInferior", "permiteAprovacaoNivelInferior", false, false, false, false));
        listFields.add(new Field("alcadaPrimeiroNivel", "alcadaPrimeiroNivel", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ProcessoNegocio";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("nomeProcessoNegocio"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return ProcessoNegocioDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
