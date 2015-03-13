package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioCargaHorariaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idServico;
    private String nomeGrupo;
    private String nomeUsuario;
    private Double soma;
    private String total;

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Double getSoma() {
        return soma;
    }

    public void setSoma(final Double soma) {
        this.soma = soma;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(final String total) {
        this.total = total;
    }

}
