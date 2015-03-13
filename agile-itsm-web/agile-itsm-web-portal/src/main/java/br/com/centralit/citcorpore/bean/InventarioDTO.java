package br.com.centralit.citcorpore.bean;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Maycon.Fernandes
 *
 */
public class InventarioDTO extends BaseEntity {

    private Integer idInventario;
    private String ip;
    private String mask;
    private String mac;

    private Integer idContrato;
    private List<NetMapDTO> ipInvetariar;
    private java.sql.Date date;

    /**
     * @return valor do atributo ipInvetariar.
     */
    public List<NetMapDTO> getIpInvetariar() {
        return ipInvetariar;
    }

    /**
     * Define valor do atributo ipInvetariar.
     *
     * @param ipInvetariar
     */
    public void setIpInvetariar(final List<NetMapDTO> ipInvetariar) {
        this.ipInvetariar = ipInvetariar;
    }

    /**
     * @return valor do atributo idInventario.
     */
    public Integer getIdInventario() {
        return idInventario;
    }

    /**
     * Define valor do atributo idInventario.
     *
     * @param idInventario
     */
    public void setIdInventario(final Integer idInventario) {
        this.idInventario = idInventario;
    }

    /**
     * @return valor do atributo ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Define valor do atributo ip.
     *
     * @param ip
     */
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * @return valor do atributo mask.
     */
    public String getMask() {
        return mask;
    }

    /**
     * Define valor do atributo mask.
     *
     * @param mask
     */
    public void setMask(final String mask) {
        this.mask = mask;
    }

    /**
     * @return valor do atributo mac.
     */
    public String getMac() {
        return mac;
    }

    /**
     * Define valor do atributo mac.
     *
     * @param mac
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     * @return valor do atributo date.
     */
    public java.sql.Date getDate() {
        return date;
    }

    /**
     * Define valor do atributo date.
     *
     * @param date
     */
    public void setDate(final java.sql.Date date) {
        this.date = date;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

}
