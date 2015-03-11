package br.com.centralit.citcorpore.negocio;
import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.service.CrudService;
public interface ComplemInfSolicitacaoServicoService extends CrudService {
    public BaseEntity deserializaObjeto(String serialize) throws Exception;
    public void validaCreate(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void validaDelete(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void validaUpdate(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;

    public BaseEntity create(TransactionControler tc, SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void update(TransactionControler tc, SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void delete(TransactionControler tc, SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    
    public void preparaSolicitacaoParaAprovacao(SolicitacaoServicoDTO solicitacaoDto, ItemTrabalho itemTrabalho, String aprovacao, Integer idJustificativa, String observacoes) throws Exception;
    public String getInformacoesComplementaresFmtTexto(SolicitacaoServicoDTO solicitacaoDto, ItemTrabalho itemTrabalho) throws Exception;
}
