package br.com.centralit.citcorpore.bean;

import java.util.Collection;
import java.util.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class ImportConfigDTO extends BaseEntity {

    private static final long serialVersionUID = 9160239194104870607L;

    private Integer idImportConfig;
    private Integer idImportarDados;
    private String tipo;
    private Integer idExternalConnection;
    private String tabelaOrigem;
    private String tabelaDestino;
    private String filtroOrigem;
    private String nome;
    private Date dataFim;

    private Collection colDadosCampos;

    public Integer getIdImportarDados() {
        return idImportarDados;
    }

    public void setIdImportarDados(final Integer idImportarDados) {
        this.idImportarDados = idImportarDados;
    }

    public Integer getIdImportConfig() {
        return idImportConfig;
    }

    public void setIdImportConfig(final Integer parm) {
        idImportConfig = parm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String parm) {
        tipo = parm;
    }

    public Integer getIdExternalConnection() {
        return idExternalConnection;
    }

    public void setIdExternalConnection(final Integer parm) {
        idExternalConnection = parm;
    }

    public String getTabelaOrigem() {
        return tabelaOrigem;
    }

    public void setTabelaOrigem(final String parm) {
        tabelaOrigem = parm;
    }

    public String getTabelaDestino() {
        return tabelaDestino;
    }

    public void setTabelaDestino(final String parm) {
        tabelaDestino = parm;
    }

    public String getFiltroOrigem() {
        return filtroOrigem;
    }

    public void setFiltroOrigem(final String parm) {
        filtroOrigem = parm;
    }

    public Collection getColDadosCampos() {
        return colDadosCampos;
    }

    public void setColDadosCampos(final Collection colDadosCampos) {
        this.colDadosCampos = colDadosCampos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
