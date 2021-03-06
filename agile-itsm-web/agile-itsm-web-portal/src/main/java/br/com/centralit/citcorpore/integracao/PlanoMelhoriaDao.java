package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PlanoMelhoriaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class PlanoMelhoriaDao extends CrudDaoDefaultImpl {

    public PlanoMelhoriaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idPlanoMelhoria", "idPlanoMelhoria", true, true, false, false));
        listFields.add(new Field("idFornecedor", "idFornecedor", false, false, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("titulo", "titulo", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("objetivo", "objetivo", false, false, false, false));
        listFields.add(new Field("visaoGeral", "visaoGeral", false, false, false, false));
        listFields.add(new Field("escopo", "escopo", false, false, false, false));
        listFields.add(new Field("visao", "visao", false, false, false, false));
        listFields.add(new Field("missao", "missao", false, false, false, false));
        listFields.add(new Field("notas", "notas", false, false, false, false));
        listFields.add(new Field("criadoPor", "criadoPor", false, false, false, false));
        listFields.add(new Field("modificadoPor", "modificadoPor", false, false, false, false));
        listFields.add(new Field("dataCriacao", "dataCriacao", false, false, false, false));
        listFields.add(new Field("ultModificacao", "ultModificacao", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "PlanoMelhoria";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("dataInicio");
    }

    @Override
    public Class getBean() {
        return PlanoMelhoriaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public void update(final BaseEntity obj) throws PersistenceException {
        final PlanoMelhoriaDTO planoMelhoriaDTO = (br.com.centralit.citcorpore.bean.PlanoMelhoriaDTO) this.restore(obj);
        if (planoMelhoriaDTO != null) {
            ((PlanoMelhoriaDTO) obj).setCriadoPor(planoMelhoriaDTO.getCriadoPor());
            ((PlanoMelhoriaDTO) obj).setDataCriacao(planoMelhoriaDTO.getDataCriacao());
        }
        super.update(obj);
    }

}
