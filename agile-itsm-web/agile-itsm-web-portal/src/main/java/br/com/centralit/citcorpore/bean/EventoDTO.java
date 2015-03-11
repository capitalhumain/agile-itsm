package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventoDTO extends BaseEntity {

    private static final long serialVersionUID = -2635865500806813198L;

    private String idEvento;
    private String title;
    private Timestamp start;
    private Timestamp end;
    private Boolean allDay;
    private String className;
    private String url;

    private String horaInicio;
    private Date data;
    private Integer idExecucao;
    private Integer idProgramacao;
    private String numeroOS;
    private String descricaoAtividadeOS;
    private String nomeTipoMudanca;

}
