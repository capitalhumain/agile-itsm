package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.FluxoServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class FluxoServicoDao extends CrudDaoDefaultImpl {

    public FluxoServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    private List getColunasRestoreAll() {
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idFluxoServico");
        listRetorno.add("idServicoContrato");
        listRetorno.add("idTipoFluxo");
        listRetorno.add("idFase");
        listRetorno.add("principal");
        listRetorno.add("deleted");
        return listRetorno;
    }

    private String getSQLRestoreAll() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT idFluxoServico, idServicoContrato, idTipoFluxo, idFase, principal, deleted FROM FluxoServico ");
        sql.append(" WHERE (deleted is null or UCASE(deleted) = 'N') ");

        return sql.toString();
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idFluxoServico", "idFluxoServico", true, true, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", false, false, false, false));
        listFields.add(new Field("idTipoFluxo", "idTipoFluxo", false, false, false, false));
        listFields.add(new Field("idFase", "idFase", false, false, false, false));
        listFields.add(new Field("principal", "principal", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "FluxoServico";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return FluxoServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdServicoContrato(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(parm);

        String sql = this.getSQLRestoreAll();
        sql += "  AND idServicoContrato = ? " + "ORDER BY idTipoFluxo ";

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(FluxoServicoDTO.class, lista, this.getColunasRestoreAll());
    }

    public Collection findByIdFluxoServico(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(parm);

        String sql = this.getSQLRestoreAll();
        sql += "  AND idFluxoServico = ? " + "ORDER BY idTipoFluxo ";

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(FluxoServicoDTO.class, lista, this.getColunasRestoreAll());
    }

    public Collection findByIdServicoContratoAndIdFase(final Integer idServicoContrato, final Integer idFase) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(idServicoContrato);
        parametro.add(idFase);

        String sql = this.getSQLRestoreAll();
        sql += "  AND idServicoContrato = ? " + "  AND idFase = ? " + "ORDER BY idTipoFluxo ";

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(FluxoServicoDTO.class, lista, this.getColunasRestoreAll());
    }

    public FluxoServicoDTO findByIdServicoContratoAndIdTipoFluxo(final Integer idServicoContrato, final Integer idTipoFluxo) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(idServicoContrato);
        parametro.add(idTipoFluxo);

        String sql = this.getSQLRestoreAll();
        sql += "  AND idServicoContrato = ? " + "  AND idTipoFluxo = ? " + "ORDER BY idTipoFluxo ";

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        FluxoServicoDTO fluxoServicoDTO = null;
        final List<FluxoServicoDTO> collection = engine.listConvertion(FluxoServicoDTO.class, lista, this.getColunasRestoreAll());
        if (collection != null && !collection.isEmpty()) {
            fluxoServicoDTO = collection.get(0);
        }

        return fluxoServicoDTO;
    }

    /**
     * Retorna FluxoServicoDTO Principal Ativo de acordo com o idServicoContrato informado.
     *
     * @param idServicoContrato
     * @return FluxoServicoDTO
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public FluxoServicoDTO findPrincipalByIdServicoContrato(final Integer idServicoContrato) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(idServicoContrato);

        String sql = this.getSQLRestoreAll();
        sql += "  AND principal = 'S'" + "  AND idServicoContrato = ? " + "ORDER BY idTipoFluxo ";

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        if (lista != null && !lista.isEmpty()) {
            final List listaResult = engine.listConvertion(FluxoServicoDTO.class, lista, this.getColunasRestoreAll());
            return (FluxoServicoDTO) listaResult.get(0);
        } else {
            return null;
        }
    }

    public boolean validarFluxoServico(final FluxoServicoDTO fluxoServicoDTO) {
        try {
            final List parametro = new ArrayList<>();
            final StringBuilder sql = new StringBuilder();
            sql.append("SELECT idFluxoServico, idServicoContrato, idTipoFluxo, idFase, principal, deleted FROM FluxoServico");
            sql.append(" WHERE (deleted is null or UCASE(deleted) = 'N') AND idfase = ? AND idTipoFluxo = ? AND idServicoContrato = ? AND principal like '"
                    + fluxoServicoDTO.getPrincipal() + "'");

            parametro.add(fluxoServicoDTO.getIdFase());
            parametro.add(fluxoServicoDTO.getIdTipoFluxo());
            parametro.add(fluxoServicoDTO.getIdServicoContrato());

            final List lista = this.execSQL(sql.toString(), parametro.toArray());

            if (lista != null && !lista.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (final PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteByIdServicoContrato(final Integer idServicocContrato) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idServicoContrato", "=", idServicocContrato));
        final FluxoServicoDTO fluxoServicoDTO = new FluxoServicoDTO();
        fluxoServicoDTO.setDeleted("y");
        super.updateNotNullByCondition(fluxoServicoDTO, condicao);
    }

}
