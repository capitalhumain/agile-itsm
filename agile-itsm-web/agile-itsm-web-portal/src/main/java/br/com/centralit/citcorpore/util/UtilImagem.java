package br.com.centralit.citcorpore.util;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class UtilImagem {

    /**
     *
     * Verifica se uma imagem passada atraves de uma url existe
     *
     * @author riubbe.oliveira
     * @param urlImagem
     * @return
     */
    public static boolean verificaSeImagemExiste(final String urlImagem) {
        try {
            final URL url = new URL(urlImagem);
            ImageIO.read(url);
            return true;

        } catch (final IOException e) {
            System.out.println("Imagem " + urlImagem + " Não encontrada");
            return false;
        }
    }

}
