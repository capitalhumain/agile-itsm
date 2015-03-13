package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 *
 * @author rodrigo.oliveira
 *
 */
public class UrgenciaDTO extends BaseEntity {

    private static final long serialVersionUID = 1577894703743197591L;

    private Integer idUrgencia;
    private String nivelUrgencia;
    private String siglaUrgencia;

    /**
     * @return the idUrgencia
     */
    public Integer getIdUrgencia() {
        return idUrgencia;
    }

    /**
     * @param idUrgencia
     *            the idUrgencia to set
     */
    public void setIdUrgencia(final Integer idUrgencia) {
        this.idUrgencia = idUrgencia;
    }

    /**
     * @return the nivelUrgencia
     */
    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    /**
     * @param nivelUrgencia
     *            the nivelUrgencia to set
     */
    public void setNivelUrgencia(final String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    /**
     * @return the siglaUrgencia
     */
    public String getSiglaUrgencia() {
        return siglaUrgencia;
    }

    /**
     * @param siglaUrgencia
     *            the siglaUrgencia to set
     */
    public void setSiglaUrgencia(final String siglaUrgencia) {
        this.siglaUrgencia = siglaUrgencia;
    }

}
