package br.com.centralit.citcorpore.integracao;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ParceiroDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 * @author david.silva
 *
 */
public class ParceiroDAO extends CrudDaoDefaultImpl {

    private static final String TABLE_NAME = "rh_parceiro";

    public ParceiroDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDPARCEIRO", "idParceiro", true, true, false, false));
        listFields.add(new Field("NOME", "nome", false, false, false, false));
        listFields.add(new Field("RAZAOSOCIAL", "razaoSocial", false, false, false, false));
        listFields.add(new Field("TIPOPESSOA", "tipoPessoa", false, false, false, false));
        listFields.add(new Field("DATAALTERACAO", "dataAlteracao", false, false, false, false));
        listFields.add(new Field("ATIVO", "ativo", false, false, false, false));
        listFields.add(new Field("SITUACAO", "situacao", false, false, false, false));
        listFields.add(new Field("FORNECEDOR", "fornecedor", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * Realiza consulta por nome atraves do AutoComplete
     * 
     * @param razaoSocial
     * @return
     * @throws Exception
     */
    public List<ParceiroDTO> consultarFornecedorPorRazaoSocialAutoComplete(String razaoSocial) throws PersistenceException {

        if (razaoSocial == null) {
            razaoSocial = "";
        }

        String texto = Normalizer.normalize(razaoSocial, Normalizer.Form.NFD);
        texto = texto.replaceAll("[^\\p{ASCII}]", "");
        texto = texto.replaceAll("áàãâéêíóôõúüçÁÀÃÂÉÊÍÓÔÕÚÜÇ´`^''-+=", "aaaaeeiooouucAAAAEEIOOOUUC ");
        texto = "%" + texto.toUpperCase() + "%";

        final Object[] objs = new Object[] {texto};

        final StringBuilder sql = new StringBuilder();

        sql.append("select  p.idparceiro, p.razaosocial, p.nome ");
        sql.append(" from rh_parceiro p ");
        sql.append(" where upper(p.razaosocial) like upper(?) ");
        sql.append(" order by p.razaosocial limit 0,10");

        final List list = this.execSQL(sql.toString(), objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idParceiro");
        listRetorno.add("razaoSocial");
        listRetorno.add("nome");

        final List result = engine.listConvertion(this.getBean(), list, listRetorno);

        return result;

    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ParceiroDTO.class;
    }

}