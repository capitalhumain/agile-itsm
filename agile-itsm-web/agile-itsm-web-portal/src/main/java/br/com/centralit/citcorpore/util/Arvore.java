package br.com.centralit.citcorpore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Objetiva controlar a criação de uma árvore a partir de itens recuperados do banco de dados; serve para qualquer objeto que use o IDpai
 * para implementar a hierarquia e atenderá a criação da lista de resultados de um AutoComplete com hierarquia.
 *
 * @author euler.ramos
 */

public class Arvore {

    private Set<NoArvore> arvore;
    private final List<String> listaTexto;
    private final List<Integer> listaID;

    public Arvore() {
        super();
        this.setArvore(new TreeSet<NoArvore>());
        listaTexto = new ArrayList<>();
        listaID = new ArrayList<>();
    }

    public Set<NoArvore> getArvore() {
        return arvore;
    }

    private void setArvore(final Set<NoArvore> arvore) {
        this.arvore = arvore;
    }

    public List<String> getListaTexto() {
        return listaTexto;
    }

    public List<Integer> getListaID() {
        return listaID;
    }

    public void adicionaNo(final Integer id, final String nome, final Integer idPai) {
        if (id != null) {
            NoArvore noArvore;
            if (this.naoAdicionada(id)) {
                final NoArvore noUnidadePai = this.procuraNoPai(id, idPai);
                noArvore = new NoArvore(id, nome, idPai);
                if (noUnidadePai == null) {
                    noArvore.setNivel(1);
                    this.getArvore().add(noArvore);
                } else {
                    noArvore.setNivel(noUnidadePai.getNivel() + 1);
                    noUnidadePai.getFilhos().add(noArvore);
                }
            }
        }
    }

    private NoArvore procuraNoPai(final Integer id, final Integer idPai) {
        NoArvore noPai;
        if (idPai == null || idPai.intValue() <= 0 || id.equals(idPai)) {
            noPai = null;
        } else {
            noPai = this.localizaNaArvore(idPai, this.getArvore());
        }
        return noPai;
    }

    private NoArvore localizaNaArvore(final Integer id, final Set<NoArvore> listaNos) {
        NoArvore noLoc = null;
        if (id != null && listaNos != null && listaNos.size() > 0) {
            for (final NoArvore noArvore : listaNos) {
                if (noArvore.getId().equals(id)) {
                    noLoc = noArvore;
                    break;
                } else {
                    noLoc = this.localizaNaArvore(id, noArvore.getFilhos());
                    if (noLoc != null) {
                        break;
                    }
                }
            }
        }
        return noLoc;
    }

    private boolean naoAdicionada(final Integer id) {
        final NoArvore noLocalizado = this.localizaNaArvore(id, this.getArvore());
        if (noLocalizado == null) {
            return true;
        } return false;
    }

    /**
     * @param idUnidadeColaborador
     *            Método implementado, porém não foi necessário utilizá-lo, por questões de performance, foi melhor filtrar a unidade na consulta ao banco de
     *            dados e utilizar a lista sem restrição;
     */
    public void geraListaUnidadeEsuperiores(final Integer idUnidadeColaborador) {
        if (idUnidadeColaborador != null && idUnidadeColaborador.intValue() > 0) {
            this.getListaTexto().clear();
            this.getListaID().clear();

            // Adicionando os nós de trás para frente
            final NoArvore noUnidade = this.localizaNaArvore(idUnidadeColaborador, this.getArvore());
            this.getListaTexto().add(0, this.retornaIdentacao(noUnidade.getNivel()) + noUnidade.getTexto());
            this.getListaID().add(0, noUnidade.getId());
            NoArvore noUnidadePai = this.procuraNoPai(noUnidade.getId(), noUnidade.getIdPai());
            while (noUnidadePai != null) {
                this.getListaTexto().add(0, this.retornaIdentacao(noUnidadePai.getNivel()) + noUnidadePai.getTexto());
                this.getListaID().add(0, noUnidadePai.getId());
                noUnidadePai = this.procuraNoPai(noUnidadePai.getId(), noUnidadePai.getIdPai());
            }

        } else {
            this.geraListaSemRestricao();
        }
    }

    public void geraListaUnidadeEsuasFilhas(final Integer idUnidadeColaborador) {
        if (idUnidadeColaborador != null && idUnidadeColaborador.intValue() > 0) {
            this.getListaTexto().clear();
            this.getListaID().clear();
            final NoArvore noUnidade = this.localizaNaArvore(idUnidadeColaborador, this.getArvore());
            if (noUnidade != null) {
                final TreeSet<NoArvore> arvoreUnidade = new TreeSet<NoArvore>();
                arvoreUnidade.add(noUnidade);
                this.addNosLista(arvoreUnidade);
            }
        } else {
            this.geraListaSemRestricao();
        }
    }

    public void geraListaSemRestricao() {
        this.getListaTexto().clear();
        this.getListaID().clear();
        this.addNosLista(this.getArvore());
    }

    private void addNosLista(final Set<NoArvore> listaNos) {
        if (listaNos != null && listaNos.size() > 0) {
            for (final NoArvore noArvore : listaNos) {
                this.getListaTexto().add(this.retornaIdentacao(noArvore.getNivel()) + noArvore.getTexto());
                this.getListaID().add(noArvore.getId());
                // Adicionando os filhos
                this.addNosLista(noArvore.getFilhos());
            }
        }
    }

    private String retornaIdentacao(final Integer nivel) {
        final StringBuilder identacao = new StringBuilder();
        for (int i = 1; i < nivel; i++) {
            identacao.append("---");
        }
        return identacao.toString();
    }

}
