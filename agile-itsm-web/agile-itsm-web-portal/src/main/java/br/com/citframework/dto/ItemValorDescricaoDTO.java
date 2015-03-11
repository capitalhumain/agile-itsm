package br.com.citframework.dto;

import br.com.agileitsm.model.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

public class ItemValorDescricaoDTO extends BaseEntity {

    private static final long serialVersionUID = 6041027505586125015L;

    @Getter
    @Setter
    private String valor;

    @Getter
    @Setter
    private String descricao;

}
