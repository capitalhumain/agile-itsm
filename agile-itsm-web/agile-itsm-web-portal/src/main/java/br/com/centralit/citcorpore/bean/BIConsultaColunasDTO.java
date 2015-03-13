package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BIConsultaColunasDTO extends BaseEntity {

    private Integer idConsultaColuna;
    private Integer idConsulta;
    private String nomeColuna;
    private String tipoFiltro;
    private Integer ordem;

    public Integer getIdConsultaColuna() {
        return idConsultaColuna;
    }

    public void setIdConsultaColuna(final Integer parm) {
        idConsultaColuna = parm;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(final Integer parm) {
        idConsulta = parm;
    }

    public String getNomeColuna() {
        return nomeColuna;
    }

    public void setNomeColuna(final String parm) {
        nomeColuna = parm;
    }

    public String getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(final String parm) {
        tipoFiltro = parm;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer parm) {
        ordem = parm;
    }

}
