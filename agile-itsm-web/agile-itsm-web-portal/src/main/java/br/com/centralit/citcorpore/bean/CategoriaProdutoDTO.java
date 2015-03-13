package br.com.centralit.citcorpore.bean;

import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class CategoriaProdutoDTO extends BaseEntity {

    private static final long serialVersionUID = 643396483433858897L;

    private Integer idCategoria;
    private Integer idCategoriaPai;
    private String nomeCategoria;
    private String nomeCategoriaPai;
    private String situacao;
    private Integer nivel;
    private String imagem;
    private Integer pesoCotacaoPreco;
    private Integer pesoCotacaoPrazoEntrega;
    private Integer pesoCotacaoPrazoPagto;
    private Integer pesoCotacaoTaxaJuros;
    private Integer pesoCotacaoPrazoGarantia;

    private List<TipoProdutoDTO> colProdutos;
    private Collection<CriterioCotacaoCategoriaDTO> colCriterios;
    private Collection<UploadDTO> fotos;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Integer parm) {
        idCategoria = parm;
    }

    public Integer getIdCategoriaPai() {
        return idCategoriaPai;
    }

    public void setIdCategoriaPai(final Integer idCategoriaPai) {
        this.idCategoriaPai = idCategoriaPai;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String parm) {
        nomeCategoria = parm;
    }

    public String getNomeCategoriaPai() {
        return nomeCategoriaPai;
    }

    public void setNomeCategoriaPai(final String nomeCategoriaPai) {
        this.nomeCategoriaPai = nomeCategoriaPai;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
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

    public List<TipoProdutoDTO> getColProdutos() {
        return colProdutos;
    }

    public void setColProdutos(final List<TipoProdutoDTO> colProdutos) {
        this.colProdutos = colProdutos;
    }

    public Integer getPesoCotacaoPreco() {
        return pesoCotacaoPreco;
    }

    public void setPesoCotacaoPreco(final Integer pesoCotacaoPreco) {
        this.pesoCotacaoPreco = pesoCotacaoPreco;
    }

    public Integer getPesoCotacaoPrazoEntrega() {
        return pesoCotacaoPrazoEntrega;
    }

    public void setPesoCotacaoPrazoEntrega(final Integer pesoCotacaoPrazoEntrega) {
        this.pesoCotacaoPrazoEntrega = pesoCotacaoPrazoEntrega;
    }

    public Integer getPesoCotacaoPrazoPagto() {
        return pesoCotacaoPrazoPagto;
    }

    public void setPesoCotacaoPrazoPagto(final Integer pesoCotacaoPrazoPagto) {
        this.pesoCotacaoPrazoPagto = pesoCotacaoPrazoPagto;
    }

    public Integer getPesoCotacaoTaxaJuros() {
        return pesoCotacaoTaxaJuros;
    }

    public void setPesoCotacaoTaxaJuros(final Integer pesoCotacaoTaxaJuros) {
        this.pesoCotacaoTaxaJuros = pesoCotacaoTaxaJuros;
    }

    public Integer getPesoCotacaoPrazoGarantia() {
        return pesoCotacaoPrazoGarantia;
    }

    public void setPesoCotacaoPrazoGarantia(final Integer pesoCotacaoPrazoGarantia) {
        this.pesoCotacaoPrazoGarantia = pesoCotacaoPrazoGarantia;
    }

    public Collection<CriterioCotacaoCategoriaDTO> getColCriterios() {
        return colCriterios;
    }

    public void setColCriterios(final Collection<CriterioCotacaoCategoriaDTO> colCriterios) {
        this.colCriterios = colCriterios;
    }

    public Collection<UploadDTO> getFotos() {
        return fotos;
    }

    public void setFotos(final Collection<UploadDTO> fotos) {
        this.fotos = fotos;
    }

    public String getNomeHierarquizado() {

        if (nomeCategoria == null) {
            return "";
        }

        if (this.getNivel() == null) {
            return nomeCategoria;
        }

        String aux = "";

        for (int i = 0; i < this.getNivel().intValue(); i++) {
            aux += ".....";
        }

        return aux + nomeCategoria;
    }

}
