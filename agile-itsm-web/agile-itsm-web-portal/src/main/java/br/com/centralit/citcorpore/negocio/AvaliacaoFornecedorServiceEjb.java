package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AvaliacaoFornecedorDTO;
import br.com.centralit.citcorpore.bean.AvaliacaoReferenciaFornecedorDTO;
import br.com.centralit.citcorpore.bean.CriterioAvaliacaoFornecedorDTO;
import br.com.centralit.citcorpore.integracao.AvaliacaoFornecedorDao;
import br.com.centralit.citcorpore.integracao.AvaliacaoReferenciaFornecedorDao;
import br.com.centralit.citcorpore.integracao.CriterioAvaliacaoFornecedorDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServiceImpl;

public class AvaliacaoFornecedorServiceEjb extends CrudServiceImpl implements AvaliacaoFornecedorService {

    private AvaliacaoFornecedorDao dao;

    @Override
    protected AvaliacaoFornecedorDao getDao() {
        if (dao == null) {
            dao = new AvaliacaoFornecedorDao();
        }
        return dao;
    }

    @Override
    public BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {

        final AvaliacaoReferenciaFornecedorDao avaliacaoReferenciaFornecedorDao = new AvaliacaoReferenciaFornecedorDao();

        final CriterioAvaliacaoFornecedorDao criterioAvaliacaoFornecedorDao = new CriterioAvaliacaoFornecedorDao();

        AvaliacaoFornecedorDTO avaliacaoFornecedorDto = (AvaliacaoFornecedorDTO) model;

        final TransactionControler transactionControler = new TransactionControlerImpl(getDao().getAliasDB());

        try {

            getDao().setTransactionControler(transactionControler);

            avaliacaoReferenciaFornecedorDao.setTransactionControler(transactionControler);

            criterioAvaliacaoFornecedorDao.setTransactionControler(transactionControler);

            transactionControler.start();

            avaliacaoFornecedorDto = (AvaliacaoFornecedorDTO) getDao().create(avaliacaoFornecedorDto);

            if (avaliacaoFornecedorDto.getListAvaliacaoReferenciaFornecedor() != null) {
                for (final AvaliacaoReferenciaFornecedorDTO avaliacaoReferenciaFornecedorDto : avaliacaoFornecedorDto.getListAvaliacaoReferenciaFornecedor()) {
                    if (avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != null && avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != 0) {

                        if (avaliacaoReferenciaFornecedorDto.getDecisao().equalsIgnoreCase("Sim")) {
                            avaliacaoReferenciaFornecedorDto.setDecisao("S");
                        } else {
                            avaliacaoReferenciaFornecedorDto.setDecisao("N");
                        }

                        avaliacaoReferenciaFornecedorDto.setIdAvaliacaoFornecedor(avaliacaoFornecedorDto.getIdAvaliacaoFornecedor());
                        avaliacaoReferenciaFornecedorDao.create(avaliacaoReferenciaFornecedorDto);
                    }

                }
            }

            if (avaliacaoFornecedorDto.getListCriterioAvaliacaoFornecedor() != null) {
                for (final CriterioAvaliacaoFornecedorDTO criterioAvaliacaoFornecedorDto : avaliacaoFornecedorDto.getListCriterioAvaliacaoFornecedor()) {
                    if (avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != null && avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != 0) {
                        if (criterioAvaliacaoFornecedorDto.getValor().equalsIgnoreCase("Sim")) {
                            criterioAvaliacaoFornecedorDto.setValorInteger(1);
                        } else {
                            if (criterioAvaliacaoFornecedorDto.getValor().equalsIgnoreCase("Não")) {
                                criterioAvaliacaoFornecedorDto.setValorInteger(0);
                            } else {
                                criterioAvaliacaoFornecedorDto.setValorInteger(2);
                            }
                        }
                        criterioAvaliacaoFornecedorDto.setIdAvaliacaoFornecedor(avaliacaoFornecedorDto.getIdAvaliacaoFornecedor());
                        criterioAvaliacaoFornecedorDao.create(criterioAvaliacaoFornecedorDto);
                    }

                }
            }

            transactionControler.commit();
            transactionControler.close();
        } catch (final Exception e) {
            e.printStackTrace();
            rollbackTransaction(transactionControler, e);
        }

        return avaliacaoFornecedorDto;

    }

