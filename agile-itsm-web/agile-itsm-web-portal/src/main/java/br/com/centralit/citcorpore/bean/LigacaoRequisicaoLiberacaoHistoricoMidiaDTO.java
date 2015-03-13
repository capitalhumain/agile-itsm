package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author murilo.pacheco
 *         classe criada para fazer a ligação da tabela de midia definitiva com os hitoricos.
 *
 */
public class LigacaoRequisicaoLiberacaoHistoricoMidiaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLigacao_lib_hist_midia;
    private Integer idRequisicaoLiberacao;
    private Integer idHistoricoLiberacao;
    private Integer idRequisicaoLiberacaoMidia;
    private Integer idMidiaSoftware;

    public Integer getIdLigacao_lib_hist_midia() {
        return idLigacao_lib_hist_midia;
    }

    public void setIdLigacao_lib_hist_midia(final Integer idLigacao_lib_hist_midia) {
        this.idLigacao_lib_hist_midia = idLigacao_lib_hist_midia;
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

    public Integer getIdRequisicaoLiberacaoMidia() {
        return idRequisicaoLiberacaoMidia;
    }

    public void setIdRequisicaoLiberacaoMidia(final Integer idRequisicaoLiberacaoMidia) {
        this.idRequisicaoLiberacaoMidia = idRequisicaoLiberacaoMidia;
    }

    public Integer getIdMidiaSoftware() {
        return idMidiaSoftware;
    }

    public void setIdMidiaSoftware(final Integer idMidiaSoftware) {
        this.idMidiaSoftware = idMidiaSoftware;
    }

}
