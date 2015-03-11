package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilHTML;

public class AssinaturaAprovacaoProjetoDTO extends BaseEntity implements Comparable<AssinaturaAprovacaoProjetoDTO> {

    private Integer idAssinaturaAprovacaoProjeto;
    private Integer idProjeto;
    private Integer idEmpregadoAssinatura;
    private String papel;
    private String ordem;
    private EmpregadoDTO empregadoDTO;
    private String nome;
    private String matricula;

    public Integer getIdAssinaturaAprovacaoProjeto() {
        return idAssinaturaAprovacaoProjeto;
    }

    public void setIdAssinaturaAprovacaoProjeto(final Integer idAssinaturaAprovacaoProjeto) {
        this.idAssinaturaAprovacaoProjeto = idAssinaturaAprovacaoProjeto;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Integer getIdEmpregadoAssinatura() {
        return idEmpregadoAssinatura;
    }

    public void setIdEmpregadoAssinatura(final Integer idEmpregadoAssinatura) {
        this.idEmpregadoAssinatura = idEmpregadoAssinatura;
    }

    public String getPapel() {
        return papel;
    }

    public String getPapelHTMLEncoded() {
        return UtilHTML.encodeHTML(papel);
    }

    public void setPapel(final String papel) {
        this.papel = papel;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(final String ordem) {
        this.ordem = ordem;
    }

    public EmpregadoDTO getEmpregadoDTO() {
        return empregadoDTO;
    }

    public void setEmpregadoDTO(final EmpregadoDTO empregadoDTO) {
        this.empregadoDTO = empregadoDTO;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeHTMLEncoded() {
        return UtilHTML.encodeHTML(nome);
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMatriculaHTMLEncoded() {
        return UtilHTML.encodeHTML(matricula);
    }

    public void setMatricula(final String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int compareTo(final AssinaturaAprovacaoProjetoDTO assinatura) {
        return ordem.compareTo(assinatura.getOrdem());
    }

}
