package br.com.centralit.citcorpore.util;

import br.com.centralit.citcorpore.bean.AnexoDTO;
import br.com.citframework.util.UtilTratamentoArquivos;

public class AnexoUtil {

    public static boolean moveToDirPermanente(final String id, final AnexoDTO anexoDto) {
        UtilTratamentoArquivos.copyFile(anexoDto.getPath(), id + "." + anexoDto.getExtensao());
        return true;
    }

}
