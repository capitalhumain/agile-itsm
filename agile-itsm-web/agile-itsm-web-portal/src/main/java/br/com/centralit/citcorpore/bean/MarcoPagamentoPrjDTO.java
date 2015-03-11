package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class MarcoPagamentoPrjDTO extends BaseEntity {

    private Integer idMarcoPagamentoPrj;
    private Integer idProjeto;
    private String nomeMarcoPag;
    private java.sql.Date dataPrevisaoPag;
    private Double valorPagamento;
    private String situacao;
    private java.sql.Date dataUltAlteracao;
    private String horaUltAlteracao;
    private String usuarioUltAlteracao;

    private Collection colPerfisByMarcosFin;
    private Collection colProdutosByMarcosFin;

    private Double tempoAlocMinutosTotal;

    private Double custoPerfil;

}
