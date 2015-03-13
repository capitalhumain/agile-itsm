package br.com.centralit.citcorpore.bean;

//Criado por Bruno.Aquino

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioMudancaItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // Atributos para a mudança

    private Integer idNumeroMudanca;
    private String tituloMudanca;
    private String grupoMudanca;
    private Date dataMudanca;
    private String descricaoProblemaMudanca;

    // Atributos para o item de configuração
    private Integer idItemConfig;
    private String nomeItem;
    // Atributos par aos parametros da tela
    private Date dataInicio;
    private Date dataFim;
    private String proprietario;
    private String formatoArquivoRelatorio;
    private Integer idContrato;
    private String contrato;
    private Collection<ItemConfiguracaoDTO> listaItensConfiguracao;

    public Collection<ItemConfiguracaoDTO> getListaItensConfiguracao() {
        return listaItensConfiguracao;
    }

    public void setListaItensConfiguracao(final Collection<ItemConfiguracaoDTO> list) {
        listaItensConfiguracao = list;
    }

    public String getTituloMudanca() {
        return tituloMudanca;
    }

    public void setTituloMudanca(final String tituloMudanca) {
        this.tituloMudanca = tituloMudanca;
    }

    public String getGrupoMudanca() {
        return grupoMudanca;
    }

    public void setGrupoMudanca(final String grupoMudanca) {
        this.grupoMudanca = grupoMudanca;
    }

    public Date getDataMudanca() {
        return dataMudanca;
    }

    public void setDataMudanca(final Date dataMudanca) {
        this.dataMudanca = dataMudanca;
    }

    public String getDescricaoProblemaMudanca() {
        return descricaoProblemaMudanca;
    }

    public void setDescricaoProblemaMudanca(final String descricaoProblemaMudanca) {
        this.descricaoProblemaMudanca = descricaoProblemaMudanca;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(final String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Integer getIdNumeroMudanca() {
        return idNumeroMudanca;
    }

    public void setIdNumeroMudanca(final Integer idNumeroMudanca) {
        this.idNumeroMudanca = idNumeroMudanca;
    }

    public Integer getIdItemConfig() {
        return idItemConfig;
    }

    public void setIdItemConfig(final Integer idItemConfig) {
        this.idItemConfig = idItemConfig;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(final String contrato) {
        this.contrato = contrato;
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

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(final String proprietario) {
        this.proprietario = proprietario;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

}
