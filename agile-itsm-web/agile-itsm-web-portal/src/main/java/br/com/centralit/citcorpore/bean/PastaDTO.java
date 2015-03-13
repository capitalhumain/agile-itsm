/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * DTO de Pasta.
 *
 * @author valdoilo.damasceno
 */
public class PastaDTO extends BaseEntity {

    private static final long serialVersionUID = -1725700162484294191L;

    private Integer id;

    private String nome;

    private Integer idPastaPai;

    private Date dataInicio;

    private Date dataFim;

    private Collection<PerfilAcessoPastaDTO> perfisDeAcesso;

    private int nivel;

    private Integer idPerfilAcesso;

    private String herdaPermissoes;

    private Integer idNotificacao;

    private String titulo;

    private String tipoNotificacao;

    private ArrayList<NotificacaoUsuarioDTO> listaDeUsuario;

    private ArrayList<NotificacaoGrupoDTO> listaDeGrupo;

    public PastaDTO(final Integer id) {
        this.id = id;
    }

    public PastaDTO() {

    }

    public String getNomeNivel() {
        if (this.getNome() == null) {
            return nome;
        }
        String str = "";
        for (int i = 0; i < this.getNivel(); i++) {
            str += "....";
        }
        return str + nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(final int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return valor do atributo id.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Define valor do atributo id.
     *
     * @param id
     */
    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return valor do atributo nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define valor do atributo nome.
     *
     * @param nome
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return valor do atributo idPastaPai.
     */
    public Integer getIdPastaPai() {
        return idPastaPai;
    }

    /**
     * Define valor do atributo idPastaPai.
     *
     * @param idPastaPai
     */
    public void setIdPastaPai(final Integer idPastaPai) {
        this.idPastaPai = idPastaPai;
    }

    /**
     * @return valor do atributo dataInicio.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define valor do atributo dataInicio.
     *
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return valor do atributo dataFim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define valor do atributo dataFim.
     *
     * @param dataFim
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return valor do atributo perfisDeAcesso.
     */
    public Collection<PerfilAcessoPastaDTO> getPerfisDeAcesso() {
        return perfisDeAcesso;
    }

    /**
     * Define valor do atributo perfisDeAcesso.
     *
     * @param perfisDeAcesso
     */
    public void setPerfisDeAcesso(final Collection<PerfilAcessoPastaDTO> perfisDeAcesso) {
        this.perfisDeAcesso = perfisDeAcesso;
    }

    /**
     * @return the idPerfilAcesso
     */
    public Integer getIdPerfilAcesso() {
        return idPerfilAcesso;
    }

    /**
     * @param idPerfilAcesso
     *            the idPerfilAcesso to set
     */
    public void setIdPerfilAcesso(final Integer idPerfilAcesso) {
        this.idPerfilAcesso = idPerfilAcesso;
    }

    /**
     * @return the herdaPermissoes
     */
    public String getHerdaPermissoes() {
        return herdaPermissoes;
    }

    /**
     * @param herdaPermissoes
     *            the herdaPermissoes to set
     */
    public void setHerdaPermissoes(final String herdaPermissoes) {
        this.herdaPermissoes = herdaPermissoes;
    }

    /**
     * @return the idNotificacao
     */
    public Integer getIdNotificacao() {
        return idNotificacao;
    }

    /**
     * @param idNotificacao
     *            the idNotificacao to set
     */
    public void setIdNotificacao(final Integer idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the tipoNotificacao
     */
    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    /**
     * @param tipoNotificacao
     *            the tipoNotificacao to set
     */
    public void setTipoNotificacao(final String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    /**
     * @return the listaDeUsuario
     */
    public ArrayList<NotificacaoUsuarioDTO> getListaDeUsuario() {
        return listaDeUsuario;
    }

    /**
     * @param listaDeUsuario
     *            the listaDeUsuario to set
     */
    public void setListaDeUsuario(final ArrayList<NotificacaoUsuarioDTO> listaDeUsuario) {
        this.listaDeUsuario = listaDeUsuario;
    }

    /**
     * @return the listaDeGrupo
     */
    public ArrayList<NotificacaoGrupoDTO> getListaDeGrupo() {
        return listaDeGrupo;
    }

    /**
     * @param litaDeGrupo
     *            the listaDeGrupo to set
     */
    public void setListaDeGrupo(final ArrayList<NotificacaoGrupoDTO> listaDeGrupo) {
        this.listaDeGrupo = listaDeGrupo;
    }

}
