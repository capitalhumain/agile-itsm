package br.com.centralit.citcorpore.bean;

public class ContratoHistoricoDTO extends ContratoDTO {

    private Integer idContrato_Hist;
    private String conteudodados;

    public Integer getIdContrato_Hist() {
        return idContrato_Hist;
    }

    public void setIdContrato_Hist(final Integer idContrato_Hist) {
        this.idContrato_Hist = idContrato_Hist;
    }

    public String getConteudodados() {
        return conteudodados;
    }

    public void setConteudodados(final String conteudodados) {
        this.conteudodados = conteudodados;
    }

}
