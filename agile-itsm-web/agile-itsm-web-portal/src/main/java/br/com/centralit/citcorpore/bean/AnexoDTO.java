package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AnexoDTO extends BaseEntity {

    private static final long serialVersionUID = -7252057961936714136L;
    private Integer idAnexo;
    private String nome;
    private String descricao;
    private String extensao;
    private String path;
    private String link;
    private String temporario;
    private Integer idExecucaoAtividade;

    public Integer getIdAnexo() {
        return idAnexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(final String extensao) {
        this.extensao = extensao;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getTemporario() {
        return temporario;
    }

    public void setTemporario(final String temporario) {
        this.temporario = temporario;
    }

    public Integer getIdExecucaoAtividade() {
        return idExecucaoAtividade;
    }

    public void setIdExecucaoAtividade(final Integer idExecucaoAtividade) {
        this.idExecucaoAtividade = idExecucaoAtividade;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public void setIdAnexo(final Integer idAnexo) {
        this.idAnexo = idAnexo;
    }

}
