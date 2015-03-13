package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoCotacao;

public class CotacaoDTO extends BaseEntity {

    private Integer idCotacao;
    private String identificacao;
    private Integer idEmpresa;
    private Timestamp dataHoraCadastro;
    private Integer idResponsavel;
    private String observacoes;
    private String situacao;
    private Date dataFinalPrevista;

    private Date dataInicialRequisicao;
    private Date dataFinalRequisicao;
    private Integer idCentroCusto;
    private Integer idProjeto;

    private Integer idItemCotacao;
    private Integer idFornecedor;
    private Integer idItemColeta;
    private Integer idFornecedorColeta;

    private UsuarioDTO usuarioDto;
    private String situacaoStr;

    private String colCriterios_Serialize;

    public Integer getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(final Integer parm) {
        idCotacao = parm;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer parm) {
        idEmpresa = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    public Date getDataInicialRequisicao() {
        return dataInicialRequisicao;
    }

    public void setDataInicialRequisicao(final Date dataInicialRequisicao) {
        this.dataInicialRequisicao = dataInicialRequisicao;
    }

    public Date getDataFinalRequisicao() {
        return dataFinalRequisicao;
    }

    public void setDataFinalRequisicao(final Date dataFinalRequisicao) {
        this.dataFinalRequisicao = dataFinalRequisicao;
    }

    public Integer getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(final Integer idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

    public Timestamp getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(final Timestamp dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Date getDataFinalPrevista() {
        return dataFinalPrevista;
    }

    public void setDataFinalPrevista(final Date dataFinalPrevista) {
        this.dataFinalPrevista = dataFinalPrevista;
    }

    public String getColCriterios_Serialize() {
        return colCriterios_Serialize;
    }

    public void setColCriterios_Serialize(final String colCriterios_Serialize) {
        this.colCriterios_Serialize = colCriterios_Serialize;
    }

    public Integer getIdItemCotacao() {
        return idItemCotacao;
    }

    public void setIdItemCotacao(final Integer idItemCotacao) {
        this.idItemCotacao = idItemCotacao;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdItemColeta() {
        return idItemColeta;
    }

    public void setIdItemColeta(final Integer idItemColeta) {
        this.idItemColeta = idItemColeta;
    }

    public Integer getIdFornecedorColeta() {
        return idFornecedorColeta;
    }

    public void setIdFornecedorColeta(final Integer idFornecedorColeta) {
        this.idFornecedorColeta = idFornecedorColeta;
    }

    public UsuarioDTO getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(final UsuarioDTO usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public String getSituacaoStr() {
        situacaoStr = "";
        if (situacao != null) {
            situacaoStr = SituacaoCotacao.valueOf(situacao).getDescricao();
        }
        return situacaoStr;
    }

    public void setSituacaoStr(final String situacaoStr) {
        this.situacaoStr = situacaoStr;
    }

}
