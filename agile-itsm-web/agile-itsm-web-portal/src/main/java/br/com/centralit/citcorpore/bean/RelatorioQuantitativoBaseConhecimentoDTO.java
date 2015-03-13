package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioQuantitativoBaseConhecimentoDTO extends BaseEntity {

    private static final long serialVersionUID = -1501060076093192977L;

    private Collection<BaseConhecimentoDTO> listaBaseConhecimento;
    private Collection<ComentariosDTO> listaComentarios;
    private Collection<BaseConhecimentoDTO> listaAutores;
    private Collection<BaseConhecimentoDTO> listaAprovadores;
    private Collection<BaseConhecimentoDTO> listaPublicadosPorOrigem;
    private Collection<BaseConhecimentoDTO> listaNaoPublicadosPorOrigem;
    private Collection<BaseConhecimentoDTO> listaConhecimentoQuantitativoEmLista;

    public Collection<BaseConhecimentoDTO> getListaBaseConhecimento() {
        return listaBaseConhecimento;
    }

    public void setListaBaseConhecimento(final Collection<BaseConhecimentoDTO> listaBaseConhecimento) {
        this.listaBaseConhecimento = listaBaseConhecimento;
    }

    public Collection<ComentariosDTO> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(final Collection<ComentariosDTO> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public Collection<BaseConhecimentoDTO> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(final Collection<BaseConhecimentoDTO> listaAutores) {
        this.listaAutores = listaAutores;
    }

    public Collection<BaseConhecimentoDTO> getListaAprovadores() {
        return listaAprovadores;
    }

    public void setListaAprovadores(final Collection<BaseConhecimentoDTO> listaAprovadores) {
        this.listaAprovadores = listaAprovadores;
    }

    public Collection<BaseConhecimentoDTO> getListaPublicadosPorOrigem() {
        return listaPublicadosPorOrigem;
    }

    public void setListaPublicadosPorOrigem(final Collection<BaseConhecimentoDTO> listaPublicadosPorOrigem) {
        this.listaPublicadosPorOrigem = listaPublicadosPorOrigem;
    }

    public Collection<BaseConhecimentoDTO> getListaNaoPublicadosPorOrigem() {
        return listaNaoPublicadosPorOrigem;
    }

    public void setListaNaoPublicadosPorOrigem(final Collection<BaseConhecimentoDTO> listaNaoPublicadosPorOrigem) {
        this.listaNaoPublicadosPorOrigem = listaNaoPublicadosPorOrigem;
    }

    public Collection<BaseConhecimentoDTO> getListaConhecimentoQuantitativoEmLista() {
        return listaConhecimentoQuantitativoEmLista;
    }

    public void setListaConhecimentoQuantitativoEmLista(final Collection<BaseConhecimentoDTO> listaConhecimentoQuantitativoEmLista) {
        this.listaConhecimentoQuantitativoEmLista = listaConhecimentoQuantitativoEmLista;
    }

}
