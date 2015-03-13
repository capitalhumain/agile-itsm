package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AlcadaCentroResultadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

public class AlcadaCentroResultadoDAO extends CrudDaoDefaultImpl {

    public AlcadaCentroResultadoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return super.find(obj, null);
    }

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idalcadacentroresultado", "idAlcadaCentroResultado", true, true, false, false));
        listFields.add(new Field("idcentroresultado", "idCentroResultado", false, false, false, false));
        listFields.add(new Field("idempregado", "idEmpregado", false, false, false, false));
        listFields.add(new Field("idalcada", "idAlcada", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "alcadacentroresultado";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idAlcadaCentroResultado"));

        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return AlcadaCentroResultadoDTO.class;
    }

    public Collection<AlcadaCentroResultadoDTO> findByIdCentroResultadoAndIdAlcada(final Integer idCentroResultado, final Integer idAlcada)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idCentroResultado", "=", idCentroResultado));
        condicao.add(new Condition("idAlcada", "=", idAlcada));
        ordenacao.add(new Order("idAlcadaCentroResultado"));

        final Collection<AlcadaCentroResultadoDTO> col = super.findByCondition(condicao, ordenacao);
        final Collection<AlcadaCentroResultadoDTO> result = new ArrayList<>();

        if (col != null) {
            for (final AlcadaCentroResultadoDTO alcadaCentroResultadoDto : col) {
                if (alcadaCentroResultadoDto.getDataFim() == null || alcadaCentroResultadoDto.getDataFim() != null
                        && alcadaCentroResultadoDto.getDataFim().compareTo(UtilDatas.getDataAtual()) >= 0) {
                    result.add(alcadaCentroResultadoDto);
                }
            }
        }
        return result;
    }

    /**
     * Retorna true ou falso
     *
     * @param obj
     * @return boolean
     * @throws Exception
     * @author thays.araujo
     */
    public boolean verificarVinculoCentroResultado(final Integer obj) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "select idalcadacentroresultado From " + this.getTableName() + "  where  idcentroresultado = ?    ";

        parametro.add(obj);
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Collection<AlcadaCentroResultadoDTO> findByIdCentroResultado(final Integer idCentroResultado) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idCentroResultado", "=", idCentroResultado));
        ordenacao.add(new Order("idAlcada"));
        ordenacao.add(new Order("dataInicio"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCentroResultado(final Integer idCentroResultado) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCentroResultado", "=", idCentroResultado));
        super.deleteByCondition(condicao);
    }

}
