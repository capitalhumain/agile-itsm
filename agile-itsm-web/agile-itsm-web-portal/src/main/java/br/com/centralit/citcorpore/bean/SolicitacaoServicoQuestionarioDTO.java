package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.DateAdapter;
import br.com.citframework.util.DateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SolicitacaoServicoQuestionario")
public class SolicitacaoServicoQuestionarioDTO extends BaseEntity {

    private static final long serialVersionUID = -1383478887132161536L;

    private Integer idSolicitacaoQuestionario;
    private Integer idQuestionario;
    private Integer idSolicitacaoServico;

    @XmlElement(name = "dataQuestionario")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataQuestionario;
    private Integer idResponsavel;
    private Integer idTarefa;
    private String aba;
    private String divAtualizarCertificado;
    private String profissional;
    private String nomeQuestionario;
    private String subForm;

    private String situacao;

    private Integer idItem;
    private Collection colValores;
    private Collection colAnexos;

    @XmlElement(name = "dataHoraGrav")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Timestamp dataHoraGrav;

    private Collection colCertificados;

    private String campoSelecaoHistorico;
    private Integer idQuestaoVisHistorico;
    private Integer idContratoVisHistorico;
    private Integer idServico;

    private String respostaObrigatoria;

    private String questionarioRespondido;

    private Integer qtde;

    private String conteudoImpresso;

    private String ordemHistorico;

    private String continuarEdt;

    public String getKey() {
        if (this.getIdSolicitacaoQuestionario() == null) {
            return "NULL";
        }
        return this.getIdSolicitacaoQuestionario().toString();
    }

    public String getTabela() {
        return "solicitacaoservquestionarios".toUpperCase();
    }

    public Date getDataQuestionario() {
        return dataQuestionario;
    }

    public void setDataQuestionario(final Date dataQuestionario) {
        this.dataQuestionario = dataQuestionario;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
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

    public Collection getColAnexos() {
        return colAnexos;
    }

    public void setColAnexos(final Collection colAnexos) {
        this.colAnexos = colAnexos;
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

    public String getSubForm() {
        return subForm;
    }

    public void setSubForm(final String subForm) {
        this.subForm = subForm;
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

    public Timestamp getDataHoraGrav() {
        return dataHoraGrav;
    }

    public void setDataHoraGrav(final Timestamp dataHoraGrav) {
        this.dataHoraGrav = dataHoraGrav;
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

    public String getContinuarEdt() {
        return continuarEdt;
    }

    public void setContinuarEdt(final String continuarEdt) {
        this.continuarEdt = continuarEdt;
    }

    public Integer getIdSolicitacaoQuestionario() {
        return idSolicitacaoQuestionario;
    }

    public void setIdSolicitacaoQuestionario(final Integer idSolicitacaoQuestionario) {
        this.idSolicitacaoQuestionario = idSolicitacaoQuestionario;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    /**
     * @return the respostaObrigatoria
     */
    public String getRespostaObrigatoria() {
        return respostaObrigatoria;
    }

    /**
     * @param respostaObrigatoria
     *            the respostaObrigatoria to set
     */
    public void setRespostaObrigatoria(final String respostaObrigatoria) {
        this.respostaObrigatoria = respostaObrigatoria;
    }

    /**
     * @return the questionarioRespondido
     */
    public String getQuestionarioRespondido() {
        return questionarioRespondido;
    }

    /**
     * @param questionarioRespondido
     *            the questionarioRespondido to set
     */
    public void setQuestionarioRespondido(final String questionarioRespondido) {
        this.questionarioRespondido = questionarioRespondido;
    }

}
