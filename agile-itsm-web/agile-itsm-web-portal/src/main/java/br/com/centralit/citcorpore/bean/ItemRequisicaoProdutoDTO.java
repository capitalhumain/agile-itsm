package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoItemRequisicaoProduto;
import br.com.citframework.util.UtilStrings;

public class ItemRequisicaoProdutoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idItemRequisicaoProduto;
    private Integer idSolicitacaoServico;
    private Integer idProduto;
    private Integer idMarca;
    private Integer idUnidadeMedida;
    private String descricaoItem;
    private String especificacoes;
    private Double quantidade;
    private String marcaPreferencial;
    private Double precoAproximado;
    private String situacao;
    private Double percVariacaoPreco;
    private Integer idParecerValidacao;
    private Integer idParecerAutorizacao;
    private Integer idItemCotacao;
    private String tipoAtendimento;
    private String descrTipoAtendimento;
    private String tipoIdentificacao;
    private Double qtdeCotada;
    private String aprovaCotacao;
    private Double valorAprovado;

    private Integer idCategoriaProduto;
    private String codigoProduto;
    private String nomeProduto;

    private String validado;
    private Integer idJustificativaValidacao;
    private String complemJustificativaValidacao;
    private String descrJustificativaValidacao;

    private String autorizado;
    private Integer idJustificativaAutorizacao;
    private String complemJustificativaAutorizacao;
    private String descrJustificativaAutorizacao;

    private String siglaUnidadeMedida;
    private String descrSituacao;
    private Double qtdeAprovada;

    private Timestamp dataHoraSolicitacao;
    private Timestamp dataHoraLimite;
    private String dataHoraLimiteStr;

    private Integer idProjeto;
    private Integer idCentroCusto;
    private String nomeCentroCusto;
    private String nomeProjeto;
    private String nomeCategoria;

    private Integer idEnderecoEntrega;
    private String enderecoStr;

    private UsuarioDTO usuarioDto;

    private ItemRequisicaoProdutoDTO itemAnteriorDto;

    private String descricaoFmtHtml;

    public Integer getIdItemRequisicaoProduto() {
        return idItemRequisicaoProduto;
    }

    public void setIdItemRequisicaoProduto(final Integer parm) {
        idItemRequisicaoProduto = parm;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(final String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(final Integer parm) {
        idUnidadeMedida = parm;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(final String parm) {
        descricaoItem = parm;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(final String parm) {
        especificacoes = parm;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Double parm) {
        quantidade = parm;
    }

    public String getMarcaPreferencial() {
        return marcaPreferencial;
    }

    public void setMarcaPreferencial(final String parm) {
        marcaPreferencial = parm;
    }

    public Double getPrecoAproximado() {
        return precoAproximado;
    }

    public void setPrecoAproximado(final Double parm) {
        precoAproximado = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
        descrSituacao = "";
        situacao = parm;
        try {
            if (situacao != null) {
                descrSituacao = SituacaoItemRequisicaoProduto.valueOf(situacao.trim()).getDescricao();
            }
        } catch (final Exception e) {
            descrSituacao = situacao;
        }
    }

    public Double getPercVariacaoPreco() {
        return percVariacaoPreco;
    }

    public void setPercVariacaoPreco(final Double parm) {
        percVariacaoPreco = parm;
    }

    public String getSiglaUnidadeMedida() {
        return siglaUnidadeMedida;
    }

    public void setSiglaUnidadeMedida(final String siglaUnidadeMedida) {
        this.siglaUnidadeMedida = siglaUnidadeMedida;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(final String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getIdParecerValidacao() {
        return idParecerValidacao;
    }

    public void setIdParecerValidacao(final Integer idParecerValidacao) {
        this.idParecerValidacao = idParecerValidacao;
    }

    public Integer getIdParecerAutorizacao() {
        return idParecerAutorizacao;
    }

    public void setIdParecerAutorizacao(final Integer idParecerAutorizacao) {
        this.idParecerAutorizacao = idParecerAutorizacao;
    }

    public String getValidado() {
        return validado;
    }

    public void setValidado(final String validado) {
        this.validado = validado;
    }

    public Integer getIdJustificativaValidacao() {
        return idJustificativaValidacao;
    }

    public void setIdJustificativaValidacao(final Integer idJustificativaValidacao) {
        this.idJustificativaValidacao = idJustificativaValidacao;
    }

    public String getComplemJustificativaValidacao() {
        return complemJustificativaValidacao;
    }

    public void setComplemJustificativaValidacao(final String complemJustificativaValidacao) {
        this.complemJustificativaValidacao = complemJustificativaValidacao;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(final String autorizado) {
        this.autorizado = autorizado;
    }

    public String getComplemJustificativaAutorizacao() {
        return complemJustificativaAutorizacao;
    }

    public void setComplemJustificativaAutorizacao(final String complemJustificativaAutorizacao) {
        this.complemJustificativaAutorizacao = complemJustificativaAutorizacao;
    }

    public Integer getIdJustificativaAutorizacao() {
        return idJustificativaAutorizacao;
    }

    public void setIdJustificativaAutorizacao(final Integer idJustificativaAutorizacao) {
        this.idJustificativaAutorizacao = idJustificativaAutorizacao;
    }

    public String getDescrSituacao() {
        return descrSituacao;
    }

    public void setDescrSituacao(final String descrSituacao) {
        this.descrSituacao = descrSituacao;
    }

    public Double getQtdeAprovada() {
        return qtdeAprovada;
    }

    public void setQtdeAprovada(final Double qtdeAprovada) {
        this.qtdeAprovada = qtdeAprovada;
    }

    public Timestamp getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(final Timestamp dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Integer getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(final Integer idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public String getNomeCentroCusto() {
        return nomeCentroCusto;
    }

    public void setNomeCentroCusto(final String nomeCentroCusto) {
        this.nomeCentroCusto = nomeCentroCusto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(final String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public Integer getIdItemCotacao() {
        return idItemCotacao;
    }

    public void setIdItemCotacao(final Integer idItemCotacao) {
        this.idItemCotacao = idItemCotacao;
    }

    public Date getDataSolicitacao() {
        if (dataHoraSolicitacao == null) {
            return null;
        }
        return new Date(dataHoraSolicitacao.getTime());
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(final String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
        descrTipoAtendimento = "";
        if (this.tipoAtendimento == null) {
            return;
        }
        if (this.tipoAtendimento.equalsIgnoreCase("C")) {
            descrTipoAtendimento = "Compra";
        } else if (this.tipoAtendimento.equalsIgnoreCase("E")) {
            descrTipoAtendimento = "Estoque";
        }
    }

    public String getDescrTipoAtendimento() {
        return descrTipoAtendimento;
    }

    public void setDescrTipoAtendimento(final String descrTipoAtendimento) {
        this.descrTipoAtendimento = descrTipoAtendimento;
    }

    public void setIdCategoriaProduto(final Integer idCategoriaProduto) {
        this.idCategoriaProduto = idCategoriaProduto;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(final Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdCategoriaProduto() {
        return idCategoriaProduto;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(final Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getTipoIdentificacao() {
        return tipoIdentificacao;
    }

    public void setTipoIdentificacao(final String tipoIdentificacao) {
        this.tipoIdentificacao = tipoIdentificacao;
    }

    public Timestamp getDataHoraLimite() {
        return dataHoraLimite;
    }

    public void setDataHoraLimite(final Timestamp dataHoraLimite) {
        this.dataHoraLimite = dataHoraLimite;
        if (dataHoraLimite != null) {
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataHoraLimiteStr = format.format(dataHoraLimite);
        }
    }

    public String getDataHoraLimiteStr() {
        if (dataHoraLimite != null) {
            return dataHoraLimiteStr;
        } else {
            return "--";
        }
    }

    public String getAprovaCotacao() {
        return aprovaCotacao;
    }

    public void setAprovaCotacao(final String aprovaCotacao) {
        this.aprovaCotacao = aprovaCotacao;
    }

    public void setDataHoraLimiteStr(final String dataHoraLimiteStr) {
        this.dataHoraLimiteStr = dataHoraLimiteStr;
    }

    public Double getQtdeCotada() {
        return qtdeCotada;
    }

    public void setQtdeCotada(final Double qtdeCotada) {
        this.qtdeCotada = qtdeCotada;
    }

    public Double getValorAprovado() {
        return valorAprovado;
    }

    public void setValorAprovado(final Double valorAprovado) {
        this.valorAprovado = valorAprovado;
    }

    public UsuarioDTO getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(final UsuarioDTO usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public Integer getIdEnderecoEntrega() {
        return idEnderecoEntrega;
    }

    public void setIdEnderecoEntrega(final Integer idEnderecoEntrega) {
        this.idEnderecoEntrega = idEnderecoEntrega;
    }

    public String getEnderecoStr() {
        return enderecoStr;
    }

    public void setEnderecoStr(final String enderecoStr) {
        this.enderecoStr = enderecoStr;
    }

    public boolean cotacaoIniciada() {
        if (situacao == null) {
            return false;
        }
        if (situacao.equals(SituacaoItemRequisicaoProduto.AguardandoCotacao.name()) && idItemCotacao != null) {
            return true;
        }
        return situacao.equals(SituacaoItemRequisicaoProduto.AguardandoAprovacaoCotacao.name())
                || situacao.equals(SituacaoItemRequisicaoProduto.CotacaoNaoAprovada.name())
                || situacao.equals(SituacaoItemRequisicaoProduto.AguardandoPedido.name())
                || situacao.equals(SituacaoItemRequisicaoProduto.AguardandoEntrega.name())
                || situacao.equals(SituacaoItemRequisicaoProduto.AguardandoInspecao.name())
                || situacao.equals(SituacaoItemRequisicaoProduto.AguardandoInspecaoGarantia.name())
                || situacao.equals(SituacaoItemRequisicaoProduto.InspecaoRejeitada.name());
    }

    public boolean dadosAlterados() {
        if (itemAnteriorDto == null) {
            return false;
        }

        return !UtilStrings.nullToVazio(itemAnteriorDto.getDescricaoItem()).equalsIgnoreCase(UtilStrings.nullToVazio(this.getDescricaoItem()))
                || !UtilStrings.nullToVazio(itemAnteriorDto.getEspecificacoes()).equalsIgnoreCase(UtilStrings.nullToVazio(this.getEspecificacoes()))
                || itemAnteriorDto.getIdUnidadeMedida() == null
                && this.getIdUnidadeMedida() != null
                || itemAnteriorDto.getIdUnidadeMedida() != null
                && this.getIdUnidadeMedida() == null
                || itemAnteriorDto.getIdUnidadeMedida() != null
                && this.getIdUnidadeMedida() != null
                && itemAnteriorDto.getIdUnidadeMedida().intValue() != this.getIdUnidadeMedida().intValue()
                || !UtilStrings.nullToVazio(itemAnteriorDto.getMarcaPreferencial()).equalsIgnoreCase(UtilStrings.nullToVazio(this.getMarcaPreferencial()))
                || itemAnteriorDto.getQuantidade().doubleValue() != this.getQuantidade().doubleValue()
                || itemAnteriorDto.getPrecoAproximado() == null
                && this.getPrecoAproximado() != null
                || itemAnteriorDto.getPrecoAproximado() != null
                && this.getPrecoAproximado() == null
                || itemAnteriorDto.getPrecoAproximado() != null
                && this.getPrecoAproximado() != null
                && itemAnteriorDto.getPrecoAproximado().doubleValue() != this.getPrecoAproximado().doubleValue()
                || this.getTipoIdentificacao() != null
                && this.getTipoIdentificacao().equalsIgnoreCase("S")
                && (itemAnteriorDto.getIdProduto() == null && this.getIdProduto() != null || itemAnteriorDto.getIdProduto() != null
                && this.getIdProduto() == null || itemAnteriorDto.getIdProduto() != null && this.getIdProduto() != null
                && this.getIdProduto().intValue() != itemAnteriorDto.getIdProduto().intValue());

    }

    public void atribuiDadosAtuais() {
        if (itemAnteriorDto == null) {
            return;
        }
        itemAnteriorDto.setDescricaoItem(this.getDescricaoItem());
        itemAnteriorDto.setEspecificacoes(this.getEspecificacoes());
        itemAnteriorDto.setIdUnidadeMedida(this.getIdUnidadeMedida());
        itemAnteriorDto.setMarcaPreferencial(this.getMarcaPreferencial());
        itemAnteriorDto.setQuantidade(this.getQuantidade());
        itemAnteriorDto.setPrecoAproximado(this.getPrecoAproximado());
        itemAnteriorDto.setIdProduto(this.getIdProduto());
    }

    public ItemRequisicaoProdutoDTO getItemAnteriorDto() {
        return itemAnteriorDto;
    }

    public void setItemAnteriorDto(final ItemRequisicaoProdutoDTO itemAnteriorDto) {
        this.itemAnteriorDto = itemAnteriorDto;
    }

    public String getDescricaoFmtHtml() {
        descricaoFmtHtml = "";
        if (UtilStrings.isNotVazio(especificacoes)) {
            descricaoFmtHtml += "<p><b>Especificações:</b> " + especificacoes + "</p>";
        }
        descricaoFmtHtml += "<p><b>Unidade de medida:</b> " + siglaUnidadeMedida + "</p>";
        if (UtilStrings.isNotVazio(marcaPreferencial)) {
            descricaoFmtHtml += "<p><b>Marca preferencial:</b> " + marcaPreferencial + "</p>";
        }
        if (precoAproximado != null) {
            descricaoFmtHtml += "<p><b>Preço aproximado:</b> " + precoAproximado + "</p>";
        }
        if (idProduto != null) {
            descricaoFmtHtml += "<p><b>Produto:</b> " + codigoProduto + " - " + nomeProduto + "</p>";
            // descricaoFmtHtml += "<p><b>Situação:</b> "+this.descrSituacao+"</p>";
        }

        if (idJustificativaValidacao != null) {
            if (descrJustificativaValidacao != null) {
                descricaoFmtHtml += "<p><b>Justificativa:</b> " + descrJustificativaValidacao;
                if (complemJustificativaValidacao != null) {
                    descricaoFmtHtml += " - " + complemJustificativaValidacao;
                }
                descricaoFmtHtml += "</p>";
            }
        } else if (idJustificativaAutorizacao != null && descrJustificativaAutorizacao != null) {
            descricaoFmtHtml += "<p><b>Justificativa:</b> " + descrJustificativaAutorizacao;
            if (complemJustificativaAutorizacao != null) {
                descricaoFmtHtml += "<br>" + complemJustificativaAutorizacao;
            }
            descricaoFmtHtml += "</p>";
        }
        return descricaoFmtHtml;
    }

    public void setDescricaoFmtHtml(final String descricaoFmtHtml) {
        this.descricaoFmtHtml = descricaoFmtHtml;
    }

    public String getDescrJustificativaValidacao() {
        return descrJustificativaValidacao;
    }

    public void setDescrJustificativaValidacao(final String descrJustificativaValidacao) {
        this.descrJustificativaValidacao = descrJustificativaValidacao;
    }

    public String getDescrJustificativaAutorizacao() {
        return descrJustificativaAutorizacao;
    }

    public void setDescrJustificativaAutorizacao(final String descrJustificativaAutorizacao) {
        this.descrJustificativaAutorizacao = descrJustificativaAutorizacao;
    }

}
