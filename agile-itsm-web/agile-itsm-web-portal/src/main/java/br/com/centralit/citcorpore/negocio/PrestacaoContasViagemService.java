package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.centralit.citcorpore.bean.IntegranteViagemDTO;
import br.com.citframework.excecao.ServiceException;

/**
 * @author ronnie.lopes
 *
 */
public interface PrestacaoContasViagemService extends ComplemInfSolicitacaoServicoService {

    Integer recuperaIdPrestacaoSeExistir(final Integer idSolicitacaoServico, final Integer idEmpregado) throws ServiceException, Exception;

    Collection<IntegranteViagemDTO> restoreByIntegranteSolicitacao(final IntegranteViagemDTO integrante) throws Exception;

}
