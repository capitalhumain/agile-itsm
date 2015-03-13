package br.com.citframework.log;

public interface Log {

    String DEBUG = "DEBUG";
    String ERROR = "ERROR";
    String FATAL = "FATAL";
    String INFO = "INFO";
    String WARN = "WARN";
    String SEPARADOR = "#|#";

    void registraLog(final String mensagem, final Class<?> classe, final String tipoMensagem) throws Exception;

    void registraLog(final Exception e, final Class<?> classe, final String tipoMensagem) throws Exception;

}
