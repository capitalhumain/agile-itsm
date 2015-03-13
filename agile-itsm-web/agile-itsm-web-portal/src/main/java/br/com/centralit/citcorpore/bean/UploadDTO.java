package br.com.centralit.citcorpore.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citged.bean.ControleGEDDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class UploadDTO extends BaseEntity {

    private String nameFile;
    private String descricao;
    private String path;
    private String temporario;
    private String situacao;
    private String notaTecnicaUpload;
    private Integer idControleGED;
    private String versao;
    private String idMudanca;
    private String caminhoRelativo;

    /* Atributo criado para implementação do upload por serviço dentro da grid, referente ao idServico */
    private Integer idLinhaPai;

    private ControleGEDDTO controleGEDDto;

    public UploadDTO() {}

    public UploadDTO(final String nameFile, final String path) {
        this.nameFile = nameFile;
        this.path = path;
    }

}
