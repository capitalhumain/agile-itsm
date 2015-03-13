package br.com.centralit.citcorpore.batch;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.centralit.citcorpore.bean.ExecucaoBatchDTO;
import br.com.centralit.citcorpore.bean.ProcessamentoBatchDTO;
import br.com.centralit.citcorpore.integracao.ExecucaoBatchDao;
import br.com.centralit.citcorpore.negocio.ProcessamentoBatchService;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.JdbcEngine;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilStrings;

public class JobProcessamentoBatchExecuteSQL implements Job {

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final String strNomeJob = jobExecutionContext.getJobDetail().getName();
        System.out.println("----> Iniciando Processamento Batch: " + strNomeJob);
        final String numStr = UtilStrings.apenasNumeros(strNomeJob);
        final Integer idProcessamentoBatch = new Integer(numStr);

        ProcessamentoBatchDTO procBean = new ProcessamentoBatchDTO();
        procBean.setIdProcessamentoBatch(idProcessamentoBatch);
        ProcessamentoBatchService procService = null;
        try {
            procService = (ProcessamentoBatchService) ServiceLocator.getInstance().getService(ProcessamentoBatchService.class, null);
        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        final ExecucaoBatchDao execucaoBatchDao = new ExecucaoBatchDao();
        if (procService != null) {
            try {
                procBean = (ProcessamentoBatchDTO) procService.restore(procBean);
            } catch (final LogicException e) {
                e.printStackTrace();
                procBean = null;
            } catch (final ServiceException e) {
                e.printStackTrace();
                procBean = null;
            }
            if (procBean != null) {
                final Timestamp tsExecucao = UtilDatas.getDataHoraAtual();
                if (procBean.getSituacao().equalsIgnoreCase("A")) {
                    String retorno = "";
                    System.out.println("JOB INICIANDO EXECUCAO -> " + procBean.getIdProcessamentoBatch() + " --> " + procBean.getExpressaoCRON() + " ("
                            + procBean.getDescricao() + ")");
                    if (procBean.getTipo().equalsIgnoreCase("S")) {
                        retorno = this.executaSQL(procBean.getConteudo(), strNomeJob);
                    } else {
                        try {
                            retorno = this.executaClasse(procBean.getConteudo(), strNomeJob, jobExecutionContext);
                        } catch (final ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    final ExecucaoBatchDTO execDto = new ExecucaoBatchDTO();
                    execDto.setIdProcessamentoBatch(idProcessamentoBatch);
                    execDto.setDataHora(tsExecucao);
                    execDto.setConteudo(retorno);
                    try {
                        execucaoBatchDao.create(execDto);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    final ExecucaoBatchDTO execDto = new ExecucaoBatchDTO();
                    execDto.setIdProcessamentoBatch(idProcessamentoBatch);
                    execDto.setDataHora(tsExecucao);
                    execDto.setConteudo("PROCESSAMENTO INATIVO! CADASTRO INATIVO!");
                    try {
                        execucaoBatchDao.create(execDto);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String executaSQL(final String sql, final String nomeJob) {
        final JdbcEngine engineDb = new JdbcEngine(Constantes.getValue("DATABASE_ALIAS"), null);
        int num = 0;
        try {
            num = engineDb.execUpdate(sql, null);
            System.out.println(nomeJob + " - EXECUÇÃO OK! Número de Linhas atualizadas: " + num);
        } catch (final PersistenceException e1) {
            System.out.println(nomeJob + " - Problemas na execucao: ");
            e1.printStackTrace();
            final Writer writer = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(writer);
            e1.printStackTrace(printWriter);
            return writer.toString();
        }
        return "EXECUÇÃO OK! Número de Linhas atualizadas: " + num;
    }

    private String executaClasse(final String pathClasseParm, final String nomeJob) {
        Class classe = null;
        try {
            classe = Class.forName(pathClasseParm);
        } catch (final ClassNotFoundException e1) {
            System.out.println(nomeJob + " - Problemas na execucao: ");
            e1.printStackTrace();
            final Writer writer = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(writer);
            e1.printStackTrace(printWriter);
            return writer.toString();
        }
        Object objeto = null;
        try {
            objeto = classe.newInstance();
        } catch (final InstantiationException e1) {
            System.out.println(nomeJob + " - Problemas na execucao: ");
            e1.printStackTrace();
            final Writer writer = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(writer);
            e1.printStackTrace(printWriter);
            return writer.toString();
        } catch (final IllegalAccessException e1) {
            System.out.println(nomeJob + " - Problemas na execucao: ");
            e1.printStackTrace();
            final Writer writer = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(writer);
            e1.printStackTrace(printWriter);
            return writer.toString();
        }

        final Method mtd = Reflexao.findMethod("executar", objeto);
        if (mtd != null) {
            try {
                mtd.invoke(objeto, null);
            } catch (final IllegalArgumentException e1) {
                System.out.println(nomeJob + " - Problemas na execucao: ");
                e1.printStackTrace();
                final Writer writer = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(writer);
                e1.printStackTrace(printWriter);
                return writer.toString();
            } catch (final IllegalAccessException e1) {
                System.out.println(nomeJob + " - Problemas na execucao: ");
                e1.printStackTrace();
                final Writer writer = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(writer);
                e1.printStackTrace(printWriter);
                return writer.toString();
            } catch (final InvocationTargetException e1) {
                System.out.println(nomeJob + " - Problemas na execucao: ");
                e1.printStackTrace();
                final Writer writer = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(writer);
                e1.printStackTrace(printWriter);
                return writer.toString();
            }
        } else {
            System.out.println(nomeJob + " - Problemas na execucao: ");
            System.out.println(nomeJob + " PROBLEMA: METODO NAO ENCONTRADAO (executar)!");
            return "PROBLEMA: METODO NAO ENCONTRADAO (executar)!";
        }

        return "EXECUÇÃO OK!";
    }

    /*
     * Refatorando o metodo executa classe com novo paramentro de contexto
     * Alterado também o metodo a ser executado para 'execute'
     */
    private String executaClasse(final String pathClasseParm, final String nomeJob, final JobExecutionContext jobExecutionContext)
            throws JobExecutionException, ClassNotFoundException {

        final Class classe = Class.forName(pathClasseParm);

        try {
            final Object objeto = classe.newInstance();
            final Method method = Reflexao.findMethod("execute", objeto);
            if (method != null) {
                method.invoke(objeto, jobExecutionContext);
            } else {
                System.out.println(nomeJob + " - Problemas na execucao: ");
                System.out.println(nomeJob + " PROBLEMA: METODO NAO ENCONTRADAO (execute)!");
                return "PROBLEMA: METODO NAO ENCONTRADAO (execute)!";
            }
        } catch (final Exception ex) {
            System.out.println(nomeJob + " - Problemas na execucao: ");
            throw new JobExecutionException(ex.getMessage());
        }

        return "EXECUÇÃO OK!";
    }

}
