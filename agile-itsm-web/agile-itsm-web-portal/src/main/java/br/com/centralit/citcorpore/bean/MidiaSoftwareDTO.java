package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class MidiaSoftwareDTO extends BaseEntity {

    private static final long serialVersionUID = -6560486489318894832L;

    private Integer idMidiaSoftware;
    private String nome;
    private String endFisico;
    private String endLogico;
    private String versao;
    private Integer licencas;
    private Integer idMidia;
    private Integer idTipoSoftware;
    private Date dataInicio;
    private Date dataFim;
    private String midiaSoftwareChaveSerializada;
    private List<MidiaSoftwareChaveDTO> midiaSoftwareChaves;
    private Integer sequenciaLiberacao;

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

    public Integer getIdMidiaSoftware() {
        return idMidiaSoftware;
    }

    public void setIdMidiaSoftware(final Integer idMidiaSoftware) {
        this.idMidiaSoftware = idMidiaSoftware;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getEndFisico() {
        return endFisico;
    }

    public void setEndFisico(final String endFisico) {
        this.endFisico = endFisico;
    }

    public String getEndLogico() {
        return endLogico;
    }

    public void setEndLogico(final String endLogico) {
        this.endLogico = endLogico;
    }

    public Integer getLicencas() {
        return licencas;
    }

    public void setLicencas(final Integer licencas) {
        this.licencas = licencas;
    }

    public Integer getIdMidia() {
        return idMidia;
    }

    public void setIdMidia(final Integer idMidia) {
        this.idMidia = idMidia;
    }

    public Integer getIdTipoSoftware() {
        return idTipoSoftware;
    }

    public void setIdTipoSoftware(final Integer idTipoSoftware) {
        this.idTipoSoftware = idTipoSoftware;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public String getMidiaSoftwareChaveSerializada() {
        return midiaSoftwareChaveSerializada;
    }

    public void setMidiaSoftwareChaveSerializada(final String midiaSoftwareChaveSerializada) {
        this.midiaSoftwareChaveSerializada = midiaSoftwareChaveSerializada;
    }

    public List<MidiaSoftwareChaveDTO> getMidiaSoftwareChaves() {
        return midiaSoftwareChaves;
    }

    public void setMidiaSoftwareChaves(final List<MidiaSoftwareChaveDTO> midiaSoftwareChaves) {
        this.midiaSoftwareChaves = midiaSoftwareChaves;
    }

    /**
     * @return the sequenciaLiberacao
     */
    public Integer getSequenciaLiberacao() {
        return sequenciaLiberacao;
    }

    /**
     * @param sequenciaLiberacao
     *            the sequenciaLiberacao to set
     */
    public void setSequenciaLiberacao(final Integer sequenciaLiberacao) {
        this.sequenciaLiberacao = sequenciaLiberacao;
    }

}
