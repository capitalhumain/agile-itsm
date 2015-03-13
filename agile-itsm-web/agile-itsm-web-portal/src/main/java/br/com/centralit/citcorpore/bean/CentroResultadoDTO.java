package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilStrings;

public class CentroResultadoDTO extends BaseEntity {

    private static final long serialVersionUID = 5314639262309812533L;

    private Integer idCentroResultado;
    private String codigoCentroResultado;
    private String nomeCentroResultado;
    private Integer idCentroResultadoPai;
    private String permiteRequisicaoProduto;
    private String situacao;
    private String nomeCentroResultadoPai;
    private Integer nivel;
    private String imagem;

    private Collection<AlcadaCentroResultadoDTO> colAlcadas;
    private Collection<ResponsavelCentroResultadoDTO> colResponsaveis;

    public Integer getIdCentroResultado() {
        return idCentroResultado;
    }

    public void setIdCentroResultado(final Integer parm) {
        idCentroResultado = parm;
    }

    public String getCodigoCentroResultado() {
        return codigoCentroResultado;
    }

    public void setCodigoCentroResultado(final String parm) {
        codigoCentroResultado = parm;
    }

    public String getNomeCentroResultado() {
        return nomeCentroResultado;
    }

    public String getNomeHierarquizado() {

        if (nomeCentroResultado == null) {
            return "";
        }

        if (this.getNivel() == null) {
            return UtilStrings.nullToVazio(codigoCentroResultado) + " " + nomeCentroResultado;
        }

        String aux = "";

        for (int i = 0; i < this.getNivel().intValue(); i++) {
            aux += ".....";
        }

        return aux + UtilStrings.nullToVazio(codigoCentroResultado) + " " + nomeCentroResultado;
    }

    public void setNomeCentroResultado(final String parm) {
        nomeCentroResultado = parm;
    }

    public Integer getIdCentroResultadoPai() {
        return idCentroResultadoPai;
    }

    public void setIdCentroResultadoPai(final Integer parm) {
        idCentroResultadoPai = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getNomeCentroResultadoPai() {
        return nomeCentroResultadoPai;
    }

    public void setNomeCentroResultadoPai(final String nomeCentroResultadoPai) {
        this.nomeCentroResultadoPai = nomeCentroResultadoPai;
    }

    public String getPermiteRequisicaoProduto() {
        return permiteRequisicaoProduto;
    }

    public void setPermiteRequisicaoProduto(final String permiteRequisicaoProduto) {
        this.permiteRequisicaoProduto = permiteRequisicaoProduto;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(final Integer nivel) {
        this.nivel = nivel;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(final String imagem) {
        this.imagem = imagem;
    }

    public String getNomeHierarquiaHTML() {

        if (nomeCentroResultado == null) {
            return "";
        }

        if (this.getNivel() == null) {
            return nomeCentroResultado;
        }

        String aux = "";

        for (int i = 0; i < this.getNivel().intValue(); i++) {
            aux += ".....";
        }

        return aux + nomeCentroResultado;
    }

    public Collection<AlcadaCentroResultadoDTO> getColAlcadas() {
        return colAlcadas;
    }

    public void setColAlcadas(final Collection<AlcadaCentroResultadoDTO> colAlcadas) {
        this.colAlcadas = colAlcadas;
    }

    public Collection<ResponsavelCentroResultadoDTO> getColResponsaveis() {
        return colResponsaveis;
    }

    public void setColResponsaveis(final Collection<ResponsavelCentroResultadoDTO> colResponsaveis) {
        this.colResponsaveis = colResponsaveis;
    }

}
