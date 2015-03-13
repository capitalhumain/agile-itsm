/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CategoriaServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author rosana.godinho
 */
public class CategoriaServicoDao extends CrudDaoDefaultImpl {

    public CategoriaServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idCategoriaServico", "idCategoriaServico", true, true, false, false));
        listFields.add(new Field("idCategoriaServicoPai", "idCategoriaServicoPai", false, false, false, false));
        listFields.add(new Field("idEmpresa", "idEmpresa", false, false, false, false));
        listFields.add(new Field("nomeCategoriaServico", "nomeCategoriaServico", false, false, false, false));
        listFields.add(new Field("DataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("DataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("nomeCategoriaServicoConcatenado", "nomeCategoriaServicoConcatenado", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CATEGORIASERVICO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeCategoriaServico"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return CategoriaServicoDTO.class;
    }

    public Collection findSemPai() throws PersistenceException {
        final String sql = "SELECT idCategoriaServico, idCategoriaServicoPai, idEmpresa, nomeCategoriaServico, DataInicio FROM CATEGORIASERVICO WHERE idCategoriaServicoPai IS NULL AND dataFim IS NULL ORDER BY nomeCategoriaServico ";
        final List colDados = this.execSQL(sql, null);
        if (colDados != null) {
            final List fields = new ArrayList<>();
            fields.add("idCategoriaServico");
            fields.add("idCategoriaServicoPai");
            fields.add("idEmpresa");
            fields.add("nomeCategoriaServico");
            fields.add("dataInicio");
            return this.listConvertion(CategoriaServicoDTO.class, colDados, fields);
        }
        return null;
    }

    public Collection findByIdPai(final Integer idCategoriaPaiParm) throws PersistenceException {
        final String sql = "SELECT idCategoriaServico, idCategoriaServicoPai, idEmpresa, nomeCategoriaServico, DataInicio FROM CATEGORIASERVICO WHERE idCategoriaServicoPai = ? AND dataFim IS NULL ORDER BY nomeCategoriaServico ";
        final List colDados = this.execSQL(sql, new Object[] {idCategoriaPaiParm});
        if (colDados != null) {
            final List fields = new ArrayList<>();
            fields.add("idCategoriaServico");
            fields.add("idCategoriaServicoPai");
            fields.add("idEmpresa");
            fields.add("nomeCategoriaServico");
            fields.add("dataInicio");
            return this.listConvertion(CategoriaServicoDTO.class, colDados, fields);
        }
        return null;
    }

    /**
     * Lista os nomes da empresa.
     *
     * @param idEmpresa
     * @return
     * @throws Exception
     */
    public Collection listByEmpresa(final Integer idEmpresa) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeCategoriaServico"));
        final CategoriaServicoDTO obj = new CategoriaServicoDTO();
        obj.setIdEmpresa(idEmpresa);
        return super.find(obj, list);
    }

    /**
     * Retorna lista de Categoria Serviço ativas.
     *
     * @return Collection
     * @throws Exception
     */
    public Collection listCategoriasAtivas() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("nomeCategoriaServico"));

        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna lista de Categoria Serviço setado idPai e idFilho.
     *
     * @return Collection
     * @throws Exception
     */
    public List<CategoriaServicoDTO> listCategoriasServicoidPaiFilho(final CategoriaServicoDTO bean) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeCategoriaServico", "=", bean.getNomeCategoriaServico()));
        condicao.add(new Condition("idCategoriaServicoPai", "=", bean.getIdCategoriaServicoPai()));
        return (List<CategoriaServicoDTO>) super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna lista de Categoria Serviço setado idCategoria e pai isnull.
     *
     * @return Collection
     * @throws Exception
     */
    public List<CategoriaServicoDTO> listCategoriasServicoidPaiIsNull(final CategoriaServicoDTO bean) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeCategoriaServico", "=", bean.getNomeCategoriaServico()));
        condicao.add(new Condition("idCategoriaServicoPai", "is", null));
        return (List<CategoriaServicoDTO>) super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna lista de Categoria Serviço ativas.
     *
     * @return Collection
     * @throws Exception
     */
    public Collection listCategoriasAtivasByNomeConcatenado() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("idCategoriaServico"));

        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Verifica se Categoria possui filho.
     *
     * @param categoriaServico
     * @return - <b>True:</b> Possui filho. - <b>False: </b>Não possui.
     * @throws PersistenceException
     */
    public boolean verificarSeCategoriaPossuiFilho(final CategoriaServicoDTO categoriaServico) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametros = new ArrayList<>();

        sql.append("SELECT DISTINCT categoriafilho.nomecategoriaservico FROM categoriaservico categoriapai ");
        sql.append("INNER JOIN categoriaservico categoriafilho ON categoriapai.idcategoriaservico = categoriafilho.idcategoriaservicopai ");
        sql.append("WHERE categoriapai.idcategoriaservico = ? AND categoriafilho.datafim IS NULL");
        parametros.add(categoriaServico.getIdCategoriaServico());

        final List filhos = this.execSQL(sql.toString(), parametros.toArray());

        if (filhos != null && !filhos.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se Categoria possui Serviço associado
     *
     * @param categoriaServico
     * @return - <b>True:</b> Possui filho. - <b>False: </b>Não possui.
     * @throws PersistenceException
     */
    public boolean verificarSeCategoriaPossuiServico(final CategoriaServicoDTO categoriaServico) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametros = new ArrayList<>();

        sql.append("SELECT DISTINCT servico.nomeservico FROM categoriaservico ");
        sql.append("INNER JOIN servico ON categoriaservico.idcategoriaservico = servico.idcategoriaservico ");
        sql.append("WHERE categoriaservico.idcategoriaservico = ?");
        parametros.add(categoriaServico.getIdCategoriaServico());

        final List categoriasEncontradas = this.execSQL(sql.toString(), parametros.toArray());

        if (categoriasEncontradas != null && !categoriasEncontradas.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se categoria informada já existe.
     *
     * @param categoriaServicoDTO
     * @return true - existe; false - não existe;
     * @throws PersistenceException
     */
    public boolean verificarSeCategoriaExiste(final CategoriaServicoDTO categoriaServicoDTO) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametros = new ArrayList<>();

        sql.append("SELECT nomecategoriaservico FROM categoriaservico ");
        sql.append("WHERE datafim is null AND nomecategoriaservico like ?");

        parametros.add(categoriaServicoDTO.getNomeCategoriaServico());

        final List categorias = this.execSQL(sql.toString(), parametros.toArray());

        if (categorias != null && !categorias.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna lista de Categoria Serviço por nome.
     *
     * @return Collection
     * @throws Exception
     */
    public Collection findByNomeCategoria(final CategoriaServicoDTO categoriaServicoDTO) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("nomeCategoriaServico", "=", categoriaServicoDTO.getNomeCategoriaServico()));
        ordenacao.add(new Order("nomeCategoriaServico"));

        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Encontra a categoria de serviço pelo ID
     *
     * @author euler.ramos
     */
    public List<CategoriaServicoDTO> findByIdCategoriaServico(final Integer id) throws PersistenceException {
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
                + " WHERE idcategoriaservico=? and (datafim IS NULL) ORDER BY idcategoriaservico";
        parametro.add(id);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<CategoriaServicoDTO>() : result;
    }

    /**
     * Encontra a categoria de serviço pelo nome
     *
     * @author euler.ramos
     */
    public List<CategoriaServicoDTO> findByNomeCategoria(final String titulo) throws PersistenceException {
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
                + " WHERE nomecategoriaservico=? and (datafim IS NULL) ORDER BY nomecategoriaservico";
        parametro.add(titulo);
        resp = this.execSQL(sql, parametro.toArray());

        final List result = engine.listConvertion(this.getBean(), resp, listRetorno);
        return result == null ? new ArrayList<CategoriaServicoDTO>() : result;
    }

}
