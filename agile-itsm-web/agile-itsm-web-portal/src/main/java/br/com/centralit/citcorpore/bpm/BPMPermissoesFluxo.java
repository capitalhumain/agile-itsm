package br.com.centralit.citcorpore.bpm;

import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.GrupoBpmDTO;
import br.com.centralit.bpm.dto.PermissoesFluxoDTO;
import br.com.centralit.bpm.negocio.IPermissoesFluxo;
import br.com.centralit.citcorpore.integracao.PermissoesFluxoDao;

public class BPMPermissoesFluxo implements IPermissoesFluxo {

    private PermissoesFluxoDao dao;

    @Override
    public PermissoesFluxoDTO getPermissoesFluxo(final GrupoBpmDTO grupoDto, final FluxoDTO fluxoDto) throws Exception {
        final PermissoesFluxoDTO permissoesFluxoDto = new PermissoesFluxoDTO();
        permissoesFluxoDto.setIdTipoFluxo(fluxoDto.getIdTipoFluxo());
        permissoesFluxoDto.setIdGrupo(grupoDto.getIdGrupo());
        return (PermissoesFluxoDTO) this.getDao().restore(permissoesFluxoDto);
    }

    private PermissoesFluxoDao getDao() {
        if (dao == null) {
            dao = new PermissoesFluxoDao();
        }
        return dao;
    }

}
