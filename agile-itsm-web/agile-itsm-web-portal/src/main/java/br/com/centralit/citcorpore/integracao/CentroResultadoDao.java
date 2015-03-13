package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class CentroResultadoDao extends CrudDaoDefaultImpl {

    public CentroResultadoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    private static final String SQL_GET_LISTA_SEM_PAI = "select " + "idcentroresultado, codigocentroresultado, nomecentroresultado, "
            + "idcentroresultadopai, permiterequisicaoproduto, situacao " + "from " + "centroresultado " + "where " + "idcentroresultadopai is null "
            + "order by " + "codigocentroresultado";

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idcentroresultado", "idCentroResultado", true, true, false, false));
        listFields.add(new Field("codigocentroresultado", "codigoCentroResultado", false, false, false, false));
        listFields.add(new Field("nomecentroresultado", "nomeCentroResultado", false, false, false, false));
        listFields.add(new Field("idcentroresultadopai", "idCentroResultadoPai", false, false, false, false));
        listFields.add(new Field("permiterequisicaoproduto", "permiteRequisicaoProduto", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "centroresultado";
    }

    @Override
    public Collection list() throws PersistenceException {

        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idCentroResultado"));

        return super.list(ordenacao);
    }

    public Collection listAtivos() throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("situacao", "=", "A"));
        ordenacao.add(new Order("nomeCentroResultado"));

        return super.findByCondition(condicao, ordenacao);
    }

    public Collection listPermiteRequisicaoProduto() throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("situacao", "=", "A"));
        condicao.add(new Condition("permiteRequisicaoProduto", "=", "S"));
        ordenacao.add(new Order("nomeCentroResultado"));

        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findSemPai() throws PersistenceException {

        final Object[] objs = new Object[] {};
        final List lista = this.execSQL(SQL_GET_LISTA_SEM_PAI, objs);

        final List listRetorno = new ArrayList<>();

        listRetorno.add("idCentroResultado");
        listRetorno.add("codigoCentroResultado");
        listRetorno.add("nomeCentroResultado");
        listRetorno.add("idCentroResultadoPai");
        listRetorno.add("permiteRequisicaoProduto");
        listRetorno.add("situacao");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);

        return result;
    }

    public Collection findByIdPai(final Integer idPai) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idCentroResultadoPai", "=", idPai));
        ordenacao.add(new Order("codigoCentroResultado"));

        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public Class getBean() {
        return CentroResultadoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return super.find(arg0, null);
    }

    public boolean temFilhos(final int idCentroResultado) {
        boolean resposta = false;

        try {
            final List resultadoSQL = this.execSQL(String.format("select * from centroresultado where idcentroresultadopai = %d", idCentroResultado), null);

            if (!resultadoSQL.isEmpty()) {
                resposta = true;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return resposta;
    }

    public Collection findByIdAlcada(final Integer idAlcada) throws PersistenceException {
        List resultado = null;

        if (idAlcada != null && idAlcada > 0) {
            try {
                resultado = this.execSQL(String.format("select * from alcadacentroresultado where idalcada = %d", idAlcada), null);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    /**
     * Metodo que consulta todos os centro de custos filhos vinculados a uma alçada
     *
     * @param idPai
     *            = Centro de resultado Pai
     * @return
     * @throws Exception
     */
    public Collection consultarCentroDeCustoComVinculoNaAlcada(final Integer idPai) throws PersistenceException {

        final List resultado = null;

        if (idPai != null && idPai > 0) {
            try {

                final List lista = this.execSQL("select * from centroresultado where idcentroresultado in "
                        + "(select idcentroresultado from alcadacentroresultado) and idcentroresultadopai = " + idPai, null);

                final List listRetorno = new ArrayList<>();

                listRetorno.add("idCentroResultado");
                listRetorno.add("codigoCentroResultado");
                listRetorno.add("nomeCentroResultado");
                listRetorno.add("idCentroResultadoPai");
                listRetorno.add("permiteRequisicaoProduto");
                listRetorno.add("situacao");

                final List result = engine.listConvertion(this.getBean(), lista, listRetorno);

                return result;

            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    /**
     * Metodo que consulta todos os centro de custos filhos vinculados a uma alçada de viagem
     *
     * @param idPai
     *            = Centro de resultado Pai
     * @return
     * @throws Exception
     */
    public Collection consultarCentroDeCustoComVinculoViagemNaAlcada(final Integer idPai) throws PersistenceException {

        final List resultado = null;

        if (idPai != null && idPai > 0) {
            try {

                final List lista = this.execSQL("select * from centroresultado where idcentroresultado in "
                        + "(select distinct idcentroresultado from alcadacentroresultado acr inner join alcada a on a.idalcada = acr.idalcada "
                        + " where a.nomealcada like '%Viagem%') and idcentroresultadopai = " + idPai, null);

                final List listRetorno = new ArrayList<>();

                listRetorno.add("idCentroResultado");
                listRetorno.add("codigoCentroResultado");
                listRetorno.add("nomeCentroResultado");
                listRetorno.add("idCentroResultadoPai");
                listRetorno.add("permiteRequisicaoProduto");
                listRetorno.add("situacao");

                final List result = engine.listConvertion(this.getBean(), lista, listRetorno);

                return result;

            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    public Collection<CentroResultadoDTO> findPaisVinculados(final Integer idSolicitante, final String tipoAlcada) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();

        sb.append("select centroresultado.idcentroresultado, centroresultado.codigocentroresultado, centroresultado.nomecentroresultado,");
        sb.append("centroresultado.idcentroresultadopai, centroresultado.permiterequisicaoproduto, centroresultado.situacao ");
        sb.append("from centroresultado join (select distinct centroresultado.idcentroresultadopai as idpai from centroresultado join (");
        sb.append("select distinct alcadacentroresultado.idcentroresultado ");
        sb.append("from alcada join alcadacentroresultado on alcada.tipoalcada = ? and alcada.situacao = 'A' and ");
        sb.append("alcada.idalcada = alcadacentroresultado.idalcada and ");
        sb.append("alcadacentroresultado.idempregado=?) a on centroresultado.idcentroresultado = a.idcentroresultado) relpai on centroresultado.idcentroresultado = relpai.idpai ");
        sb.append("order by	codigocentroresultado");
        parametro.add(tipoAlcada);
        parametro.add(idSolicitante);
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idCentroResultado");
        listRetorno.add("codigoCentroResultado");
        listRetorno.add("nomeCentroResultado");
        listRetorno.add("idCentroResultadoPai");
        listRetorno.add("permiteRequisicaoProduto");
        listRetorno.add("situacao");

        final List list = this.execSQL(sb.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return this.listConvertion(CentroResultadoDTO.class, list, listRetorno);
        } else {
            return null;
        }
    }

    public Collection<CentroResultadoDTO> findPaisVinculados(final String tipoAlcada) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();

        sb.append(
                "select distinct cr.idcentroresultado, cr.codigocentroresultado, cr.nomecentroresultado, cr.idcentroresultadopai, cr.permiterequisicaoproduto, cr.situacao from centroresultado cr ")
                .append(" inner join alcadacentroresultado acr on cr.idcentroresultado = acr.idcentroresultado inner join alcada a on a.idalcada = acr.idalcada ")
                .append(" where a.tipoalcada = ? and a.situacao = 'A' order by cr.codigocentroresultado; ");
        parametro.add(tipoAlcada);
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idCentroResultado");
        listRetorno.add("codigoCentroResultado");
        listRetorno.add("nomeCentroResultado");
        listRetorno.add("idCentroResultadoPai");
        listRetorno.add("permiteRequisicaoProduto");
        listRetorno.add("situacao");

        final List list = this.execSQL(sb.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return this.listConvertion(CentroResultadoDTO.class, list, listRetorno);
        } else {
            return null;
        }
    }

    public Collection<CentroResultadoDTO> findFilhosVinculados(final Integer idPai, final Integer idSolicitante, final String tipoAlcada)
            throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();

        sb.append("select centroresultado.idcentroresultado, centroresultado.codigocentroresultado, centroresultado.nomecentroresultado,");
        sb.append("centroresultado.idcentroresultadopai, centroresultado.permiterequisicaoproduto, centroresultado.situacao ");
        sb.append("from centroresultado join (");
        sb.append("select distinct alcadacentroresultado.idcentroresultado as idfilho ");
        sb.append("from alcada join alcadacentroresultado on alcada.tipoalcada = ? and alcada.situacao = 'A' and ");
        sb.append("alcada.idalcada = alcadacentroresultado.idalcada and ");
        sb.append("alcadacentroresultado.idempregado=?) a on centroresultado.idcentroresultado = a.idfilho and ");
        sb.append("idCentroResultadoPai = ? ");
        sb.append("order by	codigocentroresultado");
        parametro.add(tipoAlcada);
        parametro.add(idSolicitante);
        parametro.add(idPai);
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idCentroResultado");
        listRetorno.add("codigoCentroResultado");
        listRetorno.add("nomeCentroResultado");
        listRetorno.add("idCentroResultadoPai");
        listRetorno.add("permiteRequisicaoProduto");
        listRetorno.add("situacao");

        final List list = this.execSQL(sb.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return this.listConvertion(CentroResultadoDTO.class, list, listRetorno);
        }
        return null;
    }

}
