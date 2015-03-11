package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.JustificacaoEventoHistoricoDTO;
import br.com.centralit.citcorpore.bean.JustificacaoFalhasDTO;
import br.com.citframework.service.CrudService;

public interface JustificacaoFalhasService extends CrudService {

    void teste(final BaseEntity dto);

    ArrayList<JustificacaoEventoHistoricoDTO> findEventosComFalha(final JustificacaoFalhasDTO dto);

}
