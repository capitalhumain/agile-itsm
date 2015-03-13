package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TipoEventoServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class TipoEventoServicoDao extends CrudDaoDefaultImpl {

    public TipoEventoServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idTipoEventoServico", "idTipoEventoServico", true, true, false, false));
        listFields.add(new Field("nomeTipoEventoServico", "nomeTipoEventoServico", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "TipoEventoServico";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return TipoEventoServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Retorna lista de Tipo Demanda por nome.
     *
     * @return Collection
     * @throws Exception
     */
    public Collection findByNome(final TipoEventoServicoDTO tipoEventoServicoDTO) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("nomeTipoEventoServico", "=", tipoEventoServicoDTO.getNomeTipoEventoServico()));
        ordenacao.add(new Order("nomeTipoEventoServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    public boolean tipoEventoVinculadoServico(final Integer idTipoEventoServico) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append(" select idtipoeventoservico from servico where idtipoeventoservico = ? and (deleted <> 'y' or deleted is null)");

        parametro.add(idTipoEventoServico);

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());
        if (lista.size() > 0) {
            return true;
        }

        return false;
    }

}
