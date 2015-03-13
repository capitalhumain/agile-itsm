package br.com.centralit.citcorpore.bean;

public class SimulacaoAlcadaDTO extends SolicitacaoServicoDTO {

    private Integer idCentroResultado;
    private Integer idTipoFluxo;
    private Double valor;
    private Double valorOutrasAlcadas;
    private Double valorMensal;
    private String finalidade;

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(final String finalidade) {
        this.finalidade = finalidade;
    }

    public Integer getIdCentroResultado() {
        return idCentroResultado;
    }

    public void setIdCentroResultado(final Integer idCentroResultado) {
        this.idCentroResultado = idCentroResultado;
    }

    public Integer getIdTipoFluxo() {
        return idTipoFluxo;
    }

    public void setIdTipoFluxo(final Integer idTipoFluxo) {
        this.idTipoFluxo = idTipoFluxo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(final Double valor) {
        this.valor = valor;
    }

    public Double getValorOutrasAlcadas() {
        return valorOutrasAlcadas;
    }

    public void setValorOutrasAlcadas(final Double valorOutrasAlcadas) {
        this.valorOutrasAlcadas = valorOutrasAlcadas;
    }

    public Double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(final Double valorMensal) {
        this.valorMensal = valorMensal;
    }

}
