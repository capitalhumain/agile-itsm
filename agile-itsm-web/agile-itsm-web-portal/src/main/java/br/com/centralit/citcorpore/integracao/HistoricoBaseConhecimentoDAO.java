/**
 *
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.HistoricoBaseConhecimentoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author Vadoilo Damasceno
 *
 */
public class HistoricoBaseConhecimentoDAO extends CrudDaoDefaultImpl {

    public HistoricoBaseConhecimentoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDHISTORICOBASECONHECIMENTO", "idHistoricoBaseConhecimento", true, true, false, false));
        listFields.add(new Field("IDBASECONHECIMENTO", "idBaseConhecimento", true, false, false, false));
        listFields.add(new Field("IDPASTA", "idPasta", false, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));
        listFields.add(new Field("TITULO", "titulo", false, false, false, false));
        listFields.add(new Field("CONTEUDO", "conteudo", false, false, false, false));
        listFields.add(new Field("STATUS", "status", false, false, false, false));
        listFields.add(new Field("IDBASECONHECIMENTOPAI", "idBaseConhecimentoPai", false, false, false, false));
        listFields.add(new Field("DATAEXPIRACAO", "dataExpiracao", false, false, false, false));
        listFields.add(new Field("VERSAO", "versao", false, false, false, false));
        listFields.add(new Field("IDUSUARIOAUTOR", "idUsuarioAutor", false, false, false, false));
        listFields.add(new Field("IDUSUARIOAPROVADOR", "idUsuarioAprovador", false, false, false, false));
        listFields.add(new Field("FONTEREFERENCIA", "fonteReferencia", false, false, false, false));
        listFields.add(new Field("DATAPUBLICACAO", "dataPublicacao", false, false, false, false));
        listFields.add(new Field("IDNOTIFICACAO", "idNotificacao", false, false, false, false));
        listFields.add(new Field("JUSTIFICATIVAOBSERVACAO", "justificativaObservacao", false, false, false, false));
        listFields.add(new Field("FAQ", "faq", false, false, false, false));
        listFields.add(new Field("ORIGEM", "origem", false, false, false, false));
        listFields.add(new Field("ARQUIVADO", "arquivado", false, false, false, false));
        listFields.add(new Field("IDUSUARIOALTERACAO", "idUsuarioAlteracao", false, false, false, false));
        listFields.add(new Field("DATAHORAALTERACAO", "dataHoraAlteracao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "HISTORICOBASECONHECIMENTO";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idHistoricoBaseConhecimento"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return HistoricoBaseConhecimentoDTO.class;
    }

    /**
     * Retorna lista de historico de alteração da Base de Conhecimento informada.
     *
     * @param baseConhecimento
     * @return Collection<BaseConhecimentoDTO>
     * @throws Exception
     * @author Thays.araujo
     */
    public Collection<HistoricoBaseConhecimentoDTO> obterHistoricoDeAlteracao(final HistoricoBaseConhecimentoDTO historicoBaseConhecimentoDto)
            throws PersistenceException {
        final List parametro = new ArrayList<>();
        List lista = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        sql.append("select titulo, idpasta,versao,origem,idusuarioalteracao,datahoraalteracao ,status ");
        sql.append("from historicobaseconhecimento where idhistoricobaseconhecimento = ?");

        parametro.add(historicoBaseConhecimentoDto.getIdHistoricoBaseConhecimento());

        lista = this.execSQL(sql.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();

        listRetorno.add("titulo");
        listRetorno.add("idPasta");
        listRetorno.add("versao");
        listRetorno.add("origem");
        listRetorno.add("idUsuarioAlteracao");
        listRetorno.add("dataHoraAlteracao");
        listRetorno.add("status");

        if (lista != null && !lista.isEmpty()) {
            final List<HistoricoBaseConhecimentoDTO> listBaseConhecimentoAlteradas = this
                    .listConvertion(HistoricoBaseConhecimentoDTO.class, lista, listRetorno);
            return listBaseConhecimentoAlteradas;
        }

        return null;
    }

}
