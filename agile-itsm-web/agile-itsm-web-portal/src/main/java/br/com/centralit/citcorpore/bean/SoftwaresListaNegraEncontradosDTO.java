package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ronnie.lopes
 *
 */
public class SoftwaresListaNegraEncontradosDTO extends BaseEntity {

    private Integer idsoftwareslistanegraencontrados;
    private Integer iditemconfiguracao;
    private Integer idsoftwareslistanegra;
    private String softwarelistanegraencontrado;
    private Timestamp data;

    public Integer getIdsoftwareslistanegraencontrados() {
        return idsoftwareslistanegraencontrados;
    }

    public void setIdsoftwareslistanegraencontrados(final Integer idsoftwareslistanegraencontrados) {
        this.idsoftwareslistanegraencontrados = idsoftwareslistanegraencontrados;
    }

    public Integer getIditemconfiguracao() {
        return iditemconfiguracao;
    }

    public void setIditemconfiguracao(final Integer iditemconfiguracao) {
        this.iditemconfiguracao = iditemconfiguracao;
    }

    public Integer getIdsoftwareslistanegra() {
        return idsoftwareslistanegra;
    }

    public void setIdsoftwareslistanegra(final Integer idsoftwareslistanegra) {
        this.idsoftwareslistanegra = idsoftwareslistanegra;
    }

    public String getSoftwarelistanegraencontrado() {
        return softwarelistanegraencontrado;
    }

    public void setSoftwarelistanegraencontrado(final String softwarelistanegraencontrado) {
        this.softwarelistanegraencontrado = softwarelistanegraencontrado;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(final Timestamp data) {
        this.data = data;
    }

}
