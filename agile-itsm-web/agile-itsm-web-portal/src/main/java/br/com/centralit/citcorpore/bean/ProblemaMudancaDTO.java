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
@XmlRootElement(name = "ProblemaMudanca")
public class ProblemaMudancaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idProblemaMudanca;
    private Integer idProblema;
    private Integer idRequisicaoMudanca;

    private String titulo;
    private String status;

    @XmlElement(name = "dataFim")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataFim;

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getIdProblemaMudanca() {
        return idProblemaMudanca;
    }

    public void setIdProblemaMudanca(final Integer parm) {
        idProblemaMudanca = parm;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer parm) {
        idProblema = parm;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer parm) {
        idRequisicaoMudanca = parm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

}
