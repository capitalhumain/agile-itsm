package br.com.citframework.log;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.dto.LogDados;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilTratamentoArquivos;

/**
 * @author ronnie.lopes
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DbLogArquivo implements Log {

    private String pathArqBancoLog;
    private String fileArqBancoLog;
    private String extArqBancoLog;

    public DbLogArquivo() {
        this.inicializarParametros();
    }

    /**
     * Inicializa o DbLogArquivo com o caminho da gravação do arquivo, nome do arquivo, e extensão do arquivo
     *
     * @author ronnie.lopes
     */
    private void inicializarParametros() {
        try {

            pathArqBancoLog = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.PATH_ARQ_BANCO_LOG,
                    "C:/Program Files/jboss/server/default/deploy/CitCorpore.war/logBancoDados");
            fileArqBancoLog = "/log_DB_citsmart";
            extArqBancoLog = "txt";

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Monta a estrutura do log(nome do arquivo e mensagem) e envia para registrar no arquivo TXT
     *
     * @author ronnie.lopes
     */
    @Override
    public void registraLog(final String mensagem, final Class classe, final String tipoMensagem) throws Exception {
        final Date dataAtual = UtilDatas.getDataAtual();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String nomeArquivo = pathArqBancoLog + fileArqBancoLog + "_" + sdf.format(dataAtual) + "." + extArqBancoLog;
        this.manterPastaArqUltimoCincoDias(pathArqBancoLog);
        synchronized (nomeArquivo) {
            final List lista = new ArrayList<>();
            lista.add("[" + tipoMensagem + "] - " + classe.getName() + " - " + mensagem);

            UtilTratamentoArquivos.geraFileTXT(nomeArquivo, lista, true);
        }
    }

    /**
     * Monta a estrutura do log com tratamento de excessão
     *
     * @author ronnie.lopes
     */
    @Override
    public void registraLog(final Exception e, final Class classe, final String tipoMensagem) throws Exception {
        final Date dataAtual = UtilDatas.getDataAtual();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String nomeArquivo = pathArqBancoLog + fileArqBancoLog + "_" + sdf.format(dataAtual) + "." + extArqBancoLog;
        this.manterPastaArqUltimoCincoDias(pathArqBancoLog);
        final List lista = new ArrayList<>();
        lista.add("[" + tipoMensagem + "] - " + sdf.format(dataAtual) + " - " + classe.getName() + " - Exception:");
        synchronized (nomeArquivo) {
            final FileOutputStream fos = new FileOutputStream(nomeArquivo, true);
            final PrintStream out = new PrintStream(fos);
            UtilTratamentoArquivos.geraFileTXT(nomeArquivo, lista, out);
            e.printStackTrace(out);
            try {
                fos.close();
            } catch (final Exception e1) {
                System.out.println("Erro ao fechar arquivo de log: " + nomeArquivo);
                e1.printStackTrace();
            }
        }
    }

    /**
     * Verifica se o arquivo é do DbLogArquivo e Mantém apenas os arquivos dos últimos 5 dias,
     * excluindo o arquivo que foi criado a 6 dias atrás referente a data atual
     *
     * @author ronnie.lopes
     */
    public void manterPastaArqUltimoCincoDias(final String localArquivo) {
        final File localArq = new File(localArquivo);
        final File[] arquivosPasta = localArq.listFiles(new FileFilter() {

            @Override
            public boolean accept(final File pathname) {
                return pathname.getName().toLowerCase().contains("log_db_citsmart");
            }
        });

        if (arquivosPasta != null && arquivosPasta.length > 0 && arquivosPasta.length > 6 && arquivosPasta != null) {
            final Date dataAtual = UtilDatas.getDataAtual();

            for (final File arquivo : arquivosPasta) {
                final File[] arquivosPastaCont = localArq.listFiles(new FileFilter() {

                    @Override
                    public boolean accept(final File pathname) {
                        return pathname.getName().toLowerCase().contains("log_db_citsmart");
                    }
                });
                if (arquivosPastaCont.length > 6) {
                    final long milesegDataModifArq = arquivo.lastModified();
                    final Date dataModifArq = new Date(milesegDataModifArq);
                    final Integer difDiasDatas = UtilDatas.dataDiff(dataModifArq, dataAtual);

                    if (difDiasDatas > 5) {
                        arquivo.delete();
                    }
                } else {
                    return;
                }
            }
        }
    }

    /**
     * Formata em string o objeto logDados
     *
     * @author ronnie.lopes
     * @throws Exception
     */
    public String formatarStringLogDados(final LogDados logDados) throws Exception {
        final StringBuilder texto = new StringBuilder();

        if (logDados != null) {

            texto.append(" Data Hora Atualização: " + logDados.getDtAtualizacao() + " | " + " Operação: " + logDados.getOperacao() + " | " + " Dados: "
                    + logDados.getDados() + " | " + " Id Usuário: " + logDados.getIdUsuario() + " | " + " Nome Usuário: " + logDados.getNomeUsuario() + " | "
                    + " Local Origem: " + logDados.getLocalOrigem() + " | " + " Nome Tabela: " + logDados.getNomeTabela());
            return texto.toString();

        }
        return null;
    }

}
