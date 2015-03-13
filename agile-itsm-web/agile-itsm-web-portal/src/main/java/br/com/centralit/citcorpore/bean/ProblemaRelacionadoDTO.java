package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ProblemaRelacionadoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idProblema;
    private Integer idProblemaRelacionado;

    /**
     * @return the idProblema
     */
    public Integer getIdProblema() {
        return idProblema;
    }

    /**
     * @param idProblema
     *            the idProblema to set
     */
    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    /**
     * @return the idProblemaRelacionado
     */
    public Integer getIdProblemaRelacionado() {
        return idProblemaRelacionado;
    }

    /**
     * @param idProblemaRelacionado
     *            the idProblemaRelacionado to set
     */
    public void setIdProblemaRelacionado(final Integer idProblemaRelacionado) {
        this.idProblemaRelacionado = idProblemaRelacionado;
    }

}
