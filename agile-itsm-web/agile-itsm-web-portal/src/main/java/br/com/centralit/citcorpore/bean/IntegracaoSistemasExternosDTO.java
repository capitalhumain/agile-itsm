/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Carlos Santos
 *         DTO para integração de sistemas externos
 */
public class IntegracaoSistemasExternosDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    public static final String NAO_INICIADA = "N";
    public static final String EXECUTADA = "P";
    public static final String ERRO = "E";

    private Integer idIntegracao;
    private Timestamp dataHora;
    private String processo;
    private String identificador;
    private String idobjeto;
    private String situacao;

    public Integer getIdIntegracao() {
        return idIntegracao;
    }

    public void setIdIntegracao(final Integer idIntegracao) {
        this.idIntegracao = idIntegracao;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(final String processo) {
        this.processo = processo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(final String identificador) {
        this.identificador = identificador;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getIdobjeto() {
        return idobjeto;
    }

    public void setIdobjeto(final String idobjeto) {
        this.idobjeto = idobjeto;
    }

}
