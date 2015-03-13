/**
 *
 * ************************************************************************************************************
 *
 * Dependentes: BI Citsmart
 *
 * Obs:
 * Qualquer alteração nesta tabela deverá ser informada aos responsáveis pelo desenvolvimento do BI Citsmart.
 * O database do BI Citsmart precisa ter suas tabelas atualizadas de acordo com as mudanças nesta tabela.
 *
 * ************************************************************************************************************
 *
 */

package br.com.centralit.citcorpore.bean;

import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class ServicoContratoDTO extends BaseEntity {

    private static final long serialVersionUID = -3967063332902600349L;

    private Integer idServicoContrato;
    private Integer idServico;
    private Integer idContrato;
    private Integer idCondicaoOperacao;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String observacao;
    private Double custo;
    private String restricoesPressup;
    private String objetivo;
    private String permiteSLANoCadInc;
    private String linkProcesso;
    private String descricaoProcesso;
    private String tipoDescProcess;
    private String areaRequisitante;
    private Integer totalItens;
    private Integer totalPagina;
    private String observacaoPortal;

    private Integer idModeloEmailCriacao;
    private Integer idModeloEmailFinalizacao;
    private Integer idModeloEmailAcoes;
    private Integer idGrupoNivel1;
    private Integer idGrupoExecutor;
    private Integer idGrupoAprovador;
    private Integer idCalendario;

    private String permSLATempoACombinar;
    private String permMudancaSLA;
    private String permMudancaCalendario;

    private String nomeServico;
    private String nomeTipoDemandaServico;
    private ServicoDTO servicoDto;

    private String deleted;
    private Integer situacaoServico;

    private boolean temSLA;

    private Double dentroPrazo;
    private Double foraPrazo;

    private Integer qtdeDentroPrazo;
    private Integer qtdeForaPrazo;

    private Integer quantidade;
    private Double valorServico;
    private String descricao;
    private String nomeCategoriaServico;

    private List<FluxoServicoDTO> listaFluxo;
    private List<ServicoDTO> listaServico;

    // para busca de checkbox
    private String colAllLOOKUP_SERVICOATIVOS_DIFERENTE_CONTRATO;
    private String[] colServicosCheckado = null;

    private Integer idInfoCatalogoServico;

    private String expandir;

    private boolean permiteAnexar;

    private boolean exibirColunaAnexar;

    private String existeQuestionario;

    private Integer idQuestionario;

    private String respostaObrigatoria;

    private Collection<SolicitacaoServicoQuestionarioDTO> listSolicitacaoServicoQuestionario;

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer parm) {
        idServicoContrato = parm;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer parm) {
        idServico = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public Integer getIdCondicaoOperacao() {
        return idCondicaoOperacao;
    }

    public void setIdCondicaoOperacao(final Integer parm) {
        idCondicaoOperacao = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String parm) {
        observacao = parm;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(final Double parm) {
        custo = parm;
    }

    public String getRestricoesPressup() {
        return restricoesPressup;
    }

    public void setRestricoesPressup(final String parm) {
        restricoesPressup = parm;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(final String parm) {
        objetivo = parm;
    }

    public String getPermiteSLANoCadInc() {
        return permiteSLANoCadInc;
    }

    public void setPermiteSLANoCadInc(final String parm) {
        permiteSLANoCadInc = parm;
    }

    public String getLinkProcesso() {
        return linkProcesso;
    }

    public void setLinkProcesso(final String parm) {
        linkProcesso = parm;
    }

    public String getDescricaoProcesso() {
        return descricaoProcesso;
    }

    public void setDescricaoProcesso(final String parm) {
        descricaoProcesso = parm;
    }

    public String getTipoDescProcess() {
        return tipoDescProcess;
    }

    public void setTipoDescProcess(final String parm) {
        tipoDescProcess = parm;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public ServicoDTO getServicoDto() {
        return servicoDto;
    }

    public void setServicoDto(final ServicoDTO servicoDto) {
        this.servicoDto = servicoDto;
    }

    public String getAreaRequisitante() {
        return areaRequisitante;
    }

    public void setAreaRequisitante(final String areaRequisitante) {
        this.areaRequisitante = areaRequisitante;
    }

    public Integer getIdModeloEmailCriacao() {
        return idModeloEmailCriacao;
    }

    public void setIdModeloEmailCriacao(final Integer idModeloEmailCriacao) {
        this.idModeloEmailCriacao = idModeloEmailCriacao;
    }

    public Integer getIdModeloEmailFinalizacao() {
        return idModeloEmailFinalizacao;
    }

    public void setIdModeloEmailFinalizacao(final Integer idModeloEmailFinalizacao) {
        this.idModeloEmailFinalizacao = idModeloEmailFinalizacao;
    }

    public Integer getIdModeloEmailAcoes() {
        return idModeloEmailAcoes;
    }

    public void setIdModeloEmailAcoes(final Integer idModeloEmailAcoes) {
        this.idModeloEmailAcoes = idModeloEmailAcoes;
    }

    public Integer getIdGrupoNivel1() {
        return idGrupoNivel1;
    }

    public void setIdGrupoNivel1(final Integer idGrupoNivel1) {
        this.idGrupoNivel1 = idGrupoNivel1;
    }

    public Integer getIdGrupoExecutor() {
        return idGrupoExecutor;
    }

    public void setIdGrupoExecutor(final Integer idGrupoExecutor) {
        this.idGrupoExecutor = idGrupoExecutor;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public String getPermSLATempoACombinar() {
        return permSLATempoACombinar;
    }

    public void setPermSLATempoACombinar(final String permSLATempoACombinar) {
        this.permSLATempoACombinar = permSLATempoACombinar;
    }

    public String getPermMudancaSLA() {
        return permMudancaSLA;
    }

    public void setPermMudancaSLA(final String permMudancaSLA) {
        this.permMudancaSLA = permMudancaSLA;
    }

    public String getPermMudancaCalendario() {
        return permMudancaCalendario;
    }

    public void setPermMudancaCalendario(final String permMudancaCalendario) {
        this.permMudancaCalendario = permMudancaCalendario;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    public String getNomeTipoDemandaServico() {
        return nomeTipoDemandaServico;
    }

    public void setNomeTipoDemandaServico(final String nomeTipoDemandaServico) {
        this.nomeTipoDemandaServico = nomeTipoDemandaServico;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (idServico == null ? 0 : idServico.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ServicoContratoDTO other = (ServicoContratoDTO) obj;
        if (idServico == null) {
            if (other.idServico != null) {
                return false;
            }
        } else if (!idServico.equals(other.idServico)) {
            return false;
        }
        return true;
    }

    public Integer getSituacaoServico() {
        return situacaoServico;
    }

    public void setSituacaoServico(final Integer situacaoServico) {
        this.situacaoServico = situacaoServico;
    }

    public boolean getTemSLA() {
        return temSLA;
    }

    public void setTemSLA(final boolean temSLA) {
        this.temSLA = temSLA;
    }

    public Double getDentroPrazo() {
        return dentroPrazo;
    }

    public void setDentroPrazo(final Double dentroPrazo) {
        this.dentroPrazo = dentroPrazo;
    }

    public Double getForaPrazo() {
        return foraPrazo;
    }

    public void setForaPrazo(final Double foraPrazo) {
        this.foraPrazo = foraPrazo;
    }

    public Integer getQtdeDentroPrazo() {
        return qtdeDentroPrazo;
    }

    public void setQtdeDentroPrazo(final Integer qtdeDentroPrazo) {
        this.qtdeDentroPrazo = qtdeDentroPrazo;
    }

    public Integer getQtdeForaPrazo() {
        return qtdeForaPrazo;
    }

    public void setQtdeForaPrazo(final Integer qtdeForaPrazo) {
        this.qtdeForaPrazo = qtdeForaPrazo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getIdGrupoAprovador() {
        return idGrupoAprovador;
    }

    public void setIdGrupoAprovador(final Integer idGrupoAprovador) {
        this.idGrupoAprovador = idGrupoAprovador;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(final Integer totalItens) {
        this.totalItens = totalItens;
    }

    public Integer getTotalPagina() {
        return totalPagina;
    }

    public void setTotalPagina(final Integer totalPagina) {
        this.totalPagina = totalPagina;
    }

    public List<FluxoServicoDTO> getListaFluxo() {
        return listaFluxo;
    }

    public void setListaFluxo(final List<FluxoServicoDTO> listaFluxo) {
        this.listaFluxo = listaFluxo;
    }

    public List<ServicoDTO> getListaServico() {
        return listaServico;
    }

    public void setListaServico(final List<ServicoDTO> listaServico) {
        this.listaServico = listaServico;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(final Double valorServico) {
        this.valorServico = valorServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getNomeCategoriaServico() {
        return nomeCategoriaServico;
    }

    public void setNomeCategoriaServico(final String nomeCategoriaServico) {
        this.nomeCategoriaServico = nomeCategoriaServico;
    }

    public String getObservacaoPortal() {
        return observacaoPortal;
    }

    public void setObservacaoPortal(final String observacaoPortal) {
        this.observacaoPortal = observacaoPortal;
    }

    public String getColAllLOOKUP_SERVICOATIVOS_DIFERENTE_CONTRATO() {
        return colAllLOOKUP_SERVICOATIVOS_DIFERENTE_CONTRATO;
    }

    public void setColAllLOOKUP_SERVICOATIVOS_DIFERENTE_CONTRATO(final String colAllLOOKUP_SERVICOATIVOS_DIFERENTE_CONTRATO) {
        this.colAllLOOKUP_SERVICOATIVOS_DIFERENTE_CONTRATO = colAllLOOKUP_SERVICOATIVOS_DIFERENTE_CONTRATO;
    }

    public String[] getColServicosCheckado() {
        return colServicosCheckado;
    }

    public void setColServicosCheckado(final String checkados) {

        if (checkados != null) {
            colServicosCheckado = checkados.split(";");
        } else {
            colServicosCheckado = new String[] {};
        }
    }

    public String getExpandir() {
        return expandir;
    }

    public void setExpandir(final String expandir) {
        this.expandir = expandir;
    }

    public Integer getIdInfoCatalogoServico() {
        return idInfoCatalogoServico;
    }

    public void setIdInfoCatalogoServico(final Integer idInfoCatalogoServico) {
        this.idInfoCatalogoServico = idInfoCatalogoServico;
    }

    public boolean getPermiteAnexar() {
        return permiteAnexar;
    }

    public void setPermiteAnexar(final boolean permiteAnexar) {
        this.permiteAnexar = permiteAnexar;
    }

    public boolean getExibirColunaAnexar() {
        return exibirColunaAnexar;
    }

    public void setExibirColunaAnexar(final boolean exibirColunaAnexar) {
        this.exibirColunaAnexar = exibirColunaAnexar;
    }

    public String getExisteQuestionario() {
        return existeQuestionario;
    }

    public void setExisteQuestionario(final String existeQuestionario) {
        this.existeQuestionario = existeQuestionario;
    }

    /**
     * @return the idQuestionario
     */
    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    /**
     * @param idQuestionario
     *            the idQuestionario to set
     */
    public void setIdQuestionario(final Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    /**
     * @return the listSolicitacaoServicoQuestionario
     */
    public Collection<SolicitacaoServicoQuestionarioDTO> getListSolicitacaoServicoQuestionario() {
        return listSolicitacaoServicoQuestionario;
    }

    /**
     * @param listSolicitacaoServicoQuestionario
     *            the listSolicitacaoServicoQuestionario to set
     */
    public void setListSolicitacaoServicoQuestionario(final Collection<SolicitacaoServicoQuestionarioDTO> listSolicitacaoServicoQuestionario) {
        this.listSolicitacaoServicoQuestionario = listSolicitacaoServicoQuestionario;
    }

    public String getRespostaObrigatoria() {
        return respostaObrigatoria;
    }

    public void setRespostaObrigatoria(final String respostaObrigatoria) {
        this.respostaObrigatoria = respostaObrigatoria;
    }

}
