package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LiberacaoProblemaDTO extends BaseEntity {

    private Integer idLiberacao;
    private Integer idProblema;
    private String status;

    private String titulo;
    private Integer idHistoricoLiberacao;

    public Integer getIdLiberacao() {
        return idLiberacao;
    }

    public void setIdLiberacao(final Integer idLiberacao) {
        this.idLiberacao = idLiberacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Integer getIdHistoricoLiberacao() {
        return idHistoricoLiberacao;
    }

    public void setIdHistoricoLiberacao(final Integer idHistoricoLiberacao) {
        this.idHistoricoLiberacao = idHistoricoLiberacao;
    }

}
