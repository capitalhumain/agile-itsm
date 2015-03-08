package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;
import java.util.Collection;

import br.com.centralit.citcorpore.bean.RelatorioDocumentosAcessadosBaseConhecimentoDTO;
import br.com.citframework.service.CrudService;

public interface RelatorioDocumentosAcessadosBaseConhecimentoService extends CrudService {

	Collection<RelatorioDocumentosAcessadosBaseConhecimentoDTO> listarDocumentosAcessadosBaseConhecimentoAnalitico(RelatorioDocumentosAcessadosBaseConhecimentoDTO relatorioDocumentosAcessadosBaseConhecimentoDTO);
	ArrayList<RelatorioDocumentosAcessadosBaseConhecimentoDTO> listarDocumentosAcessadosBaseConhecimentoResumido(RelatorioDocumentosAcessadosBaseConhecimentoDTO relatorioDocumentosAcessadosBaseConhecimentoDTO);
}