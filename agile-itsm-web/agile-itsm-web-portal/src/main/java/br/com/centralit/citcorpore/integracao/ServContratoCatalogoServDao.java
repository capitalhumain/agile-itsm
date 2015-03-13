package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CatalogoServicoDTO;
import br.com.centralit.citcorpore.bean.ServContratoCatalogoServDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 *
 * @author pedro
 *
 */
public class ServContratoCatalogoServDao extends CrudDaoDefaultImpl {

    private static final String SQL_DELETE = "DELETE FROM servcontratocatalogoserv WHERE idcatalogoservico = ? ";

    private static final String SQL_FIND = "SELECT * FROM servcontratocatalogoserv WHERE idcatalogoservico = ? ";

    public ServContratoCatalogoServDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("idCatalogoServico", "idCatalogoServico", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "servcontratocatalogoserv";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();

        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();

        return super.list(list);
    }

    @Override
    public Class getBean() {
        return ServContratoCatalogoServDTO.class;
    }

    public void deleteByIdServContratoCatalogo(final CatalogoServicoDTO catalogo) throws PersistenceException {
        super.execUpdate(SQL_DELETE, new Object[] {catalogo.getIdCatalogoServico()});
    }

    public Collection findByIdCatalogo(final ServContratoCatalogoServDTO servContratoCatalogoServDTO) throws PersistenceException {
        return super.listConvertion(this.getBean(), super.execSQL(SQL_FIND, new Object[] {servContratoCatalogoServDTO.getIdCatalogoServico()}), new ArrayList(
                this.getFields()));
    }

}
