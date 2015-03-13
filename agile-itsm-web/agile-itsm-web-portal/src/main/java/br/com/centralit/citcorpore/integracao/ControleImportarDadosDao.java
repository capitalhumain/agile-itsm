package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ControleImportarDadosDTO;
import br.com.centralit.citcorpore.bean.ImportarDadosDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class ControleImportarDadosDao extends CrudDaoDefaultImpl {

    public ControleImportarDadosDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idcontroleimportardados", "idControleImportarDados", true, true, false, false));
        listFields.add(new Field("idimportardados", "idImportarDados", false, false, false, false));
        listFields.add(new Field("dataexecucao", "dataExecucao", false, false, false, false));

        return listFields;

    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ControleImportarDados";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("nome");
    }

    @Override
    public Class getBean() {
        return ControleImportarDadosDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Consulta o ultimo elemento inserido na tabela pelo parametro
     *
     * @param importar
     * @return
     * @throws Exception
     */
    public ControleImportarDadosDTO consultarControleImportarDados(final ImportarDadosDTO importar) throws PersistenceException {

        final String sql = "select * from controleimportardados where idimportardados = ? order by idcontroleimportardados desc limit 1";

        final List lstParms = new ArrayList<>();
        lstParms.add(importar.getIdImportarDados());

        final List lstDados = this.execSQL(sql, lstParms.toArray());

        final List lst = this.listConvertion(this.getBean(), lstDados, this.getListNamesFieldClass());

        if (lst == null || lst.size() == 0) {
            return null;
        }

        return (ControleImportarDadosDTO) lst.get(0);

    }

}
