package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Comparator;

import br.com.agileitsm.model.support.BaseEntity;

public class ScriptsDTO extends BaseEntity implements Comparator<ScriptsDTO> {

    private static final long serialVersionUID = 1L;

    static public final String TIPO_CONSULTA = "consulta";
    static public final String TIPO_UPDATE = "update";

    static public final int TIPO_CRIAR_TABELA = 1;
    static public final int TIPO_INSERIR_REGISTRO = 2;
    static public final int TIPO_DELETAR_REGISTRO = 3;
    static public final int TIPO_ADICIONAR_COLUNA = 4;
    static public final int TIPO_ADICIONAR_CONSTRAINT = 5;
    static public final int TIPO_ALTERAR_COLUNA = 6;
    static public final int TIPO_DELETAR_COLUNA = 7;
    static public final int TIPO_DELETAR_TABELA = 8;

    private Date dataFim;
    private Date dataInicio;
    private String descricao;
    private String historico;
    private Integer idScript;
    private Integer idVersao;
    private String nome;
    private String sqlQuery;
    private String tipo;

    public Date getDataFim() {
        return dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHistorico() {
        return historico;
    }

    public Integer getIdScript() {
        return idScript;
    }

    public Integer getIdVersao() {
        return idVersao;
    }

    public String getNome() {
        return nome;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public void setHistorico(final String historico) {
        this.historico = historico;
    }

    public void setIdScript(final Integer idScript) {
        this.idScript = idScript;
    }

    public void setIdVersao(final Integer idVersao) {
        this.idVersao = idVersao;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public void setSqlQuery(final String setSql) {
        sqlQuery = setSql;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int compare(final ScriptsDTO o1, final ScriptsDTO o2) {
        return o1.getNome().compareToIgnoreCase(o2.getNome());
    }

}
