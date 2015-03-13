package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

/**
 * DTO que contém atributos comuns aos DTOs usados para persistência/filtro da Gestão da Força de trabalho
 *
 * @author bruno.ribeiro - <a href="mailto:bruno.ribeiro@centrait.com.br">bruno.ribeiro@centrait.com.br</a>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractGestaoForcaAtendimentoDTO extends BaseEntity {

    private static final long serialVersionUID = -85203475551247789L;

    // atributos para filtro
    private Integer idUF;
    private Integer idCidade;
    private Integer idContrato;
    private Integer idGrupo;
    private Integer idUnidade;
    private Integer idAtendente;
    private Date dataInicio;
    private Date dataFim;
    private Timestamp timestampInicio;
    private Timestamp timestampFim;

}
