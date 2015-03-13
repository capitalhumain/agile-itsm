package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.DateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RequisicaoMudancaResponsavel")
public class RequisicaoMudancaResponsavelDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Integer idRequisicaoMudanca;
    private Integer idResponsavel;

    private Integer idRequisicaoMudancaResp;
    private String nomeResponsavel;
    private String papelResponsavel;
    private String telResponsavel;
    private String emailResponsavel;
    private String nomeCargo;

    @XmlElement(name = "dataFim")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataFim;

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public Integer getIdRequisicaoMudancaResp() {
        return idRequisicaoMudancaResp;
    }

    public void setIdRequisicaoMudancaResp(final Integer idRequisicaoMudancaResp) {
        this.idRequisicaoMudancaResp = idRequisicaoMudancaResp;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getPapelResponsavel() {
        return papelResponsavel;
    }

    public void setPapelResponsavel(final String papelResponsavel) {
        this.papelResponsavel = papelResponsavel;
    }

    public String getTelResponsavel() {
        return telResponsavel;
    }

    public void setTelResponsavel(final String telResponsavel) {
        this.telResponsavel = telResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(final String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(final String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