    @Override
    public void update(final BaseEntity model) throws ServiceException, LogicException {
        final AvaliacaoReferenciaFornecedorDao avaliacaoReferenciaFornecedorDao = new AvaliacaoReferenciaFornecedorDao();

        final CriterioAvaliacaoFornecedorDao criterioAvaliacaoFornecedorDao = new CriterioAvaliacaoFornecedorDao();

        final AvaliacaoFornecedorDTO avaliacaoFornecedorDto = (AvaliacaoFornecedorDTO) model;

        final TransactionControler transactionControler = new TransactionControlerImpl(getDao().getAliasDB());

        try {

            getDao().setTransactionControler(transactionControler);

            avaliacaoReferenciaFornecedorDao.setTransactionControler(transactionControler);

            criterioAvaliacaoFornecedorDao.setTransactionControler(transactionControler);

            transactionControler.start();

            getDao().update(avaliacaoFornecedorDto);

            if (avaliacaoFornecedorDto.getListAvaliacaoReferenciaFornecedor() != null) {
                if (avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != null && avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != 0) {
                    avaliacaoReferenciaFornecedorDao.deleteByIdAvaliacaoFornecedor(avaliacaoFornecedorDto.getIdAvaliacaoFornecedor());
                    for (final AvaliacaoReferenciaFornecedorDTO avaliacaoReferenciaFornecedorDto : avaliacaoFornecedorDto
                            .getListAvaliacaoReferenciaFornecedor()) {

                        if (avaliacaoReferenciaFornecedorDto.getDecisao().equalsIgnoreCase("Sim")) {
                            avaliacaoReferenciaFornecedorDto.setDecisao("S");
                        } else {
                            avaliacaoReferenciaFornecedorDto.setDecisao("N");
                        }

                        avaliacaoReferenciaFornecedorDto.setIdAvaliacaoFornecedor(avaliacaoFornecedorDto.getIdAvaliacaoFornecedor());
                        avaliacaoReferenciaFornecedorDao.create(avaliacaoReferenciaFornecedorDto);

                    }
                }

            }

            if (avaliacaoFornecedorDto.getListCriterioAvaliacaoFornecedor() != null) {
                if (avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != null && avaliacaoFornecedorDto.getIdAvaliacaoFornecedor() != 0) {
                    criterioAvaliacaoFornecedorDao.deleteByIdAvaliacaoFornecedor(avaliacaoFornecedorDto.getIdAvaliacaoFornecedor());
                    for (final CriterioAvaliacaoFornecedorDTO criterioAvaliacaoFornecedorDto : avaliacaoFornecedorDto.getListCriterioAvaliacaoFornecedor()) {

                        if (criterioAvaliacaoFornecedorDto.getValor().equalsIgnoreCase("Sim")) {
                            criterioAvaliacaoFornecedorDto.setValorInteger(1);
                        } else {
                            if (criterioAvaliacaoFornecedorDto.getValor().equalsIgnoreCase("Não")) {
                                criterioAvaliacaoFornecedorDto.setValorInteger(0);
                            } else {
                                criterioAvaliacaoFornecedorDto.setValorInteger(2);
                            }
                        }
                        criterioAvaliacaoFornecedorDto.setIdAvaliacaoFornecedor(avaliacaoFornecedorDto.getIdAvaliacaoFornecedor());
                        criterioAvaliacaoFornecedorDao.create(criterioAvaliacaoFornecedorDto);

                    }
                }

            }

            transactionControler.commit();
            transactionControler.close();
        } catch (final Exception e) {
            e.printStackTrace();
            rollbackTransaction(transactionControler, e);
        }

    }

    @Override
    public Collection findByIdFornecedor(final Integer parm) throws Exception {
        return getDao().findByIdFornecedor(parm);
    }

    @Override
    public boolean fornecedorQualificado(final Integer idFornecedor) throws Exception {
        boolean result = false;
        final Collection<AvaliacaoFornecedorDTO> colAvaliacoes = findByIdFornecedor(idFornecedor);
        if (colAvaliacoes != null) {
            for (final AvaliacaoFornecedorDTO avaliacaoFornecedorDto : colAvaliacoes) {
                if (avaliacaoFornecedorDto.getDecisaoQualificacao().equals("Q")) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}
