/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
public class GanttSolicitacaoServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 4420655477272385856L;

    private String name;

    private String desc;

    private ValueGanttDTO values;

    private String tipoDemandaServico;

    private String idGruposSeguranca;
    private String apenasMeus;

    private Date dataInicio;
    private Date dataFim;

    private String situacao;

    /**
     * @return valor do atributo name.
     */
    public String getName() {
        return name;
    }

    /**
     * Define valor do atributo name.
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return valor do atributo desc.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Define valor do atributo desc.
     *
     * @param desc
     */
    public void setDesc(final String desc) {
        this.desc = desc;
    }

    /**
     * @return valor do atributo values.
     */
    public ValueGanttDTO getValues() {
        return values;
    }

    /**
     * Define valor do atributo values.
     *
     * @param values
     */
    public void setValues(final ValueGanttDTO values) {
        this.values = values;
    }

    /**
     * @return valor do atributo tipoDemandaServico.
     */
    public String getTipoDemandaServico() {
        return tipoDemandaServico;
    }

    /**
     * Define valor do atributo tipoDemandaServico.
     *
     * @param tipoDemandaServico
     */
    public void setTipoDemandaServico(final String tipoDemandaServico) {
        this.tipoDemandaServico = tipoDemandaServico;
    }

    /**
     * @return valor do atributo idGruposSeguranca.
     */
    public String getIdGruposSeguranca() {
        return idGruposSeguranca;
    }

    /**
     * Define valor do atributo idGruposSeguranca.
     *
     * @param idGruposSeguranca
     */
    public void setIdGruposSeguranca(final String idGruposSeguranca) {
        this.idGruposSeguranca = idGruposSeguranca;
    }

    public String getApenasMeus() {
        return apenasMeus;
    }

    public void setApenasMeus(final String apenasMeus) {
        this.apenasMeus = apenasMeus;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

}
