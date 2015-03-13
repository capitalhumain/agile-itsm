package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.IntegranteViagemDTO;
import br.com.centralit.citcorpore.bean.ItemPrestacaoContasViagemDTO;
import br.com.centralit.citcorpore.bean.PrestacaoContasViagemDTO;
import br.com.centralit.citcorpore.bean.RequisicaoViagemDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.IntegranteViagemDao;
import br.com.centralit.citcorpore.integracao.ItemPrestacaoContasViagemDao;
import br.com.centralit.citcorpore.integracao.PrestacaoContasViagemDao;
import br.com.centralit.citcorpore.integracao.RequisicaoViagemDAO;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.WebUtil;

/**
 * @author ronnie.lopes
 *
 */
public class PrestacaoContasViagemServiceEjb extends ComplemInfSolicitacaoServicoServiceEjb implements PrestacaoContasViagemService {

    private PrestacaoContasViagemDao dao;

    @Override
    protected PrestacaoContasViagemDao getDao() {
        if (dao == null) {
            dao = new PrestacaoContasViagemDao();
        }
        return dao;
    }

    public String i18n_Message(final UsuarioDTO usuario, final String key) {
        if (usuario != null) {
            if (UtilI18N.internacionaliza(usuario.getLocale(), key) != null) {
                return UtilI18N.internacionaliza(usuario.getLocale(), key);
            }
            return key;
        }
        return key;
    }

    @Override
    public BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {
        return null;
    }

    @Override
    public Collection<IntegranteViagemDTO> restoreByIntegranteSolicitacao(final IntegranteViagemDTO integrante) throws Exception {
        final IntegranteViagemDao dao = new IntegranteViagemDao();
        return dao.restoreByIntegranteSolicitacao(integrante);
    }

    @Override
    public BaseEntity deserializaObjeto(final String serialize) throws Exception {
        PrestacaoContasViagemDTO prestacaoContasViagemDto = null;
        if (serialize != null) {
            prestacaoContasViagemDto = (PrestacaoContasViagemDTO) WebUtil.deserializeObject(PrestacaoContasViagemDTO.class, serialize);
            if (prestacaoContasViagemDto != null && prestacaoContasViagemDto.getItensPrestacaoContasViagemSerialize() != null) {
                prestacaoContasViagemDto.setListaItemPrestacaoContasViagemDTO(WebUtil.deserializeCollectionFromString(ItemPrestacaoContasViagemDTO.class,
                        prestacaoContasViagemDto.getItensPrestacaoContasViagemSerialize()));
            }
            if (prestacaoContasViagemDto.getIntegranteSerialize() != null) {
                prestacaoContasViagemDto.setIntegranteViagemDto((IntegranteViagemDTO) WebUtil.deserializeObject(IntegranteViagemDTO.class,
                        prestacaoContasViagemDto.getIntegranteSerialize()));
            }
        }
        return prestacaoContasViagemDto;
    }

