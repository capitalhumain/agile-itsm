package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilStrings;

public class ContratoBIDTO extends BaseEntity {

    private static final long serialVersionUID = -5899256214152706919L;

    private String nome;

    private Integer idContrato;

    private Integer idCliente;

    private String numero;

    private String objeto;

    private java.sql.Date dataContrato;

    private java.sql.Date dataFimContrato;

    private Double valorEstimado;

    private String tipoTempoEstimado;

    private Integer tempoEstimado;

    private String tipo;

    private String situacao;

    private Integer idMoeda;

    private Integer idFluxo;

    private Double cotacaoMoeda;

    private Integer idFornecedor;

    private String deleted;

    private Integer idGrupoSolicitante;

    private java.sql.Date criadoEm;
    private String criadoPor;
    private java.sql.Date modificadoEm;
    private String modificadoPor;

    private Integer idConexaoBI;
    private Integer idSuperintendente;

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(final Integer parm) {
        idCliente = parm;
    }

    public String getNumero() {
        return numero;
    }

    public String getNumeroHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(numero));
    }

    public void setNumero(final String parm) {
        numero = parm;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(final String parm) {
        objeto = parm;
    }

    public java.sql.Date getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(final java.sql.Date parm) {
        dataContrato = parm;
    }

    public Double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(final Double parm) {
        valorEstimado = parm;
    }

    public String getTipoTempoEstimado() {
        return tipoTempoEstimado;
    }

    public void setTipoTempoEstimado(final String parm) {
        tipoTempoEstimado = parm;
    }

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(final Integer parm) {
        tempoEstimado = parm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String parm) {
        tipo = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public Integer getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(final Integer idMoeda) {
        this.idMoeda = idMoeda;
    }

    public Integer getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(final Integer idFluxo) {
        this.idFluxo = idFluxo;
    }

    public Double getCotacaoMoeda() {
        return cotacaoMoeda;
    }

    public void setCotacaoMoeda(final Double cotacaoMoeda) {
        this.cotacaoMoeda = cotacaoMoeda;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    public Integer getIdGrupoSolicitante() {
        return idGrupoSolicitante;
    }

    public void setIdGrupoSolicitante(final Integer idGrupoSolicitante) {
        this.idGrupoSolicitante = idGrupoSolicitante;
    }

    /**
     * @return the dataFimContrato
     */
    public java.sql.Date getDataFimContrato() {
        return dataFimContrato;
    }

    /**
     * @param dataFimContrato
     *            the dataFimContrato to set
     */
    public void setDataFimContrato(final java.sql.Date dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
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

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (idContrato == null ? 0 : idContrato.hashCode());
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
        final ContratoBIDTO other = (ContratoBIDTO) obj;
        if (idContrato == null) {
            if (other.idContrato != null) {
                return false;
            }
        } else if (!idContrato.equals(other.idContrato)) {
            return false;
        }
        return true;
    }

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

    public Integer getIdSuperintendente() {
        return idSuperintendente;
    }

    public void setIdSuperintendente(final Integer idSuperintendente) {
        this.idSuperintendente = idSuperintendente;
    }

}
