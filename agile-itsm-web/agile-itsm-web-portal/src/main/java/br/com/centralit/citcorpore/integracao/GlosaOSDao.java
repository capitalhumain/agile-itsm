package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GlosaOSDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class GlosaOSDao extends CrudDaoDefaultImpl {

    public GlosaOSDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
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
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "GlosaOS";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return GlosaOSDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdOs(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idOs", "=", parm));
        ordenacao.add(new Order("idGlosaOS"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdOs(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idOs", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByOsNotIn(final Integer idOs, final String notIn) throws PersistenceException {
        final String sql = "DELETE FROM " + this.getTableName() + " WHERE idOs = ? AND idGlosaOS NOT IN (?)";
        super.execUpdate(sql, new Object[] {idOs, notIn});
    }

    public Double retornarCustoGlosaOSByIdOs(final Integer idOs) throws PersistenceException {
        final String sql = "select sum(custoglosa) from glosaos where idos = ?";
        final List dados = this.execSQL(sql, new Object[] {idOs});
        final List fields = new ArrayList<>();
        fields.add("custoGlosa");

        final Collection<GlosaOSDTO> listaDeGlosaOS = this.listConvertion(this.getBean(), dados, fields);

        if (listaDeGlosaOS != null && !listaDeGlosaOS.isEmpty()) {
            for (final GlosaOSDTO glosa : listaDeGlosaOS) {
                return glosa.getCustoGlosa();
            }
            return null;
        } else {
            return null;
        }

    }

    public Collection<GlosaOSDTO> listaDeGlosas(final Integer idOs) throws PersistenceException {
        final List listRetorno = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        sql.append("select idglosaos, descricaoglosa,ocorrencias,percaplicado,custoglosa,numeroOcorrencias FROM " + this.getTableName() + " where idos = ?");
        parametro.add(idOs);

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());
        listRetorno.add("idGlosaOS");
        listRetorno.add("descricaoGlosa");
        listRetorno.add("ocorrencias");
        listRetorno.add("percAplicado");
        listRetorno.add("custoGlosa");
        listRetorno.add("numeroOcorrencias");
        if (lista != null && !lista.isEmpty()) {
            final List listaDeGlosas = engine.listConvertion(GlosaOSDTO.class, lista, listRetorno);
            return listaDeGlosas;

        } else {

            return new ArrayList<>();
        }

    }

}
