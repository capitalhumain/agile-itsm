package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GrupoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.SQLConfig;

public class GrupoItemConfiguracaoDAO extends CrudDaoDefaultImpl {

    public GrupoItemConfiguracaoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idGrupoItemConfiguracao", "idGrupoItemConfiguracao", true, true, false, false));
        listFields.add(new Field("idGrupoItemConfiguracaoPai", "idGrupoItemConfiguracaoPai", false, false, false, false));
        listFields.add(new Field("nomeGrupoItemConfiguracao", "nomeGrupoItemConfiguracao", false, false, false, false));
        listFields.add(new Field("emailGrupoItemConfiguracao", "emailGrupoItemConfiguracao", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "grupoitemconfiguracao";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        final List<Condition> condicao = new ArrayList<>();
        ordenacao.add(new Order("nomeGrupoItemConfiguracao"));
        condicao.add(new Condition("dataFim", "is", null));

        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public Class getBean() {
        return GrupoItemConfiguracaoDTO.class;
    }

    /**
     * Verifica se existe outro grupo igual criado.
     * Se existir retorna 'true', senao retorna 'false';
     *
     * @param grupoItemConfiguracao
     * @return estaCadastrado
     * @throws PersistenceException
     */
    public boolean VerificaSeCadastrado(final GrupoItemConfiguracaoDTO grupoItemConfiguracao) throws PersistenceException {
        boolean estaCadastrado = false;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.POSTGRESQL)) {
            // Se o banco for POSTGRESQL utilizar 'ilike' se outro banco utilizar 'like'.
            sql.append("select nomegrupoitemconfiguracao from " + this.getTableName() + "  where nomegrupoitemconfiguracao ilike ? and datafim is null  ");
        } else {
            sql.append("select nomegrupoitemconfiguracao from " + this.getTableName() + "  where nomegrupoitemconfiguracao like ? and datafim is null  ");
        }
        parametro.add(grupoItemConfiguracao.getNomeGrupoItemConfiguracao());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            estaCadastrado = true;
        }
        return estaCadastrado;
    }

    @Override
    public BaseEntity createWithID(final BaseEntity obj) throws PersistenceException {
        return super.createWithID(obj);
    }

    /**
     * Apenas verica se existe não aplicando a data fim
     * (Antigamente duplicado)
     *
     * @param grupoItemConfiguracao
     * @return estaCadastrado
     * @throws PersistenceException
     */
    public boolean verificaSeExiste(final GrupoItemConfiguracaoDTO grupoItemConfiguracao) throws PersistenceException {
        boolean estaCadastrado;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idGrupoItemConfiguracao from " + this.getTableName() + "  where  idGrupoItemConfiguracao = ? ");
        parametro.add(grupoItemConfiguracao.getIdGrupoItemConfiguracao());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            estaCadastrado = true;
        } else {
            estaCadastrado = false;
        }
        return estaCadastrado;
    }

    /**
     * Verifica se existe relacionamento do grupo com outras tabelas.
     * Se existir retorna 'true', se nao existir retorna 'false';
     *
     * @param grupoItemConfiguracao
     * @return estaRelacionado
     * @throws PersistenceException
     */
    public boolean VerificaSeRelacionado(final GrupoItemConfiguracaoDTO grupoItemConfiguracao) throws PersistenceException {
        boolean estaRelacionado;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idGrupoItemConfiguracao from " + this.getTableName() + "  where  nomeGrupoItemConfiguracao = ? and datafim is null  ");
        parametro.add(grupoItemConfiguracao.getNomeGrupoItemConfiguracao());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            estaRelacionado = true;
        } else {
            estaRelacionado = false;
        }
        return estaRelacionado;
    }

    /**
     * Verifica se existe relacionamento do grupo com item configuracao.
     * Se existir retorna 'true', se nao existir retorna 'false';
     *
     * @param grupoItemConfiguracao
     * @throws PersistenceException
     */
    public boolean verificaICRelacionados(final GrupoItemConfiguracaoDTO grupoItemConfiguracao) throws PersistenceException {
        boolean estaRelacionado;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select *from itemconfiguracao  where  idGrupoItemConfiguracao = ? and datafim is null  ");
        parametro.add(grupoItemConfiguracao.getIdGrupoItemConfiguracao());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            estaRelacionado = true;
        } else {
            estaRelacionado = false;
        }
        return estaRelacionado;
    }

    /**
     * Lista os grupos associados ao evento passado como parametro.
     *
     * @param idEvento
     * @return Collection<GrupoItemConfiguracaoDTO> relacionados ao evento
     * @throws Exception
     */
    public Collection<GrupoItemConfiguracaoDTO> listByEvento(final Integer idEvento) throws PersistenceException {
        final String sql = "SELECT idgrupoitemconfiguracao, nomegrupoitemconfiguracao FROM " + this.getTableName()
                + " INNER JOIN eventogrupo AS eg ON eg.idgrupo = idgrupoitemconfiguracao WHERE eg.idevento = ?";
        final List<?> dados = this.execSQL(sql, new Object[] {idEvento});
        final List<String> fields = new ArrayList<String>();
        fields.add("idGrupoItemConfiguracao");
        fields.add("nomeGrupoItemConfiguracao");
        return this.listConvertion(this.getBean(), dados, fields);

    }

    /**
     * Lista os grupos associados ao evento passado como parametro.
     *
     * @param IDPAI
     * @return Collection<GrupoItemConfiguracaoDTO>
     * @throws Exception
     */
    public Collection<GrupoItemConfiguracaoDTO> listByIdGrupoItemConfiguracaoPai(final Integer idGrupoItemConfiguracaoPai) throws PersistenceException {
        final String sql = "SELECT idgrupoitemconfiguracao, nomegrupoitemconfiguracao FROM " + this.getTableName()
                + "  WHERE idGrupoItemConfiguracaoPai = ? AND dataFim IS NULL";
        final List<?> dados = this.execSQL(sql, new Object[] {idGrupoItemConfiguracaoPai});
        final List<String> fields = new ArrayList<String>();
        fields.add("idGrupoItemConfiguracao");
        fields.add("nomeGrupoItemConfiguracao");
        return this.listConvertion(this.getBean(), dados, fields);

    }

    public Collection<GrupoItemConfiguracaoDTO> listByIdGrupoItemConfiguracaoPaiNull() throws PersistenceException {
        final String sql = "SELECT idgrupoitemconfiguracao, nomegrupoitemconfiguracao FROM " + this.getTableName()
                + "  WHERE idGrupoItemConfiguracaoPai IS NULL AND dataFim IS NULL";
        final List<?> dados = this.execSQL(sql, null);
        final List<String> fields = new ArrayList<String>();
        fields.add("idGrupoItemConfiguracao");
        fields.add("nomeGrupoItemConfiguracao");
        return this.listConvertion(this.getBean(), dados, fields);

    }

    /**
     * Lista os grupos associados ao evento passado como parametro.
     *
     * @param IDPAI
     * @return Collection<GrupoItemConfiguracaoDTO>
     * @throws Exception
     */
    // /*Thiago Fernandes - 03/11/2013 - 09:40 - Sol. 121468 - Correção de sql de busca por lista os grupos associados ao evento passado como parametro.
    public Collection<GrupoItemConfiguracaoDTO> listByIdGrupoItemConfiguracaoDesenvolvimento(final Integer idGrupoItemConfiguracaoPai)
            throws PersistenceException {
        final String sql = "SELECT idgrupoitemconfiguracao, nomegrupoitemconfiguracao FROM " + this.getTableName()
                + "  WHERE (idGrupoItemConfiguracaoPai = ? OR idGrupoItemConfiguracaoPai IS NULL) AND dataFim IS NULL";
        final List<?> dados = this.execSQL(sql, new Object[] {idGrupoItemConfiguracaoPai});
        final List<String> fields = new ArrayList<String>();
        fields.add("idGrupoItemConfiguracao");
        fields.add("nomeGrupoItemConfiguracao");
        return this.listConvertion(this.getBean(), dados, fields);

    }

    /**
     * Lista os grupos associados ao evento passado como parametro.
     *
     * @param IDPAI
     * @return Collection<GrupoItemConfiguracaoDTO>
     * @throws Exception
     */
    public Collection<GrupoItemConfiguracaoDTO> listByIdGrupoItemConfiguracao(final Integer idGrupoItemConfiguracaoPai) throws PersistenceException {
        final String sql = "SELECT idgrupoitemconfiguracao, nomegrupoitemconfiguracao FROM " + this.getTableName()
                + "  WHERE idGrupoItemConfiguracaoPai = ? AND dataFim IS NULL";
        final List<?> dados = this.execSQL(sql, new Object[] {idGrupoItemConfiguracaoPai});
        final List<String> fields = new ArrayList<String>();
        fields.add("idGrupoItemConfiguracao");
        fields.add("nomeGrupoItemConfiguracao");
        return this.listConvertion(this.getBean(), dados, fields);

    }

    public boolean verificaGrupoPai(final GrupoItemConfiguracaoDTO grupoItemConfiguracao) throws PersistenceException {
        boolean isOk = false;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idGrupoItemConfiguracao from " + this.getTableName()
                + "  where  idGrupoItemConfiguracaoPai = ? and idGrupoItemConfiguracao = ? and datafim is null  ");
        parametro.add(grupoItemConfiguracao.getIdGrupoItemConfiguracaoPai());
        parametro.add(grupoItemConfiguracao.getIdGrupoItemConfiguracao());
        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            isOk = true;
        }
        return isOk;
    }

    public boolean verificaGrupoPadrao(final GrupoItemConfiguracaoDTO grupoItemConfiguracao) throws PersistenceException {
        boolean isOk = false;
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "select idGrupoItemConfiguracao from " + this.getTableName() + "  where  idGrupoItemConfiguracao = ? and datafim is null  ";
        parametro.add(grupoItemConfiguracao.getIdGrupoItemConfiguracao());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            isOk = true;
        }
        return isOk;
    }

}
