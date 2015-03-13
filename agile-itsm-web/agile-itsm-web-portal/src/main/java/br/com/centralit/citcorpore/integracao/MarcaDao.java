package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.MarcaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class MarcaDao extends CrudDaoDefaultImpl {

    public MarcaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idMarca", "idMarca", true, true, false, false));
        listFields.add(new Field("idFabricante", "idFabricante", false, false, false, false));
        listFields.add(new Field("nomeMarca", "nomeMarca", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Marca";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return MarcaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdFabricante(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idFabricante", "=", parm));
        ordenacao.add(new Order("nomeMarca"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdFabricante(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idFabricante", "=", parm));
        super.deleteByCondition(condicao);
    }

    /**
     * Retorna true ou falso caso nomeMarca ja exista no banco de dados
     *
     * @param marcaDto
     * @return boolean
     * @throws Exception
     * @author thays.araujo
     */
    public boolean consultarMarcas(final MarcaDTO marcaDto) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idmarca From " + this.getTableName() + "  where  nomeMarca = ?    ");

        parametro.add(marcaDto.getNomeMarca());

        if (marcaDto.getIdMarca() != null) {
            sql.append("and idmarca <> ?");
            parametro.add(marcaDto.getIdMarca());
        }

        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

}
