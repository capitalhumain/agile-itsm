package br.com.citframework.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemValorDescricaoDTO extends BaseEntity {

    private static final long serialVersionUID = 6041027505586125015L;

    private String valor;
    private String descricao;

}
