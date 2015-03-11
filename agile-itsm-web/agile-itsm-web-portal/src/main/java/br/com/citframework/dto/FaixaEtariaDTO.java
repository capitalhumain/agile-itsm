package br.com.citframework.dto;

import br.com.agileitsm.model.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

public class FaixaEtariaDTO extends BaseEntity {

    private static final long serialVersionUID = -6808816301966007685L;

    @Getter
    @Setter
    private Integer inicio;

    @Getter
    @Setter
    private Integer fim;

    public String getDescricao() {
        if (getInicio() == null) {
            setInicio(0);
        }
        if (getFim() == null) {
            setFim(999);
        }
        String strRet = "";

        if (getInicio().intValue() <= 0) {
            strRet += "Até ";
        } else {
            strRet += getInicio() + " a ";
        }
        if (getFim().intValue() >= 150) {
            strRet += "+";
        } else {
            strRet += getFim() + "";
        }
        return strRet;
    }

}
