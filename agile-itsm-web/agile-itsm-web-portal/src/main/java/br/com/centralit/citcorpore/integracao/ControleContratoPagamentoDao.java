package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.bean.ControleContratoPagamentoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author Pedro
 *
 */
public class ControleContratoPagamentoDao extends CrudDaoDefaultImpl {

    public ControleContratoPagamentoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ControleContratoPagamentoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idccpagamento", "idCcPagamento", true, true, false, false));
        listFields.add(new Field("parcelaccpagamento", "parcelaCcPagamento", false, false, false, false));
        listFields.add(new Field("idcontrolecontrato", "idControleContrato", false, false, false, false));
        listFields.add(new Field("dataatrasoccpagamento", "dataAtrasoCcPagamento", false, false, false, false));
        listFields.add(new Field("dataccpagamento", "dataCcPagamento", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CONTROLECONTRATOPAGAMENTO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("nomeModuloSistema"));
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeModuloSistema"));
        return super.list(list);
    }

    private static final String SQL_DELETE = "DELETE FROM CONTROLECONTRATOPAGAMENTO WHERE idcontrolecontrato = ? ";

    public void deleteByIdControleContrato(final ControleContratoDTO controleContrato) throws PersistenceException {
        super.execUpdate(SQL_DELETE, new Object[] {controleContrato.getIdControleContrato()});
    }

    private static final String SQL_FIND = "SELECT * FROM CONTROLECONTRATOPAGAMENTO WHERE idcontrolecontrato = ? ";

    public Collection findByIdControleContrato(final ControleContratoPagamentoDTO dto) throws PersistenceException {
        return super.listConvertion(this.getBean(), super.execSQL(SQL_FIND, new Object[] {dto.getIdControleContrato()}), new ArrayList(this.getFields()));
    }

}
