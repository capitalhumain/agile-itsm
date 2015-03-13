package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class HistoricoServicoDTO extends BaseEntity {

    private Integer idHistoricoServico;
    private Integer idServico;
    private Integer idCategoriaServico;
    private Integer idSituacaoServico;
    private Integer idTipoServico;
    private Integer idImportanciaNegocio;
    private Integer idEmpresa;
    private Integer idTipoEventoServico;
    private Integer idTipoDemandaServico;
    private Integer idLocalExecucaoServico;
    private String nomeServico;
    private String detalheServico;
    private String objetivo;
    private String passosServico;
    private java.sql.Date dataInicio;
    private String linkProcesso;
    private String descricaoProcesso;
    private String tipoDescProcess;
    private String dispPortal;
    private String quadroOrientPortal;
    private String siglaAbrev;
    private String deleted;
    private Integer idBaseconhecimento;

    private Integer idTemplateSolicitacao;
    private Integer idTemplateAcompanhamento;

    private String nomeCategoriaServico;
    private String nomeTipoServico;

    private java.sql.Date modificadoEm;
    private String modificadoPor;
    private String conteudodados;
    private java.sql.Date criadoEm;
    private String criadoPor;

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer parm) {
        idServico = parm;
    }

    public Integer getIdCategoriaServico() {
        return idCategoriaServico;
    }

    public void setIdCategoriaServico(final Integer parm) {
        idCategoriaServico = parm;
    }

    public Integer getIdSituacaoServico() {
        return idSituacaoServico;
    }

    public void setIdSituacaoServico(final Integer parm) {
        idSituacaoServico = parm;
    }

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(final Integer parm) {
        idTipoServico = parm;
    }

    public Integer getIdImportanciaNegocio() {
        return idImportanciaNegocio;
    }

    public void setIdImportanciaNegocio(final Integer parm) {
        idImportanciaNegocio = parm;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer parm) {
        idEmpresa = parm;
    }

    public Integer getIdTipoEventoServico() {
        return idTipoEventoServico;
    }

    public void setIdTipoEventoServico(final Integer parm) {
        idTipoEventoServico = parm;
    }

    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public void setIdTipoDemandaServico(final Integer parm) {
        idTipoDemandaServico = parm;
    }

    public Integer getIdLocalExecucaoServico() {
        return idLocalExecucaoServico;
    }

    public void setIdLocalExecucaoServico(final Integer parm) {
        idLocalExecucaoServico = parm;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String parm) {
        nomeServico = parm;
    }

    public String getDetalheServico() {
        return detalheServico;
    }

    public void setDetalheServico(final String parm) {
        detalheServico = parm;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(final String parm) {
        objetivo = parm;
    }

    public String getPassosServico() {
        return passosServico;
    }

    public void setPassosServico(final String parm) {
        passosServico = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
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

    public String getDispPortal() {
        return dispPortal;
    }

    public void setDispPortal(final String parm) {
        dispPortal = parm;
    }

    public String getQuadroOrientPortal() {
        return quadroOrientPortal;
    }

    public void setQuadroOrientPortal(final String parm) {
        quadroOrientPortal = parm;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

    public String getSiglaAbrev() {
        return siglaAbrev;
    }

    public void setSiglaAbrev(final String siglaAbrev) {
        this.siglaAbrev = siglaAbrev;
    }

    public Integer getIdBaseconhecimento() {
        return idBaseconhecimento;
    }

    public void setIdBaseconhecimento(final Integer idBaseconhecimento) {
        this.idBaseconhecimento = idBaseconhecimento;
    }

    public Integer getIdTemplateSolicitacao() {
        return idTemplateSolicitacao;
    }

    public void setIdTemplateSolicitacao(final Integer idTemplateSolicitacao) {
        this.idTemplateSolicitacao = idTemplateSolicitacao;
    }

    public Integer getIdTemplateAcompanhamento() {
        return idTemplateAcompanhamento;
    }

    public void setIdTemplateAcompanhamento(final Integer idTemplateAcompanhamento) {
        this.idTemplateAcompanhamento = idTemplateAcompanhamento;
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
        final HistoricoServicoDTO other = (HistoricoServicoDTO) obj;
        if (idServico == null) {
            if (other.idServico != null) {
                return false;
            }
        } else if (!idServico.equals(other.idServico)) {
            return false;
        }
        return true;
    }

    public String getNomeCategoriaServico() {
        return nomeCategoriaServico;
    }

    public String getNomeTipoServico() {
        return nomeTipoServico;
    }

    public void setNomeTipoServico(final String nomeTipoServico) {
        this.nomeTipoServico = nomeTipoServico;
    }

    public void setNomeCategoriaServico(final String nomeCategoriaServico) {
        this.nomeCategoriaServico = nomeCategoriaServico;
    }

    public java.sql.Date getModificadoEm() {
        return modificadoEm;
    }

    public void setModificadoEm(final java.sql.Date modificadoEm) {
        this.modificadoEm = modificadoEm;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(final String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getConteudodados() {
        return conteudodados;
    }

    public void setConteudodados(final String conteudodados) {
        this.conteudodados = conteudodados;
    }

    public Integer getIdHistoricoServico() {
        return idHistoricoServico;
    }

    public void setIdHistoricoServico(final Integer idHistoricoServico) {
        this.idHistoricoServico = idHistoricoServico;
    }

    public java.sql.Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(final java.sql.Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(final String criadoPor) {
        this.criadoPor = criadoPor;
    }

}
