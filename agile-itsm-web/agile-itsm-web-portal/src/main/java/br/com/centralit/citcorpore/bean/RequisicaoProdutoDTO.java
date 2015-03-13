package br.com.centralit.citcorpore.bean;

import java.util.Collection;

public class RequisicaoProdutoDTO extends SolicitacaoServicoDTO {

    public static final String ACAO_CRIACAO = "criacao";
    public static final String ACAO_VALIDACAO = "validacao";
    public static final String ACAO_AUTORIZACAO = "autorizacao";
    public static final String ACAO_ACOMPANHAMENTO = "acompanhamento";
    public static final String ACAO_APROVACAO = "aprovacao";
    public static final String ACAO_INSPECAO = "inspecao";
    public static final String ACAO_GARANTIA = "garantia";

    private Integer idProjeto;
    private Integer idCentroCusto;
    private String finalidade;
    private Integer idEnderecoEntrega;
    private Integer idCategoriaProduto;
    private Integer idProduto;
    private String tipoIdentificacaoItem;
    private String rejeitada;
    private String exigeNovaAprovacao;
    private String itemAlterado;

    private Integer idFornecedorColeta;
    private Integer idItemColeta;
    private Integer idItemCotacao;
    private Integer idEntrega;

    private String acao;

    private Double valorAprovado;

    private Integer idColetaPreco;
    private Integer idItemRequisicaoProduto;

    private String centroCusto;
    private String projeto;

    private Collection<ItemRequisicaoProdutoDTO> itensRequisicao;
    private Collection<ItemRequisicaoProdutoDTO> itensValidos;
    private Collection<CotacaoItemRequisicaoDTO> itensCotacao;
    private Collection<EntregaItemRequisicaoDTO> itensEntrega;

    private String itensRequisicao_serialize;
    private String itensCotacao_serialize;
    private String itensEntrega_serialize;

    private String loginAprovadores;

