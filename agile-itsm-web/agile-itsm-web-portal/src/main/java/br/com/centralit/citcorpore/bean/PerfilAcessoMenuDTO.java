package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class PerfilAcessoMenuDTO extends BaseEntity {

    private Integer idMenu;

    private Integer idPerfilAcesso;

    private String pesquisa;

    private String grava;

    private String deleta;

    private Date dataInicio;

    private Date dataFim;

    /**
     * @return the grava
     */
    public String getGrava() {
        return grava;
    }

    /**
     * @param grava
     *            the grava to set
     */
    public void setGrava(final String grava) {
        this.grava = grava;
    }

    /**
     * @return valor do atributo idMenu.
     */
    public Integer getIdMenu() {
        return idMenu;
    }

    /**
     * Define valor do atributo idMenu.
     *
     * @param idMenu
     */
    public void setIdMenu(final Integer idMenu) {
        this.idMenu = idMenu;
    }

    /**
     * @return valor do atributo idPerfilAcesso.
     */
    public Integer getIdPerfilAcesso() {
        return idPerfilAcesso;
    }

    /**
     * Define valor do atributo idPerfilAcesso.
     *
     * @param idPerfilAcesso
     */
    public void setIdPerfilAcesso(final Integer idPerfilAcesso) {
        this.idPerfilAcesso = idPerfilAcesso;
    }

    /**
     * @return valor do atributo pesquisa.
     */
    public String getPesquisa() {
        return pesquisa;
    }

    /**
     * Define valor do atributo pesquisa.
     *
     * @param pesquisa
     */
    public void setPesquisa(final String pesquisa) {
        this.pesquisa = pesquisa;
    }

    /**
     * @return valor do atributo deleta.
     */
    public String getDeleta() {
        return deleta;
    }

    /**
     * Define valor do atributo deleta.
     *
     * @param deleta
     */
    public void setDeleta(final String deleta) {
        this.deleta = deleta;
    }

    /**
     * @return valor do atributo dataInicio.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define valor do atributo dataInicio.
     *
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return valor do atributo dataFim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define valor do atributo dataFim.
     *
     * @param dataFim
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
