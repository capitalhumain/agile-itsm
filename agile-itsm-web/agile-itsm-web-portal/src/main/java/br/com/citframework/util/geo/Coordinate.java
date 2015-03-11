package br.com.citframework.util.geo;

import lombok.Getter;
import lombok.Setter;

/**
 * Simples representação de coordenadas geográficas em graus
 *
 * @author bruno.ribeiro - <a href="mailto:bruno.ribeiro@centrait.com.br">bruno.ribeiro@centrait.com.br</a>
 * @since 28/10/2014
 *
 */
public class Coordinate {

    @Getter
    @Setter
    private double lat;

    @Getter
    @Setter
    private double lng;

}
