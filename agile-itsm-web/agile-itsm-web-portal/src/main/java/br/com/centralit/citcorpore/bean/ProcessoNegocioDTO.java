package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bpm.negocio.ExecucaoSolicitacao;

public class ProcessoNegocioDTO extends BaseEntity {

    private static final long serialVersionUID = -1540754378060788134L;

    private Integer idProcessoNegocio;
    private Integer idGrupoExecutor;
    private Integer idGrupoAdministrador;
    private String nomeProcessoNegocio;
    private String permissaoSolicitacao;
    private Double percDispensaNovaAprovacao;
    private String permiteAprovacaoNivelInferior;
    private String alcadaPrimeiroNivel;

    private Integer[] idTipoFluxo;

    private Collection<ProcessoNivelAutoridadeDTO> colAutoridades;
    private Collection<ExecucaoSolicitacao> colExecucaoSolicitacao;
    private Collection<LimiteAprovacaoDTO> colLimitesAprovacao;

    private NivelAutoridadeDTO nivelAutoridadeDto;

    public Integer getIdProcessoNegocio() {
        return idProcessoNegocio;
    }

    public void setIdProcessoNegocio(final Integer parm) {
        idProcessoNegocio = parm;
    }

    public Integer getIdGrupoExecutor() {
        return idGrupoExecutor;
    }

    public void setIdGrupoExecutor(final Integer parm) {
        idGrupoExecutor = parm;
    }

    public Integer getIdGrupoAdministrador() {
        return idGrupoAdministrador;
    }

    public void setIdGrupoAdministrador(final Integer parm) {
        idGrupoAdministrador = parm;
    }

    public String getNomeProcessoNegocio() {
        return nomeProcessoNegocio;
    }

    public void setNomeProcessoNegocio(final String parm) {
        nomeProcessoNegocio = parm;
    }

    public String getPermissaoSolicitacao() {
        return permissaoSolicitacao;
    }

    public void setPermissaoSolicitacao(final String parm) {
        permissaoSolicitacao = parm;
    }

    public Double getPercDispensaNovaAprovacao() {
        return percDispensaNovaAprovacao;
    }

    public void setPercDispensaNovaAprovacao(final Double parm) {
        percDispensaNovaAprovacao = parm;
    }

    public String getPermiteAprovacaoNivelInferior() {
        return permiteAprovacaoNivelInferior;
    }

    public void setPermiteAprovacaoNivelInferior(final String parm) {
        permiteAprovacaoNivelInferior = parm;
    }

    public Collection<ProcessoNivelAutoridadeDTO> getColAutoridades() {
        return colAutoridades;
    }

    public void setColAutoridades(final Collection<ProcessoNivelAutoridadeDTO> colAutoridades) {
        this.colAutoridades = colAutoridades;
    }

    public Integer[] getIdTipoFluxo() {
        return idTipoFluxo;
    }

    public void setIdTipoFluxo(final Integer[] idTipoFluxo) {
        this.idTipoFluxo = idTipoFluxo;
    }

    public NivelAutoridadeDTO getNivelAutoridadeDto() {
        return nivelAutoridadeDto;
    }

    public void setNivelAutoridadeDto(final NivelAutoridadeDTO nivelAutoridadeDto) {
        this.nivelAutoridadeDto = nivelAutoridadeDto;
    }

    public Collection<ExecucaoSolicitacao> getColExecucaoSolicitacao() {
        return colExecucaoSolicitacao;
    }

    public void setColExecucaoSolicitacao(final Collection<ExecucaoSolicitacao> colExecucaoSolicitacao) {
        this.colExecucaoSolicitacao = colExecucaoSolicitacao;
    }

    public Collection<LimiteAprovacaoDTO> getColLimitesAprovacao() {
        return colLimitesAprovacao;
    }

    public void setColLimitesAprovacao(final Collection<LimiteAprovacaoDTO> colLimitesAprovacao) {
        this.colLimitesAprovacao = colLimitesAprovacao;
    }

    public String getAlcadaPrimeiroNivel() {
        return alcadaPrimeiroNivel;
    }

    public void setAlcadaPrimeiroNivel(final String alcadaPrimeiroNivel) {
        this.alcadaPrimeiroNivel = alcadaPrimeiroNivel;
    }

}
