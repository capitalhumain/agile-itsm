package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CatalogoServicoDTO;
import br.com.centralit.citcorpore.bean.InfoCatalogoServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 *
 * @author pedro
 *
 */

public class CatalogoServicoDao extends CrudDaoDefaultImpl {

    public CatalogoServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idCatalogoServico", "idCatalogoServico", true, true, false, false));
        listFields.add(new Field("tituloCatalogoServico", "tituloCatalogoServico", false, false, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("obs", "obs", false, false, false, false));
        listFields.add(new Field("nomeServico", "nomeServico", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CATALOGOSERVICO";
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

    public Collection<CatalogoServicoDTO> listAllCatalogos() throws PersistenceException {
        final List parametro = new ArrayList<>();
        final String sql = "SELECT idCatalogoServico, tituloCatalogoServico FROM " + this.getTableName() + "  WHERE dataFim IS NULL";
        final List<InfoCatalogoServicoDTO> dados = this.execSQL(sql, parametro.toArray());
        final List<String> fields = new ArrayList<String>();
        fields.add("idCatalogoServico");
        fields.add("tituloCatalogoServico");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public boolean verificaSeCatalogoExiste(final CatalogoServicoDTO catalogoServicoDTO) throws PersistenceException {

        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idCatalogoServico from " + this.getTableName() + "  where  tituloCatalogoServico = ? and datafim is null ");
        parametro.add(catalogoServicoDTO.getTituloCatalogoServico());

        if (catalogoServicoDTO.getIdCatalogoServico() != null) {
            sql.append("and idCatalogoServico <> ?");
            parametro.add(catalogoServicoDTO.getIdCatalogoServico());
        }
        list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Class getBean() {
        return CatalogoServicoDTO.class;
    }

    public Collection<CatalogoServicoDTO> listByIdContrato(final Integer idContrato) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final String sql = "SELECT idCatalogoServico, idContrato, tituloCatalogoServico FROM " + this.getTableName()
                + "  WHERE dataFim IS NULL AND  idcontrato = ?";
        parametro.add(idContrato);
        final List<InfoCatalogoServicoDTO> dados = this.execSQL(sql, parametro.toArray());
        final List<String> fields = new ArrayList<String>();
        fields.add("idCatalogoServico");
        fields.add("idContrato");
        fields.add("tituloCatalogoServico");
        return this.listConvertion(this.getBean(), dados, fields);
    }

}
