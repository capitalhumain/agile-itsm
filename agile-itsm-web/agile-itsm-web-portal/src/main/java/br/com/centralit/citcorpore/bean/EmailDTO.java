/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *         DTO de referencia de email para os parametros do sistema
 */
public class EmailDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoEmail;
    private String atributoEmail;
    private String valorAtributoEmail;
    private Collection<EmailDTO> listEmailDTO;

    public String getIdAtributoEmail() {
        return idAtributoEmail;
    }

    public String getAtributoEmail() {
        return atributoEmail;
    }

    public String getValorAtributoEmail() {
        return valorAtributoEmail;
    }

    public Collection<EmailDTO> getListEmailDTO() {
        return listEmailDTO;
    }

    public void setIdAtributoEmail(final String idAtributoEmail) {
        this.idAtributoEmail = idAtributoEmail;
    }

    public void setAtributoEmail(final String atributoEmail) {
        this.atributoEmail = atributoEmail;
    }

    public void setValorAtributoEmail(final String valorAtributoEmail) {
        this.valorAtributoEmail = valorAtributoEmail;
    }

    public void setListEmailDTO(final Collection<EmailDTO> listEmailDTO) {
        this.listEmailDTO = listEmailDTO;
    }

}
