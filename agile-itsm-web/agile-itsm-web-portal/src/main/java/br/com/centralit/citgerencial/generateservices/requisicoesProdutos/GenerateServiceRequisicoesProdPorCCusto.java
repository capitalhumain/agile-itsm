package br.com.centralit.citgerencial.generateservices.requisicoesProdutos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.centralit.citcorpore.bean.ItemRequisicaoProdutoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoProdutoDTO;
import br.com.centralit.citcorpore.integracao.ItemRequisicaoProdutoDao;
import br.com.centralit.citcorpore.integracao.RequisicaoProdutoDao;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citgerencial.bean.GerencialGenerateService;
import br.com.citframework.util.SQLConfig;
import br.com.citframework.util.UtilDatas;

/**
 * @author rodrigo.oliveira
 * @since 14/08/2012
 */
public class GenerateServiceRequisicoesProdPorCCusto extends GerencialGenerateService {

    private HashMap novoParametro = new HashMap<>();

    @Override
    public List execute(final HashMap parametersValues, final Collection paramtersDefinition) throws ParseException {

        final Set set = parametersValues.entrySet();
        final Iterator i = set.iterator();

        while (i.hasNext()) {
            final Map.Entry entrada = (Map.Entry) i.next();
            this.getNovoParametro().put(entrada.getKey(), entrada.getValue());
        }

        final String datainicial = (String) this.getNovoParametro().get("PARAM.dataInicial");
        final String datafinal = (String) this.getNovoParametro().get("PARAM.dataFinal");

        Date datafim = new Date();
        Date datainicio = new Date();

        try {
            datainicio = UtilDatas.convertStringToDate(TipoDate.DATE_DEFAULT, datainicial, super.getLanguage(paramtersDefinition));
            datafim = UtilDatas.convertStringToDate(TipoDate.DATE_DEFAULT, datafinal, super.getLanguage(paramtersDefinition));
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(datafim);
        calendar.add(GregorianCalendar.DATE, 1);

        if (CITCorporeUtil.SGBD_PRINCIPAL.equalsIgnoreCase(SQLConfig.MYSQL)) {
            this.getNovoParametro().put("PARAM.dataInicial",
                    UtilDatas.convertDateToString(TipoDate.FORMAT_DATABASE, datainicio, super.getLanguage(paramtersDefinition)));
            this.getNovoParametro().put("PARAM.dataFinal",
                    UtilDatas.convertDateToString(TipoDate.FORMAT_DATABASE, calendar.getTime(), super.getLanguage(paramtersDefinition)));
        } else {
            this.getNovoParametro().put("PARAM.dataInicial", new java.sql.Date(datainicio.getTime()));
            this.getNovoParametro().put("PARAM.dataFinal", new java.sql.Date(calendar.getTime().getTime()));
        }

        final Collection col = new ArrayList<>();
        List listaRetorno = null;
        final RequisicaoProdutoDao requisicaoDao = new RequisicaoProdutoDao();

        try {
            final Collection<RequisicaoProdutoDTO> colRequisicoes = requisicaoDao.consultaRequisicoesPorCCusto(this.getNovoParametro());
            if (colRequisicoes != null) {
                for (final RequisicaoProdutoDTO requisicaoDto : colRequisicoes) {
                    double valor = 0;
                    requisicaoDto.setDataHoraSolicitacao(requisicaoDto.getDataHoraSolicitacao());
                    final Collection<ItemRequisicaoProdutoDTO> colItens = new ItemRequisicaoProdutoDao().findByIdSolicitacaoServico(requisicaoDto
                            .getIdSolicitacaoServico());
                    if (colItens != null) {
                        for (final ItemRequisicaoProdutoDTO itemRequisicaoDto : colItens) {
                            if (itemRequisicaoDto.getPrecoAproximado() == null) {
                                continue;
                            }
                            valor += itemRequisicaoDto.getPrecoAproximado().doubleValue() * itemRequisicaoDto.getQuantidade().intValue();
                        }
                    }
                    col.add(new Object[] {requisicaoDto.getCentroCusto(), requisicaoDto.getIdSolicitacaoServico(), requisicaoDto.getDataHoraSolicitacaoStr(),
                            requisicaoDto.getContrato(), requisicaoDto.getProjeto(), requisicaoDto.getNomeUnidadeSolicitante(), requisicaoDto.getServico(),
                            requisicaoDto.getDescrSituacao(), valor});
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (col != null && !col.isEmpty()) {
            listaRetorno = (List) col;
        } else {
            listaRetorno = new ArrayList<>();
        }

        // resetando parâmetro
        this.setNovoParametro(null);

        return listaRetorno;
    }

    public HashMap getNovoParametro() {
        return novoParametro;
    }

    public void setNovoParametro(final HashMap novoParametro) {
        this.novoParametro = novoParametro;
    }

}
