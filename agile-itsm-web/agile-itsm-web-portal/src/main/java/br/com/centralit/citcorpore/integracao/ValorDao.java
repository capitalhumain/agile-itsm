package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.TipoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ValorDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ValorDao extends CrudDaoDefaultImpl {

    public ValorDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("IDVALOR", "idValor", true, true, false, false));
        listFields.add(new Field("IDITEMCONFIGURACAO", "idItemConfiguracao", false, false, false, false));
        listFields.add(new Field("IDBASEITEMCONFIGURACAO", "idBaseItemConfiguracao", false, false, false, false));
        listFields.add(new Field("IDCARACTERISTICA", "idCaracteristica", false, false, false, false));
        listFields.add(new Field("VALORSTR", "valorStr", false, false, false, false));
        listFields.add(new Field("VALORLONGO", "valorLongo", false, false, false, false));
        listFields.add(new Field("VALORDECIMAL", "valorDecimal", false, false, false, false));
        listFields.add(new Field("VALORDATE", "valorDate", false, false, false, false));
        return listFields;
    }

    /**
     * Consulta valor por idItemConfiguracao.
     *
     * @param idItemConfiguracao
     * @return Collection
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection findByIdItemConfiguracao(final Integer idItemConfiguracao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idItemConfiguracao", "=", idItemConfiguracao));
        ordenacao.add(new Order("numero"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Exclui valor por idItemConfiguracao.
     *
     * @param idItemConfiguracao
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public void deleteByIdItemConfiguracao(final Integer idItemConfiguracao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idItemConfiguracao", "=", idItemConfiguracao));
        super.deleteByCondition(condicao);
    }

    /**
     * Consulta Valor por idCaracteristica.
     *
     * @param idCaracteristica
     * @return Collection
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection findByIdCaracteristica(final Integer idCaracteristica) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCaracteristica", "=", idCaracteristica));
        ordenacao.add(new Order("numero"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Exclui valor por idCaracteristica.
     *
     * @param idCaracteristica
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public void deleteByIdCaracteristica(final Integer idCaracteristica) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCaracteristica", "=", idCaracteristica));
        super.deleteByCondition(condicao);
    }

    /**
     * Retorna Valor da Característica do Item Configuração.
     *
     * @param idBaseItemConfiguracao
     * @param idCaracteristica
     * @return ValorDTO
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public ValorDTO restore(final boolean isItemConfigruacao, final Integer idBaseItemConfiguracao, final Integer idCaracteristica) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        if (isItemConfigruacao) {
            condicao.add(new Condition("idItemConfiguracao", "=", idBaseItemConfiguracao));
        } else {
            condicao.add(new Condition("idBaseItemConfiguracao", "=", idBaseItemConfiguracao));
        }
        condicao.add(new Condition("idCaracteristica", "=", idCaracteristica));
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("valorStr"));
        final List resultado = (List) super.findByCondition(condicao, ordenacao);

        if (resultado != null && !resultado.isEmpty()) {
            return (ValorDTO) resultado.get(0);
        } else {
            return null;
        }
    }

    /**
     * Retorna Valor da Característica do Item Configuração.
     *
     * @param idBaseItemConfiguracao
     * @param idCaracteristica
     * @return ValorDTO
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public ValorDTO restoreItemConfiguracao(final Integer idItemConfiguracao, final Integer idCaracteristica) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idItemConfiguracao", "=", idItemConfiguracao));
        condicao.add(new Condition("idCaracteristica", "=", idCaracteristica));
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("valorStr"));
        final List resultado = (List) super.findByCondition(condicao, ordenacao);

        if (resultado != null && !resultado.isEmpty()) {
            return (ValorDTO) resultado.get(0);
        } else {
            return null;
        }
    }

    public ValorDTO restoreValorByIdItemConfiguracao(final Integer idItemConfiguracao, final Integer idCaracteristica, final String valor)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idItemConfiguracao", "=", idItemConfiguracao));
        condicao.add(new Condition("idCaracteristica", "=", idCaracteristica));
        condicao.add(new Condition("valorStr", "=", valor));
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("valorStr"));
        final List resultado = (List) super.findByCondition(condicao, ordenacao);

        if (resultado != null && !resultado.isEmpty()) {
            return (ValorDTO) resultado.get(0);
        } else {
            return null;
        }
    }

    /**
     * Retorna características e seus respectivos valores.
     *
     * @param itemConfiguracao
     *            - ItemConfiguracaoDTO.
     * @param tipoItemConfiguracao
     *            - TipoItemConfiguracaoDTO
     * @return listCaracteristica
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection<ValorDTO> findByItemAndTipoItemConfiguracao(final ItemConfiguracaoDTO itemConfiguracao, final TipoItemConfiguracaoDTO tipoItemConfiguracao)
            throws PersistenceException {
        final StringBuilder sql = new StringBuilder();

        final List parametro = new ArrayList<>();

        this.gerarSqlFindByItemAndTipoItemConfiguracao(itemConfiguracao, tipoItemConfiguracao, sql, parametro);

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        final Collection<ValorDTO> listCaracteristica = engine.listConvertion(this.getBean(), list, this.getListaDeRetornoCaracteristicaValor());

        return listCaracteristica;
    }

    /**
     *
     * Gerar SQL para a consulta de características e seus respectivos valores.
     *
     * @param itemConfiguracao
     * @param tipoItemConfiguracao
     * @param sql
     * @param parametro
     * @author valdoilo.damasceno
     */
    private void gerarSqlFindByItemAndTipoItemConfiguracao(final ItemConfiguracaoDTO itemConfiguracao, final TipoItemConfiguracaoDTO tipoItemConfiguracao,
            final StringBuilder sql, final List parametro) {
        String sqlHardware = "";
        parametro.add(itemConfiguracao.getIdItemConfiguracao());
        if (tipoItemConfiguracao.getTag().equalsIgnoreCase("HARDWARE")) {
            sqlHardware += "AND (tipo.tagtipoitemconfiguracao IN ('HARDWARE', 'DEVICEID', 'MEMORIES' , 'INPUTS', 'PORTS', 'CONTROLLERS', 'SOUNDS', 'STORAGES', 'DRIVES', 'NETWORKS', 'VIDEOS', 'MONITORS', 'PRINTERS',  'QUERY', 'RAM', 'SLOTS', 'MODEMS', 'TAG_TIPO_IC_01', 'VIDEOS') ";
            sqlHardware += " OR tipo.categoria = " + tipoItemConfiguracao.getCategoria() + ")";
        } else {
            sqlHardware += "AND (tipo.tagtipoitemconfiguracao LIKE ?";
            parametro.add("%" + tipoItemConfiguracao.getTag());
            sqlHardware += " OR tipo.categoria = " + tipoItemConfiguracao.getCategoria() + ")";
        }
        sql.append("SELECT item.iditemconfiguracao, item.datainicio, caracteristica.idcaracteristica, caracteristica.nomecaracteristica, valor.idvalor, valor.valorstr, caracteristica.tagcaracteristica, tipo.tagtipoitemconfiguracao ");
        sql.append("FROM itemconfiguracao item ");
        sql.append("INNER JOIN tipoitemconfiguracao tipo ON item.idtipoitemconfiguracao = tipo.idtipoitemconfiguracao and (item.datafim is null) ");
        sql.append("INNER JOIN tipoitemcfgcaracteristica tipocaracteristica ON tipo.idtipoitemconfiguracao = tipocaracteristica.idtipoitemconfiguracao ");
        sql.append("INNER JOIN caracteristica caracteristica ON tipocaracteristica.idcaracteristica = caracteristica.idcaracteristica ");
        sql.append("INNER JOIN valor valor ON caracteristica.idcaracteristica = valor.idcaracteristica AND valor.iditemconfiguracao = item.iditemconfiguracao ");
        sql.append("WHERE item.iditemconfiguracaopai = ? " + sqlHardware + " ORDER BY tipo.tagtipoitemconfiguracao, item.iditemconfiguracao");
    }

    /**
     *
     * Gerar SQL para a consulta de características e seus respectivos valores.
     *
     * @param itemConfiguracao
     * @param tipoItemConfiguracao
     * @param sql
     * @param parametro
     * @author valdoilo.damasceno
     * @throws throws Exception
     */
    public Collection<ValorDTO> findByItemAndTipoItemConfiguracaoSofware(final ItemConfiguracaoDTO itemConfiguracao,
            final TipoItemConfiguracaoDTO tipoItemConfiguracao) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        String sqlSofware = "";
        parametro.add(itemConfiguracao.getIdItemConfiguracao());
        sqlSofware += "AND (tipo.tagtipoitemconfiguracao LIKE ?";
        parametro.add("%" + tipoItemConfiguracao.getTag());
        sqlSofware += " OR tipo.categoria = " + tipoItemConfiguracao.getCategoria() + ")";
        sql.append("SELECT item.iditemconfiguracao, item.datainicio, caracteristica.idcaracteristica, caracteristica.nomecaracteristica, valor.valorstr, caracteristica.tagcaracteristica, tipo.tagtipoitemconfiguracao ");
        sql.append("FROM itemconfiguracao item ");
        sql.append("INNER JOIN tipoitemconfiguracao tipo ON item.idtipoitemconfiguracao = tipo.idtipoitemconfiguracao and (item.datafim is null) ");
        sql.append("INNER JOIN tipoitemcfgcaracteristica tipocaracteristica ON tipo.idtipoitemconfiguracao = tipocaracteristica.idtipoitemconfiguracao ");
        sql.append("INNER JOIN caracteristica caracteristica ON tipocaracteristica.idcaracteristica = caracteristica.idcaracteristica ");
        sql.append("INNER JOIN valor valor ON caracteristica.idcaracteristica = valor.idcaracteristica AND valor.iditemconfiguracao = item.iditemconfiguracao ");
        sql.append("WHERE item.iditemconfiguracao = ? " + sqlSofware + " AND caracteristica.tagcaracteristica LIKE 'NAME%'  ORDER BY item.iditemconfiguracao");
        final List list = this.execSQL(sql.toString(), parametro.toArray());
        final Collection<ValorDTO> listCaracteristica = engine.listConvertion(this.getBean(), list, this.getListaDeRetornoCaracteristicaValor());

        return listCaracteristica;
    }

    /**
     * Monta lista de retorno (idItemConfiguracao, nomeCaracteristica, valorStr, valorDate, dataInicio)
     *
     * @return List
     * @author valdoilo.damasceno
     */
    private List getListaDeRetornoCaracteristicaValor() {
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idItemConfiguracao");
        listRetorno.add("dataInicio");
        listRetorno.add("idCaracteristica");
        listRetorno.add("nomeCaracteristica");
        listRetorno.add("idValor");
        listRetorno.add("valorStr");
        listRetorno.add("tag");
        listRetorno.add("tagtipoitemconfiguracao");
        return listRetorno;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Valor";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ValorDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection listByItemConfiguracaoAndTagCaracteristica(final Integer idItemConfiguracao, final String tag) throws PersistenceException {
        final String sql = "SELECT "
                + this.getNamesFieldsStr("valor")
                + " "
                + "FROM valor INNER JOIN caracteristica ON valor.idcaracteristica = caracteristica.idcaracteristica  where iditemconfiguracao = ? AND datafim is null "
                + "AND tagcaracteristica = ?";
        final List<?> dados = this.execSQL(sql, new Object[] {idItemConfiguracao, tag});
        final List<String> fields = this.getListNamesFieldClass();
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public Collection listUniqueValuesByTagCaracteristica(final String tag) throws PersistenceException {
        String sql = "";
        if (CITCorporeUtil.SGBD_PRINCIPAL.equalsIgnoreCase("mysql")) {
            sql = "SELECT DISTINCT  replace(replace(valorstr,'\\\\\\\\','-'),'\\\\', '-')  FROM valor INNER JOIN caracteristica ON valor.idcaracteristica = caracteristica.idcaracteristica  where datafim is null AND tagcaracteristica = ? AND valorstr <> '' ORDER BY 1";
        } else {
            sql = "SELECT DISTINCT  replace(replace(valorstr,'\\\\','-'), '\\', '-')FROM valor INNER JOIN caracteristica ON valor.idcaracteristica = caracteristica.idcaracteristica  where datafim is null AND tagcaracteristica =? AND valorstr <> '' ORDER BY 1";
        }
        final List<?> dados = this.execSQL(sql, new Object[] {tag});
        final List<String> fields = new ArrayList<>();
        fields.add("valorStr");
        return this.listConvertion(this.getBean(), dados, fields);
    }

}
