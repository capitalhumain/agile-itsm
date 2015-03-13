package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Bruno Rodrigues
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ControleRendimentoExecucaoDTO extends BaseEntity {

    private Integer idGrupo;
    private Integer idGrupoExecucao;
    private Integer idGrupoRelatorio;
    private Integer idPessoa;
    private Date dataInicio;
    private Date dataFim;

    // atributos auxiliares para setar as informações na tabela de informações do grupo
    private String tipoSla;
    private String qtdSolicitacoes;
    private String qtdTotalPontos;
    private String qtdPontosPositivos;
    private String qtdPontosNegativos;
    private String mediaRelativa;

    // atributos auxiliares para setar as informações na tabela de informações da pessoa
    private String nomePessoa;
    private String aprovacao;

    // atributos auxiliares do ControleRendimentoDTO
    private String mes;
    private String ano;

    private String qtdItensEntregues;
    private String qtdItensRetornados;

    public ControleRendimentoExecucaoDTO() {}

    public ControleRendimentoExecucaoDTO(final String tipoSla, final String qtdSolicitacoes, final String qtdTotalPontos, final String qtdPontosPositivos,
            final String qtdPontosNegativos, final String mediaRelativa) {
        this.tipoSla = tipoSla;
        this.qtdSolicitacoes = qtdSolicitacoes;
        this.qtdTotalPontos = qtdTotalPontos;
        this.qtdPontosPositivos = qtdPontosPositivos;
        this.qtdPontosNegativos = qtdPontosNegativos;
        this.mediaRelativa = mediaRelativa;
    }

    public ControleRendimentoExecucaoDTO(final String nomePessoa, final String qtdTotalPontos, final String aprovacao, final Integer idPessoa) {
        this.nomePessoa = nomePessoa;
        this.qtdTotalPontos = qtdTotalPontos;
        this.aprovacao = aprovacao;
        this.idPessoa = idPessoa;
    }

}
