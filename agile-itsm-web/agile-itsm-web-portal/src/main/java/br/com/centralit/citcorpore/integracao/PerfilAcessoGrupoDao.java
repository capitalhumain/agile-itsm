package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.PerfilAcessoGrupoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

public class PerfilAcessoGrupoDao extends CrudDaoDefaultImpl {

    public PerfilAcessoGrupoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDPERFIL", "idPerfilAcessoGrupo", true, false, false, false));
        listFields.add(new Field("IDGRUPO", "idGrupo", true, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", true, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "PERFILACESSOGRUPO";
    }

    public PerfilAcessoGrupoDTO listByIdGrupo(final PerfilAcessoGrupoDTO obj) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select idperfil from " + this.getTableName() + " where idgrupo = " + obj.getIdGrupo() + " ";
        fields.add("idPerfilAcessoGrupo");
        list = this.execSQL(sql, null);

        if (list != null && !list.isEmpty()) {
            return (PerfilAcessoGrupoDTO) this.listConvertion(this.getBean(), list, fields).get(0);
        } else {
            return null;
        }
    }

    /**
     * Retorna perfil de acesso ativo do grupo.
     *
     * @param grupo
     * @return
     * @throws Exception
     */
    public PerfilAcessoGrupoDTO obterPerfilAcessoGrupo(final GrupoDTO grupo) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        final List parametros = new ArrayList<>();

        sql.append("SELECT idperfil FROM perfilacessogrupo WHERE idgrupo = ? AND datafim IS NULL");
        parametros.add(grupo.getIdGrupo());

        fields.add("idPerfilAcessoGrupo");

        list = this.execSQL(sql.toString(), parametros.toArray());

        if (list != null && !list.isEmpty()) {
            return (PerfilAcessoGrupoDTO) this.listConvertion(this.getBean(), list, fields).get(0);
        } else {
            return null;
        }
    }

    @Override
    public Class getBean() {
        return PerfilAcessoGrupoDTO.class;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public void updateDataFim(final PerfilAcessoGrupoDTO obj) throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add(UtilDatas.getDataAtual());
        parametros.add(obj.getIdGrupo());

        final String sql = "UPDATE " + this.getTableName() + " SET DATAFIM = ? WHERE IDGRUPO = ? ";

        this.execUpdate(sql, parametros.toArray());
    }

    @Override
    public void delete(final BaseEntity obj) throws PersistenceException {
        final PerfilAcessoGrupoDTO dto = (PerfilAcessoGrupoDTO) obj;
        final String sql = "DELETE FROM " + this.getTableName() + " WHERE IDGRUPO = " + dto.getIdGrupo() + " ";
        this.execUpdate(sql, null);
    }

    /**
     * Retorna PerfilAcessoGrupo Ativos por idPerfilAcesso.
     *
     * @param idPerfilAcesso
     * @return
     * @throws Exception
     */
    public Collection findByIdPerfil(final Integer idPerfilAcesso) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idPerfilAcessoGrupo", "=", idPerfilAcesso));
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("idPerfilAcessoGrupo"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * @param idPerfilAcesso
     * @return
     * @throws Exception
     */
    public boolean existeGrupoVinculadoPerfil(final Integer idPerfilAcesso) throws PersistenceException {
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        final List parametros = new ArrayList<>();

        sql.append("SELECT * FROM perfilacessogrupo WHERE idPerfil = ?");

        parametros.add(idPerfilAcesso);

        list = this.execSQL(sql.toString(), parametros.toArray());

        return list != null && list.size() > 0;
    }

}
