package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ImportanciaConhecimentoUsuarioDTO extends BaseEntity {

    private static final long serialVersionUID = -6697113995744689763L;

    private Integer idBaseConhecimento;

    private Integer idUsuario;

    private String grauImportanciaUsuario;

    /**
     * @return the idBaseConhecimento
     */
    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    /**
     * @param idBaseConhecimento
     *            the idConhecimento to set
     */
    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario
     *            the idUsuario to set
     */
    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the grauImportancia
     */
    public String getGrauImportanciaUsuario() {
        return grauImportanciaUsuario;
    }

    /**
     * @param grauImportanciaUsuario
     *            the grauImportancia to set
     */
    public void setGrauImportanciaUsuario(final String grauImportanciaUsuario) {
        this.grauImportanciaUsuario = grauImportanciaUsuario;
    }

}
