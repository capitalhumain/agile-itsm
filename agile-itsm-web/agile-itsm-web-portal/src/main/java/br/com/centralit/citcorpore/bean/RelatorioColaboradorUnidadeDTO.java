package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioColaboradorUnidadeDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idContrato;
    private Integer idUnidade;
    private Integer[] idColaborador;
    private String formatoArquivoRelatorio;

    private String nomeUnidade;
    private String nomeColaborador;
    private String emailColaborador;
    private String telefoneColaborador;

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer[] getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(final Integer[] idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(final String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(final String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(final String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    public String getTelefoneColaborador() {
        return telefoneColaborador;
    }

    public void setTelefoneColaborador(final String telefoneColaborador) {
        this.telefoneColaborador = telefoneColaborador;
    }

}
