package br.com.centralit.citcorpore.negocio.alcada;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AlcadaDTO;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.ParametroDTO;
import br.com.centralit.citcorpore.integracao.EmpregadoDao;
import br.com.centralit.citcorpore.integracao.ParametroDao;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.TransactionControler;

public class AlcadaImpl implements IAlcada {

    protected BaseEntity objetoNegocioDto = null;
    protected EmpregadoDTO solicitante = null;
    protected AlcadaDTO alcadaDto = null;
    protected TransactionControler tc = null;

    public static boolean isNovaAlcada() throws Exception {
        final ParametroDTO parametroDto = new ParametroDao().getValue("ALCADA", "NOVA_ALCADA", new Integer(1));
        return parametroDto != null && parametroDto.getValor() != null && parametroDto.getValor().equalsIgnoreCase("S");
    }

    @Override
    public AlcadaDTO determinaAlcada(final BaseEntity objetoNegocioDto, final CentroResultadoDTO centroCustoDto, final TransactionControler tc)
            throws Exception {
        return null;
    }

    @Override
    public void determinaResponsaveis(final GrupoDTO grupoDto, final String abrangenciaCentroCusto, final CentroResultadoDTO centroCustoDto) throws Exception {}

    protected EmpregadoDTO recuperaEmpregado(final Integer idEmpregado) throws Exception {
        final EmpregadoDTO empregadoDto = new EmpregadoDTO();
        empregadoDto.setIdEmpregado(idEmpregado);
        final EmpregadoDao empregadoDao = new EmpregadoDao();
        this.setTransacaoDao(empregadoDao);
        return (EmpregadoDTO) empregadoDao.restore(empregadoDto);
    }

    protected void setTransacaoDao(final CrudDaoDefaultImpl dao) throws Exception {
        if (tc != null) {
            dao.setTransactionControler(tc);
        }
    }

    @Override
    public boolean permiteResponsavel(final Integer idEmpregado) throws Exception {
        return false;
    }

}
