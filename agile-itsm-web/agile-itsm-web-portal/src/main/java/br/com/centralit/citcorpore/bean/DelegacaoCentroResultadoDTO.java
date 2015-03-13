package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class DelegacaoCentroResultadoDTO extends BaseEntity {

    private static final long serialVersionUID = -2657181157736313801L;

    public static final String NOVAS_EXISTENTES = "E";
    public static final String NOVAS = "N";
    public static final String ESPECIFICAS = "R";

    private Integer idDelegacaoCentroResultado;
    private Integer idResponsavel;
    private Integer idCentroResultado;
    private Integer idEmpregado;
    private Integer idResponsavelRegistro;
    private Integer idResponsavelRevogacao;
    private Timestamp dataHoraRegistro;
    private Date dataInicio;
    private Date dataFim;
    private String abrangencia;
    private String revogada;
    private Timestamp dataHoraRevogacao;

    private String nomeResponsavel;
    private String nomeEmpregado;
    private String requisicoes;

    private Integer[] idProcessoNegocio;
    private Collection<ExecucaoSolicitacaoDTO> colInstancias;

}
