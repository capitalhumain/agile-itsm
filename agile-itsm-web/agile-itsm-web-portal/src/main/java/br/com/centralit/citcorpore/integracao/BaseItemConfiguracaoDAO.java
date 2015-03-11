/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BaseItemConfiguracaoDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * DAO de BaseItemConfiguracao.
 *
 * @author valdoilo.damasceno
 *
 */
public class BaseItemConfiguracaoDAO extends CrudDaoDefaultImpl {

    /**
     * Construtor padrão.
     */
    public BaseItemConfiguracaoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseEntity restore(final BaseEntity obj) throws PersistenceException {
        final List<Object> parametro = new ArrayList<Object>();
        List<Object> list = new ArrayList<Object>();
        final String sql = "SELECT	fl.idbaseitemconfiguracao, " + "fl.idtipoitemconfiguracao,	" + "pai.nomebaseitemconfiguracao, " + "fl.executavel, "
                + "fl.tipoexecucao, " + "fl.comando, " + "fl.idbaseitemconfiguracaopai, " + "pai.datainicio, " + "pai.datafim " + "FROM " + getTableName()
                + " AS pai " + "INNER JOIN " + getTableName() + " AS fl " + "ON pai.idbaseitemconfiguracao = fl.idbaseitemconfiguracaopai "
                + "WHERE fl.idbaseitemconfiguracao = ?";
        parametro.add(((BaseItemConfiguracaoDTO) obj).getId());
        list = execSQL(sql, parametro.toArray());
        final List<?> result = listConvertion(BaseItemConfiguracaoDTO.class, list, generateFieldsResult());
        if (!result.isEmpty()) {
            return (BaseEntity) result.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<BaseEntity> restoreFilhos(final BaseEntity pai) throws PersistenceException {
        final List<Object> parametro = new ArrayList<Object>();
        List<Object> list = new ArrayList<Object>();
        final String sql = "SELECT fl.idbaseitemconfiguracao, fl.idtipoitemconfiguracao, pai.nomebaseitemconfiguracao, fl.executavel, fl.tipoexecucao, fl.comando, fl.idbaseitemconfiguracaopai, pai.datainicio, pai.datafim "
                + "FROM baseitemconfiguracao AS pai "
                + "INNER JOIN baseitemconfiguracao AS fl "
                + "ON pai.idbaseitemconfiguracao = fl.idbaseitemconfiguracaopai "
                + "WHERE 	pai.idbaseitemconfiguracaopai IS NULL "
                + "AND pai.datafim IS NULL " + "AND fl.idbaseitemconfiguracaopai = ?";
        parametro.add(((BaseItemConfiguracaoDTO) pai).getId());
        list = execSQL(sql, parametro.toArray());
        final List<?> result = listConvertion(BaseItemConfiguracaoDTO.class, list, generateFieldsResult());
        return (List<BaseEntity>) result;
    }

    private List<Object> generateFieldsResult() {
        final List<Object> fields = new ArrayList<Object>();
        fields.add("id");
        fields.add("idTipoItemConfiguracao");
        fields.add("nome");
        fields.add("executavel");
        fields.add("tipoexecucao");
        fields.add("comando");
        fields.add("idBaseItemConfiguracaoPai");
        fields.add("dataInicio");
        fields.add("dataFim");
        return fields;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDBASEITEMCONFIGURACAO", "id", true, true, false, false));
        listFields.add(new Field("IDTIPOITEMCONFIGURACAO", "idTipoItemConfiguracao", false, false, false, false));
        listFields.add(new Field("NOMEBASEITEMCONFIGURACAO", "nome", false, false, false, false));
        listFields.add(new Field("IDBASEITEMCONFIGURACAOPAI", "idBaseItemConfiguracaoPai", false, false, false, false));
        listFields.add(new Field("EXECUTAVEL", "executavel", false, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));
        listFields.add(new Field("TIPOEXECUCAO", "tipoexecucao", false, false, false, false));
        listFields.add(new Field("COMANDO", "comando", false, false, false, false));

        return listFields;
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public String getTableName() {
        return "BASEITEMCONFIGURACAO";
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList();
        list.add(new Order("IDBASEITEMCONFIGURACAOPAI"));
        return super.list(list);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getBean() {
        return BaseItemConfiguracaoDTO.class;
    }

    /**
     * Busca na tabela se existe cadastro para a baseItemConfiguração
     *
     * @param obj
     * @param nomePai
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean existBaseItemConfiguracao(final BaseItemConfiguracaoDTO dto) throws PersistenceException {
        final List<Object> parametro = new ArrayList<Object>();
        final List<Object> fields = new ArrayList<Object>();
        List<Object> list = new ArrayList<Object>();
        String sql = null;
        final String strSGBDPrincipal = CITCorporeUtil.SGBD_PRINCIPAL;
        if (strSGBDPrincipal.equalsIgnoreCase("ORACLE")) {
            sql = "SELECT idbaseitemconfiguracao FROM " + getTableName() + " b "
                    + "WHERE b.nomebaseitemconfiguracao = ? AND b.datafim IS NULL AND b.idbaseitemconfiguracaopai IS NULL";
        } else {
            sql = "SELECT idbaseitemconfiguracao FROM " + getTableName() + " AS b "
                    + "WHERE b.nomebaseitemconfiguracao = ? AND b.datafim IS NULL AND b.idbaseitemconfiguracaopai IS NULL";
        }
        parametro.add(dto.getNome());
        list = execSQL(sql, parametro.toArray());
        fields.add("IDBASEITEMCONFIGURACAO");
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
