package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CalendarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class CalendarioDao extends CrudDaoDefaultImpl {

    public CalendarioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idCalendario", "idCalendario", true, true, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("consideraFeriados", "consideraFeriados", false, false, false, false));
        listFields.add(new Field("idJornadaSeg", "idJornadaSeg", false, false, false, false));
        listFields.add(new Field("idJornadaTer", "idJornadaTer", false, false, false, false));
        listFields.add(new Field("idJornadaQua", "idJornadaQua", false, false, false, false));
        listFields.add(new Field("idJornadaQui", "idJornadaQui", false, false, false, false));
        listFields.add(new Field("idJornadaSex", "idJornadaSex", false, false, false, false));
        listFields.add(new Field("idJornadaSab", "idJornadaSab", false, false, false, false));
        listFields.add(new Field("idJornadaDom", "idJornadaDom", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Calendario";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("descricao");
    }

    @Override
    public Class getBean() {
        return CalendarioDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Verifica se a jornada de trabalho passada está cadastrada para algum calendário.
     *
     * @param idJornada
     * @return boolean
     * @throws Exception
     * @author rodrigo.oliveira
     */
    public boolean verificaJornada(final Integer idJornada) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(idJornada);
        parametro.add(idJornada);
        parametro.add(idJornada);
        parametro.add(idJornada);
        parametro.add(idJornada);
        parametro.add(idJornada);
        parametro.add(idJornada);
        final String sql = "select idcalendario from "
                + this.getTableName()
                + " where idjornadaseg = ? or idjornadater = ? or idjornadaqua = ? or idjornadaqui = ? or idjornadasex = ? or idjornadasab = ? or idjornadadom = ?";
        final List lista = this.execSQL(sql.toString(), parametro.toArray());
        if (lista != null && lista.isEmpty() || lista == null) {
            return false;
        }
        return true;
    }

    /**
     * Método para verificar se existe calendário com a mesma descrição
     *
     * @author rodrigo.oliveira
     * @param calendarioDTO
     * @return Se caso exista calendario com a mesma descrição retorna true
     * @throws Exception
     */
    public boolean verificaSeExisteCalendario(final CalendarioDTO calendarioDTO) throws PersistenceException {

        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "SELECT idcalendario FROM " + this.getTableName() + " WHERE descricao = ? ";
        parametro.add(calendarioDTO.getDescricao());

        if (calendarioDTO.getIdCalendario() != null) {
            sql += " AND idcalendario <>  ? ";
            parametro.add(calendarioDTO.getIdCalendario());
        }

        list = this.execSQL(sql, parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    public Collection<CalendarioDTO> listaCalendarios() throws PersistenceException {
        List lista = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        sql.append("select idcalendario, descricao from calendario order by descricao,idcalendario ");

        lista = this.execSQL(sql.toString(), null);
        listRetorno.add("descricao");
        listRetorno.add("idcalendario");
        if (lista != null && !lista.isEmpty()) {
            final Collection<CalendarioDTO> listaCalendarios = engine.listConvertion(this.getBean(), lista, listRetorno);
            return listaCalendarios;
        }
        return null;
    }

}
