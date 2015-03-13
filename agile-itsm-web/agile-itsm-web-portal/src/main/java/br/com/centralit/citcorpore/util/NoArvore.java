package br.com.centralit.citcorpore.util;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author euler.ramos
 *
 */
public class NoArvore implements Comparable<NoArvore> {

    private final Integer id;
    private final String texto;
    private final Integer idPai;
    private Integer nivel;
    private final Set<NoArvore> filhos = new TreeSet<>();

    public NoArvore(final Integer id, final String texto, final Integer idPai) {
        super();
        this.id = id;
        this.texto = texto;
        this.idPai = idPai;
    }

    public Integer getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Integer getIdPai() {
        return idPai;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(final Integer nivel) {
        this.nivel = nivel;
    }

    public Set<NoArvore> getFilhos() {
        return filhos;
    }

    @Override
    public int compareTo(final NoArvore outroNoArvore) {
        return this.getTexto().compareTo(outroNoArvore.getTexto());
    }

}
