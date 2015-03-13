package br.com.centralit.citcorpore.util;

import java.util.Comparator;

import br.com.centralit.citcorpore.bean.BaseConhecimentoPesquisaDTO;

public class OrdenadorDocumentosPesquisa implements Comparator<BaseConhecimentoPesquisaDTO> {

    @Override
    public int compare(final BaseConhecimentoPesquisaDTO c1, final BaseConhecimentoPesquisaDTO c2) {
        final int contador1 = c1.getContadorCliques();
        final int contador2 = c2.getContadorCliques();
        return contador2 - contador1;
    }

}
