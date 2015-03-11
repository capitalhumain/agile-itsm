package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class HistoricoValorDTO extends ValorDTO {

    private static final long serialVersionUID = -6847076405909756057L;

    @Getter
    @Setter
    private Integer idHistoricoValor;

    @Getter
    @Setter
    private Integer idHistoricoIC;

    @Getter
    @Setter
    private Timestamp dataHoraAlteracao;

    @Getter
    @Setter
    private Integer idAutorAlteracao;

    @Getter
    @Setter
    private String baseLine;

}
