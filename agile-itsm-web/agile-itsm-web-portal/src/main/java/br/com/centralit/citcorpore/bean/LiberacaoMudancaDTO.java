package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LiberacaoMudancaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLiberacao;
    private Integer idRequisicaoMudanca;
    private Integer idHistoricoLiberacao;
    private Integer idHistoricoMudanca;
    private String titulo;

    private String status;

    private String situacaoLiberacao;

    private String descricao;

    public Integer getIdLiberacao() {
        return idLiberacao;
    }

    public void setIdLiberacao(final Integer parm) {
        idLiberacao = parm;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer parm) {
        idRequisicaoMudanca = parm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdHistoricoLiberacao() {
        return idHistoricoLiberacao;
    }

    public void setIdHistoricoLiberacao(final Integer idHistoricoLiberacao) {
        this.idHistoricoLiberacao = idHistoricoLiberacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getSituacaoLiberacao() {
        return situacaoLiberacao;
    }

    public void setSituacaoLiberacao(final String situacaoLiberacao) {
        this.situacaoLiberacao = situacaoLiberacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdHistoricoMudanca() {
        return idHistoricoMudanca;
    }

    public void setIdHistoricoMudanca(final Integer idHistoricoMudanca) {
        this.idHistoricoMudanca = idHistoricoMudanca;
    }
}
