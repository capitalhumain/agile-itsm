package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DeParaCatalogoServicosBIDTO extends BaseEntity {

    private static final long serialVersionUID = 3776156239250249864L;

    private Integer idServicoDe;
    private Integer idServicoPara;
    private Integer idConexaoBI;
    private String nomeServicoDe;
    private String nomeServicoPara;

    public Integer getIdServicoDe() {
        return idServicoDe;
    }

    public void setIdServicoDe(final Integer idServicoDe) {
        this.idServicoDe = idServicoDe;
    }

    public Integer getIdServicoPara() {
        return idServicoPara;
    }

    public void setIdServicoPara(final Integer idServicoPara) {
        this.idServicoPara = idServicoPara;
    }

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

    public String getNomeServicoDe() {
        return nomeServicoDe;
    }

    public void setNomeServicoDe(final String nomeServicoDe) {
        this.nomeServicoDe = nomeServicoDe;
    }

    public String getNomeServicoPara() {
        return nomeServicoPara;
    }

    public void setNomeServicoPara(final String nomeServicoPara) {
        this.nomeServicoPara = nomeServicoPara;
    }

}
