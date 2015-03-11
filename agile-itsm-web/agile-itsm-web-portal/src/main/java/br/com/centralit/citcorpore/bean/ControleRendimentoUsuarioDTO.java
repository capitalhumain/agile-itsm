package br.com.centralit.citcorpore.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class ControleRendimentoUsuarioDTO extends BaseEntity implements Comparable<ControleRendimentoUsuarioDTO> {

    private static final long serialVersionUID = -5557022284191013735L;

    private Integer idControleRendimentoUsuario;
    private Integer idControleRendimento;
    private Integer idGrupo;
    private Integer idUsuario;
    private Double qtdTotalPontos;
    private String aprovacao;
    private String ano;
    private String mes;

    // campos auxiliares para o relatorio
    private String nomeUsuario;
    private String qtdPontosPositivos;
    private String qtdPontosNegativos;
    private String qtdItensEntregues;
    private String qtdItensRetornados;

    @Override
    public int compareTo(final ControleRendimentoUsuarioDTO o) {
        return qtdTotalPontos.compareTo(o.getQtdTotalPontos());
    }

}
