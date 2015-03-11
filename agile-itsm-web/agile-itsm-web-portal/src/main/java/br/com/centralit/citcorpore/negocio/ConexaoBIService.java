package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;
import java.util.Collection;

import br.com.centralit.citcorpore.bean.BICitsmartResultRotinaDTO;
import br.com.centralit.citcorpore.bean.ConexaoBIDTO;
import br.com.centralit.citcorpore.bean.ProcessamentoBatchDTO;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudService;

public interface ConexaoBIService extends CrudService {

    Collection listarConexoesPaginadas(final Collection<ConexaoBIDTO> conexaoBIDTO, final Integer pgAtual, final Integer qtdPaginacao) throws Exception;

    Collection listarConexoesPaginadasFiltradas(final ConexaoBIDTO conexaoBIDTO, final Integer pgAtual, final Integer qtdPaginacao) throws Exception;

    boolean jaExisteRegistroComMesmoNome(final ConexaoBIDTO conexaoBIDTO) throws Exception;

    boolean jaExisteRegistroComMesmoLink(final ConexaoBIDTO conexaoBIDTO) throws Exception;

    Integer obterTotalDePaginas(final Integer itensPorPagina, final String loginUsuario, final ConexaoBIDTO conexaoBIBean) throws Exception;

    ConexaoBIDTO findByIdProcessBatch(final Integer idProcessamentoBatch) throws Exception;

    ArrayList<ConexaoBIDTO> listarConexoesAutomaticasSemAgendEspOuExcecao() throws ServiceException, Exception;

    String getIdProcEspecificoOuExcecao() throws Exception;

    java.util.Date getProxDtExecucao(final ConexaoBIDTO conexaoBI) throws ServiceException, Exception;

    java.util.Date getProxDtExecucaoPadraoOuEspecifica(final ConexaoBIDTO conexaoBI) throws ServiceException, Exception;

    BICitsmartResultRotinaDTO validaAgendamentoExcecao(final ConexaoBIDTO conexaoBI, final ProcessamentoBatchDTO processamentoBatchDTO)
            throws ServiceException, Exception;

}
