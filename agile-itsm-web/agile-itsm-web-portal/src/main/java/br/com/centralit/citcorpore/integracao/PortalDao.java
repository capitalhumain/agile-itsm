package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PortalDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class PortalDao extends CrudDaoDefaultImpl {

    public PortalDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idPortal", "idPortal", true, true, false, false));
        listFields.add(new Field("idItem", "idItem", false, false, false, false));
        listFields.add(new Field("posicaoX", "posicaoX", false, false, false, false));
        listFields.add(new Field("posicaoY", "posicaoY", false, false, false, false));
        listFields.add(new Field("idUsuario", "idUsuario", false, false, false, false));
        listFields.add(new Field("coluna", "coluna", false, false, false, false));
        listFields.add(new Field("largura", "largura", false, false, false, false));
        listFields.add(new Field("altura", "altura", false, false, false, false));
        listFields.add(new Field("data", "data", false, false, false, false));
        /* listFields.add(new Field("hora", "hora", false, false, false, false)); */

        return listFields;
    }

    @Override
    public String getTableName() {
        return "portal";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idUsuario"));
        return super.list(list);
    }

    public Collection listByUsuario(final Integer idUsuario) throws Exception {
        final Object[] objs = new Object[] {idUsuario};

        final String sql = " SELECT idUsuario from portal where idUsuario = ? ";

        final List lista = this.execSQL(sql, objs);
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idUsuario");

        return engine.listConvertion(this.getBean(), lista, listRetorno);
    }

    public Collection findByCondition(final Integer id) throws Exception {
        final List list1 = new ArrayList<>();
        final List list2 = new ArrayList<>();
        list1.add(new Condition("idUsuario", "=", id));
        list2.add(new Order("idItem"));
        return super.findByCondition(list1, list2);
    }

    public Collection findByCondition(final Integer idUsuario, final Integer idItem) throws Exception {
        final List list1 = new ArrayList<>();
        final List list2 = new ArrayList<>();
        list1.add(new Condition("idUsuario", "=", idUsuario));
        list1.add(new Condition("idItem", "=", idItem));
        list2.add(new Order("idItem"));
        return super.findByCondition(list1, list2);
    }

    @Override
    public void update(final BaseEntity obj) throws PersistenceException {
        final PortalDTO dto = (PortalDTO) obj;
        final List param = new ArrayList<>();
        param.add(dto.getPosicaoX());
        param.add(dto.getPosicaoY());
        param.add(dto.getLargura());
        param.add(dto.getAltura());
        param.add(dto.getData());
        /* param.add(dto.getHora()); */
        param.add(dto.getIdUsuario());
        param.add(dto.getIdItem());
        final String str = "UPDATE " + this.getTableName()
                + " SET posicaoX = ?, posicaoY = ?, largura=? ,altura =?, data =? WHERE idusuario = ? AND iditem = ?";
        super.execUpdate(str, param.toArray());
    }

    @Override
    public void delete(final BaseEntity obj) throws PersistenceException {
        final PortalDTO dto = (PortalDTO) obj;
        final List param = new ArrayList<>();
        param.add(dto.getIdUsuario());
        param.add(dto.getIdItem());
        final String str = "DELETE FROM " + this.getTableName() + " WHERE idusuario = ? AND iditem = ?";
        super.execUpdate(str, param.toArray());
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("idUsuario"));
        return super.find(obj, ordem);
    }

    @Override
    public Class getBean() {
        return PortalDTO.class;
    }

    /**
     * @param idServico
     * @return
     * @throws Exception
     */
    public boolean existeQuestionarioServico(final Integer idServico) throws PersistenceException {

        try {
            final Object[] param = new Object[] {idServico};

            final String query = "SELECT count(*) FROM " + "	servico s" + " INNER JOIN" + " servicocontrato sc ON sc.idservico = s.idservico" + " INNER JOIN"
                    + "	templatesolicitacaoservico t ON t.idtemplate = s.idtemplatesolicitacao" + " INNER JOIN"
                    + "	questionario q ON q.idquestionario = t.idquestionario" + " WHERE" + "	s.idservico = ?;";

            final List lista = this.execSQL(query, param);

            final Object[] row = (Object[]) lista.get(0);

            return Integer.parseInt(row[0].toString()) > 0;

        } catch (final NumberFormatException ex) {

            return Boolean.FALSE;
        }
    }

    /**
     * @param idServico
     * @return
     * @throws Exception
     */
    public boolean existeQuestionario(final Integer idServico) throws PersistenceException {

        try {

            final Object[] param = new Object[] {idServico};

            final String query = "SELECT COUNT(idquestionario) FROM servico s "
                    + "		INNER JOIN templatesolicitacaoservico t ON t.idtemplate = s.idtemplatesolicitacao " + "		WHERE s.idservico=?";

            final List lista = this.execSQL(query, param);

            final Object[] row = (Object[]) lista.get(0);

            return Integer.parseInt(row[0].toString()) > 0;

        } catch (final NumberFormatException ex) {

            return Boolean.FALSE;
        }

    }

    /**
     * @param idServicoCatalogo
     * @return
     */
    public Integer obterIdQuestionarioServico(final Integer idServicoCatalogo) {

        final Object[] param = new Object[] {idServicoCatalogo};

        final String query = "select idQuestionario from servico s INNER JOIN templatesolicitacaoservico t ON t.idtemplate = s.idtemplatesolicitacao WHERE s.idservico=?";

        List lista;
        try {

            lista = this.execSQL(query, param);

            if (lista != null && lista.size() > 0) {

                final Object[] row = (Object[]) lista.get(0);

                return Integer.parseInt(row[0].toString());
            }

        } catch (final PersistenceException e) {

            e.printStackTrace();
        }

        return null;
    }

}
