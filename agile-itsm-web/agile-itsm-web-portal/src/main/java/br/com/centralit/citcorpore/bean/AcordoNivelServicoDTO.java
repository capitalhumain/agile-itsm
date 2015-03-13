package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class AcordoNivelServicoDTO extends BaseEntity {

    private static final long serialVersionUID = -8242462699115061652L;

    private Integer idAcordoNivelServico;
    private Integer idServicoContrato;
    private Integer idPrioridadePadrao;
    private String situacao;
    private String tituloSLA;
    private Double disponibilidade;
    private String descricaoSLA;
    private String escopoSLA;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private java.sql.Date avaliarEm;
    private String tipo;
    private String permiteMudarImpUrg;
    private String impacto;
    private String urgencia;
    private String contatos;
    private String deleted;

    private Double valorLimite;
    private String detalheGlosa;
    private String detalheLimiteGlosa;
    private String unidadeValorLimite;

    private Double tempoAuto;
    private Integer idPrioridadeAuto1;
    private Integer idGrupo1;

    private Integer idFormula;

    private Timestamp criadoEm;
    private String criadoPor;
    private Timestamp modificadoEm;
    private String modificadoPor;

    private Integer[] hhCaptura = new Integer[5];
    private Integer[] hhResolucao = new Integer[5];
    private Integer[] mmCaptura = new Integer[5];
    private Integer[] mmResolucao = new Integer[5];

    private List<PrioridadeAcordoNivelServicoDTO> listaPrioridadeUnidade;
    private List<PrioridadeServicoUsuarioDTO> listaPrioridadeUsuario;
    private List<SlaRequisitoSlaDTO> listaslaRequisitoSlaDTO;
    private List<RevisarSlaDTO> listaRevisarSlaDTO;

    private Integer idEmail;

    public Integer getIdAcordoNivelServico() {
        return idAcordoNivelServico;
    }

    public void setIdAcordoNivelServico(final Integer parm) {
        idAcordoNivelServico = parm;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer parm) {
        idServicoContrato = parm;
    }

    public Integer getIdPrioridadePadrao() {
        return idPrioridadePadrao;
    }

    public void setIdPrioridadePadrao(final Integer parm) {
        idPrioridadePadrao = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getTituloSLA() {
        return tituloSLA;
    }

    public void setTituloSLA(final String parm) {
        tituloSLA = parm;
    }

    public Double getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(final Double parm) {
        disponibilidade = parm;
    }

    public String getDescricaoSLA() {
        return descricaoSLA;
    }

    public void setDescricaoSLA(final String parm) {
        descricaoSLA = parm;
    }

    public String getEscopoSLA() {
        return escopoSLA;
    }

    public void setEscopoSLA(final String parm) {
        escopoSLA = parm;
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

    public java.sql.Date getAvaliarEm() {
        return avaliarEm;
    }

    public void setAvaliarEm(final java.sql.Date parm) {
        avaliarEm = parm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String parm) {
        tipo = parm;
    }

    public String getDeleted() {
        if (deleted == null || deleted.trim().equalsIgnoreCase("")) {
            return "N";
        }
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    public Double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(final Double valorLimite) {
        this.valorLimite = valorLimite;
    }

    public String getDetalheGlosa() {
        return detalheGlosa;
    }

    public void setDetalheGlosa(final String detalheGlosa) {
        this.detalheGlosa = detalheGlosa;
    }

    public String getDetalheLimiteGlosa() {
        return detalheLimiteGlosa;
    }

    public void setDetalheLimiteGlosa(final String detalheLimiteGlosa) {
        this.detalheLimiteGlosa = detalheLimiteGlosa;
    }

    public String getUnidadeValorLimite() {
        return unidadeValorLimite;
    }

    public void setUnidadeValorLimite(final String unidadeValorLimite) {
        this.unidadeValorLimite = unidadeValorLimite;
    }

    public String getPermiteMudarImpUrg() {
        return permiteMudarImpUrg;
    }

    public void setPermiteMudarImpUrg(final String permiteMudarImpUrg) {
        this.permiteMudarImpUrg = permiteMudarImpUrg;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(final String impacto) {
        this.impacto = impacto;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public Integer getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(final Integer idFormula) {
        this.idFormula = idFormula;
    }

    public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(final Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(final String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Timestamp getModificadoEm() {
        return modificadoEm;
    }

    public void setModificadoEm(final Timestamp modificadoEm) {
        this.modificadoEm = modificadoEm;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(final String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Double getTempoAuto() {
        return tempoAuto;
    }

    public void setTempoAuto(final Double tempoAuto) {
        this.tempoAuto = tempoAuto;
    }

    public Integer getIdPrioridadeAuto1() {
        return idPrioridadeAuto1;
    }

    public void setIdPrioridadeAuto1(final Integer idPrioridadeAuto1) {
        this.idPrioridadeAuto1 = idPrioridadeAuto1;
    }

    public Integer getIdGrupo1() {
        return idGrupo1;
    }

    public void setIdGrupo1(final Integer idGrupo1) {
        this.idGrupo1 = idGrupo1;
    }

    public Integer[] getHhCaptura() {
        return hhCaptura;
    }

    public void setHhCaptura(final Integer[] hhCaptura) {
        this.hhCaptura = hhCaptura;
    }

    public Integer[] getHhResolucao() {
        return hhResolucao;
    }

    public void setHhResolucao(final Integer[] hhResolucao) {
        this.hhResolucao = hhResolucao;
    }

    public Integer[] getMmCaptura() {
        return mmCaptura;
    }

    public void setMmCaptura(final Integer[] mmCaptura) {
        this.mmCaptura = mmCaptura;
    }

    public Integer[] getMmResolucao() {
        return mmResolucao;
    }

    public void setMmResolucao(final Integer[] mmResolucao) {
        this.mmResolucao = mmResolucao;
    }

    public List<PrioridadeAcordoNivelServicoDTO> getListaPrioridadeUnidade() {
        return listaPrioridadeUnidade;
    }

    public void setListaPrioridadeUnidade(final List<PrioridadeAcordoNivelServicoDTO> listaPrioridadeUnidade) {
        this.listaPrioridadeUnidade = listaPrioridadeUnidade;
    }

    public List<PrioridadeServicoUsuarioDTO> getListaPrioridadeUsuario() {
        return listaPrioridadeUsuario;
    }

    public void setListaPrioridadeUsuario(final List<PrioridadeServicoUsuarioDTO> listaPrioridadeUsuario) {
        this.listaPrioridadeUsuario = listaPrioridadeUsuario;
    }

    public List<SlaRequisitoSlaDTO> getListaSlaRequisitoSlaDTO() {
        return listaslaRequisitoSlaDTO;
    }

    public void setListaSlaRequisitoSlaDTO(final List<SlaRequisitoSlaDTO> listaslaRequisitoSlaDTO) {
        this.listaslaRequisitoSlaDTO = listaslaRequisitoSlaDTO;
    }

    public List<RevisarSlaDTO> getListaRevisarSlaDTO() {
        return listaRevisarSlaDTO;
    }

    public void setListaRevisarSlaDTO(final List<RevisarSlaDTO> listaRevisarSlaDTO) {
        this.listaRevisarSlaDTO = listaRevisarSlaDTO;
    }

    public String getContatos() {
        return contatos;
    }

    public void setContatos(final String contatos) {
        this.contatos = contatos;
    }

    public Integer getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(final Integer idEmail) {
        this.idEmail = idEmail;
    }

}
