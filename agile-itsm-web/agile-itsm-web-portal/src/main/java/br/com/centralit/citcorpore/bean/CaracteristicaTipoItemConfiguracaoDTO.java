package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

/**
 * DTO de CaracteriticaTipoItemConfiguracao.
 *
 * @author valdoilo.damasceno
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CaracteristicaTipoItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 5249455279181128086L;

    private Integer idTipoItemConfiguracao;
    private Integer idCaracteristica;
    private Date dataInicio;
    private Date dataFim;
    private String nameInfoAgente;

}
