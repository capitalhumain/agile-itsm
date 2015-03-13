/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *         DTO de referencia de ged para os parametros do sistema
 */
public class GedDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoGed;
    private String atributoGed;
    private String valorAtributoGed;
    private Collection<GedDTO> listGedDTO;

    public String getIdAtributoGed() {
        return idAtributoGed;
    }

    public String getAtributoGed() {
        return atributoGed;
    }

    public String getValorAtributoGed() {
        return valorAtributoGed;
    }

    public Collection<GedDTO> getListGedDTO() {
        return listGedDTO;
    }

    public void setIdAtributoGed(final String idAtributoGed) {
        this.idAtributoGed = idAtributoGed;
    }

    public void setAtributoGed(final String atributoGed) {
        this.atributoGed = atributoGed;
    }

    public void setValorAtributoGed(final String valorAtributoGed) {
        this.valorAtributoGed = valorAtributoGed;
    }

    public void setListGedDTO(final Collection<GedDTO> listGedDTO) {
        this.listGedDTO = listGedDTO;
    }

}
