package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ExecucaoDemandaDTO;
import br.com.centralit.citcorpore.bean.HistoricoExecucaoDTO;
import br.com.centralit.citcorpore.integracao.ExecucaoDemandaDao;
import br.com.centralit.citcorpore.integracao.HistoricoExecucaoDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServiceImpl;
import br.com.citframework.util.UtilDatas;

public class ExecucaoDemandaServiceEjb extends CrudServiceImpl implements ExecucaoDemandaService {

    private ExecucaoDemandaDao dao;

    @Override
    protected ExecucaoDemandaDao getDao() {
        if (dao == null) {
            dao = new ExecucaoDemandaDao();
        }
        return dao;
    }

    @Override
    public Collection getAtividadesByGrupoAndPessoa(final Integer idEmpregado, final String[] grupo) throws LogicException, ServiceException {
        try {
            final List lst = (List) this.getDao().getAtividadesByGrupoAndPessoa(idEmpregado, grupo);
            final Collection colRetorno = new ArrayList<>();
            ExecucaoDemandaDTO exec = new ExecucaoDemandaDTO();
            if (lst != null) {
                for (int i = 0; i < lst.size(); i++) {
                    exec = (ExecucaoDemandaDTO) lst.get(i);
                    if (!exec.getSituacao().equalsIgnoreCase("F")) {
                        colRetorno.add(lst.get(i));
                    }
                }
            }
            return colRetorno;
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean temAtividadeNaSequencia(final BaseEntity bean) throws LogicException, ServiceException {
        return true;
    }

    @Override
    public void updateAtribuir(final BaseEntity bean) throws LogicException, ServiceException {

    }

    @Override
    public void updateStatus(final BaseEntity bean) throws LogicException, ServiceException {
        // Instancia Objeto controlador de transacao
        final ExecucaoDemandaDao crudDao = this.getDao();
        final HistoricoExecucaoDao historicoExecucaoDao = new HistoricoExecucaoDao();
        final TransactionControler tc = new TransactionControlerImpl(crudDao.getAliasDB());

        final ExecucaoDemandaDTO execDemandaBean = (ExecucaoDemandaDTO) bean;
        try {
            // Seta o TransactionController para os DAOs
            crudDao.setTransactionControler(tc);
            historicoExecucaoDao.setTransactionControler(tc);

            // Inicia transacao
            tc.start();

            // Executa operacoes pertinentes ao negocio.
            crudDao.updateStatus(bean);

            final HistoricoExecucaoDTO historicoExecucaoBean = new HistoricoExecucaoDTO();
            historicoExecucaoBean.setData(UtilDatas.getDataAtual());
            historicoExecucaoBean.setHora(new Long(UtilDatas.getDataHoraAtual().getTime()));
            historicoExecucaoBean.setIdExecucao(execDemandaBean.getIdExecucao());
            historicoExecucaoBean.setSituacao(execDemandaBean.getSituacao());
            historicoExecucaoBean.setIdEmpregadoExecutor(execDemandaBean.getIdEmpregadoLogado());
            historicoExecucaoBean.setDetalhamento("Alteração de Situação");

            historicoExecucaoDao.create(historicoExecucaoBean);

            // Faz commit e fecha a transacao.
            tc.commit();

        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
        } finally {
            tc.closeQuietly();
        }
    }

    @Override
    public void updateFinalizar(final BaseEntity bean) throws LogicException, ServiceException {

    }

}
