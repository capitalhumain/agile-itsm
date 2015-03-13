package br.com.centralit.citcorpore.quartz.job;

import java.util.Collection;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.centralit.citcorpore.bean.RequisicaoProdutoDTO;
import br.com.centralit.citcorpore.bpm.negocio.ExecucaoRequisicaoProduto;
import br.com.centralit.citcorpore.integracao.RequisicaoProdutoDao;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;

public class VerificaRequisicoesCompra implements Job {

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        final RequisicaoProdutoDao requisicaoProdutoDao = new RequisicaoProdutoDao();
        TransactionControler tc = null;
        try {
            final Collection<RequisicaoProdutoDTO> requisicoes = requisicaoProdutoDao.consultaRequisicoesEmAndamento();
            if (requisicoes != null) {
                final ExecucaoRequisicaoProduto execucaoRequisicao = new ExecucaoRequisicaoProduto();
                for (final RequisicaoProdutoDTO requisicaoProdutoDto : requisicoes) {
                    tc = new TransactionControlerImpl(requisicaoProdutoDao.getAliasDB());
                    try {
                        tc.start();
                        execucaoRequisicao.setObjetoNegocioDto(requisicaoProdutoDto);
                        execucaoRequisicao.setTransacao(tc);
                        execucaoRequisicao.verificaExpiracao();
                        tc.commit();
                    } catch (final Exception ex) {
                        ex.printStackTrace();
                        try {
                            if (tc.isStarted()) { // Se estiver startada, entao faz roolback.
                                tc.rollback();
                            }
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    } finally {
                        try {
                            tc.close();
                            tc = null;
                        } catch (final PersistenceException e) {}
                    }
                }
            }
        } catch (final Exception e1) {
            e1.printStackTrace();
        }
    }

}
