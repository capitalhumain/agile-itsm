/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *         DTO de referencia de item de configuração para os parametros do sistema
 */
public class ICDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoIC;
    private String atributoIC;
    private String valorAtributoIC;
    private Collection<ICDTO> listICDTO;

    public String getIdAtributoIC() {
        return idAtributoIC;
    }

    public String getAtributoIC() {
        return atributoIC;
    }

    public String getValorAtributoIC() {
        return valorAtributoIC;
    }

    public Collection<ICDTO> getListICDTO() {
        return listICDTO;
    }

    public void setIdAtributoIC(final String idAtributoIC) {
        this.idAtributoIC = idAtributoIC;
    }

    public void setAtributoIC(final String atributoIC) {
        this.atributoIC = atributoIC;
    }

    public void setValorAtributoIC(final String valorAtributoIC) {
        this.valorAtributoIC = valorAtributoIC;
    }

    public void setListICDTO(final Collection<ICDTO> listICDTO) {
        this.listICDTO = listICDTO;
    }

}
