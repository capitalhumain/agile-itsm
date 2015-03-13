package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @since 14/06/2013
 * @author rodrigo.oliveira
 *
 */
public class SlaRequisitoSlaDTO extends BaseEntity {

    private Integer idRequisitoSLA;
    private Integer idAcordoNivelServico;
    private java.sql.Date dataVinculacao;
    private java.sql.Date dataUltModificacao;
    private String deleted;

    public Integer getIdRequisitoSLA() {
        return idRequisitoSLA;
    }

    public void setIdRequisitoSLA(final Integer idRequisitoSLA) {
        this.idRequisitoSLA = idRequisitoSLA;
    }

    public Integer getIdAcordoNivelServico() {
        return idAcordoNivelServico;
    }

    public void setIdAcordoNivelServico(final Integer idAcordoNivelServico) {
        this.idAcordoNivelServico = idAcordoNivelServico;
    }

    public java.sql.Date getDataVinculacao() {
        return dataVinculacao;
    }

    public void setDataVinculacao(final java.sql.Date dataVinculacao) {
        this.dataVinculacao = dataVinculacao;
    }

    public java.sql.Date getDataUltModificacao() {
        return dataUltModificacao;
    }

    public void setDataUltModificacao(final java.sql.Date dataUltModificacao) {
        this.dataUltModificacao = dataUltModificacao;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
