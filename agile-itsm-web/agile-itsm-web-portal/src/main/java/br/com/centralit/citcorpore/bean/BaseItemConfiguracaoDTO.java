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
public class BaseItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 7291574008258558256L;

    private String nome;
    private Integer idTipoItemConfiguracao;
    private String executavel;
    private Date dataInicio;
    private Date dataFim;
    private String tipoexecucao;
    private TipoItemConfiguracaoDTO tipoItemConfiguracao;
    private String comando;
    private Integer idBaseItemConfiguracaoPai;

}
