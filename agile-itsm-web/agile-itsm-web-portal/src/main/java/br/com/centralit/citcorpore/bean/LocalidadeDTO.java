package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class LocalidadeDTO extends BaseEntity {

    private Integer idLocalidade;
    private String nomeLocalidade;
    private Date dataInicio;
    private Date dataFim;

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
     * @return the nomeLocalidade
     */
    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    /**
     * @param nomeLocalidade
     *            the nomeLocalidade to set
     */
    public void setNomeLocalidade(final String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
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
