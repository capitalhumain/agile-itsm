package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ContratoQuestionariosDTO extends BaseEntity {

    private static final long serialVersionUID = -1383478887132161536L;

    private Integer idContratoQuestionario;
    private Integer idQuestionario;
    private Integer idContrato;
    private Date dataQuestionario;
    private Integer idProfissional;
    private Integer idEmpresa;
    private String aba;
    private String divAtualizarCertificado;
    private String profissional;
    private String nomeQuestionario;

    private String situacao;
    private String situacaoComplemento;

    private Integer idItem;
    private Collection colValores;
    private Collection colAnexos;

    private String codigoCID;
    private String descricaoCID;
    private String campoRetornoCID;

    private String produtos;
    private Integer[] idProduto;

    private String migrado;
    private Timestamp datahoragrav;

    private Collection colCertificados;

    private String campoSelecaoHistorico;
    private Integer idQuestaoVisHistorico;
    private Integer idContratoVisHistorico;

    private Integer qtde;

    private String conteudoImpresso;
    private Integer idMigracao;

    private String ordemHistorico;
    private String matricula;
    private String nomeDepartamento;
    private String sexo;
    private Double idade;

    private String continuarEdt;

    public Integer getIdMigracao() {
        return idMigracao;
    }

    public void setIdMigracao(final Integer idMigracao) {
        this.idMigracao = idMigracao;
    }

    public String getKey() {
        if (this.getIdContratoQuestionario() == null) {
            return "NULL";
        }
        return this.getIdContratoQuestionario().toString();
    }

    public String getTabela() {
        return "ContratosQuestionarios".toUpperCase();
    }

    public Date getDataQuestionario() {
        return dataQuestionario;
    }

    public void setDataQuestionario(final Date dataQuestionario) {
        this.dataQuestionario = dataQuestionario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdContratoQuestionario() {
        return idContratoQuestionario;
    }

    public void setIdContratoQuestionario(final Integer idContratoQuestionario) {
        this.idContratoQuestionario = idContratoQuestionario;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(final Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(final Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public String getAba() {
        return aba;
    }

    public void setAba(final String aba) {
        this.aba = aba;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(final Integer idItem) {
        this.idItem = idItem;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Collection getColValores() {
        return colValores;
    }

    public void setColValores(final Collection colValores) {
        this.colValores = colValores;
    }

    public String getCodigoCid10() {
        return this.getCodigoCID();
    }

    public void setCodigoCid10(final String codigoCID) {
        this.setCodigoCID(codigoCID);
    }

    public String getCodigoCID() {
        return codigoCID;
    }

    public void setCodigoCID(final String codigoCID) {
        this.codigoCID = codigoCID;
    }

    public String getDescricaoCid10() {
        return this.getDescricaoCID();
    }

    public void setDescricaoCid10(final String descricaoCID) {
        this.setDescricaoCID(descricaoCID);
    }

    public String getDescricaoCID() {
        return descricaoCID;
    }

    public void setDescricaoCID(final String descricaoCID) {
        this.descricaoCID = descricaoCID;
    }

    public String getCampoRetornoCID() {
        return campoRetornoCID;
    }

    public void setCampoRetornoCID(final String campoRetornoCID) {
        this.campoRetornoCID = campoRetornoCID;
    }

    public Collection getColAnexos() {
        return colAnexos;
    }

    public void setColAnexos(final Collection colAnexos) {
        this.colAnexos = colAnexos;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(final String produtos) {
        this.produtos = produtos;
    }

    public Integer[] getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(final Integer[] idProduto) {
        this.idProduto = idProduto;
    }

    public String getDivAtualizarCertificado() {
        return divAtualizarCertificado;
    }

    public void setDivAtualizarCertificado(final String divAtualizarCertificado) {
        this.divAtualizarCertificado = divAtualizarCertificado;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(final String profissional) {
        this.profissional = profissional;
    }

    public String getNomeQuestionario() {
        return nomeQuestionario;
    }

    public void setNomeQuestionario(final String nomeQuestionario) {
        this.nomeQuestionario = nomeQuestionario;
    }

    public Collection getColCertificados() {
        return colCertificados;
    }

    public void setColCertificados(final Collection colCertificados) {
        this.colCertificados = colCertificados;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(final Integer qtde) {
        this.qtde = qtde;
    }

    public String getCampoSelecaoHistorico() {
        return campoSelecaoHistorico;
    }

    public void setCampoSelecaoHistorico(final String campoSelecaoHistorico) {
        this.campoSelecaoHistorico = campoSelecaoHistorico;
    }

    public Integer getIdQuestaoVisHistorico() {
        return idQuestaoVisHistorico;
    }

    public void setIdQuestaoVisHistorico(final Integer idQuestaoVisHistorico) {
        this.idQuestaoVisHistorico = idQuestaoVisHistorico;
    }

    public Integer getIdContratoVisHistorico() {
        return idContratoVisHistorico;
    }

    public void setIdContratoVisHistorico(final Integer idContratoVisHistorico) {
        this.idContratoVisHistorico = idContratoVisHistorico;
    }

    public String getSituacaoComplemento() {
        return situacaoComplemento;
    }

    public void setSituacaoComplemento(final String situacaoComplemento) {
        this.situacaoComplemento = situacaoComplemento;
    }

    public String getMigrado() {
        return migrado;
    }

    public void setMigrado(final String migrado) {
        this.migrado = migrado;
    }

    public Timestamp getDatahoragrav() {
        return datahoragrav;
    }

    public void setDatahoragrav(final Timestamp datahoragrav) {
        this.datahoragrav = datahoragrav;
    }

    public String getConteudoImpresso() {
        return conteudoImpresso;
    }

    public void setConteudoImpresso(final String conteudoImpresso) {
        this.conteudoImpresso = conteudoImpresso;
    }

    public String getOrdemHistorico() {
        return ordemHistorico;
    }

    public void setOrdemHistorico(final String ordemHistorico) {
        this.ordemHistorico = ordemHistorico;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(final String matricula) {
        this.matricula = matricula;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(final String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(final Double idade) {
        this.idade = idade;
    }

    public String getContinuarEdt() {
        return continuarEdt;
    }

    public void setContinuarEdt(final String continuarEdt) {
        this.continuarEdt = continuarEdt;
    }

}
