package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ImportManagerDTO extends BaseEntity {

    private static final long serialVersionUID = 5826581473179525246L;

    private Integer idImportConfig;
    private Integer idExternalConnection;
    private Integer idImportarDados;
    private String tabelaOrigem;
    private String tabelaDestino;
    private String jsonMatriz;

    private String nome;
    private String tipo;
    private String filtroOrigem;

    private Object result;

    public Integer getIdExternalConnection() {
        return idExternalConnection;
    }

    public void setIdExternalConnection(final Integer idExternalConnection) {
        this.idExternalConnection = idExternalConnection;
    }

    public String getTabelaOrigem() {
        return tabelaOrigem;
    }

    public void setTabelaOrigem(final String tabelaOrigem) {
        this.tabelaOrigem = tabelaOrigem;
    }

    public String getTabelaDestino() {
        return tabelaDestino;
    }

    public void setTabelaDestino(final String tabelaDestino) {
        this.tabelaDestino = tabelaDestino;
    }

    public String getJsonMatriz() {
        return jsonMatriz;
    }

    public void setJsonMatriz(final String jsonMatriz) {
        this.jsonMatriz = jsonMatriz;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(final Object result) {
        this.result = result;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getFiltroOrigem() {
        return filtroOrigem;
    }

    public void setFiltroOrigem(final String filtroOrigem) {
        this.filtroOrigem = filtroOrigem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Integer getIdImportConfig() {
        return idImportConfig;
    }

    public void setIdImportConfig(final Integer idImportConfig) {
        this.idImportConfig = idImportConfig;
    }

    public Integer getIdImportarDados() {
        return idImportarDados;
    }

    public void setIdImportarDados(final Integer idImportarDados) {
        this.idImportarDados = idImportarDados;
    }

}
