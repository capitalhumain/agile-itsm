package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import br.com.agileitsm.model.support.BaseEntity;

/**
 * DTO que contém atributos comuns aos DTOs usados para persistência/filtro da Gestão da Força de trabalho
 *
 * @author bruno.ribeiro - <a href="mailto:bruno.ribeiro@centrait.com.br">bruno.ribeiro@centrait.com.br</a>
 *
 */
public abstract class AbstractGestaoForcaAtendimentoDTO extends BaseEntity {

    private static final long serialVersionUID = -85203475551247789L;

    // atributos para filtro
    @Getter
    @Setter
    private Integer idUF;

    @Getter
    @Setter
    private Integer idCidade;

    @Getter
    @Setter
    private Integer idContrato;

    @Getter
    @Setter
    private Integer idGrupo;

    @Getter
    @Setter
    private Integer idUnidade;

    @Getter
    @Setter
    private Integer idAtendente;

    @Getter
    @Setter
    private Date dataInicio;

    @Getter
    @Setter
    private Date dataFim;

    @Getter
    @Setter
    private Timestamp timestampInicio;

    @Getter
    @Setter
    private Timestamp timestampFim;

}
