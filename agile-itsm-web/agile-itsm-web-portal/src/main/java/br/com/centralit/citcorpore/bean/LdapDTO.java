/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
public class LdapDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoLdap;

    private String atributoLdap;

    private String valorAtributoLdap;

    private Collection<LdapDTO> listLdapDTO;

    public String getIdAtributoLdap() {
        return idAtributoLdap;
    }

    public void setIdAtributoLdap(final String idAtributoLdap) {
        this.idAtributoLdap = idAtributoLdap;
    }

    public String getAtributoLdap() {
        return atributoLdap;
    }

    public void setAtributoLdap(final String atributoLdap) {
        this.atributoLdap = atributoLdap;
    }

    public String getValorAtributoLdap() {
        return valorAtributoLdap;
    }

    public void setValorAtributoLdap(final String valorAtributoLdap) {
        this.valorAtributoLdap = valorAtributoLdap;
    }

    public Collection<LdapDTO> getListLdapDTO() {
        return listLdapDTO;
    }

    public void setListLdapDTO(final Collection<LdapDTO> listLdapDTO) {
        this.listLdapDTO = listLdapDTO;
    }

}
