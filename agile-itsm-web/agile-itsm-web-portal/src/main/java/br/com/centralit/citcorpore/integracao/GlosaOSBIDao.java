package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GlosaOSBIDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class GlosaOSBIDao extends CrudDaoDefaultImpl {

    public GlosaOSBIDao() {
        super(Constantes.getValue("DATABASE_BI_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idGlosaOS", "idGlosaOS", true, true, false, false));
        listFields.add(new Field("idOs", "idOs", false, false, false, false));
        listFields.add(new Field("dataCriacao", "dataCriacao", false, false, false, false));
        listFields.add(new Field("dataUltModificacao", "dataUltModificacao", false, false, false, false));
        listFields.add(new Field("descricaoGlosa", "descricaoGlosa", false, false, false, false));
        listFields.add(new Field("ocorrencias", "ocorrencias", false, false, false, false));
        listFields.add(new Field("percAplicado", "percAplicado", false, false, false, false));
        listFields.add(new Field("custoGlosa", "custoGlosa", false, false, false, false));
        listFields.add(new Field("numeroOcorrencias", "numeroOcorrencias", false, false, false, false));
        listFields.add(new Field("idConexaoBI", "idConexaoBI", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "GlosaOS";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return GlosaOSBIDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
