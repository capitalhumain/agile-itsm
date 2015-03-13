package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author murilo.pacheco
 *         classe criada para fazer a ligação da tabela de midia definitiva com os hitoricos.
 *
 */
public class LigacaoRequisicaoLiberacaoHistoricoComprasDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLigacaoLibHistCompras;
    private Integer idRequisicaoLiberacao;
    private Integer idHistoricoLiberacao;
    private Integer idRequisicaoLiberacaoCompras;
    private Integer idSolicitacaoServico;

    public Integer getIdLigacaoLibHistCompras() {
        return idLigacaoLibHistCompras;
    }

    public void setIdLigacaoLibHistCompras(final Integer idLigacaoLibHistCompras) {
        this.idLigacaoLibHistCompras = idLigacaoLibHistCompras;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

    public Integer getIdHistoricoLiberacao() {
        return idHistoricoLiberacao;
    }

    public void setIdHistoricoLiberacao(final Integer idHistoricoLiberacao) {
        this.idHistoricoLiberacao = idHistoricoLiberacao;
    }

    public Integer getIdRequisicaoLiberacaoCompras() {
        return idRequisicaoLiberacaoCompras;
    }

    public void setIdRequisicaoLiberacaoCompras(final Integer idRequisicaoLiberacaoCompras) {
        this.idRequisicaoLiberacaoCompras = idRequisicaoLiberacaoCompras;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

}
