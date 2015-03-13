package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class UnidadesAccServicosDTO extends BaseEntity {

    private static final long serialVersionUID = 907067099262037701L;

    private Integer idUnidade;
    private Integer idServico;
    private String nomeServico;
    private String detalheServico;

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(final Integer parm) {
        idUnidade = parm;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer parm) {
        idServico = parm;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDetalheServico() {
        return detalheServico;
    }

    public void setDetalheServico(final String detalheServico) {
        this.detalheServico = detalheServico;
    }

}
