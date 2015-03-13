package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class EventoItemConfigRelDTO extends BaseEntity {

    private Integer idItemConfiguracao;
    private Integer idEvento;

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(final Integer idEvento) {
        this.idEvento = idEvento;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (idItemConfiguracao == null ? 0 : idItemConfiguracao.hashCode());
        return result;
    }

    /**
     * Gerado para comparar se tem Item de configuração repetido na lista de disparo de evento
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final EventoItemConfigRelDTO other = (EventoItemConfigRelDTO) obj;
        if (idItemConfiguracao == null) {
            if (other.idItemConfiguracao != null) {
                return false;
            }
        } else if (!idItemConfiguracao.equals(other.idItemConfiguracao)) {
            return false;
        }
        return true;
    }

}
