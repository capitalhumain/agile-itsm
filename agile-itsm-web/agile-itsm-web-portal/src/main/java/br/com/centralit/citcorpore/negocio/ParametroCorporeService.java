package br.com.centralit.citcorpore.negocio;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ParametroCorporeDTO;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudService;

/**
 * @author valdoilo.damasceno
 *
 */
public interface ParametroCorporeService extends CrudService {

    void create(final ParametroCorporeDTO parametroBean, final HttpServletRequest request) throws ServiceException, LogicException;

    List<ParametroCorporeDTO> pesquisarParamentro(final Integer id, final String nomeParametro) throws ServiceException, LogicException, Exception;

    /**
     * Cria e atualiza o HashMap statico de Parâmetros do CITSMart.
     *
     * @throws Exception
     * @author valdoilo.damasceno
     */
    void criarParametrosNovos() throws Exception;

    ParametroCorporeDTO getParamentroAtivo(final Integer id) throws Exception;

    /**
     * Atualiza Parâmetros utilizando UpdateNotNull. Informar apenas o ID do Parâmetro e o Valor.
     *
     * @param parametroDto
     * @throws Exception
     * @author valdoilo.damasceno
     */
    void atualizarParametros(final ParametroCorporeDTO parametroDto) throws Exception;

    void updateNotNull(final BaseEntity dto) throws Exception;

    /**
     * Atualiza o valor do parametro pelo id
     *
     */
    void atualizarParametro(final Integer id, final String valor) throws Exception;

}
