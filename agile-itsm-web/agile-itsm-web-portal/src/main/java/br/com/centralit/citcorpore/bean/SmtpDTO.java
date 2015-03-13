/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *         DTO de referencia de smtp para os parametros do sistema
 */
public class SmtpDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoSMTP;
    private String atributoSMTP;
    private String valorAtributoSMTP;
    private Collection<SmtpDTO> listSMTPDTO;

    public String getIdAtributoSMTP() {
        return idAtributoSMTP;
    }

    public String getAtributoSMTP() {
        return atributoSMTP;
    }

    public String getValorAtributoSMTP() {
        return valorAtributoSMTP;
    }

    public Collection<SmtpDTO> getListSMTPDTO() {
        return listSMTPDTO;
    }

    public void setIdAtributoSMTP(final String idAtributoSMTP) {
        this.idAtributoSMTP = idAtributoSMTP;
    }

    public void setAtributoSMTP(final String atributoSMTP) {
        this.atributoSMTP = atributoSMTP;
    }

    public void setValorAtributoSMTP(final String valorAtributoSMTP) {
        this.valorAtributoSMTP = valorAtributoSMTP;
    }

    public void setListSMTPDTO(final Collection<SmtpDTO> listSMTPDTO) {
        this.listSMTPDTO = listSMTPDTO;
    }

}