    @Override
    public void validaCreate(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {}

    @Override
    public void validaDelete(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {}

    @Override
    public void validaUpdate(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {}

    @Override
    public BaseEntity create(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {

        PrestacaoContasViagemDTO prestacaoContasViagemDto = (PrestacaoContasViagemDTO) model;

        final RequisicaoViagemDAO reqViagemDao = new RequisicaoViagemDAO();
        RequisicaoViagemDTO reqViagemDto = new RequisicaoViagemDTO();
        final PrestacaoContasViagemDao prestacaoContasViagemDao = this.getDao();
        final ItemPrestacaoContasViagemDao itemPrestacaoContasViagemDao = new ItemPrestacaoContasViagemDao();
        final SolicitacaoServicoDao solicitacaoServicoDao = new SolicitacaoServicoDao();
        final IntegranteViagemDao viagemDao = new IntegranteViagemDao();
        final IntegranteViagemDTO integranteViagem = new IntegranteViagemDTO();

        try {

            reqViagemDao.setTransactionControler(tc);
            prestacaoContasViagemDao.setTransactionControler(tc);
            itemPrestacaoContasViagemDao.setTransactionControler(tc);
            solicitacaoServicoDao.setTransactionControler(tc);
            viagemDao.setTransactionControler(tc);

            if (prestacaoContasViagemDto.getRemarcacao() == null || prestacaoContasViagemDto.getRemarcacao().equalsIgnoreCase("N")
                    || prestacaoContasViagemDto.getRemarcacao().equalsIgnoreCase("")) {
                prestacaoContasViagemDto.setRemarcacao("N");
            } else {
                prestacaoContasViagemDto.setRemarcacao("S");
            }
            prestacaoContasViagemDto.setIdSolicitacaoServico(solicitacaoServicoDto.getIdSolicitacaoServico());
            integranteViagem.setRemarcacao(prestacaoContasViagemDto.getRemarcacao());
            integranteViagem.setIdRespPrestacaoContas(prestacaoContasViagemDto.getIdRespPrestacaoContas());

            if (prestacaoContasViagemDto.getIdEmpregado() != null && !prestacaoContasViagemDto.getIdEmpregado().equals("")) {
                integranteViagem.setIdEmpregado(prestacaoContasViagemDto.getIdEmpregado());
            } else {
                integranteViagem.setIdEmpregado(prestacaoContasViagemDto.getIntegranteViagemDto().getIdEmpregado());
            }

            integranteViagem.setIdSolicitacaoServico(prestacaoContasViagemDto.getIdSolicitacaoServico());

            prestacaoContasViagemDto.setDataHora(UtilDatas.getDataHoraAtual());

            prestacaoContasViagemDto.setIdEmpregado(prestacaoContasViagemDto.getIntegranteViagemDto().getIdEmpregado());
            prestacaoContasViagemDto.setIdResponsavel(prestacaoContasViagemDto.getIntegranteViagemDto().getIdRespPrestacaoContas());

            reqViagemDto.setIdSolicitacaoServico(solicitacaoServicoDto.getIdSolicitacaoServico());

            reqViagemDto = (RequisicaoViagemDTO) reqViagemDao.restore(reqViagemDto);

            prestacaoContasViagemDto.setDataHora(UtilDatas.getDataHoraAtual());
            viagemDao.updateBySolicitacaoEmpregado(integranteViagem);

            prestacaoContasViagemDto.setIdSolicitacaoServico(solicitacaoServicoDto.getIdSolicitacaoServico());
            prestacaoContasViagemDto.setIntegranteFuncionario(prestacaoContasViagemDto.getIntegranteViagemDto().getIntegranteFuncionario());
            prestacaoContasViagemDto.setNomeNaoFuncionario(prestacaoContasViagemDto.getIntegranteViagemDto().getNomeNaoFuncionario());

            if (solicitacaoServicoDto.getAcaoFluxo().equalsIgnoreCase("E")) {
                this.validaCreate(solicitacaoServicoDto, model);
                prestacaoContasViagemDto.setSituacao(PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA);
                prestacaoContasViagemDto.setIdItemTrabalho(null);
                if (reqViagemDto != null) {
                    reqViagemDto.setEstado(RequisicaoViagemDTO.AGUARDANDO_CONFERENCIA);
                    reqViagemDao.update(reqViagemDto);
                }
            }

            prestacaoContasViagemDto = (PrestacaoContasViagemDTO) prestacaoContasViagemDao.create(prestacaoContasViagemDto);

            if (prestacaoContasViagemDto.getListaItemPrestacaoContasViagemDTO() != null
                    && prestacaoContasViagemDto.getListaItemPrestacaoContasViagemDTO().size() > 0) {
                for (final ItemPrestacaoContasViagemDTO itemPrestacaoContasViagemDTO : prestacaoContasViagemDto.getListaItemPrestacaoContasViagemDTO()) {
                    itemPrestacaoContasViagemDTO.setIdPrestacaoContasViagem(prestacaoContasViagemDto.getIdPrestacaoContasViagem());
                    itemPrestacaoContasViagemDao.create(itemPrestacaoContasViagemDTO);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return prestacaoContasViagemDto;
    }

    @Override
    public void update(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDTO, final BaseEntity model) throws Exception {
        final ItemPrestacaoContasViagemDao itemPrestacaoContasViagemDAO = new ItemPrestacaoContasViagemDao();
        final IntegranteViagemDao integranteViagemDAO = new IntegranteViagemDao();

        itemPrestacaoContasViagemDAO.setTransactionControler(tc);
        integranteViagemDAO.setTransactionControler(tc);

        PrestacaoContasViagemDTO prestacaoContasViagemDTO = (PrestacaoContasViagemDTO) model;
        IntegranteViagemDTO integranteViagemDTO = new IntegranteViagemDTO();

        prestacaoContasViagemDTO.setIntegranteViagemDto(integranteViagemDAO.getIntegranteByIdSolicitacaoAndTarefa(
                solicitacaoServicoDTO.getIdSolicitacaoServico(), solicitacaoServicoDTO.getIdTarefa()));

        if (prestacaoContasViagemDTO.getIdEmpregado() == null || prestacaoContasViagemDTO.getIdEmpregado().equals("")) {
            prestacaoContasViagemDTO.setIdEmpregado(prestacaoContasViagemDTO.getIntegranteViagemDto().getIdEmpregado());
        }

        if (prestacaoContasViagemDTO.getValorDiferenca() > 0.0 && solicitacaoServicoDTO.getAcaoFluxo().equalsIgnoreCase("E")) {
            throw new Exception(this.i18n_Message(solicitacaoServicoDTO.getUsuarioDto(), "requisicaoViagem.valorDiferencaPrestacaoAdiantamento"));
        }

        if (solicitacaoServicoDTO.getAcaoFluxo().equalsIgnoreCase("E")) {
            prestacaoContasViagemDTO.setSituacao(PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA);
            prestacaoContasViagemDTO.setIdItemTrabalho(null);
        }

        if (prestacaoContasViagemDTO.getIdPrestacaoContasViagem() == null) {
            prestacaoContasViagemDTO = (PrestacaoContasViagemDTO) this.create(tc, solicitacaoServicoDTO, prestacaoContasViagemDTO);
            prestacaoContasViagemDTO.setCorrecao("N");
        } else {
            prestacaoContasViagemDTO.setIdItemTrabalho(null);
            prestacaoContasViagemDTO.setSituacao(PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA);
            this.update(prestacaoContasViagemDTO);
            prestacaoContasViagemDTO.setCorrecao("S");
        }

        if (prestacaoContasViagemDTO.getListaItemPrestacaoContasViagemDTO() != null
                && !prestacaoContasViagemDTO.getListaItemPrestacaoContasViagemDTO().isEmpty()) {
            itemPrestacaoContasViagemDAO.deleteByIdPrestacaoContasViagem(prestacaoContasViagemDTO.getIdPrestacaoContasViagem());

            for (final ItemPrestacaoContasViagemDTO itemPrestacaoContasViagemDTO : prestacaoContasViagemDTO.getListaItemPrestacaoContasViagemDTO()) {
                itemPrestacaoContasViagemDTO.setIdPrestacaoContasViagem(prestacaoContasViagemDTO.getIdPrestacaoContasViagem());
                itemPrestacaoContasViagemDAO.create(itemPrestacaoContasViagemDTO);
            }
        }

        if (solicitacaoServicoDTO.getAcaoFluxo().equalsIgnoreCase("E")) {
            final RequisicaoViagemDAO requisicaoViagemDAO = new RequisicaoViagemDAO();
            requisicaoViagemDAO.setTransactionControler(tc);

            RequisicaoViagemDTO requisicaoViagemDTO = new RequisicaoViagemDTO();

            requisicaoViagemDTO.setIdSolicitacaoServico(solicitacaoServicoDTO.getIdSolicitacaoServico());
            requisicaoViagemDTO = (RequisicaoViagemDTO) requisicaoViagemDAO.restore(requisicaoViagemDTO);

            requisicaoViagemDTO.setEstado(PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA);

            requisicaoViagemDAO.update(requisicaoViagemDTO);
        }

        integranteViagemDTO = integranteViagemDAO.getIntegranteByIdSolicitacaoAndTarefa(solicitacaoServicoDTO.getIdSolicitacaoServico(),
                prestacaoContasViagemDTO.getIntegranteViagemDto().getIdTarefa());

        if (solicitacaoServicoDTO.getAcaoFluxo().equalsIgnoreCase("E")) {
            if (prestacaoContasViagemDTO != null && prestacaoContasViagemDTO.getCorrecao() != null && !prestacaoContasViagemDTO.getCorrecao().equals("N")) {
                integranteViagemDTO.setEstado(PrestacaoContasViagemDTO.EM_CONFERENCIA);
                integranteViagemDTO.setIdTarefa(null);
            } else {
                integranteViagemDTO.setEstado(PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA);
                integranteViagemDTO.setIdTarefa(null);
            }
        }

        integranteViagemDAO.update(integranteViagemDTO);

    }

    @Override
    public void delete(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {}

    @Override
    public Integer recuperaIdPrestacaoSeExistir(final Integer idSolicitacaoServico, final Integer idEmpregado) throws ServiceException, Exception {
        final PrestacaoContasViagemDao dao = this.getDao();
        final PrestacaoContasViagemDTO prestacaoContasViagemDto = dao.findBySolicitacaoAndEmpregado(idSolicitacaoServico, idEmpregado);
        if (prestacaoContasViagemDto != null && prestacaoContasViagemDto.getIdPrestacaoContasViagem() != null) {
            return prestacaoContasViagemDto.getIdPrestacaoContasViagem();
        }
        return null;
    }

}
