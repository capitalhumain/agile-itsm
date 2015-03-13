package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class ContadorAcessoDTO extends BaseEntity {

    private Integer idContadorAcesso;
    private Integer idUsuario;
    private Integer idBaseConhecimento;
    private Integer contadorAcesso;
    private Timestamp dataHoraAcesso;

    /**
     * @return the idContadorAcesso
     */
    public Integer getIdContadorAcesso() {
        return idContadorAcesso;
    }

    /**
     * @param idContadorAcesso
     *            the idContadorAcesso to set
     */
    public void setIdContadorAcesso(final Integer idContadorAcesso) {
        this.idContadorAcesso = idContadorAcesso;
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

    /**
     * @return the contadorAcesso
     */
    public Integer getContadorAcesso() {
        return contadorAcesso;
    }

    /**
     * @param contadorAcesso
     *            the contadorAcesso to set
     */
    public void setContadorAcesso(final Integer contadorAcesso) {
        this.contadorAcesso = contadorAcesso;
    }

    /**
     * @return the dataHoraAcesso
     */
    public Timestamp getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    /**
     * @param dataHoraAcesso
     *            the dataHoraAcesso to set
     */
    public void setDataHoraAcesso(final Timestamp dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }

}
