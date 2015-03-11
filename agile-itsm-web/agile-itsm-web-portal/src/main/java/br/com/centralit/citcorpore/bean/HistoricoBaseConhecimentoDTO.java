package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Vadoilo Damasceno
 *
 */
public class HistoricoBaseConhecimentoDTO extends BaseConhecimentoDTO {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer idUsuarioAlteracao;

    @Getter
    @Setter
    private Timestamp dataHoraAlteracao;

}
