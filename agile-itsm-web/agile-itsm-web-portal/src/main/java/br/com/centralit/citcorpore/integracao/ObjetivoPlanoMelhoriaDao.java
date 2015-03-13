package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ObjetivoPlanoMelhoriaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ObjetivoPlanoMelhoriaDao extends CrudDaoDefaultImpl {

    public ObjetivoPlanoMelhoriaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idObjetivoPlanoMelhoria", "idObjetivoPlanoMelhoria", true, true, false, false));
        listFields.add(new Field("idPlanoMelhoria", "idPlanoMelhoria", false, false, false, false));
        listFields.add(new Field("tituloObjetivo", "tituloObjetivo", false, false, false, false));
        listFields.add(new Field("detalhamento", "detalhamento", false, false, false, false));
        listFields.add(new Field("resultadoEsperado", "resultadoEsperado", false, false, false, false));
        listFields.add(new Field("medicao", "medicao", false, false, false, false));
        listFields.add(new Field("responsavel", "responsavel", false, false, false, false));
        listFields.add(new Field("criadoPor", "criadoPor", false, false, false, false));
        listFields.add(new Field("modificadoPor", "modificadoPor", false, false, false, false));
        listFields.add(new Field("dataCriacao", "dataCriacao", false, false, false, false));
        listFields.add(new Field("ultModificacao", "ultModificacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ObjetivoPlanoMelhoria";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ObjetivoPlanoMelhoriaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdPlanoMelhoria(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idPlanoMelhoria", "=", parm));
        ordenacao.add(new Order("tituloObjetivo"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdPlanoMelhoria(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idPlanoMelhoria", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection<ObjetivoPlanoMelhoriaDTO> listObjetivosPlanoMelhoria(final ObjetivoPlanoMelhoriaDTO obj) throws PersistenceException {

        final StringBuilder sql = new StringBuilder();

        final List parametro = new ArrayList<>();

        List list = new ArrayList<>();

        sql.append("select idobjetivoplanomelhoria,tituloobjetivo,resultadoesperado,medicao,responsavel from " + this.getTableName()
                + " where idplanomelhoria = ? ");

        parametro.add(obj.getIdPlanoMelhoria());

        list = this.execSQL(sql.toString(), parametro.toArray());
        final List listaRetorno = new ArrayList<>();
        listaRetorno.add("idObjetivoPlanoMelhoria");
        listaRetorno.add("tituloObjetivo");
        listaRetorno.add("resultadoEsperado");
        listaRetorno.add("medicao");
        listaRetorno.add("responsavel");

        if (list != null && !list.isEmpty()) {
            final Collection<ObjetivoPlanoMelhoriaDTO> listObjetivoPlanoMelhoria = this.listConvertion(ObjetivoPlanoMelhoriaDTO.class, list, listaRetorno);
            return listObjetivoPlanoMelhoria;
        }

        return null;
    }

    @Override
    public void update(final BaseEntity obj) throws PersistenceException {
        final ObjetivoPlanoMelhoriaDTO objetivoPlanoMelhoriaDTO = (br.com.centralit.citcorpore.bean.ObjetivoPlanoMelhoriaDTO) this.restore(obj);
        if (objetivoPlanoMelhoriaDTO != null) {
            ((ObjetivoPlanoMelhoriaDTO) obj).setCriadoPor(objetivoPlanoMelhoriaDTO.getCriadoPor());
            ((ObjetivoPlanoMelhoriaDTO) obj).setDataCriacao(objetivoPlanoMelhoriaDTO.getDataCriacao());
        }
        super.update(obj);
    }

}
