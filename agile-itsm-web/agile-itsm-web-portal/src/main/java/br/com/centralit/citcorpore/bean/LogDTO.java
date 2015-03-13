/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *         DTO de referencia de logs para os parametros do sistema
 */
public class LogDTO extends BaseEntity {

    private static final long serialVersionUID = -3593079788503253157L;

    private String idAtributoLog;
    private String atributoLog;
    private String valorAtributoLog;
    private Collection<LogDTO> listLogDTO;

    public String getIdAtributoLog() {
        return idAtributoLog;
    }

    public String getAtributoLog() {
        return atributoLog;
    }

    public String getValorAtributoLog() {
        return valorAtributoLog;
    }

    public Collection<LogDTO> getListLogDTO() {
        return listLogDTO;
    }

    public void setIdAtributoLog(final String idAtributoLog) {
        this.idAtributoLog = idAtributoLog;
    }

    public void setAtributoLog(final String atributoLog) {
        this.atributoLog = atributoLog;
    }

    public void setValorAtributoLog(final String valorAtributoLog) {
        this.valorAtributoLog = valorAtributoLog;
    }

    public void setListLogDTO(final Collection<LogDTO> listLogDTO) {
        this.listLogDTO = listLogDTO;
    }

}
