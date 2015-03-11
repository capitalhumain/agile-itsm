/**
 * CentralIT - CITSmart.
 */
package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citcorpore.bean.CaracteristicaDTO;
import br.com.centralit.citcorpore.bean.TipoItemConfiguracaoDTO;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudService;

/**
 * Service de TipoItemConfiguracao.
 *
 * @author valdoilo.damasceno
 *
 */
public interface TipoItemConfiguracaoService extends CrudService {

    /**
     * Cria novo TipoItemConfiguracao.
     *
     * @param tipo
     * @param request
     * @return BaseEntity
     * @throws LogicException
     * @throws ServiceException
     * @author valdoilo.damasceno
     */
    public BaseEntity create(final BaseEntity tipo, final HttpServletRequest request) throws LogicException, ServiceException;

    /**
     * Restaura GRID de Característica.
     *
     * @param document
     *            - DocumentHTML
     * @param caracteristicas
     *            - Collection
     * @author valdoilo.damasceno
     */
    public void restaurarGridCaracteristicas(final DocumentHTML document, final Collection<CaracteristicaDTO> caracteristicas);

    /**
     * Restura GRID de características do Item de Configuração Filho selecionado na tela de Patrimônio.
     *
     * @param document
     *            - DocumentHTML
     * @param caracteristicas
     *            -Collection
     * @author valdoilo.damasceno
     */
    public void restaurarGridCaracteristicasItemConfiguracaoFilho(final DocumentHTML document, final Collection<CaracteristicaDTO> caracteristicas);

    /**
     * Verifica se Tipo Item Configuração informada existe.
     *
     * @param tipoItemConfiguracao
     * @return
     * @throws PersistenceException
     * @author Thays.araujo
     */
    public boolean verificarSeTipoItemConfiguracaoExiste(final TipoItemConfiguracaoDTO tipoItemConfiguracao) throws PersistenceException;

    /**
     * Verifica se o Tipo Item Configuração está associado a algum Item Configuração
     * 
     * @param tipoItemConfiguracao
     * @return
     * @throws PersistenceException
     * @author thyen.chang
     */
    public boolean verificaAssociacaoItemConfiguracao(final TipoItemConfiguracaoDTO tipoItemConfiguracao) throws PersistenceException;

}
