package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.bean.TipoServicoDTO;
import br.com.centralit.citcorpore.integracao.TipoServicoDao;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudServiceImpl;

public class TipoServicoServiceEjb extends CrudServiceImpl implements TipoServicoService {

    private TipoServicoDao dao;

    @Override
    protected TipoServicoDao getDao() {
        if (dao == null) {
            dao = new TipoServicoDao();
        }
        return dao;
    }

    @Override
    public boolean verificarSeTipoServicoExiste(final TipoServicoDTO tipoServicoDto) throws PersistenceException, ServiceException {
        return this.getDao().verificarSeTipoServicoExiste(tipoServicoDto);
    }

    /**
     * Metodo responsavel por verificar se existe vinculo entre o tipo de serviço e cadastro de serviço
     *
     * @param idTipoServico
     * @author Ezequiel
     * @throws Exception
     * @throws PersistenceException
     * @throws Exception
     * @data 25/11/2014
     */
    @Override
    public boolean existeVinculadoCadastroServico(final Integer idTipoServico) throws PersistenceException, Exception {
        return this.getDao().existeVinculadoCadastroServico(idTipoServico);
    }

}
