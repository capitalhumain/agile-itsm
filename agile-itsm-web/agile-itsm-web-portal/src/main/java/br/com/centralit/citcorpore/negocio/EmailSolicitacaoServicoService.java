package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.EmailSolicitacaoServicoDTO;
import br.com.citframework.service.CrudService;

public interface EmailSolicitacaoServicoService extends CrudService {
    public EmailSolicitacaoServicoDTO listSituacao(String messageid) throws Exception;
    public Collection<EmailSolicitacaoServicoDTO> getEmailByOrigem(String origem) throws Exception;
    public EmailSolicitacaoServicoDTO getEmailByIdSolicitacaoAndOrigem(Integer idSolicitacao, String origem) throws Exception;
    public EmailSolicitacaoServicoDTO getEmailByIdMessageAndOrigem(String idMessage, String origem) throws Exception;
    public EmailSolicitacaoServicoDTO getEmailByIdMessage(String idMessage) throws Exception;
    public BaseEntity createWithID( BaseEntity model) throws Exception;
}

