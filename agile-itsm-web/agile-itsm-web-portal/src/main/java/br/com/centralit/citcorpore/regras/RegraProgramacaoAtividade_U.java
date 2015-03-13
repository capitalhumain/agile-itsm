package br.com.centralit.citcorpore.regras;

import br.com.centralit.citcorpore.bean.ProgramacaoAtividadeDTO;

public class RegraProgramacaoAtividade_U extends RegraProgramacaoAtividade {

    @Override
    public java.util.Date calculaProximaExecucao(final ProgramacaoAtividadeDTO programacaoAtividadeDto, final java.util.Date dataRef) throws Exception {
        java.util.Date proximaExecucao = null;
        if (programacaoAtividadeDto.getDataInicio().compareTo(dataRef) >= 0) {
            proximaExecucao = programacaoAtividadeDto.getDataInicio();
        }
        return proximaExecucao;
    }

    @Override
    public void valida(final ProgramacaoAtividadeDTO programacaoAtividadeDto) throws Exception {}

    @Override
    public String getDetalhamento(final ProgramacaoAtividadeDTO programacaoAtividadeDto) throws Exception {
        final String descricao = "Às " + programacaoAtividadeDto.getHoraInicio();
        return descricao;
    }

}
