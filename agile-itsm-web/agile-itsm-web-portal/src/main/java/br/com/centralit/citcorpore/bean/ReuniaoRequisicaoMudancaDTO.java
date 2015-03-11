package br.com.centralit.citcorpore.bean;

import lombok.Getter;
import lombok.Setter;

/*
 * author Bruno Rodrigues Franco
 */
public class ReuniaoRequisicaoMudancaDTO extends AtividadePeriodicaDTO {

    private static final long serialVersionUID = 5595137125004385542L;

    @Getter
    @Setter
    private Integer idReuniaoRequisicaoMudanca;

    @Getter
    @Setter
    private Integer idUsuario;

    @Getter
    @Setter
    private String localReuniao;

    @Getter
    @Setter
    private String horaInicio;

    @Getter
    @Setter
    private String status;

}
