package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.DateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AprovacaoProposta")
public class AprovacaoPropostaDTO extends BaseEntity {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (idEmpregado == null ? 0 : idEmpregado.hashCode());
        return result;
    }

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
        final AprovacaoPropostaDTO other = (AprovacaoPropostaDTO) obj;
        if (idEmpregado == null) {
            if (other.idEmpregado != null) {
                return false;
            }
        } else if (!idEmpregado.equals(other.idEmpregado)) {
            return false;
        }
        return true;
    }

    private static final long serialVersionUID = 1L;

    private Integer idAprovacaoProposta;

    private Integer idRequisicaoMudanca;

    private Integer idEmpregado;

    private String nomeEmpregado;

    private String voto;

    private String comentario;

    @XmlElement(name = "dataHoraInicio")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Timestamp dataHoraInicio;

    private Integer quantidadeVotoAprovada;

    private Integer quantidadeVotoRejeitada;

    private Integer quantidadeAprovacaoProposta;

    private String dataHoraVotacao;

    /**
     * @return the idAprovacaoMudanca
     */
    public Integer getIdAprovacaoProposta() {
        return idAprovacaoProposta;
    }

    /**
     * @param idAprovacaoMudanca
     *            the idAprovacaoMudanca to set
     */
    public void setIdAprovacaoProposta(final Integer idAprovacaoProposta) {
        this.idAprovacaoProposta = idAprovacaoProposta;
    }

    /**
     * @return the idRequisicaoMudanca
     */
    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    /**
     * @param idRequisicaoMudanca
     *            the idRequisicaoMudanca to set
     */
    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    /**
     * @return the idEmpregado
     */
    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    /**
     * @param idEmpregado
     *            the idEmpregado to set
     */
    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    /**
     * @return the nomeEmpregado
     */
    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    /**
     * @param nomeEmpregado
     *            the nomeEmpregado to set
     */
    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    /**
     * @return the voto
     */
    public String getVoto() {
        return voto;
    }

    /**
     * @param voto
     *            the voto to set
     */
    public void setVoto(final String voto) {
        this.voto = voto;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario
     *            the comentario to set
     */
    public void setComentario(final String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the dataHoraInicio
     */
    public Timestamp getDataHoraInicio() {
        return dataHoraInicio;
    }

    /**
     * @param dataHoraInicio
     *            the dataHoraInicio to set
     */
    public void setDataHoraInicio(final Timestamp dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    /**
     * @return the quantidadeVotoAprovada
     */
    public Integer getQuantidadeVotoAprovada() {
        return quantidadeVotoAprovada;
    }

    /**
     * @param quantidadeVotoAprovada
     *            the quantidadeVotoAprovada to set
     */
    public void setQuantidadeVotoAprovada(final Integer quantidadeVotoAprovada) {
        this.quantidadeVotoAprovada = quantidadeVotoAprovada;
    }

    /**
     * @return the quantidadeVotoRejeitada
     */
    public Integer getQuantidadeVotoRejeitada() {
        return quantidadeVotoRejeitada;
    }

    /**
     * @param quantidadeVotoRejeitada
     *            the quantidadeVotoRejeitada to set
     */
    public void setQuantidadeVotoRejeitada(final Integer quantidadeVotoRejeitada) {
        this.quantidadeVotoRejeitada = quantidadeVotoRejeitada;
    }

    /**
     * @return the quantidadeAprovacaoMudanca
     */
    public Integer getQuantidadeAprovacaoProposta() {
        return quantidadeAprovacaoProposta;
    }

    /**
     * @param quantidadeAprovacaoMudanca
     *            the quantidadeAprovacaoMudanca to set
     */
    public void setQuantidadeAprovacaoProposta(final Integer quantidadeAprovacaoProposta) {
        this.quantidadeAprovacaoProposta = quantidadeAprovacaoProposta;
    }

    public String getDataHoraVotacao() {
        return dataHoraVotacao;
    }

    public void setDataHoraVotacao(final String dataHoraVotacao) {
        this.dataHoraVotacao = dataHoraVotacao;
    }

}
