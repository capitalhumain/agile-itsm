package br.com.centralit.citcorpore.bean;

import java.util.HashMap;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.MotivoRejeicaoAlcada;

public class AlcadaProcessoNegocioDTO extends BaseEntity {

    private CentroResultadoDTO centroResultadoDto;
    private EmpregadoDTO empregadoDto;
    private List<ProcessoNegocioDTO> processosNegocio;
    private HashMap<String, GrupoEmpregadoDTO> mapGruposEmpregado;

    private boolean alcadaRejeitada;
    private boolean delegacao;
    private AlcadaProcessoNegocioDTO alcadaOrigemDto;
    private MotivoRejeicaoAlcada motivoRejeicao;
    private String complementoRejeicao;

    private String chaveOrdenacao;

    public HashMap<String, GrupoEmpregadoDTO> getMapGruposEmpregado() {
        return mapGruposEmpregado;
    }

    public void setMapGruposEmpregado(final HashMap<String, GrupoEmpregadoDTO> mapGruposEmpregado) {
        this.mapGruposEmpregado = mapGruposEmpregado;
    }

    public CentroResultadoDTO getCentroResultadoDto() {
        return centroResultadoDto;
    }

    public void setCentroResultadoDto(final CentroResultadoDTO centroResultadoDto) {
        this.centroResultadoDto = centroResultadoDto;
    }

    public EmpregadoDTO getEmpregadoDto() {
        return empregadoDto;
    }

    public void setEmpregadoDto(final EmpregadoDTO empregadoDto) {
        this.empregadoDto = empregadoDto;
    }

    public List<ProcessoNegocioDTO> getProcessosNegocio() {
        return processosNegocio;
    }

    public void setProcessosNegocio(final List<ProcessoNegocioDTO> processosNegocio) {
        this.processosNegocio = processosNegocio;
    }

    public MotivoRejeicaoAlcada getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(final MotivoRejeicaoAlcada motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
    }

    public boolean isDelegacao() {
        return delegacao;
    }

    public void setDelegacao(final boolean delegacao) {
        this.delegacao = delegacao;
    }

    public AlcadaProcessoNegocioDTO getAlcadaOrigemDto() {
        return alcadaOrigemDto;
    }

    public void setAlcadaOrigemDto(final AlcadaProcessoNegocioDTO alcadaOrigemDto) {
        this.alcadaOrigemDto = alcadaOrigemDto;
    }

    public boolean isAlcadaRejeitada() {
        return alcadaRejeitada;
    }

    public void setAlcadaRejeitada(final boolean alcadaRejeitada) {
        this.alcadaRejeitada = alcadaRejeitada;
    }

    public String getComplementoRejeicao() {
        return complementoRejeicao;
    }

    public void setComplementoRejeicao(final String complementoRejeicao) {
        this.complementoRejeicao = complementoRejeicao;
    }

    public String getChaveOrdenacao() {
        return chaveOrdenacao;
    }

    public void setChaveOrdenacao(final String chaveOrdenacao) {
        this.chaveOrdenacao = chaveOrdenacao;
    }

    public int recuperaHierarquiaNivelAutoridade() {
        int result = 0;
        if (this.getProcessosNegocio() != null) {
            if (this.getProcessosNegocio().get(0).getNivelAutoridadeDto() != null) {
                result = this.getProcessosNegocio().get(0).getNivelAutoridadeDto().getHierarquia();
            }
        }
        return result;
    }

}
