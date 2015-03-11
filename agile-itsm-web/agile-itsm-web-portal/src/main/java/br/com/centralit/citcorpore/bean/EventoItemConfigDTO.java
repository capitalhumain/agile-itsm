package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventoItemConfigDTO extends BaseEntity {

    private static final long serialVersionUID = -5414282566015818110L;

    private Integer idEvento;
    private Integer idEmpresa;
    private String descricao;
    private String ligarCasoDesl;
    private String usuario;
    private String senha;
    private Date dataInicio;
    private Date dataFim;

    private List<ItemConfigEventoDTO> lstItemConfigEvento;
    private List<EventoGrupoDTO> lstGrupo;
    private List<EventoItemConfigRelDTO> lstItemConfiguracao;

    private Integer idItemCfg;
    private String nomeItemCfg;
    private String linhaComando;

}
