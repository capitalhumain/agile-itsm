package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ProdutoDTO extends BaseEntity {

    private Integer idProduto;
    private Integer idTipoProduto;
    private Integer idMarca;
    private String modelo;
    private String detalhes;
    private Double precoMercado;
    private String nomeMarca;
    private String codigoProduto;

    private String imagem;
    private String identificacao;

    private Integer idCategoria;
    private Integer idUnidadeMedida;
    private String nomeProduto;
    private String situacao;
    private String aceitaRequisicao;

    private String nomeCategoria;
    private String siglaUnidMedida;
    private String complemento;

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(final Integer parm) {
        idTipoProduto = parm;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(final Integer parm) {
        idMarca = parm;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(final String detalhes) {
        this.detalhes = detalhes;
    }

    public Double getPrecoMercado() {
        return precoMercado;
    }

    public void setPrecoMercado(final Double precoMercado) {
        this.precoMercado = precoMercado;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(final String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(final String imagem) {
        this.imagem = imagem;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(final String modelo) {
        this.modelo = modelo;
    }

    public void montaIdentificacao() {
        identificacao = "";
        if (nomeProduto != null) {
            identificacao += nomeProduto;
        }
        if (complemento != null) {
            if (identificacao.length() > 0) {
                identificacao += " ";
            }
            identificacao += complemento;
        }
        if (modelo != null) {
            if (identificacao.length() > 0) {
                identificacao += " ";
            }
            identificacao += modelo;
        }
        if (nomeMarca != null) {
            if (identificacao.length() > 0) {
                identificacao += " - ";
            }
            identificacao += nomeMarca;
        }
    }

    public String getIdentificacao() {
        if (identificacao == null) {
            this.montaIdentificacao();
        }
        return identificacao;
    }

    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(final Integer idUnidadeMedida) {
        this.idUnidadeMedida = idUnidadeMedida;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(final String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getAceitaRequisicao() {
        return aceitaRequisicao;
    }

    public void setAceitaRequisicao(final String aceitaRequisicao) {
        this.aceitaRequisicao = aceitaRequisicao;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getSiglaUnidMedida() {
        return siglaUnidMedida;
    }

    public void setSiglaUnidMedida(final String siglaUnidMedida) {
        this.siglaUnidMedida = siglaUnidMedida;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(final Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(final String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento
     *            the complemento to set
     */
    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }

}
