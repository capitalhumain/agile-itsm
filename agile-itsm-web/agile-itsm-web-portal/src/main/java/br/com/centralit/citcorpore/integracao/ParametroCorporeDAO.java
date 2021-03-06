package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ParametroCorporeDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author valdoilo.damasceno
 *
 */
public class ParametroCorporeDAO extends CrudDaoDefaultImpl {

    private final String SQL_UPDATE_PARAMETRO = "update " + this.getTableName() + " set valor = ? where IDPARAMETROCORPORE = ?";
    private final String SQL_GET_PARAMETRO_CITCORPORE = "SELECT IDPARAMETROCORPORE, NOMEPARAMETROCORPORE, VALOR, IDEMPRESA, DATAINICIO, DATAFIM FROM parametrocorpore ";

    public ParametroCorporeDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<ParametroCorporeDTO> find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("IDPARAMETROCORPORE", "id", true, false, false, true));
        listFields.add(new Field("NOMEPARAMETROCORPORE", "nome", false, false, false, true, "Nome Parâmetro!"));
        listFields.add(new Field("VALOR", "valor", false, false, false, false));
        listFields.add(new Field("IDEMPRESA", "idEmpresa", false, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));
        listFields.add(new Field("tipodado", "tipoDado", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "PARAMETROCORPORE";
    }

    @Override
    public Collection<ParametroCorporeDTO> list() throws PersistenceException {
        final List<Order> list = new ArrayList<>();
        list.add(new Order("id"));
        return super.list(list);
    }

    /**
     * @param id
     *            Integer IdParamentro do Sistema
     * @param NomeParametro
     *            String Nome Paramentro do Sistema
     * @return Lista
     * @throws Exception
     * @author Maycon.Fernandes
     */
    public ParametroCorporeDTO getParamentroAtivo(final Integer id) throws PersistenceException {
        final List objs = new ArrayList<>();
        objs.add(id);

        String sql = SQL_GET_PARAMETRO_CITCORPORE;

        sql += " WHERE ";
        sql += " (IDPARAMETROCORPORE = ?) AND (DATAFIM IS NULL) ";

        sql += " ORDER BY NOMEPARAMETROCORPORE, DATAINICIO DESC, DATAFIM";
        final List lista = this.execSQL(sql, objs.toArray());

        final List listRetorno = this.prepararListaDeRetorno();

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return (ParametroCorporeDTO) result.get(0);
    }

    public List<ParametroCorporeDTO> pesquisarParamentro(final Integer id, final String NomeParametro) throws PersistenceException {
        final List<Integer> objs = new ArrayList<>();
        objs.add(id);

        final StringBuilder sql = new StringBuilder(SQL_GET_PARAMETRO_CITCORPORE);

        sql.append(" WHERE ");
        sql.append(" (IDPARAMETROCORPORE = ?) ");

        sql.append(" ORDER BY NOMEPARAMETROCORPORE");
        final List lista = this.execSQL(sql.toString(), objs.toArray());

        final List listRetorno = this.prepararListaDeRetorno();

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return result;
    }

    @Override
    public Class<ParametroCorporeDTO> getBean() {
        return ParametroCorporeDTO.class;
    }

    private List prepararListaDeRetorno() {
        final List<String> listRetorno = new ArrayList<>();
        listRetorno.add("id");
        listRetorno.add("nome");
        listRetorno.add("valor");
        listRetorno.add("idEmpresa");
        listRetorno.add("dataInicio");
        listRetorno.add("dataFim");
        return listRetorno;
    }

    public Collection findByID(final ParametroCorporeDTO parametroCorporeDTO) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("id", "=", parametroCorporeDTO.getId()));
        ordenacao.add(new Order("id"));
        condicao.add(new Condition(Condition.AND, "dataFim", "is", null));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Atualiza o valor do parametro pelo id
     *
     */
    public void atualizarParametro(final Integer id, final String valor) {
        final List<Object> parametros = new ArrayList<>();
        parametros.add(valor);
        parametros.add(id);

        try {
            super.execUpdate(SQL_UPDATE_PARAMETRO, parametros.toArray());
        } catch (final PersistenceException e) {
            e.printStackTrace();
        }
    }

}
