/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PastaDTO;
import br.com.centralit.citcorpore.bean.PerfilAcessoDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * DAO de PerfilAcesso.
 *
 * @author thays.araujo
 */
public class PerfilAcessoDao extends CrudDaoDefaultImpl {

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDPERFIL", "idPerfilAcesso", true, true, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));
        listFields.add(new Field("NOME", "nomePerfilAcesso", false, false, false, false));
        listFields.add(new Field("ACESSOSISTEMACITSMART", "acessoSistemaCitsmart", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "PERFILACESSO";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomePerfilAcesso"));
        return super.list(list);
    }

    public PerfilAcessoDTO listByName(final PerfilAcessoDTO obj) throws PersistenceException {
        final List fields = new ArrayList<>();
        fields.add("idPerfilAcesso");
        fields.add("nomePerfilAcesso");
        final String sql = "SELECT idPerfil, nome FROM " + this.getTableName() + " WHERE dataFim IS NULL AND idPerfil = ? ";
        final List dados = this.execSQL(sql, new Object[] {obj.getIdPerfilAcesso()});
        final List perfis = this.listConvertion(this.getBean(), dados, fields);

        if (perfis != null && !perfis.isEmpty()) {
            return (PerfilAcessoDTO) perfis.get(0);
        }

        return null;
    }

    /**
     * Consulta Perfils de Acesso com DataFim = NULL.
     *
     * @return perfisAtivos
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection<PerfilAcessoDTO> consultarPerfisDeAcessoAtivos() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        final List<Condition> condicao = new ArrayList<>();
        ordenacao.add(new Order("nomePerfilAcesso"));
        condicao.add(new Condition("dataFim", "is", null));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Consulta Perfis de Acesso da Pasta informada.
     *
     * @param pastaBean
     * @return perfisDeAcessoAtivos
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection<PerfilAcessoDTO> consultarPerfisDeAcessoAtivos(final PastaDTO pastaBean) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        final List retorno = new ArrayList<>();

        sql.append("SELECT perfilacesso.idperfil, perfilacesso.nome, perfilacessopasta.aprovaBaseConhecimento, perfilacessopasta.permiteleitura, perfilacessopasta.permiteleituragravacao ");
        sql.append("FROM perfilacessopasta ");
        sql.append("INNER JOIN perfilacesso ON perfilacessopasta.idperfil = perfilacesso.idperfil ");
        sql.append("WHERE perfilacessopasta.idpasta = ? ");

        parametro.add(pastaBean.getId());

        retorno.add("idPerfilAcesso");
        retorno.add("nomePerfilAcesso");
        retorno.add("aprovaBaseConhecimento");
        retorno.add("permiteLeitura");
        retorno.add("permiteLeituraGravacao");

        final List list = this.execSQL(sql.toString(), parametro.toArray());
        return engine.listConvertion(this.getBean(), list, retorno);
    }

    @Override
    public Class getBean() {
        return PerfilAcessoDTO.class;
    }

    public PerfilAcessoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public BaseEntity restore(final BaseEntity obj) throws PersistenceException {
        final PerfilAcessoDTO perfilAcessoDTO = (PerfilAcessoDTO) obj;
        final List fields = new ArrayList<>();
        fields.add("idPerfilAcesso");
        fields.add("dataInicio");
        fields.add("dataFim");
        fields.add("nomePerfilAcesso");
        fields.add("acessoSistemaCitsmart");
        final String sql = "SELECT idPerfil, dataInicio, dataFim, nome, acessoSistemaCitsmart FROM " + this.getTableName()
                + " WHERE dataFim IS NULL AND idPerfil = ? ";
        final List dados = this.execSQL(sql, new Object[] {perfilAcessoDTO.getIdPerfilAcesso()});
        return (BaseEntity) this.listConvertion(this.getBean(), dados, fields).get(0);
    }

    public Integer listarIdAdministrador() throws PersistenceException {
        final String SGBD = CITCorporeUtil.SGBD_PRINCIPAL.trim();
        final List parametro = new ArrayList<>();
        PerfilAcessoDTO perfilAcessoDTO = new PerfilAcessoDTO();
        final StringBuilder sql = new StringBuilder();
        if (SGBD.equalsIgnoreCase("ORACLE")) {
            sql.append("SELECT idPerfil FROM " + this.getTableName() + " WHERE dataFim IS NULL AND UPPER( nome ) LIKE '%Administrador%' ");
        } else {
            sql.append("SELECT idPerfil FROM " + this.getTableName() + " WHERE dataFim IS NULL AND  nome LIKE '%Administrador%' ");
        }
        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idPerfilAcesso");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result != null && result.size() > 0) {
            perfilAcessoDTO = (PerfilAcessoDTO) result.get(0);
        } else {
            perfilAcessoDTO = new PerfilAcessoDTO();
        }
        Integer resultado = new Integer(1);
        if (perfilAcessoDTO != null) {
            resultado = perfilAcessoDTO.getIdPerfilAcesso();
            return resultado;
        }
        return null;
    }

    /**
     * Verifica se PerfilAcessoInformado informada existe.
     *
     * @param perfilAcesso
     * @return true - existe; false - não existe;
     * @throws PersistenceException
     */
    public boolean verificarSePerfilAcessoExiste(final PerfilAcessoDTO perfilAcesso) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idperfil from " + this.getTableName() + "  where  nome = ? and datafim is null ");
        parametro.add(perfilAcesso.getNomePerfilAcesso());

        if (perfilAcesso.getIdPerfilAcesso() != null) {
            sql.append("and idperfil <> ?");
            parametro.add(perfilAcesso.getIdPerfilAcesso());
        }

        list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param perfilAcessoDTO
     * @return
     * @throws Exception
     */
    public PerfilAcessoDTO findByIdPerfilAcesso(final PerfilAcessoDTO perfilAcessoDTO) throws PersistenceException {

        final List fields = new ArrayList<>();

        final String sql = "select acessoSistemaCitsmart, nome, dataInicio from perfilacesso where idperfil = " + perfilAcessoDTO.getIdPerfilAcesso();

        fields.add("acessoSistemaCitsmart");

        fields.add("nome");

        fields.add("dataInicio");

        final List list = this.execSQL(sql, null);

        final List resultado = this.listConvertion(PerfilAcessoDTO.class, list, fields);

        if (resultado != null && !resultado.isEmpty()) {

            return (PerfilAcessoDTO) resultado.get(0);

        } else {

            return null;

        }
    }

    public String getAcessoCitsmartByUsuario(final Integer idUsuario) throws PersistenceException {

        final List parametros = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT pa.acessoSistemaCitsmart ");
        sql.append("FROM perfilacesso pa ");
        sql.append("	JOIN perfilacessogrupo pag ON pag.idperfil = pa.idperfil AND pag.datafim IS NULL ");
        sql.append("	JOIN gruposempregados ge ON ge.idgrupo = pag.idgrupo ");
        sql.append("	JOIN usuario u ON u.idempregado = ge.idempregado AND u.idusuario = ? ");
        sql.append("WHERE pa.acessoSistemaCitsmart <> 'N' AND pa.datafim IS NULL ");
        parametros.add(idUsuario);

        final List resultado = this.execSQL(sql.toString(), parametros.toArray());

        if (resultado != null && !resultado.isEmpty()) {
            return "S";
        }
        return "N";
    }

}
