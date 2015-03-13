package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CotacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class CotacaoDao extends CrudDaoDefaultImpl {

    public CotacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idCotacao", "idCotacao", true, true, false, false));
        listFields.add(new Field("idEmpresa", "idEmpresa", false, false, false, false));
        listFields.add(new Field("identificacao", "identificacao", false, false, false, false));
        listFields.add(new Field("dataHoraCadastro", "dataHoraCadastro", false, false, false, false));
        listFields.add(new Field("dataFinalPrevista", "dataFinalPrevista", false, false, false, false));
        listFields.add(new Field("idResponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("observacoes", "observacoes", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Cotacao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return CotacaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public void atualizaSituacao(final CotacaoDTO cotacaoDto) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + this.getTableName() + " SET situacao = ? WHERE (idCotacao = ?)");
        final Object[] params = {cotacaoDto.getSituacao(), cotacaoDto.getIdCotacao()};
        try {
            this.execUpdate(sql.toString(), params);
        } catch (final PersistenceException e) {
            System.out.println("Problemas com atualização cotação.");
            e.printStackTrace();
        }
    }

}
