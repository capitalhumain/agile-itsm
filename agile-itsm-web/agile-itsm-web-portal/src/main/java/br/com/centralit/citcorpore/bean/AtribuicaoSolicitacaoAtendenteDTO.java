package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import br.com.agileitsm.model.support.BaseEntity;

public class AtribuicaoSolicitacaoAtendenteDTO extends BaseEntity {

    private static final long serialVersionUID = 117435070092875202L;

    @Getter
    @Setter
    private Integer idUsuario;

    @Getter
    @Setter
    private Integer idSolicitacao;

    @Getter
    @Setter
    private Integer priorityOrder;

    @Getter
    @Setter
    private Double latitude;

    @Getter
    @Setter
    private Double longitude;

    @Getter
    @Setter
    private Date dataExecucao;

    @Getter
    @Setter
    private Timestamp dataInicioAtendimento;

    @Getter
    @Setter
    private Integer active;

}
