package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author thiago.borges
 *
 */
public class ExternalConnectionDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idExternalConnection;
    private String nome;
    private String tipo;
    private String urlJdbc;
    private String jdbcDbName;
    private String jdbcDriver;
    private String jdbcUser;
    private String jdbcPassword;
    private String fileName;
    private String schemaDb;
    private String deleted;

    /**
     * Metodos Gets and Sets
     */
    public Integer getIdExternalConnection() {
        return idExternalConnection;
    }

    public void setIdExternalConnection(final Integer idExternalConnection) {
        this.idExternalConnection = idExternalConnection;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getUrlJdbc() {
        return urlJdbc;
    }

    public void setUrlJdbc(final String urlJdbc) {
        this.urlJdbc = urlJdbc;
    }

    public String getJdbcDbName() {
        return jdbcDbName;
    }

    public void setJdbcDbName(final String jdbcDbName) {
        this.jdbcDbName = jdbcDbName;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(final String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(final String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(final String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getSchemaDb() {
        return schemaDb;
    }

    public void setSchemaDb(final String schemaDb) {
        this.schemaDb = schemaDb;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

}
