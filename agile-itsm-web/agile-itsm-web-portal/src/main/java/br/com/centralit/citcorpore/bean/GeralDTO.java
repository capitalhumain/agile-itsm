/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *         DTO de referencia para os parametros do sistema
 */
public class GeralDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoGeral;
    private String atributoGeral;
    private String valorAtributoGeral;
    private Collection<GeralDTO> listGeralDTO;

    public String getIdAtributoGeral() {
        return idAtributoGeral;
    }

    public String getAtributoGeral() {
        return atributoGeral;
    }

    public String getValorAtributoGeral() {
        return valorAtributoGeral;
    }

    public Collection<GeralDTO> getListGeralDTO() {
        return listGeralDTO;
    }

    public void setIdAtributoGeral(final String idAtributoGeral) {
        this.idAtributoGeral = idAtributoGeral;
    }

    public void setAtributoGeral(final String atributoGeral) {
        this.atributoGeral = atributoGeral;
    }

    public void setValorAtributoGeral(final String valorAtributoGeral) {
        this.valorAtributoGeral = valorAtributoGeral;
    }

    public void setListGeralDTO(final Collection<GeralDTO> listGeralDTO) {
        this.listGeralDTO = listGeralDTO;
    }

}
