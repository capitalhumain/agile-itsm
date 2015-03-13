package br.com.centralit.citcorpore.negocio.alcada;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AlcadaDTO;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.citframework.integracao.TransactionControler;

public interface IAlcada {

    AlcadaDTO determinaAlcada(final BaseEntity objetoNegocioDto, final CentroResultadoDTO centroCustoDto, final TransactionControler tc) throws Exception;

    void determinaResponsaveis(final GrupoDTO grupoDto, final String abrangenciaCentroCusto, final CentroResultadoDTO centroCustoDto) throws Exception;

    boolean permiteResponsavel(final Integer idEmpregado) throws Exception;

}
