package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.bean.ControleContratoModuloSistemaDTO;
import br.com.centralit.citcorpore.bean.ControleContratoOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.ControleContratoPagamentoDTO;
import br.com.centralit.citcorpore.bean.ControleContratoTreinamentoDTO;
import br.com.centralit.citcorpore.bean.ControleContratoVersaoDTO;
import br.com.centralit.citcorpore.integracao.ControleContratoDao;
import br.com.centralit.citcorpore.integracao.ControleContratoModuloSistemaDao;
import br.com.centralit.citcorpore.integracao.ControleContratoOcorrenciaDao;
import br.com.centralit.citcorpore.integracao.ControleContratoPagamentoDao;
import br.com.centralit.citcorpore.integracao.ControleContratoTreinamentoDao;
import br.com.centralit.citcorpore.integracao.ControleContratoVersaoDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class ControleContratoServiceEjb extends CrudServiceImpl implements ControleContratoService {

    private ControleContratoDao dao;

    @Override
    protected ControleContratoDao getDao() {
        if (dao == null) {
            dao = new ControleContratoDao();
        }
        return dao;
    }

    @Override
    public BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {

        ControleContratoDTO controleContratoDto = (ControleContratoDTO) model;
        final ControleContratoDao controleContratoDao = getDao();

        final ControleContratoVersaoDao controleContratoVersaoDao = new ControleContratoVersaoDao();
        final ControleContratoPagamentoDao controleContratoPagamentoDao = new ControleContratoPagamentoDao();
        final ControleContratoTreinamentoDao controleContratoTreinamentoDao = new ControleContratoTreinamentoDao();
        final ControleContratoOcorrenciaDao controleContratoOcorrenciaDao = new ControleContratoOcorrenciaDao();
        final ControleContratoModuloSistemaDao controleContratoModuloSistemaDao = new ControleContratoModuloSistemaDao();

        final TransactionControler tc = new TransactionControlerImpl(controleContratoDao.getAliasDB());

        try {
            validaCreate(model);
            /** TC **/
            controleContratoDao.setTransactionControler(tc);
            controleContratoVersaoDao.setTransactionControler(tc);
            controleContratoPagamentoDao.setTransactionControler(tc);
            controleContratoTreinamentoDao.setTransactionControler(tc);
            controleContratoOcorrenciaDao.setTransactionControler(tc);
            controleContratoModuloSistemaDao.setTransactionControler(tc);

            tc.start();
            /** MODEL **/
            controleContratoDto = (ControleContratoDTO) controleContratoDao.create(model);
            /** VERSAO **/
            if (controleContratoDto.getLstVersao() != null && !controleContratoDto.getLstVersao().isEmpty()) {
                ControleContratoVersaoDTO item = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstVersao().size(); i++) {
                    item = (ControleContratoVersaoDTO) controleContratoDto.getLstVersao().get(i);
                    item.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoVersaoDao.create(item);
                }
            }
            /** PAGAMENTO **/
            if (controleContratoDto.getLstPagamento() != null && !controleContratoDto.getLstPagamento().isEmpty()) {
                ControleContratoPagamentoDTO itemPagamento = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstPagamento().size(); i++) {
                    itemPagamento = (ControleContratoPagamentoDTO) controleContratoDto.getLstPagamento().get(i);
                    itemPagamento.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoPagamentoDao.create(itemPagamento);
                }
            }
            /** TREINAMENTO **/
            if (controleContratoDto.getLstTreinamento() != null && !controleContratoDto.getLstTreinamento().isEmpty()) {
                ControleContratoTreinamentoDTO itemTreinamento = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstTreinamento().size(); i++) {
                    itemTreinamento = (ControleContratoTreinamentoDTO) controleContratoDto.getLstTreinamento().get(i);
                    itemTreinamento.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoTreinamentoDao.create(itemTreinamento);
                }
            }
            /** OCORRENCIA **/
            if (controleContratoDto.getLstOcorrencia() != null && !controleContratoDto.getLstOcorrencia().isEmpty()) {
                ControleContratoOcorrenciaDTO itemOcorrencia = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstOcorrencia().size(); i++) {
                    itemOcorrencia = (ControleContratoOcorrenciaDTO) controleContratoDto.getLstOcorrencia().get(i);
                    itemOcorrencia.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoOcorrenciaDao.create(itemOcorrencia);
                }
            }

            /** MODULOS ATIVOS **/
            if (controleContratoDto.getLstModulosAtivos() != null && !controleContratoDto.getLstModulosAtivos().isEmpty()) {
                final ControleContratoModuloSistemaDTO item = new ControleContratoModuloSistemaDTO();
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstModulosAtivos().size(); i++) {
                    item.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    item.setIdModuloSistema(Integer.parseInt((String) controleContratoDto.getLstModulosAtivos().get(i)));
                    // grava cada item da lista
                    controleContratoModuloSistemaDao.create(item);
                }
            }

            tc.commit();
            tc.close();
        } catch (final Exception e) {
            e.printStackTrace();
            rollbackTransaction(tc, e);
        }

        return controleContratoDto;

    }

    @Override
    public void update(final BaseEntity model) throws ServiceException, LogicException {
        final ControleContratoDTO controleContratoDto = (ControleContratoDTO) model;
        final ControleContratoDao controleContratoDao = getDao();

        final ControleContratoVersaoDao controleContratoVersaoDao = new ControleContratoVersaoDao();
        final ControleContratoPagamentoDao controleContratoPagamentoDao = new ControleContratoPagamentoDao();
        final ControleContratoTreinamentoDao controleContratoTreinamentoDao = new ControleContratoTreinamentoDao();
        final ControleContratoOcorrenciaDao controleContratoOcorrenciaDao = new ControleContratoOcorrenciaDao();
        final ControleContratoModuloSistemaDao controleContratoModuloSistemaDao = new ControleContratoModuloSistemaDao();

        final TransactionControler tc = new TransactionControlerImpl(controleContratoDao.getAliasDB());

        try {

            validaCreate(model);
            controleContratoDao.setTransactionControler(tc);
            controleContratoVersaoDao.setTransactionControler(tc);
            controleContratoPagamentoDao.setTransactionControler(tc);
            controleContratoTreinamentoDao.setTransactionControler(tc);
            controleContratoOcorrenciaDao.setTransactionControler(tc);
            controleContratoModuloSistemaDao.setTransactionControler(tc);

            tc.start();
            /** MODEL **/

            controleContratoDao.update(controleContratoDto);
            /** VERSAO **/
            controleContratoVersaoDao.deleteByIdControleContrato(controleContratoDto); // delete
            if (controleContratoDto.getLstVersao() != null && !controleContratoDto.getLstVersao().isEmpty()) {
                ControleContratoVersaoDTO item = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstVersao().size(); i++) {
                    item = (ControleContratoVersaoDTO) controleContratoDto.getLstVersao().get(i);
                    item.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoVersaoDao.create(item);
                }
            }
            /** PAGAMENTO **/
            controleContratoPagamentoDao.deleteByIdControleContrato(controleContratoDto); // delete
            if (controleContratoDto.getLstPagamento() != null && !controleContratoDto.getLstPagamento().isEmpty()) {
                ControleContratoPagamentoDTO itemPagamento = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstPagamento().size(); i++) {
                    itemPagamento = (ControleContratoPagamentoDTO) controleContratoDto.getLstPagamento().get(i);
                    itemPagamento.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoPagamentoDao.create(itemPagamento);
                }
            }
            /** TREINAMENTO **/
            controleContratoTreinamentoDao.deleteByIdControleContrato(controleContratoDto); // delete
            if (controleContratoDto.getLstTreinamento() != null && !controleContratoDto.getLstTreinamento().isEmpty()) {
                ControleContratoTreinamentoDTO itemTreinamento = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstTreinamento().size(); i++) {
                    itemTreinamento = (ControleContratoTreinamentoDTO) controleContratoDto.getLstTreinamento().get(i);
                    itemTreinamento.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoTreinamentoDao.create(itemTreinamento);
                }
            }
            /** OCORRENCIA **/
            controleContratoOcorrenciaDao.deleteByIdControleContrato(controleContratoDto); // delete
            if (controleContratoDto.getLstOcorrencia() != null && !controleContratoDto.getLstOcorrencia().isEmpty()) {
                ControleContratoOcorrenciaDTO itemOcorrencia = null;
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstOcorrencia().size(); i++) {
                    itemOcorrencia = (ControleContratoOcorrenciaDTO) controleContratoDto.getLstOcorrencia().get(i);
                    itemOcorrencia.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    controleContratoOcorrenciaDao.create(itemOcorrencia);
                }
            }

            /** MODULOS ATIVOS **/
            controleContratoModuloSistemaDao.deleteByIdControleContrato(controleContratoDto); // delete
            if (controleContratoDto.getLstModulosAtivos() != null && !controleContratoDto.getLstModulosAtivos().isEmpty()) {
                final ControleContratoModuloSistemaDTO item = new ControleContratoModuloSistemaDTO();
                // varre a lista de serviços
                for (int i = 0; i < controleContratoDto.getLstModulosAtivos().size(); i++) {
                    item.setIdControleContrato(controleContratoDto.getIdControleContrato());
                    item.setIdModuloSistema(Integer.parseInt((String) controleContratoDto.getLstModulosAtivos().get(i)));
                    // grava cada item da lista
                    controleContratoModuloSistemaDao.create(item);
                }
            }

            tc.commit();
            tc.close();
        } catch (final Exception e) {
            e.printStackTrace();
            rollbackTransaction(tc, e);
        }

    }

    // FIXME - Ajustar forma de obtenção dos DAOs
    @Override
    public BaseEntity restore(final BaseEntity model) throws ServiceException, LogicException {
        ControleContratoDTO controleContratoDto = (ControleContratoDTO) model;
        final ControleContratoDao controleContratoDao = getDao();

        try {
            controleContratoDto = (ControleContratoDTO) controleContratoDao.restore(model);
            final ControleContratoVersaoDTO controleContratoVersaoDTO = new ControleContratoVersaoDTO();
            controleContratoVersaoDTO.setIdControleContrato(controleContratoDto.getIdControleContrato());
            controleContratoDto.setLstVersao(new ArrayList(new ControleContratoVersaoDao().findByIdControleContrato(controleContratoVersaoDTO)));

            final ControleContratoPagamentoDTO controleContratoPagamentoDTO = new ControleContratoPagamentoDTO();
            controleContratoPagamentoDTO.setIdControleContrato(controleContratoDto.getIdControleContrato());
            controleContratoDto.setLstPagamento(new ArrayList(new ControleContratoPagamentoDao().findByIdControleContrato(controleContratoPagamentoDTO)));

            final ControleContratoTreinamentoDTO controleContratoTreinamentoDTO = new ControleContratoTreinamentoDTO();
            controleContratoTreinamentoDTO.setIdControleContrato(controleContratoDto.getIdControleContrato());
            controleContratoDto.setLstTreinamento(new ArrayList(new ControleContratoTreinamentoDao().findByIdControleContrato(controleContratoTreinamentoDTO)));

            final ControleContratoOcorrenciaDTO controleContratoOcorrenciaDTO = new ControleContratoOcorrenciaDTO();
            controleContratoOcorrenciaDTO.setIdControleContrato(controleContratoDto.getIdControleContrato());
            controleContratoDto.setLstOcorrencia(new ArrayList(new ControleContratoOcorrenciaDao().findByIdControleContrato(controleContratoOcorrenciaDTO)));

            final ControleContratoModuloSistemaDTO controleContratoModuloSistemaDTO = new ControleContratoModuloSistemaDTO();
            controleContratoModuloSistemaDTO.setIdControleContrato(controleContratoDto.getIdControleContrato());
            controleContratoDto.setLstModulosAtivos(new ArrayList(new ControleContratoModuloSistemaDao()
                    .findByIdControleContrato(controleContratoModuloSistemaDTO)));

        } catch (final Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return controleContratoDto;
    }

}
