package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class EventoEmpregadoDTO extends BaseEntity {

    // Atributos relacionados ao banco
    private Integer idEvento;
    private Integer idGrupo;
    private Integer idUnidade;
    private Integer idEmpregado;
    private Integer idItemConfiguracaoPai;

    private String nome;

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(final Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Integer getIdItemConfiguracaoPai() {
        return idItemConfiguracaoPai;
    }

    public void setIdItemConfiguracaoPai(final Integer idItemConfiguracaoPai) {
        this.idItemConfiguracaoPai = idItemConfiguracaoPai;
    }

}
