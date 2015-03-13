package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.DateAdapter;

/**
 * DTO de TipoItemConfiguracao.
 *
 * @author valdoilo.damasceno
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TipoItemConfiguracao")
public class TipoItemConfiguracaoDTO extends BaseEntity {

    public TipoItemConfiguracaoDTO() {}

    public TipoItemConfiguracaoDTO(final String nome, final String tag) {
        super();
        this.nome = nome;
        this.tag = tag;
    }

    private static final long serialVersionUID = -8131964625770147319L;

    private Integer idEmpresa;

    private String nome;

    private String tag;

    private String sistema;

    private Integer categoria;

    @SuppressWarnings("rawtypes")
    private List caracteristicas;

    @XmlElement(name = "dataInicio")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataInicio;

    @XmlElement(name = "dataFim")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataFim;

    private String imagem;

}
