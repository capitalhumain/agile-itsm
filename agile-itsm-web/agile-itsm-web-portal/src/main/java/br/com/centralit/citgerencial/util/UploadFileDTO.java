package br.com.centralit.citgerencial.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class UploadFileDTO extends BaseEntity {

    private static final long serialVersionUID = -4210606336617625912L;

    private String arquivo;
    private String caminhoRelativoArquivo;
    private String caminhoRealArquivo;

}
