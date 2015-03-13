package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioQuantitativoMudancaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Collection<RequisicaoMudancaDTO> listaQuantidadeERelacionamentos;
    private Collection<RequisicaoMudancaDTO> listaQuantidadePorImpacto;
    private Collection<RequisicaoMudancaDTO> listaQuantidadePorPeriodo;
    private Collection<RequisicaoMudancaDTO> listaQuantidadePorProprietario;
    private Collection<RequisicaoMudancaDTO> listaQuantidadePorSolicitante;
    private Collection<RequisicaoMudancaDTO> listaQuantidadePorStatus;
    private Collection<RequisicaoMudancaDTO> listaQuantidadePorUrgencia;
    private Collection<RequisicaoMudancaDTO> listaQuantidadeSemAprovacaoPorPeriodo;

    public Collection<RequisicaoMudancaDTO> getListaQuantidadeERelacionamentos() {
        return listaQuantidadeERelacionamentos;
    }

    public Collection<RequisicaoMudancaDTO> getListaQuantidadePorImpacto() {
        return listaQuantidadePorImpacto;
    }

    public Collection<RequisicaoMudancaDTO> getListaQuantidadePorPeriodo() {
        return listaQuantidadePorPeriodo;
    }

    public Collection<RequisicaoMudancaDTO> getListaQuantidadePorProprietario() {
        return listaQuantidadePorProprietario;
    }

    public Collection<RequisicaoMudancaDTO> getListaQuantidadePorSolicitante() {
        return listaQuantidadePorSolicitante;
    }

    public Collection<RequisicaoMudancaDTO> getListaQuantidadePorStatus() {
        return listaQuantidadePorStatus;
    }

    public Collection<RequisicaoMudancaDTO> getListaQuantidadePorUrgencia() {
        return listaQuantidadePorUrgencia;
    }

    public Collection<RequisicaoMudancaDTO> getListaQuantidadeSemAprovacaoPorPeriodo() {
        return listaQuantidadeSemAprovacaoPorPeriodo;
    }

    public void setListaQuantidadeERelacionamentos(final Collection<RequisicaoMudancaDTO> listaQuantidadeERelacionamentos) {
        this.listaQuantidadeERelacionamentos = listaQuantidadeERelacionamentos;
    }

    public void setListaQuantidadePorImpacto(final Collection<RequisicaoMudancaDTO> listaQuantidadePorImpacto) {
        this.listaQuantidadePorImpacto = listaQuantidadePorImpacto;
    }

    public void setListaQuantidadePorPeriodo(final Collection<RequisicaoMudancaDTO> listaQuantidadePorPeriodo) {
        this.listaQuantidadePorPeriodo = listaQuantidadePorPeriodo;
    }

    public void setListaQuantidadePorProprietario(final Collection<RequisicaoMudancaDTO> listaQuantidadePorProprietario) {
        this.listaQuantidadePorProprietario = listaQuantidadePorProprietario;
    }

    public void setListaQuantidadePorSolicitante(final Collection<RequisicaoMudancaDTO> listaQuantidadePorSolicitante) {
        this.listaQuantidadePorSolicitante = listaQuantidadePorSolicitante;
    }

    public void setListaQuantidadePorStatus(final Collection<RequisicaoMudancaDTO> listaQuantidadePorStatus) {
        this.listaQuantidadePorStatus = listaQuantidadePorStatus;
    }

    public void setListaQuantidadePorUrgencia(final Collection<RequisicaoMudancaDTO> listaQuantidadePorUrgencia) {
        this.listaQuantidadePorUrgencia = listaQuantidadePorUrgencia;
    }

    public void setListaQuantidadeSemAprovacaoPorPeriodo(final Collection<RequisicaoMudancaDTO> listaQuantidadeSemAprovacaoPorPeriodo) {
        this.listaQuantidadeSemAprovacaoPorPeriodo = listaQuantidadeSemAprovacaoPorPeriodo;
    }

}
