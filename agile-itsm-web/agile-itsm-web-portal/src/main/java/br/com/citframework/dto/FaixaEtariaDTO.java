package br.com.citframework.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class FaixaEtariaDTO extends BaseEntity {

    private static final long serialVersionUID = -6808816301966007685L;

    private Integer inicio;
    private Integer fim;

    public String getDescricao() {
        if (this.getInicio() == null) {
            this.setInicio(0);
        }
        if (this.getFim() == null) {
            this.setFim(999);
        }
        String strRet = "";

        if (this.getInicio().intValue() <= 0) {
            strRet += "Até ";
        } else {
            strRet += this.getInicio() + " a ";
        }
        if (this.getFim().intValue() >= 150) {
            strRet += "+";
        } else {
            strRet += this.getFim() + "";
        }
        return strRet;
    }

}
