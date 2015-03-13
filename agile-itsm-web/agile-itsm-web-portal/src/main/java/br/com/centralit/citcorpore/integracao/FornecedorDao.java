package br.com.centralit.citcorpore.integracao;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.FornecedorDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.SQLConfig;

public class FornecedorDao extends CrudDaoDefaultImpl {

    public FornecedorDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idfornecedor", "idFornecedor", true, true, false, false));
        listFields.add(new Field("razaosocial", "razaoSocial", false, false, false, false));
        listFields.add(new Field("nomefantasia", "nomeFantasia", false, false, false, false));
        listFields.add(new Field("cnpj", "cnpj", false, false, false, false));
        listFields.add(new Field("email", "email", false, false, false, false));
        listFields.add(new Field("observacao", "observacao", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("telefone", "telefone", false, false, false, false));
        listFields.add(new Field("fax", "fax", false, false, false, false));
        listFields.add(new Field("idendereco", "idEndereco", false, false, false, false));
        listFields.add(new Field("nomecontato", "nomeContato", false, false, false, false));
        listFields.add(new Field("inscricaomunicipal", "inscricaoMunicipal", false, false, false, false));
        listFields.add(new Field("inscricaoestadual", "inscricaoEstadual", false, false, false, false));
        listFields.add(new Field("tipopessoa", "tipoPessoa", false, false, false, false));
        listFields.add(new Field("responsabilidades", "responsabilidades", false, false, false, false));
        listFields.add(new Field("idTipoRegistro", "idTipoRegistro", false, false, false, false));
        listFields.add(new Field("idFrequencia", "idFrequencia", false, false, false, false));
        listFields.add(new Field("idFormaContato", "idFormaContato", false, false, false, false));
        listFields.add(new Field("ativ_responsabilidades", "ativ_responsabilidades", false, false, false, false));
        listFields.add(new Field("gerenciamentodesacordo", "gerenciamentodesacordo", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Fornecedor";
    }

    @Override
    public Collection list() throws PersistenceException {
        String sql = "SELECT " + this.getNamesFieldsStr() + " FROM " + this.getTableName() + " WHERE ";
        if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.POSTGRESQL)) {
            sql += "UPPER(deleted) IS NULL OR UPPER(deleted) = 'N' ";
        } else {
            sql += "deleted IS NULL OR deleted = 'N' ";
        }
        sql += "ORDER BY razaoSocial ";

        final List lst = this.execSQL(sql, null);

        return this.listConvertion(this.getBean(), lst, this.getListNamesFieldClass());
    }

    @Override
    public Class getBean() {
        return FornecedorDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Retorna uma lista de escopo de fornecimento de acordo com o fornecedor passado
     *
     * @param fornecedorProdutoDto
     * @return Collection<FornecedorProdutoDTO>
     * @throws Exception
     * @author Thays.araujo
     *
     */
    public Collection<FornecedorDTO> listEscopoFornecimento(final FornecedorDTO fornecedorDto) throws PersistenceException {

        final List parametro = new ArrayList<>();
        final List listaRetornor = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select fornecedor.nomeFantasia, nomeProduto, nomeMarca ");
        sql.append("from fornecedorproduto ");
        sql.append("inner join tipoproduto tipo on tipo.idTipoProduto = fornecedorproduto.idTipoProduto ");
        sql.append("inner join fornecedor fornecedor on fornecedor.idFornecedor = fornecedorproduto.idFornecedor ");
        sql.append(" left join marca marca on marca.idMarca = fornecedorproduto.idMarca ");
        if (fornecedorDto.getIdFornecedor() != null) {
            sql.append("where fornecedor.idFornecedor = ? ");
            parametro.add(fornecedorDto.getIdFornecedor());
        }

        list = this.execSQL(sql.toString(), parametro.toArray());

        listaRetornor.add("nomeFantasia");
        listaRetornor.add("nomeProduto");
        listaRetornor.add("marca");

        if (list != null && !list.isEmpty()) {

            final Collection<FornecedorDTO> listEscopoFornecimento = this.listConvertion(FornecedorDTO.class, list, listaRetornor);

            return listEscopoFornecimento;

        }

        return null;
    }

    public boolean consultarCargosAtivos(final FornecedorDTO fornecedor) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "select idfornecedor From " + this.getTableName() + "  where  razaosocial = ?   and deleted is null ";

        if (fornecedor.getIdFornecedor() != null) {
            sql += " and idfornecedor <> " + fornecedor.getIdFornecedor();
        }

        parametro.add(fornecedor.getRazaoSocial());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean excluirFornecedor(final FornecedorDTO fornecedor) throws PersistenceException {
        try {
            this.execUpdate("delete from fornecedor where idfornecedor = " + fornecedor.getIdFornecedor(), null);
            return true;
        } catch (final Exception e) {
            return false;
        }

    }

    /**
     * Encontra o Fornecedor pelo ID
     *
     * @author euler.ramos
     * @throws Exception
     */
    public List<FornecedorDTO> findByIdFornecedor(final Integer id) throws PersistenceException {
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
                + " WHERE idfornecedor=? and ((deleted is NULL)OR(deleted<>'y')) ORDER BY idfornecedor";
        parametro.add(id);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<FornecedorDTO>() : result;
    }

    /**
     * Encontra o Fornecedor pela Razão Social
     *
     * @author euler.ramos
     * @throws Exception
     */
    public List<FornecedorDTO> findByRazaoSocial(final String razaoSocial) throws PersistenceException {
        List resp = new ArrayList<>();
        final String razaoSocialPar = "%" + razaoSocial + "%";

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
                + " WHERE razaosocial like ? and ((deleted is NULL)OR(deleted<>'y')) ORDER BY razaosocial";
        parametro.add(razaoSocialPar);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<FornecedorDTO>() : result;
    }

    /**
     * Realiza consulta por nome atraves do AutoComplete
     *
     * @param razaoSocial
     * @return
     * @throws Exception
     */
    public List<FornecedorDTO> consultarFornecedorPorRazaoSocialAutoComplete(String razaoSocial) throws PersistenceException {

        if (razaoSocial == null) {
            razaoSocial = "";
        }

        String texto = Normalizer.normalize(razaoSocial, Normalizer.Form.NFD);
        texto = texto.replaceAll("[^\\p{ASCII}]", "");
        texto = texto.replaceAll("áàãâéêíóôõúüçÁÀÃÂÉÊÍÓÔÕÚÜÇ´`^''-+=", "aaaaeeiooouucAAAAEEIOOOUUC ");
        texto = "%" + texto.toUpperCase() + "%";

        final Object[] objs = new Object[] {texto};

        final StringBuilder sql = new StringBuilder("select idfornecedor, razaosocial from fornecedor ").append(" where upper(razaoSocial) like upper(?)")
                .append(" order by razaosocial limit 10");

        final List list = this.execSQL(sql.toString(), objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idFornecedor");
        listRetorno.add("razaoSocial");

        final List result = engine.listConvertion(this.getBean(), list, listRetorno);

        return result;

    }

    /**
     * @param idFornecedor
     * @return
     * @throws Exception
     * @author mario.haysaki
     */
    public boolean existeFornecedorAssociadoContrato(final Integer idFornecedor) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append(" select count(*) from contratos co ");
        sql.append(" inner join fornecedor f on co.idfornecedor = f.idfornecedor ");
        sql.append(" where f.idfornecedor = ? and (co.deleted <> 'y' or co.deleted is null) and co.situacao = 'A' ");

        parametro.add(idFornecedor);

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
