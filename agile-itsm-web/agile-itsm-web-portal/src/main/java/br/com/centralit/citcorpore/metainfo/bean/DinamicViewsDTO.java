package br.com.centralit.citcorpore.metainfo.bean;

import java.util.Collection;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.agileitsm.model.support.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class DinamicViewsDTO extends BaseEntity {

    private static final long serialVersionUID = -843622611996605689L;

    private Integer idVisao;
    private Integer idVisaoEdit;
    private Integer idVisaoPesquisa;
    private Integer dinamicViewsIdVisao;
    private Integer dinamicViewsIdVisaoPesquisaSelecionada;
    private String dinamicViewsAcaoPesquisaSelecionada;
    private VisaoDTO visaoDto;
    private Collection colGrupos;

    private String dinamicViewsJson_data;
    private String jsonMatriz;
    private String jsonDataEdit;
    private String dinamicViewsDadosAdicionais;
    private String dinamicViewsJson_tempData;
    private String keyControl;

    private Map<String, Object> dinamicViewsMapDadosAdicional;

    private String modoExibicao;
    private Integer idFluxo;
    private Integer idTarefa;
    private String acaoFluxo;

    private String identificacao;

    private String saveInfo;

    private String msgRetorno = "";

    private boolean abortFuncaoPrincipal = false;

}
