package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class PortalDTO extends BaseEntity {

    private static final long serialVersionUID = 638687400065001805L;

    private Integer idPortal;
    private Integer idItem;
    private Double posicaoX;
    private Double posicaoY;
    private Integer idUsuario;
    private Double largura;
    private Double altura;
    private Date data;
    private Integer idServico;
    private String nomeServico;
    private Integer coluna;
    private Integer idPost;
    private Integer idCatalogoServico;
    private String filtroCatalogo;
    private Double valorTotalServico;
    private Integer idContratoUsuario;
    private String observacaoPortal;
    private String finalizaCompra;
    private String anexarArquivos;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(final Integer idItem) {
        this.idItem = idItem;
    }

    public Double getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(final Double posicaoX) {
        this.posicaoX = posicaoX;
    }

    public Double getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(final Double posicaoY) {
        this.posicaoY = posicaoY;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(final Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(final Double altura) {
        this.altura = altura;
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public Integer getIdPortal() {
        return idPortal;
    }

    public void setIdPortal(final Integer idPortal) {
        this.idPortal = idPortal;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getColuna() {
        return coluna;
    }

    public void setColuna(final Integer coluna) {
        this.coluna = coluna;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(final Integer idPost) {
        this.idPost = idPost;
    }

    public Integer getIdCatalogoServico() {
        return idCatalogoServico;
    }

    public void setIdCatalogoServico(final Integer idCatalogoServico) {
        this.idCatalogoServico = idCatalogoServico;
    }

    public String getFiltroCatalogo() {
        return filtroCatalogo;
    }

    public void setFiltroCatalogo(final String filtroCatalogo) {
        this.filtroCatalogo = filtroCatalogo;
    }

    public Double getValorTotalServico() {
        return valorTotalServico;
    }

    public void setValorTotalServico(final Double valorTotalServico) {
        this.valorTotalServico = valorTotalServico;
    }

    public Integer getIdContratoUsuario() {
        return idContratoUsuario;
    }

    public void setIdContratoUsuario(final Integer idContratoUsuario) {
        this.idContratoUsuario = idContratoUsuario;
    }

    public String getObservacaoPortal() {
        return observacaoPortal;
    }

    public void setObservacaoPortal(final String observacaoPortal) {
        this.observacaoPortal = observacaoPortal;
    }

    public String getFinalizaCompra() {
        return finalizaCompra;
    }

    public void setFinalizaCompra(final String finalizaCompra) {
        this.finalizaCompra = finalizaCompra;
    }

    public String getAnexarArquivos() {
        return anexarArquivos;
    }

    public void setAnexarArquivos(final String anexarArquivos) {
        this.anexarArquivos = anexarArquivos;
    }

}
