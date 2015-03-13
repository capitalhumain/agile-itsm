package br.com.centralit.citcorpore.integracao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ClienteDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.SQLConfig;

public class ClienteDao extends CrudDaoDefaultImpl {

    public ClienteDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ClienteDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDCLIENTE", "idCliente", true, true, false, false));
        listFields.add(new Field("NOMERAZAOSOCIAL", "nomeRazaoSocial", false, false, false, false));
        listFields.add(new Field("NOMEFANTASIA", "nomeFantasia", false, false, false, false));
        listFields.add(new Field("CPFCNPJ", "cpfCnpj", false, false, false, false));
        listFields.add(new Field("OBSERVACOES", "observacoes", false, false, false, false));
        listFields.add(new Field("SITUACAO", "situacao", false, false, false, false));
        listFields.add(new Field("DELETED", "deleted", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CLIENTES";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeFantasia"));
        return super.list(list);
    }

    /**
     * Metodo que verifica se existe um registro com os mesmos dados na base de dados.
     *
     * @param nomeRazaoSocial
     *            - nome da razao social do cliente.
     * @return Retorna 'true' se existir um registro igual e 'false' caso contrario.
     * @throws Exception
     */
    public boolean existeDuplicacao(final String nomeRazaoSocial) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeRazaoSocial", "=", nomeRazaoSocial));
        condicao.add(new Condition("deleted", "is", null));
        ordenacao.add(new Order("nomeRazaoSocial"));

        final List result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Encontra o Cliente pelo ID
     * 
     * @author euler.ramos
     * @throws Exception
     */
    public List<ClienteDTO> findByIdCliente(final Integer id) throws PersistenceException {
        List resp = new ArrayList<>();

        final Collection fields = this.getFields();
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        String campos = "";
        for (final Iterator it = fields.iterator(); it.hasNext();) {
            final Field field = (Field) it.next();
            if (!campos.trim().equalsIgnoreCase("")) {
                campos = campos + ",";
            }
            campos = campos + field.getFieldDB();
            listRetorno.add(field.getFieldClass());
        }

        final String sql = "SELECT " + campos + " FROM " + this.getTableName()
                + " WHERE idcliente=? and ((deleted is NULL)OR(deleted<>'y')) ORDER BY idcliente";
        parametro.add(id);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<ClienteDTO>() : result;
    }

    /**
     * Encontra o cliente pela Razão Social
     * 
     * @author euler.ramos
     * @throws Exception
     */
    public List<ClienteDTO> findByRazaoSocial(final String razaoSocial) throws PersistenceException {
        List resp = new ArrayList<>();

        final Collection fields = this.getFields();
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        String campos = "";
        for (final Iterator it = fields.iterator(); it.hasNext();) {
            final Field field = (Field) it.next();
            if (!campos.trim().equalsIgnoreCase("")) {
                campos = campos + ",";
            }
            campos = campos + field.getFieldDB();
            listRetorno.add(field.getFieldClass());
        }
        final String sql = "SELECT " + campos + " FROM " + this.getTableName()
                + " WHERE nomerazaosocial=? and ((deleted is NULL)OR(deleted<>'y')) ORDER BY nomerazaosocial";
        parametro.add(razaoSocial);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<ClienteDTO>() : result;
    }

    /**
     * Verifica se existe cliente vinculado à algum contrato.
     * 
     * @param idCliente
     * @return
     * @throws Exception
     * @author mario.haysaki
     */
    public boolean clienteVinculadoContrato(final Integer idCliente) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append(" select count(*) from contratos co ");
        sql.append(" inner join clientes cl on co.idcliente = cl.idcliente ");
        sql.append(" where cl.idcliente = ? and (co.deleted <> 'y' or co.deleted is null) and co.situacao = 'A' ");

        parametro.add(idCliente);

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        Long total = 0l;
        BigDecimal totalLinhaBigDecimal;
        Integer totalLinhaInteger;
        if (lista != null) {
            final Object[] totalLinha = (Object[]) lista.get(0);
            if (totalLinha != null && totalLinha.length > 0) {
                if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.POSTGRESQL)
                        || CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.MYSQL)) {
                    total = (Long) totalLinha[0];
                }
                if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.ORACLE)) {
                    totalLinhaBigDecimal = (BigDecimal) totalLinha[0];
                    total = totalLinhaBigDecimal.longValue();
                }
                if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.SQLSERVER)) {
                    totalLinhaInteger = (Integer) totalLinha[0];
                    total = Long.valueOf(totalLinhaInteger);
                }
            }
        }

        if (total > 0) {
            return true;
        }
        return false;
    }

}
