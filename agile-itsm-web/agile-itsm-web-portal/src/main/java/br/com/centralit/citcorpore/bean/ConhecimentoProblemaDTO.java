package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ConhecimentoProblemaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idProblema;
    private Integer idBaseConhecimento;
    private String status;
    private String titulo;

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
     * @return the idBaseConhecimento
     */
    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    /**
     * @param idBaseConhecimento
     *            the idBaseConhecimento to set
     */
    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

}
