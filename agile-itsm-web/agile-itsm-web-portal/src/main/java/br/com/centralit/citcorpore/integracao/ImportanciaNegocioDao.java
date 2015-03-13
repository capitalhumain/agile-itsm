package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.ajaxForms.Servico;
import br.com.centralit.citcorpore.bean.ImportanciaNegocioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ImportanciaNegocioDao extends CrudDaoDefaultImpl {

    public ImportanciaNegocioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ImportanciaNegocioDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDIMPORTANCIANEGOCIO", "idImportanciaNegocio", true, true, false, false));
        listFields.add(new Field("IDEMPRESA", "idEmpresa", false, false, false, false));
        listFields.add(new Field("NOMEIMPORTANCIANEGOCIO", "nomeImportanciaNegocio", false, false, false, false));
        listFields.add(new Field("SITUACAO", "situacao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "IMPORTANCIANEGOCIO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("nomeImportanciaNegocio"));
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeImportanciaNegocio"));
        return super.list(list);
    }

    /**
     * Metodo reponsavel por verificar se existe um vinculo entre Inmportancia Negocio e Cadastro de Serviço
     *
     *
     * @param idImportanciaNegocio
     * @author Ezequiel Bispo Nunes
     * @date 2014-11-25
     */
    public Boolean existeVinculoCadastroServico(final Integer idImportanciaNegocio) throws PersistenceException {

        final StringBuilder query = new StringBuilder();

        query.append(" select * from servico where idimportancianegocio = " + idImportanciaNegocio);

        final List retornos = new ArrayList<>();

        final List servicos = this.execSQL(query.toString(), new ArrayList<>().toArray());

        retornos.add("idservico");

        final List resultado = engine.listConvertion(Servico.class, servicos, retornos);

        return resultado != null && resultado.size() > 0;
    }

}
