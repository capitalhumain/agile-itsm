package br.com.centralit.citcorpore.bean;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class AlcadaDTO extends BaseEntity {

    private Integer idAlcada;
    private String nomeAlcada;
    private String tipoAlcada;
    private String situacao;
    private ArrayList<LimiteAlcadaDTO> listaDeLimites;
    private String listLimites;

    private Collection<LimiteAlcadaDTO> colLimites;

    private Collection<EmpregadoDTO> colResponsaveis;

    public Integer getIdAlcada() {
        return idAlcada;
    }

    public void setIdAlcada(final Integer parm) {
        idAlcada = parm;
    }

    public String getNomeAlcada() {
        return nomeAlcada;
    }

    public void setNomeAlcada(final String parm) {
        nomeAlcada = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getTipoAlcada() {
        return tipoAlcada;
    }

    public void setTipoAlcada(final String tipoAlcada) {
        this.tipoAlcada = tipoAlcada;
    }

    public Collection<EmpregadoDTO> getColResponsaveis() {
        return colResponsaveis;
    }

    public Collection<LimiteAlcadaDTO> getColLimites() {
        return colLimites;
    }

    public void setColLimites(final Collection<LimiteAlcadaDTO> colLimites) {
        this.colLimites = colLimites;
    }

    public void setColResponsaveis(final Collection<EmpregadoDTO> colResponsaveis) {
        this.colResponsaveis = colResponsaveis;
    }

    public ArrayList<LimiteAlcadaDTO> getListaDeLimites() {
        return listaDeLimites;
    }

    public void setListaDeLimites(final ArrayList<LimiteAlcadaDTO> listaDeLimites) {
        this.listaDeLimites = listaDeLimites;
    }

    public String getListLimites() {
        return listLimites;
    }

    public void setListLimites(final String listLimites) {
        this.listLimites = listLimites;
    }

    public boolean mesmosResponsaveis(final Collection<EmpregadoDTO> colComparacao) {
        if (this.getColResponsaveis() != null && colComparacao == null) {
            return false;
        }
        if (this.getColResponsaveis() == null && colComparacao != null) {
            return false;
        }
        if (this.getColResponsaveis().size() != colComparacao.size()) {
            return false;
        }

        int i = 0;
        for (final EmpregadoDTO empregadoCompDto : colComparacao) {
            for (final EmpregadoDTO empregadoDto : this.getColResponsaveis()) {
                if (empregadoCompDto.getIdEmpregado().intValue() == empregadoDto.getIdEmpregado().intValue()) {
                    i++;
                }
            }
        }
        if (i != colComparacao.size()) {
            return false;
        }

        i = 0;
        for (final EmpregadoDTO empregadoDto : this.getColResponsaveis()) {
            for (final EmpregadoDTO empregadoCompDto : colComparacao) {
                if (empregadoCompDto.getIdEmpregado().intValue() != empregadoDto.getIdEmpregado().intValue()) {
                    i++;
                }
            }
        }
        return i == this.getColResponsaveis().size();
    }

}
