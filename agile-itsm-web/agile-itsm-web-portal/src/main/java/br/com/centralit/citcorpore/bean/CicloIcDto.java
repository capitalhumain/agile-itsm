package br.com.centralit.citcorpore.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author flavio.santana
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CicloIcDto extends BaseEntity {

    private static final long serialVersionUID = 3449929803357182887L;

    private String nome;

}
