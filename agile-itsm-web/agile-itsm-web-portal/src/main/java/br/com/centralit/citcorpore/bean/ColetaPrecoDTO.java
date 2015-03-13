package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ColetaPrecoDTO extends BaseEntity {

    public static final String RESULT_MELHOR_COTACAO = "M";
    public static final String RESULT_EMPATE = "E";
    public static final String RESULT_DESCLASSIFICADA = "D";

    private Integer idCotacao;
    private Integer idColetaPreco;
    private Integer idItemCotacao;
    private Integer idPedido;
    private Integer idFornecedor;
    private Integer idResponsavel;
    private Date dataColeta;
    private Date dataValidade;
    private Double preco;
    private Double valorDesconto;
    private Double valorAcrescimo;
    private Double valorFrete;
    private Integer prazoEntrega;
    private Integer prazoMedioPagto;
    private Double taxaJuros;
    private Integer prazoGarantia;
    private String especificacoes;
    private Double quantidadeCotada;
    private Double pontuacao;
    private String resultadoCalculo;
    private Double quantidadeCalculo;
    private String resultadoFinal;
    private Double quantidadeCompra;
    private Double quantidadeAprovada;
    private Double quantidadePedido;

    private Integer idJustifResultado;
    private String complemJustifResultado;
    private Integer idRespResultado;

    private Integer idProduto;
    private String codigoProduto;
    private String descricaoItem;
    private String cpfCnpjFornecedor;
    private String nomeFornecedor;
    private Double quantidade;

    private Double resultPreco;
    private Integer resultPrazoEntrega;
    private Integer resultPrazoPagto;
    private Double resultJuros;
    private Double resultGarantia;

    private Double resultCriterios;

    private Integer[] idCriterioColeta;
    private Integer[] pesoCriterioColeta;

    private Collection<UploadDTO> anexos;

    private String resultadoCalculoStr;
    private String resultadoFinalStr;

    private Double valorLiquido;

    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }

    public void setIdColetaPreco(final Integer idColetaPreco) {
        this.idColetaPreco = idColetaPreco;
    }

    public Integer getIdItemCotacao() {
        return idItemCotacao;
    }

    public void setIdItemCotacao(final Integer idItemCotacao) {
        this.idItemCotacao = idItemCotacao;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(final Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Date getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(final Date dataColeta) {
        this.dataColeta = dataColeta;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(final Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(final Double preco) {
        this.preco = preco;
    }

    public Double getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(final Double valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(final Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(final Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Integer getPrazoMedioPagto() {
        return prazoMedioPagto;
    }

    public void setPrazoMedioPagto(final Integer prazoMedioPagto) {
        this.prazoMedioPagto = prazoMedioPagto;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(final Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Integer getPrazoGarantia() {
        return prazoGarantia;
    }

    public void setPrazoGarantia(final Integer prazoGarantia) {
        this.prazoGarantia = prazoGarantia;
    }

    public Double getQuantidadeCotada() {
        return quantidadeCotada;
    }

    public void setQuantidadeCotada(final Double quantidadeCotada) {
        this.quantidadeCotada = quantidadeCotada;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(final Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(final Integer idCotacao) {
        this.idCotacao = idCotacao;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(final Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Integer[] getIdCriterioColeta() {
        return idCriterioColeta;
    }

    public void setIdCriterioColeta(final Integer[] idCriterioColeta) {
        this.idCriterioColeta = idCriterioColeta;
    }

    public Integer[] getPesoCriterioColeta() {
        return pesoCriterioColeta;
    }

    public void setPesoCriterioColeta(final Integer[] pesoCriterioColeta) {
        this.pesoCriterioColeta = pesoCriterioColeta;
    }

    public Double getResultPreco() {
        return resultPreco;
    }

    public void setResultPreco(final Double resultPreco) {
        this.resultPreco = resultPreco;
    }

    public Double getResultJuros() {
        return resultJuros;
    }

    public void setResultJuros(final Double resultJuros) {
        this.resultJuros = resultJuros;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(final String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(final String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Double quantidade) {
        this.quantidade = quantidade;
    }

    private String getEspecificacoesStr() {
        if (especificacoes != null) {
            if (especificacoes.length() > 200) {
                return especificacoes.substring(0, 200) + "...";
            } else {
                return especificacoes;
            }
        } else {
            return "";
        }
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(final String especificacoes) {
        this.especificacoes = especificacoes;
    }

    public Collection<UploadDTO> getAnexos() {
        return anexos;
    }

    public void setAnexos(final Collection<UploadDTO> anexos) {
        this.anexos = anexos;
    }

    public Integer getResultPrazoPagto() {
        return resultPrazoPagto;
    }

    public void setResultPrazoPagto(final Integer resultPrazoPagto) {
        this.resultPrazoPagto = resultPrazoPagto;
    }

    public Double getResultCriterios() {
        return resultCriterios;
    }

    public void setResultCriterios(final Double resultCriterios) {
        this.resultCriterios = resultCriterios;
    }

    public String getResultadoCalculo() {
        return resultadoCalculo;
    }

    public void setResultadoCalculo(final String resultadoCalculo) {
        this.resultadoCalculo = resultadoCalculo;
        if (this.resultadoCalculo == null) {
            return;
        }
        resultadoCalculoStr = "";
        if (this.resultadoCalculo.equals(RESULT_EMPATE)) {
            resultadoCalculoStr = "Empate";
        }
        if (this.resultadoCalculo.equals(RESULT_MELHOR_COTACAO)) {
            resultadoCalculoStr = "Melhor cotação";
        }
        if (this.resultadoCalculo.equals(RESULT_DESCLASSIFICADA)) {
            resultadoCalculoStr = "Desclassificada";
        }
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(final String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
        if (this.resultadoFinal == null) {
            return;
        }
        resultadoFinalStr = "";
        if (this.resultadoFinal.equals(RESULT_EMPATE)) {
            resultadoFinalStr = "Empate";
        }
        if (this.resultadoFinal.equals(RESULT_MELHOR_COTACAO)) {
            resultadoFinalStr = "Melhor cotação";
        }
        if (this.resultadoFinal.equals(RESULT_DESCLASSIFICADA)) {
            resultadoFinalStr = "Desclassificada";
        }
    }

    public Double getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(final Double quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    public Double getQuantidadeCalculo() {
        return quantidadeCalculo;
    }

    public void setQuantidadeCalculo(final Double quantidadeCalculo) {
        this.quantidadeCalculo = quantidadeCalculo;
    }

    public String getResultadoCalculoStr() {
        return resultadoCalculoStr;
    }

    public void setResultadoCalculoStr(final String resultadoCalculoStr) {
        this.resultadoCalculoStr = resultadoCalculoStr;
    }

    public String getResultadoFinalStr() {
        return resultadoFinalStr;
    }

    public void setResultadoFinalStr(final String resultadoFinalStr) {
        this.resultadoFinalStr = resultadoFinalStr;
    }

    public Integer getIdJustifResultado() {
        return idJustifResultado;
    }

    public void setIdJustifResultado(final Integer idJustifResultado) {
        this.idJustifResultado = idJustifResultado;
    }

    public String getComplemJustifResultado() {
        return complemJustifResultado;
    }

    public void setComplemJustifResultado(final String complemJustifResultado) {
        this.complemJustifResultado = complemJustifResultado;
    }

    public Integer getIdRespResultado() {
        return idRespResultado;
    }

    public void setIdRespResultado(final Integer idRespResultado) {
        this.idRespResultado = idRespResultado;
    }

    public Integer getResultPrazoEntrega() {
        return resultPrazoEntrega;
    }

    public void setResultPrazoEntrega(final Integer resultPrazoEntrega) {
        this.resultPrazoEntrega = resultPrazoEntrega;
    }

    public Double getResultGarantia() {
        return resultGarantia;
    }

    public void setResultGarantia(final Double resultGarantia) {
        this.resultGarantia = resultGarantia;
    }

    public String getCpfCnpjFornecedor() {
        return cpfCnpjFornecedor;
    }

    public void setCpfCnpjFornecedor(final String cpfCnpjFornecedor) {
        this.cpfCnpjFornecedor = cpfCnpjFornecedor;
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

    public Double getQuantidadeAprovada() {
        return quantidadeAprovada;
    }

    public void setQuantidadeAprovada(final Double quantidadeAprovada) {
        this.quantidadeAprovada = quantidadeAprovada;
    }

    public Double getQuantidadePedido() {
        return quantidadePedido;
    }

    public void setQuantidadePedido(final Double quantidadePedido) {
        this.quantidadePedido = quantidadePedido;
    }

    public Double getQuantidadeDisponivel() {
        double qtdeAprovada = 0;
        double qtdePedido = 0;
        if (quantidadeAprovada != null) {
            qtdeAprovada = quantidadeAprovada;
        }
        if (quantidadePedido != null) {
            qtdePedido = quantidadePedido;
        }
        return new Double(qtdeAprovada - qtdePedido);
    }

    public Double getValorLiquido() {
        valorLiquido = 0.0;
        if (preco != null) {
            valorLiquido = preco;
            if (valorDesconto != null) {
                valorLiquido = valorLiquido - valorDesconto;
            }
        }
        return valorLiquido;
    }

    public void setValorLiquido(final Double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

}
