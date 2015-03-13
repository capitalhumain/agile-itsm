package br.com.citframework.util.geo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Simples representação de coordenadas geográficas em graus
 *
 * @author bruno.ribeiro - <a href="mailto:bruno.ribeiro@centrait.com.br">bruno.ribeiro@centrait.com.br</a>
 * @since 28/10/2014
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Coordinate {

    private double lat;
    private double lng;

}
