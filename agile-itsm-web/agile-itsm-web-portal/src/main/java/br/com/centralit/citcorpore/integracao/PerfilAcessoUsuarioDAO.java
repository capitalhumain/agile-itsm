/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PerfilAcessoUsuarioDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

/**
 * DAO de PerfilAcessoUsuario.
 *
 * @author valdoilo.damasceno
 */
public class PerfilAcessoUsuarioDAO extends CrudDaoDefaultImpl {

    public PerfilAcessoUsuarioDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDPERFIL", "idPerfilAcesso", true, false, false, false));
        listFields.add(new Field("IDUSUARIO", "idUsuario", true, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));

        return listFields;
    }

    /**
     * Verifica se Usuário possui Perfi de Acesso específico.
     *
     * @param usuario
     * @return boolean
     * @author valdoilo.damasceno
     * @throws PersistenceException
     */
    public boolean verificarSeUsuarioPossuiPerfilAcessoEspecifico(final UsuarioDTO usuario) throws PersistenceException {
        if (usuario == null) {
            return false;
        }
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();

        sql.append("SELECT idusuario FROM perfilacessousuario ");
        sql.append("WHERE idusuario = ? and datafim IS NULL");

        parametro.add(usuario.getIdUsuario());

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna PerfilAcessoUsuario.
     *
     * @param usuario
     * @return PerfilAcessoUsuarioDTO
     * @throws Exception
     */
    public PerfilAcessoUsuarioDTO obterPerfilAcessoUsuario(final UsuarioDTO usuario) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        final List fields = this.gerarFieldsRetornoPerfilAcessoUsuario();

        sql.append("SELECT idPerfil, idUsuario, dataInicio, dataFim FROM perfilacessousuario ");
        sql.append("WHERE idusuario = ? and datafim IS NULL");

        parametro.add(usuario.getIdUsuario());

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            final List novaLista = this.listConvertion(PerfilAcessoUsuarioDTO.class, list, fields);
            return (PerfilAcessoUsuarioDTO) novaLista.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String getTableName() {
        return "PERFILACESSOUSUARIO";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return PerfilAcessoUsuarioDTO.class;
    }

    public PerfilAcessoUsuarioDTO listByIdUsuario(final PerfilAcessoUsuarioDTO obj) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select idperfil from " + this.getTableName() + " where idUsuario = " + obj.getIdUsuario() + " AND dataFim IS NULL";
        fields.add("idPerfilAcesso");
        list = this.execSQL(sql, null);
        final List novaLista = this.listConvertion(this.getBean(), list, fields);

        if (novaLista != null && !novaLista.isEmpty()) {
            return (PerfilAcessoUsuarioDTO) novaLista.get(0);
        } else {
            return null;
        }
    }

    public Collection listPerfilByIdUsuario(final UsuarioDTO usuarioDTO) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select idperfil from " + this.getTableName() + " where idUsuario = " + usuarioDTO.getIdUsuario() + " AND dataFim IS NULL";
        fields.add("idPerfilAcesso");
        list = this.execSQL(sql, null);
        final List novaLista = this.listConvertion(this.getBean(), list, fields);

        if (novaLista != null && !novaLista.isEmpty()) {
            return novaLista;
        } else {
            return null;
        }
    }

    public void updateDataFim(final PerfilAcessoUsuarioDTO obj) throws PersistenceException {
        final PerfilAcessoUsuarioDTO perfilAcessoUsuarioDTO = obj;
        final List parametros = new ArrayList<>();
        parametros.add(UtilDatas.getDataAtual());
        parametros.add(perfilAcessoUsuarioDTO.getIdUsuario());

        final String sql = "UPDATE " + this.getTableName() + " SET DATAFIM = ? WHERE IDUSUARIO = ?";

        this.execUpdate(sql, parametros.toArray());
    }

    /**
     * Atualiza o Perfil de Acesso do usuário.
     *
     * @param perfilAcessoUsuario
     * @throws Exception
     */
    public void update(final PerfilAcessoUsuarioDTO perfilAcessoUsuario) throws PersistenceException {
        final PerfilAcessoUsuarioDTO perfilAcessoUsuarioDTO = perfilAcessoUsuario;
        final List parametros = new ArrayList<>();
        parametros.add(perfilAcessoUsuario.getIdPerfilAcesso());
        parametros.add(perfilAcessoUsuarioDTO.getIdUsuario());

        final String sql = "UPDATE " + this.getTableName() + " SET idperfil = ? WHERE IDUSUARIO = ? ";

        this.execUpdate(sql, parametros.toArray());
    }

    @Override
    public BaseEntity restore(final BaseEntity obj) throws PersistenceException {
        final PerfilAcessoUsuarioDTO perfilAcessoUsuarioDTO = (PerfilAcessoUsuarioDTO) obj;
        final List fields = this.gerarFieldsRetornoPerfilAcessoUsuario();
        final String sql = "SELECT idPerfil, idUsuario, dataInicio, dataFim FROM " + this.getTableName() + " WHERE dataFim IS NULL AND idPerfil = "
                + perfilAcessoUsuarioDTO.getIdUsuario();
        final List dados = this.execSQL(sql, null);
        return (BaseEntity) this.listConvertion(this.getBean(), dados, fields);
    }

    public BaseEntity restorePerfilAcessoUsuario(final BaseEntity obj) throws PersistenceException {
        final PerfilAcessoUsuarioDTO perfilAcessoUsuarioDTO = (PerfilAcessoUsuarioDTO) obj;
        final List fields = this.gerarFieldsRetornoPerfilAcessoUsuario();
        final String sql = "SELECT idPerfil, idUsuario, dataInicio, dataFim FROM " + this.getTableName() + " WHERE dataFim IS NULL AND idUsuario = "
                + perfilAcessoUsuarioDTO.getIdUsuario();
        final List dados = this.execSQL(sql, null);
        if (dados != null && !dados.isEmpty()) {
            return (BaseEntity) this.listConvertion(this.getBean(), dados, fields).get(0);
        } else {
            return null;
        }
    }

    private List gerarFieldsRetornoPerfilAcessoUsuario() {
        final List fields = new ArrayList<>();
        fields.add("idPerfilAcesso");
        fields.add("idUsuario");
        fields.add("dataInicio");
        fields.add("dataFim");
        return fields;
    }

    @Override
    public void delete(final BaseEntity obj) throws PersistenceException {
        final PerfilAcessoUsuarioDTO dto = (PerfilAcessoUsuarioDTO) obj;
        final String sql = "DELETE FROM " + this.getTableName() + " WHERE IDUSUARIO = ? ";
        this.execUpdate(sql, new Object[] {dto.getIdUsuario()});
    }

    /**
     * Retorna PerfilAcessoUsuario Ativos por idPerfilAcesso.
     *
     * @param idPerfilAcesso
     * @return
     * @throws Exception
     */
    public Collection findByIdPerfil(final Integer idPerfilAcesso) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idPerfilAcesso", "=", idPerfilAcesso));
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("idPerfilAcesso"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void reativaPerfisUsuario(final Integer idUsuario) throws PersistenceException {

        final String sql = "UPDATE " + this.getTableName() + " SET DATAFIM = null WHERE IDUSUARIO = ? ";

        this.execUpdate(sql, new Object[] {idUsuario});

    }

}
