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

public class InfoCatalogoServicoDao extends CrudDaoDefaultImpl {

    /**
     * @author pedro
     */
    private static final String SQL_DELETE = "DELETE FROM infocatalogoservico WHERE idcatalogoservico = ? ";

    private static final String SQL_FIND = "SELECT * " + "FROM infocatalogoservico WHERE idcatalogoservico = ? ";

    public InfoCatalogoServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idInfoCatalogoServico", "idInfoCatalogoServico", true, true, false, false));
        listFields.add(new Field("idCatalogoServico", "idCatalogoServico", false, false, false, false));
        listFields.add(new Field("descInfoCatalogoServico", "descInfoCatalogoServico", false, false, false, false));
        listFields.add(new Field("nomeInfoCatalogoServico", "nomeInfoCatalogoServico", false, false, false, false));
        listFields.add(new Field("idServicoCatalogo", "idServicoCatalogo", false, false, false, false));
        listFields.add(new Field("nomeServicoContrato", "nomeServicoContrato", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "INFOCATALOGOSERVICO";
    }

    @Override
    public Collection<InfoCatalogoServicoDTO> find(final BaseEntity obj) throws PersistenceException {
        final List<InfoCatalogoServicoDTO> ordem = new ArrayList<InfoCatalogoServicoDTO>();
        return super.find(obj, ordem);
    }

    @Override
    public Collection<InfoCatalogoServicoDTO> list() throws PersistenceException {
        final List<InfoCatalogoServicoDTO> list = new ArrayList<InfoCatalogoServicoDTO>();
        return super.list(list);
    }

    @Override
    public Class<InfoCatalogoServicoDTO> getBean() {
        return InfoCatalogoServicoDTO.class;
    }

    public void deleteByIdInfoCatalogo(final CatalogoServicoDTO catalogo) throws PersistenceException {
        super.execUpdate(SQL_DELETE, new Object[] {catalogo.getIdCatalogoServico()});
    }

    public Collection<InfoCatalogoServicoDTO> findByIdInfoCatalogo(final InfoCatalogoServicoDTO infoCatalogoServicoDTO) throws PersistenceException {
        return super.listConvertion(this.getBean(), super.execSQL(SQL_FIND, new Object[] {infoCatalogoServicoDTO.getIdCatalogoServico()}),
                new ArrayList(this.getFields()));

    }

    public Collection<InfoCatalogoServicoDTO> findByCatalogoServico(final Integer idCatalogoServico) throws PersistenceException {
        final String sql = "SELECT idInfoCatalogoServico, idServicoCatalogo, nomeServicoContrato, nomeInfoCatalogoServico, descInfoCatalogoServico FROM "
                + this.getTableName() + "  WHERE idCatalogoServico = ?";
        final List<InfoCatalogoServicoDTO> dados = this.execSQL(sql, new Object[] {idCatalogoServico});
        final List<String> fields = new ArrayList<String>();
        fields.add("idInfoCatalogoServico");
        fields.add("idServicoCatalogo");
        fields.add("nomeServicoContrato");
        fields.add("nomeInfoCatalogoServico");
        fields.add("descInfoCatalogoServico");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public boolean findByContratoServico(final Integer idContratoServico) throws PersistenceException {
        final String sql = "select serv.idservico,nomeServico FROM servico serv inner join servicocontrato servcont on serv.idservico = servcont.idservico  WHERE IDCONTRATO = ? ORDER BY NOMESERVICO";
        final List lista = this.execSQL(sql, new Object[] {idContratoServico});
        if (lista.isEmpty()) {
            return false;
        }
        return true;
    }

    public InfoCatalogoServicoDTO findById(final Integer idInfoCatalogo) throws PersistenceException {

        final String sql = "SELECT idInfoCatalogoServico, idServicoCatalogo, nomeServicoContrato, nomeInfoCatalogoServico, descInfoCatalogoServico FROM "
                + this.getTableName() + "  WHERE idInfoCatalogoServico = ?";

        final List<InfoCatalogoServicoDTO> dados = this.execSQL(sql, new Object[] {idInfoCatalogo});

        final List<String> fields = new ArrayList<String>();

        fields.add("idInfoCatalogoServico");

        fields.add("idServicoCatalogo");

        fields.add("nomeServicoContrato");

        fields.add("nomeInfoCatalogoServico");

        fields.add("descInfoCatalogoServico");

        final List<InfoCatalogoServicoDTO> resultado = this.listConvertion(this.getBean(), dados, fields);

        return resultado != null && resultado.size() > 0 ? resultado.get(0) : null;
    }

}