    private RequisicaoProdutoDTO requisicaoAnteriorDto;
    private CentroResultadoDTO centroCustoDto;

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer parm) {
        idProjeto = parm;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(final String parm) {
        finalidade = parm;
    }

    public Collection<ItemRequisicaoProdutoDTO> getItensRequisicao() {
        return itensRequisicao;
    }

    public void setItensRequisicao(final Collection<ItemRequisicaoProdutoDTO> itensRequisicao) {
        this.itensRequisicao = itensRequisicao;
    }

    public String getItensRequisicao_serialize() {
        return itensRequisicao_serialize;
    }

    public void setItensRequisicao_serialize(final String itensRequisicao_serialize) {
        this.itensRequisicao_serialize = itensRequisicao_serialize;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(final String acao) {
        this.acao = acao;
    }

    public Integer getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(final Integer idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public Double getValorAprovado() {
        return valorAprovado;
    }

    public void setValorAprovado(final Double valorAprovado) {
        this.valorAprovado = valorAprovado;
    }

    public Integer getIdEnderecoEntrega() {
        return idEnderecoEntrega;
    }

    public void setIdEnderecoEntrega(final Integer idEnderecoEntrega) {
        this.idEnderecoEntrega = idEnderecoEntrega;
    }

    public Integer getIdCategoriaProduto() {
        return idCategoriaProduto;
    }

    public void setIdCategoriaProduto(final Integer idCategoriaProduto) {
        this.idCategoriaProduto = idCategoriaProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(final Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getTipoIdentificacaoItem() {
        return tipoIdentificacaoItem;
    }

    public void setTipoIdentificacaoItem(final String tipoIdentificacaoItem) {
        this.tipoIdentificacaoItem = tipoIdentificacaoItem;
    }

    public String getRejeitada() {
        return rejeitada;
    }

    public void setRejeitada(final String rejeitada) {
        this.rejeitada = rejeitada;
    }

    public Integer getIdFornecedorColeta() {
        return idFornecedorColeta;
    }

    public void setIdFornecedorColeta(final Integer idFornecedorColeta) {
        this.idFornecedorColeta = idFornecedorColeta;
    }

    public Integer getIdItemColeta() {
        return idItemColeta;
    }

    public void setIdItemColeta(final Integer idItemColeta) {
        this.idItemColeta = idItemColeta;
    }

    public Collection<ItemRequisicaoProdutoDTO> getItensValidos() {
        return itensValidos;
    }

    public void setItensValidos(final Collection<ItemRequisicaoProdutoDTO> itensValidos) {
        this.itensValidos = itensValidos;
    }

    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }

    public void setIdColetaPreco(final Integer idColetaPreco) {
        this.idColetaPreco = idColetaPreco;
    }

    public Integer getIdItemRequisicaoProduto() {
        return idItemRequisicaoProduto;
    }

    public void setIdItemRequisicaoProduto(final Integer idItemRequisicaoProduto) {
        this.idItemRequisicaoProduto = idItemRequisicaoProduto;
    }

    public Collection<CotacaoItemRequisicaoDTO> getItensCotacao() {
        return itensCotacao;
    }

    public void setItensCotacao(final Collection<CotacaoItemRequisicaoDTO> itensCotacao) {
        this.itensCotacao = itensCotacao;
    }

    public String getItensCotacao_serialize() {
        return itensCotacao_serialize;
    }

    public void setItensCotacao_serialize(final String itensCotacao_serialize) {
        this.itensCotacao_serialize = itensCotacao_serialize;
    }

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(final Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Collection<EntregaItemRequisicaoDTO> getItensEntrega() {
        return itensEntrega;
    }

    public void setItensEntrega(final Collection<EntregaItemRequisicaoDTO> itensEntrega) {
        this.itensEntrega = itensEntrega;
    }

    public String getItensEntrega_serialize() {
        return itensEntrega_serialize;
    }

    public void setItensEntrega_serialize(final String itensEntrega_serialize) {
        this.itensEntrega_serialize = itensEntrega_serialize;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(final String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(final String projeto) {
        this.projeto = projeto;
    }

    public String getLoginAprovadores() {
        return loginAprovadores;
    }

    public void setLoginAprovadores(final String loginAprovadores) {
        this.loginAprovadores = loginAprovadores;
    }

    public RequisicaoProdutoDTO getRequisicaoAnteriorDto() {
        return requisicaoAnteriorDto;
    }

    public void setRequisicaoAnteriorDto(final RequisicaoProdutoDTO requisicaoAnteriorDto) {
        this.requisicaoAnteriorDto = requisicaoAnteriorDto;
    }

    public boolean dadosAlterados() {
        if (requisicaoAnteriorDto == null) {
            return false;
        }

        return requisicaoAnteriorDto.getIdCentroCusto().intValue() != this.getIdCentroCusto().intValue()
                || requisicaoAnteriorDto.getIdProjeto().intValue() != this.getIdProjeto().intValue()
                || requisicaoAnteriorDto.getIdEnderecoEntrega().intValue() != this.getIdEnderecoEntrega().intValue()
                || !requisicaoAnteriorDto.getFinalidade().equals(this.getFinalidade());
    }

    public boolean centroCustoAlterado() {
        if (requisicaoAnteriorDto == null) {
            return false;
        }

        return requisicaoAnteriorDto.getIdCentroCusto().intValue() != this.getIdCentroCusto().intValue();
    }

    public CentroResultadoDTO getCentroCustoDto() {
        return centroCustoDto;
    }

    public void setCentroCustoDto(final CentroResultadoDTO centroCustoDto) {
        this.centroCustoDto = centroCustoDto;
    }

    public String getExigeNovaAprovacao() {
        return exigeNovaAprovacao;
    }

    public void setExigeNovaAprovacao(final String exigeNovaAprovacao) {
        this.exigeNovaAprovacao = exigeNovaAprovacao;
    }

    public String getItemAlterado() {
        return itemAlterado;
    }

    public void setItemAlterado(final String itemAlterado) {
        this.itemAlterado = itemAlterado;
    }

    public Integer getIdItemCotacao() {
        return idItemCotacao;
    }

    public void setIdItemCotacao(final Integer idItemCotacao) {
        this.idItemCotacao = idItemCotacao;
    }

}
