package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ImportarDadosDTO extends BaseEntity {

    private static final long serialVersionUID = 4942283426115769824L;

    private Integer idImportarDados;
    private Integer idExternalConnection;
    private String importarPor;
    private String tipo;
    private String nome;
    private String script;
    private String agendarRotina;
    private String executarPor;
    private String horaExecucao;
    private Integer periodoHora;
    private Date dataFim;
    private String tabelaOrigem;
    private String tabelaDestino;
    private Collection<UploadDTO> anexos;
    private String jsonMatriz;

    public Collection<UploadDTO> getAnexos() {
        return anexos;
    }

    public void setAnexos(final Collection<UploadDTO> anexos) {
        this.anexos = anexos;
    }

    public Integer getIdImportarDados() {
        return idImportarDados;
    }

    public void setIdImportarDados(final Integer idImportarDados) {
        this.idImportarDados = idImportarDados;
    }

    public Integer getIdExternalConnection() {
        return idExternalConnection;
    }

    public void setIdExternalConnection(final Integer idExternalConnection) {
        this.idExternalConnection = idExternalConnection;
    }

    public String getImportarPor() {
        return importarPor;
    }

    public void setImportarPor(final String importarPor) {
        this.importarPor = importarPor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getScript() {
        return script;
    }

    public void setScript(final String script) {
        this.script = script;
    }

    public String getAgendarRotina() {
        return agendarRotina;
    }

    public void setAgendarRotina(final String agendarRotina) {
        this.agendarRotina = agendarRotina;
    }

    public String getExecutarPor() {
        return executarPor;
    }

    public void setExecutarPor(final String executarPor) {
        this.executarPor = executarPor;
    }

    public String getHoraExecucao() {
        return horaExecucao;
    }

    public void setHoraExecucao(final String horaExecucao) {
        this.horaExecucao = horaExecucao;
    }

    public Integer getPeriodoHora() {
        return periodoHora;
    }

    public void setPeriodoHora(final Integer periodoHora) {
        this.periodoHora = periodoHora;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
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

}
