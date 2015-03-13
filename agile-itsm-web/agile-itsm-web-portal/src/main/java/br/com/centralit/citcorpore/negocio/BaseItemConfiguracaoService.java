package br.com.centralit.citcorpore.negocio;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BaseItemConfiguracaoDTO;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudService;

/**
 * Service de BaseItemConfiguracao.
 *
 * @author valdoilo.damasceno
 *
 */
public interface BaseItemConfiguracaoService extends CrudService {

    /**
     * Verifica se existe cadastro pra BaseItemConfiguracão.
     *
     * @param obj
     * @param nomePai
     * @return
     * @throws Exception
     */
    boolean existBaseItemConfiguracao(final BaseItemConfiguracaoDTO dto) throws Exception;

    /**
     * Método que persiste nova BaseItemConfiguração
     *
     * @param baseItemConfiguracao
     * @return
     * @throws ServiceException
     * @throws LogicException
     */
    BaseEntity[] create(final BaseItemConfiguracaoDTO[] baseItemConfiguracao) throws ServiceException, LogicException;

    List<BaseEntity> restoreChildren(final BaseItemConfiguracaoDTO baseItemConfiguracaoBean) throws Exception;

    void update(final BaseItemConfiguracaoDTO[] vetorBaseItemConfiguracao) throws ServiceException, LogicException;

}
