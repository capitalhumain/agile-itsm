package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author mario.haysaki
 *
 */
public class RelatorioListaNegraDTO extends BaseEntity {

    private Integer idItemConfiguracao;
    private Integer idGrupoItemConfiguracao;
    private String nomeGrupoItemConfiguracao;
    private Integer idSoftwaresListaNegra;
    private String nomeSoftwaresListaNegra;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private String formatoArquivoRelatorio;
    private String localidade;

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public Integer getIdGrupoItemConfiguracao() {
        return idGrupoItemConfiguracao;
    }

    public void setIdGrupoItemConfiguracao(final Integer idGrupoItemConfiguracao) {
        this.idGrupoItemConfiguracao = idGrupoItemConfiguracao;
    }

    public String getNomeGrupoItemConfiguracao() {
        return nomeGrupoItemConfiguracao;
    }

    public void setNomeGrupoItemConfiguracao(final String nomeGrupoItemConfiguracao) {
        this.nomeGrupoItemConfiguracao = nomeGrupoItemConfiguracao;
    }

    public Integer getIdSoftwaresListaNegra() {
        return idSoftwaresListaNegra;
    }

    public void setIdSoftwaresListaNegra(final Integer idSoftwaresListaNegra) {
        this.idSoftwaresListaNegra = idSoftwaresListaNegra;
    }

    public String getNomeSoftwaresListaNegra() {
        return nomeSoftwaresListaNegra;
    }

    public void setNomeSoftwaresListaNegra(final String nomeSoftwaresListaNegra) {
        this.nomeSoftwaresListaNegra = nomeSoftwaresListaNegra;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(final String localidade) {
        this.localidade = localidade;
    }

}
