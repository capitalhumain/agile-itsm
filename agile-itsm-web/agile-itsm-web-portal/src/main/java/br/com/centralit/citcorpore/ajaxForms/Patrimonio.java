package br.com.centralit.citcorpore.ajaxForms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citcorpore.bean.CaracteristicaDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.PatrimonioDTO;
import br.com.centralit.citcorpore.bean.TipoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.negocio.CaracteristicaService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.TipoItemConfiguracaoService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.WebUtil;

/**
 * @author Vadoilo Damasceno
 *
 */
public class Patrimonio extends AjaxFormAction {

    @Override
    public Class<PatrimonioDTO> getBeanClass() {
        return PatrimonioDTO.class;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final String parametroIdTipoItemConfiguracao = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.PATRIMONIO_IDTIPOITEMCONFIGURACAO, null);

        if (parametroIdTipoItemConfiguracao != null) {

            document.executeScript("ocultarItemConfiguracaoFilho()");
        } else {

            document.alert("Tipo de Item Configuração Patrimônio não definido.");

            return;
        }

    }

    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final String parametroIdTipoItemConfiguracao = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.PATRIMONIO_IDTIPOITEMCONFIGURACAO, null);

        if (parametroIdTipoItemConfiguracao == null || StringUtils.isBlank(parametroIdTipoItemConfiguracao)) {

            document.alert("Tipo de Item Configuração Patrimônio não definido.");

            return;
        }

        PatrimonioDTO patrimonioDto = new PatrimonioDTO();

        patrimonioDto = (PatrimonioDTO) document.getBean();

        final ItemConfiguracaoService itemService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);

        final ItemConfiguracaoDTO itemConfiguracaoPaiDto = new ItemConfiguracaoDTO();

        final ItemConfiguracaoDTO itemConfiguracaoFilhoDto = new ItemConfiguracaoDTO();

        final TipoItemConfiguracaoDTO tipoItemConfiguracaoPai = new TipoItemConfiguracaoDTO();

        final TipoItemConfiguracaoDTO tipoItemConfiguracaoFilho = new TipoItemConfiguracaoDTO();

        tipoItemConfiguracaoPai.setCaracteristicas((List) WebUtil.deserializeCollectionFromRequest(CaracteristicaDTO.class, "caracteristicasSerializadas",
                request));

        tipoItemConfiguracaoFilho.setCaracteristicas((List) WebUtil.deserializeCollectionFromRequest(CaracteristicaDTO.class,
                "caracteristicasSerializadasItemFilho", request));

        itemConfiguracaoPaiDto.setTipoItemConfiguracaoSerializadas(tipoItemConfiguracaoPai);

        itemConfiguracaoFilhoDto.setTipoItemConfiguracaoSerializadas(tipoItemConfiguracaoFilho);

        if (patrimonioDto.getIdItemConfiguracao() != null) {

            if (patrimonioDto.getIdItemConfiguracao() != null && patrimonioDto.getIdItemConfiguracaoFilho() == null) {

                itemConfiguracaoPaiDto.setIdItemConfiguracao(patrimonioDto.getIdItemConfiguracao());

                itemService.criarEAssociarValorDaCaracteristicaAoItemConfiguracao(itemConfiguracaoPaiDto, null, null);

                document.alert("Patrimônio gravado com sucesso.");

            }

            if (patrimonioDto.getIdItemConfiguracaoFilho() != null) {

                itemConfiguracaoPaiDto.setIdItemConfiguracao(patrimonioDto.getIdItemConfiguracao());

                itemConfiguracaoFilhoDto.setIdItemConfiguracao(patrimonioDto.getIdItemConfiguracaoFilho());

                itemService.criarEAssociarValorDaCaracteristicaAoItemConfiguracao(itemConfiguracaoPaiDto, null, null);

                itemService.criarEAssociarValorDaCaracteristicaAoItemConfiguracao(itemConfiguracaoFilhoDto, null, null);

                document.alert("Patrimônio gravado com sucesso.");

            }

        }

        CITCorporeUtil.limparFormulario(document);
        document.executeScript("limpar()");
    }

    /**
     * Restaura o Tipo de Item Configuração e carrega a Grid de Características Ativas.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public void restoreTipoItemConfiguracao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final String parametroIdTipoItemConfiguracao = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.PATRIMONIO_IDTIPOITEMCONFIGURACAO, null);

        if (parametroIdTipoItemConfiguracao == null || StringUtils.isBlank(parametroIdTipoItemConfiguracao)) {

            document.alert("Tipo de Item Configuração Patrimônio não definido.");

            return;
        }

        final TipoItemConfiguracaoService tipoService = (TipoItemConfiguracaoService) ServiceLocator.getInstance().getService(
                TipoItemConfiguracaoService.class, null);

        final CaracteristicaService caracteristicaService = (CaracteristicaService) ServiceLocator.getInstance().getService(CaracteristicaService.class, null);

        PatrimonioDTO patrimonioDto = new PatrimonioDTO();

        patrimonioDto = (PatrimonioDTO) document.getBean();

        document.executeScript("deleteAllRows()");

        document.executeScript("deleteAllRowsItemFilho()");

        if (patrimonioDto.getIdItemConfiguracao() == null) {

            tipoService.restaurarGridCaracteristicas(document,
                    caracteristicaService.consultarCaracteristicasAtivas(Integer.parseInt(parametroIdTipoItemConfiguracao)));

        } else {

            if (patrimonioDto.getIdItemConfiguracao() != null) {

                tipoService.restaurarGridCaracteristicas(document, caracteristicaService.consultarCaracteristicasComValoresItemConfiguracao(
                        Integer.parseInt(parametroIdTipoItemConfiguracao), patrimonioDto.getIdItemConfiguracao()));

            }

            if (patrimonioDto.getIdItemConfiguracaoFilho() != null) {

                tipoService.restaurarGridCaracteristicasItemConfiguracaoFilho(document,
                        caracteristicaService.consultarCaracteristicasComValoresItemConfiguracao(32, patrimonioDto.getIdItemConfiguracaoFilho()));

            }

        }

        document.executeScript("fecharPopup()");
    }

}
