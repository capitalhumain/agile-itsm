package br.com.citframework.dto;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author karem.ricarte
 *
 */
public class LogTabela extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long idLog;
    private String nomeTabela;

    public Long getIdLog() {
        return idLog;
    }

    public void setIdLog(final Long idLog) {
        this.idLog = idLog;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(final String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

}
