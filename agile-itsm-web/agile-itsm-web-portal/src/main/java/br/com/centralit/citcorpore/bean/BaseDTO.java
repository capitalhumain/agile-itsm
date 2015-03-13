/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *         DTO de referencia de base de conhecimento para os parametros do sistema
 */
public class BaseDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoBase;
    private String atributoBase;
    private String valorAtributoBase;
    private Collection<BaseDTO> listBaseDTO;

    public String getIdAtributoBase() {
        return idAtributoBase;
    }

    public String getAtributoBase() {
        return atributoBase;
    }

    public String getValorAtributoBase() {
        return valorAtributoBase;
    }

    public Collection<BaseDTO> getListBaseDTO() {
        return listBaseDTO;
    }

    public void setIdAtributoBase(final String idAtributoBase) {
        this.idAtributoBase = idAtributoBase;
    }

    public void setAtributoBase(final String atributoBase) {
        this.atributoBase = atributoBase;
    }

    public void setValorAtributoBase(final String valorAtributoBase) {
        this.valorAtributoBase = valorAtributoBase;
    }

    public void setListBaseDTO(final Collection<BaseDTO> listBaseDTO) {
        this.listBaseDTO = listBaseDTO;
    }

}
