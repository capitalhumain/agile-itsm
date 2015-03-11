package br.com.centralit.citquestionario.bean;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListagemLinhaDTO extends BaseEntity {

    private static final long serialVersionUID = 7915036904228171712L;

    private String descricao;
    private Collection dados;

}
