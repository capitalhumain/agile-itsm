package br.com.centralit.citcorpore.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ygor.magalhaes
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComandoDTO extends BaseEntity {

    private static final long serialVersionUID = -6707773018956063512L;

    private String descricao;

}
