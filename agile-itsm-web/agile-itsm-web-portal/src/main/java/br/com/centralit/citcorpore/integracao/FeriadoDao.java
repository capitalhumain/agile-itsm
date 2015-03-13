package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.FeriadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

public class FeriadoDao extends CrudDaoDefaultImpl {

    public FeriadoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idFeriado", "idFeriado", true, true, false, false));
        listFields.add(new Field("data", "data", false, false, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("abrangencia", "abrangencia", false, false, false, false));
        listFields.add(new Field("idUf", "idUf", false, false, false, false));
        listFields.add(new Field("idCidade", "idCidade", false, false, false, false));
        listFields.add(new Field("recorrente", "recorrente", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {

        return "Feriado";
    }

    @Override
    public Class getBean() {
        return FeriadoDTO.class;
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("data");
    }

    public boolean isFeriado(final Date data, final Integer idCidade, final Integer idUf) throws Exception {
        final String SQL = "SELECT data FROM Feriado " + " WHERE (data = ? AND abrangencia IN ('N','I')) " + "    OR (data = ? AND abrangencia IN ('N','I')) "
                + "    OR (data = ? AND ((? <> 0 AND idUf = ?) OR (? <> 0 AND idCidade = ?)))"
                + "    OR (data = ? AND ((? <> 0 AND idUf = ?) OR (? <> 0 AND idCidade = ?)))";

        String dataStr = UtilDatas.dateToSTR(data);
        dataStr = dataStr.substring(0, 6) + "1900";
        final Date dataAux = UtilDatas.strToSQLDate(dataStr);

        Integer idCidadeParm = 0;
        if (idCidade != null) {
            idCidadeParm = idCidade;
        }
        Integer idUfParm = 0;
        if (idUf != null) {
            idUfParm = idUf;
        }

        final Object[] objs = new Object[] {dataAux, data, dataAux, idUfParm, idUfParm, idCidadeParm, idCidadeParm, data, idUfParm, idUfParm, idCidadeParm,
                idCidadeParm};

        final List lista = this.execSQL(SQL, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("data");
        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result != null && result.size() > 0;
    }

}
