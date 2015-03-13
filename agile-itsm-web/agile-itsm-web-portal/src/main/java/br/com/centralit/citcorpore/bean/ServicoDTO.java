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

import java.sql.Date;
import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class ServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String conteudodados;
    private Date dataFim;
    private Date dataInicio;
    private String deleted;
    private String descricaoProcesso;
    private String detalheServico;
    private String dispPortal;
    private Integer idBaseconhecimento;
    private Integer idCategoriaServico;
    private Integer idEmpresa;
    private Integer idImportanciaNegocio;
    private Integer idLocalExecucaoServico;
    private Integer idServico;
    private Integer idContrato;
    private Integer idSituacaoServico;
    private Integer idTemplateAcompanhamento;
    private Integer idTemplateSolicitacao;
    private Integer idTipoDemandaServico;
    private Integer idTipoEventoServico;
    private Integer idTipoServico;
    private String linkProcesso;
    private Timestamp modificadoEm;
    private String modificadoPor;
    private String nomeCategoriaServico;
    private String nomeServico;
    private String nomeTipoServico;
    private String objetivo;
    private String passosServico;
    private String quadroOrientPortal;
    private String siglaAbrev;
    private String tipoDescProcess;

    private Integer idSolicitacao;
    private String complexidade;
    private String nomeTipoDemandaServico;
    private String custoAtividade;
    private String slaAtendido;
    private String nomeSolucionador;

    private String tempoDecorrido;
    private String situacao;

    private Integer tempoAtendimentoHH;
    private Integer tempoAtendimentoMM;
    private Double valorServico;
    private Double valorTotalServico;

    /**
     * Valor Top List
     *
     * @author thyen.chang
     */
    private Integer topList;

    public Integer getTopList() {
        return topList;
    }

    public void setTopList(final Integer topList) {
        this.topList = topList;
    }

    public String getSlaAtendido() {
        return slaAtendido;
    }

    public void setSlaAtendido(final String slaAtendido) {
        this.slaAtendido = slaAtendido;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(final Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(final String complexidade) {
        this.complexidade = complexidade;
    }

    public String getNomeTipoDemandaServico() {
        return nomeTipoDemandaServico;
    }

    public void setNomeTipoDemandaServico(final String nomeTipoDemandaServico) {
        this.nomeTipoDemandaServico = nomeTipoDemandaServico;
    }

    public String getCustoAtividade() {
        return custoAtividade;
    }

    public void setCustoAtividade(final String custoAtividade) {
        this.custoAtividade = custoAtividade;
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
        final ServicoDTO other = (ServicoDTO) obj;
        if (idServico == null) {
            if (other.idServico != null) {
                return false;
            }
        } else if (!idServico.equals(other.idServico)) {
            return false;
        }
        return true;
    }

    public String getConteudodados() {
        return conteudodados;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public String getDeleted() {
        return deleted;
    }

    public String getDescricaoProcesso() {
        return descricaoProcesso;
    }

    public String getDetalheServico() {
        return detalheServico;
    }

    public String getDispPortal() {
        return dispPortal;
    }

    public Integer getIdBaseconhecimento() {
        return idBaseconhecimento;
    }

    public Integer getIdCategoriaServico() {
        return idCategoriaServico;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public Integer getIdImportanciaNegocio() {
        return idImportanciaNegocio;
    }

    public Integer getIdLocalExecucaoServico() {
        return idLocalExecucaoServico;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public Integer getIdSituacaoServico() {
        return idSituacaoServico;
    }

    public Integer getIdTemplateAcompanhamento() {
        return idTemplateAcompanhamento;
    }

    public Integer getIdTemplateSolicitacao() {
        return idTemplateSolicitacao;
    }

    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public Integer getIdTipoEventoServico() {
        return idTipoEventoServico;
    }

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public String getLinkProcesso() {
        return linkProcesso;
    }

    public Timestamp getModificadoEm() {
        return modificadoEm;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public String getNomeCategoriaServico() {
        return nomeCategoriaServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public String getNomeTipoServico() {
        return nomeTipoServico;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getPassosServico() {
        return passosServico;
    }

    public String getQuadroOrientPortal() {
        return quadroOrientPortal;
    }

    public String getSiglaAbrev() {
        return siglaAbrev;
    }

    public String getTipoDescProcess() {
        return tipoDescProcess;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (idServico == null ? 0 : idServico.hashCode());
        return result;
    }

    public void setConteudodados(final String conteudodados) {
        this.conteudodados = conteudodados;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

    public void setDescricaoProcesso(final String parm) {
        descricaoProcesso = parm;
    }

    public void setDetalheServico(final String parm) {
        detalheServico = parm;
    }

    public void setDispPortal(final String parm) {
        dispPortal = parm;
    }

    public void setIdBaseconhecimento(final Integer idBaseconhecimento) {
        this.idBaseconhecimento = idBaseconhecimento;
    }

    public void setIdCategoriaServico(final Integer parm) {
        idCategoriaServico = parm;
    }

    public void setIdEmpresa(final Integer parm) {
        idEmpresa = parm;
    }

    public void setIdImportanciaNegocio(final Integer parm) {
        idImportanciaNegocio = parm;
    }

    public void setIdLocalExecucaoServico(final Integer parm) {
        idLocalExecucaoServico = parm;
    }

    public void setIdServico(final Integer parm) {
        idServico = parm;
    }

    public void setIdSituacaoServico(final Integer parm) {
        idSituacaoServico = parm;
    }

    public void setIdTemplateAcompanhamento(final Integer idTemplateAcompanhamento) {
        this.idTemplateAcompanhamento = idTemplateAcompanhamento;
    }

    public void setIdTemplateSolicitacao(final Integer idTemplateSolicitacao) {
        this.idTemplateSolicitacao = idTemplateSolicitacao;
    }

    public void setIdTipoDemandaServico(final Integer parm) {
        idTipoDemandaServico = parm;
    }

    public void setIdTipoEventoServico(final Integer parm) {
        idTipoEventoServico = parm;
    }

    public void setIdTipoServico(final Integer parm) {
        idTipoServico = parm;
    }

    public void setLinkProcesso(final String parm) {
        linkProcesso = parm;
    }

    public void setModificadoEm(final Timestamp modificadoEm) {
        this.modificadoEm = modificadoEm;
    }

    public void setModificadoPor(final String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public void setNomeCategoriaServico(final String nomeCategoriaServico) {
        this.nomeCategoriaServico = nomeCategoriaServico;
    }

    public void setNomeServico(final String parm) {
        nomeServico = parm;
    }

    public void setNomeTipoServico(final String nomeTipoServico) {
        this.nomeTipoServico = nomeTipoServico;
    }

    public void setObjetivo(final String parm) {
        objetivo = parm;
    }

    public void setPassosServico(final String parm) {
        passosServico = parm;
    }

    public void setQuadroOrientPortal(final String parm) {
        quadroOrientPortal = parm;
    }

    public void setSiglaAbrev(final String siglaAbrev) {
        this.siglaAbrev = siglaAbrev;
    }

    public void setTipoDescProcess(final String parm) {
        tipoDescProcess = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getNomeSolucionador() {
        return nomeSolucionador;
    }

    public void setNomeSolucionador(final String nomeSolucionador) {
        this.nomeSolucionador = nomeSolucionador;
    }

    public Integer getTempoAtendimentoHH() {
        return tempoAtendimentoHH;
    }

    public void setTempoAtendimentoHH(final Integer tempoAtendimentoHH) {
        this.tempoAtendimentoHH = tempoAtendimentoHH;
    }

    public Integer getTempoAtendimentoMM() {
        return tempoAtendimentoMM;
    }

    public void setTempoAtendimentoMM(final Integer tempoAtendimentoMM) {
        this.tempoAtendimentoMM = tempoAtendimentoMM;
    }

    public String getTempoDecorrido() {
        return tempoDecorrido;
    }

    public void setTempoDecorrido(final String tempoDecorrido) {
        this.tempoDecorrido = tempoDecorrido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(final Double valorServico) {
        this.valorServico = valorServico;
    }

    public Double getValorTotalServico() {
        return valorTotalServico;
    }

    public void setValorTotalServico(final Double valorTotalServico) {
        this.valorTotalServico = valorTotalServico;
    }

}
