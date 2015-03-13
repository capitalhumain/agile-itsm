package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class LocalidadeUnidadeDTO extends BaseEntity {

    private Integer idLocalidadeUnidade;
    private Integer idLocalidade;
    private Integer idUnidade;
    private Date dataInicio;
    private Date dataFim;

    /**
     * @return the idLocalidadeUnidade
     */
    public Integer getIdLocalidadeUnidade() {
        return idLocalidadeUnidade;
    }

    /**
     * @param idLocalidadeUnidade
     *            the idLocalidadeUnidade to set
     */
    public void setIdLocalidadeUnidade(final Integer idLocalidadeUnidade) {
        this.idLocalidadeUnidade = idLocalidadeUnidade;
    }

    /**
     * @return the idLocalidade
     */
    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    /**
     * @param idLocalidade
     *            the idLocalidade to set
     */
    public void setIdLocalidade(final Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    /**
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade
     *            the idUnidade to set
     */
    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio
     *            the dataInicio to set
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim
     *            the dataFim to set
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
