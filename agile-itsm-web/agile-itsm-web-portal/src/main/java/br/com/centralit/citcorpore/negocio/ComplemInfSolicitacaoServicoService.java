package br.com.centralit.citcorpore.negocio;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.service.CrudService;

public interface ComplemInfSolicitacaoServicoService extends CrudService {

    BaseEntity deserializaObjeto(final String serialize) throws Exception;

    void validaCreate(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception;

    void validaDelete(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception;

    void validaUpdate(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception;

    BaseEntity create(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception;

    void update(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception;

    void delete(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception;

    void preparaSolicitacaoParaAprovacao(final SolicitacaoServicoDTO solicitacaoDto, final ItemTrabalho itemTrabalho, final String aprovacao,
            final Integer idJustificativa, final String observacoes) throws Exception;

    String getInformacoesComplementaresFmtTexto(final SolicitacaoServicoDTO solicitacaoDto, final ItemTrabalho itemTrabalho) throws Exception;
}
