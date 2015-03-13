package br.com.centralit.citcorpore.negocio;

import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.service.CrudServiceImpl;

public abstract class ComplemInfSolicitacaoServicoServiceEjb extends CrudServiceImpl implements ComplemInfSolicitacaoServicoService {

    @Override
    public void preparaSolicitacaoParaAprovacao(final SolicitacaoServicoDTO solicitacaoDto, final ItemTrabalho itemTrabalho, final String aprovacao,
            final Integer idJustificativa, final String observacoes) throws Exception {}

    @Override
    public String getInformacoesComplementaresFmtTexto(final SolicitacaoServicoDTO solicitacaoDto, final ItemTrabalho itemTrabalho) throws Exception {
        String result = "";
        if (solicitacaoDto.getSolicitante() != null && !solicitacaoDto.getSolicitante().trim().equals("")) {
            result = "Solicitante: " + solicitacaoDto.getSolicitante() + "\n";
        }
        result += solicitacaoDto.getDescricaoSemFormatacao();
        return result;
    }

}
