package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParametroCorporeDTO extends BaseEntity {

    private static final long serialVersionUID = 3449929803357182887L;

    private String nome;
    private String valor;
    private Integer idEmpresa;
    private Date dataInicio;
    private Date dataFim;
    private String tipoDado;

}
