package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.PermissoesFluxoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class PermissoesFluxoDao extends CrudDaoDefaultImpl {

    public PermissoesFluxoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idTipoFluxo", "idTipoFluxo", true, false, false, false));
        listFields.add(new Field("idGrupo", "idGrupo", true, false, false, false));
        listFields.add(new Field("criar", "criar", false, false, false, false));
        listFields.add(new Field("executar", "executar", false, false, false, false));
        listFields.add(new Field("delegar", "delegar", false, false, false, false));
        listFields.add(new Field("suspender", "suspender", false, false, false, false));
        listFields.add(new Field("reativar", "reativar", false, false, false, false));
        listFields.add(new Field("alterarsla", "alterarSLA", false, false, false, false));
        listFields.add(new Field("reabrir", "reabrir", false, false, false, false));
        listFields.add(new Field("cancelar", "cancelar", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "PermissoesFluxo";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return PermissoesFluxoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdTipoFluxo(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idTipoFluxo", "=", parm));
        ordenacao.add(new Order("idGrupo"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdTipoFluxo(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idTipoFluxo", "=", parm));
        super.deleteByCondition(condicao);
    }

    public List<PermissoesFluxoDTO> findByIdFluxoAndIdUsuario(final Integer idUsuario, final Integer idItemtrabalho) throws PersistenceException {
        final List param = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        sql.append("select distinct tipo.idTipoFluxo, ");
        sql.append("    perm.idGrupo, ");
        sql.append("    criar, ");
        sql.append("    executar, ");
        sql.append("    delegar, ");
        sql.append("    suspender, ");
        sql.append("    reativar, ");
        sql.append("    alterarSLA, ");
        sql.append("    reabrir, ");
        sql.append("    cancelar from bpm_tipofluxo tipo ");
        sql.append("	INNER JOIN bpm_fluxo flx on tipo.idtipofluxo = flx.idtipofluxo ");
        sql.append("	INNER JOIN permissoesFluxo perm on tipo.idtipofluxo = perm.idtipofluxo ");
        sql.append("	INNER JOIN gruposempregados gremp  ON perm.idgrupo = gremp.idgrupo ");
        sql.append("	INNER JOIN empregados emp  ON emp.idempregado = gremp.idempregado ");
        sql.append("	INNER JOIN usuario usu ON usu.idempregado = emp.idempregado ");
        sql.append("	INNER JOIN bpm_instanciafluxo  inst ON flx.idfluxo = inst.idfluxo ");
        sql.append("	INNER JOIN bpm_itemtrabalhofluxo item ON  item.idinstancia = inst.idinstancia ");
        sql.append("where item.iditemtrabalho = ? and usu.idusuario = ? ");

        param.add(idItemtrabalho);
        param.add(idUsuario);

        list = this.execSQL(sql.toString(), param.toArray());

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, this.getListRetorno());
        }
        return null;
    }

    public List getListRetorno() {
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idTipoFluxo");
        listRetorno.add("idGrupo");
        listRetorno.add("criar");
        listRetorno.add("executar");
        listRetorno.add("delegar");
        listRetorno.add("suspender");
        listRetorno.add("reativar");
        listRetorno.add("alterarSLA");
        listRetorno.add("reabrir");
        listRetorno.add("cancelar");
        return listRetorno;
    }

    public Collection findByIdGrupo(final Integer parm) throws PersistenceException {
        final String sql = "SELECT pf.idTipoFluxo, idGrupo, criar, executar, delegar, suspender, reativar, alterarsla, reabrir, cancelar FROM "
                + this.getTableName()
                + " pf JOIN bpm_tipofluxo tf ON pf.idtipofluxo = tf.idtipofluxo WHERE idgrupo = ? ORDER BY pf.idtipofluxo, criar, executar, delegar, suspender";
        final List param = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();
        param.add(parm);
        listRetorno.add("idTipoFluxo");
        listRetorno.add("idGrupo");
        listRetorno.add("criar");
        listRetorno.add("executar");
        listRetorno.add("delegar");
        listRetorno.add("suspender");
        listRetorno.add("reativar");
        listRetorno.add("alterarSLA");
        listRetorno.add("reabrir");
        listRetorno.add("cancelar");
        list = this.execSQL(sql.toString(), param.toArray());
        if (list != null && !list.isEmpty()) {

            return this.listConvertion(this.getBean(), list, listRetorno);

        } else {

            return null;
        }
    }

    public void deleteByIdGrupo(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idGrupo", "=", parm));
        super.deleteByCondition(condicao);
    }

    public boolean permissaoGrupoExecutor(final Integer idTipoMudanca, final Integer idGrupoExecutor) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        boolean resultado = false;
        final List parametro = new ArrayList<>();
        parametro.add(idGrupoExecutor);
        parametro.add(idTipoMudanca);

        sql.append("SELECT pf.idtipofluxo ");
        sql.append(" FROM permissoesFluxo pf ");
        sql.append(" INNER JOIN tipomudanca tm ON tm.idtipofluxo = pf.idtipofluxo ");
        sql.append(" INNER JOIN bpm_tipofluxo tf ON tf.idtipofluxo = tm.idtipofluxo ");
        sql.append(" where pf.idgrupo = ? ");
        sql.append(" AND tm.idtipomudanca = ? ");
        sql.append(" AND pf.criar = 'S' ");

        List result = null;
        result = this.execSQL(sql.toString(), parametro.toArray());

        if (result == null || result.size() <= 0) {
            resultado = false;
        } else {
            resultado = true;
        }

        return resultado;
    }

    public boolean permissaoGrupoExecutorLiberacao(final Integer idTipoMudanca, final Integer idGrupoExecutor) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        boolean resultado = false;
        final List parametro = new ArrayList<>();
        parametro.add(idGrupoExecutor);
        parametro.add(idTipoMudanca);

        sql.append("SELECT pf.idtipofluxo ");
        sql.append(" FROM permissoesFluxo pf ");
        sql.append(" INNER JOIN tipoliberacao tl ON tl.idtipofluxo = pf.idtipofluxo ");
        sql.append(" INNER JOIN bpm_tipofluxo tf ON tf.idtipofluxo = tl.idtipofluxo ");
        sql.append(" where pf.idgrupo = ? ");
        sql.append(" AND tl.idtipoliberacao = ? ");
        sql.append(" AND pf.criar = 'S' ");

        List result = null;
        result = this.execSQL(sql.toString(), parametro.toArray());

        if (result == null || result.size() <= 0) {
            resultado = false;
        } else {
            resultado = true;
        }

        return resultado;
    }

    public boolean permissaoGrupoExecutorProblema(final Integer idCategoriaProblema, final Integer idGrupoExecutor) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        boolean resultado = false;
        final List parametro = new ArrayList<>();
        parametro.add(idGrupoExecutor);
        parametro.add(idCategoriaProblema);

        sql.append("SELECT pf.idtipofluxo ");
        sql.append(" FROM permissoesFluxo pf ");
        sql.append(" INNER JOIN categoriaproblema tl ON tl.idtipofluxo = pf.idtipofluxo ");
        sql.append(" INNER JOIN bpm_tipofluxo tf ON tf.idtipofluxo = tl.idtipofluxo ");
        sql.append(" where pf.idgrupo = ? ");
        sql.append(" AND tl.idcategoriaproblema = ? ");
        sql.append(" AND pf.criar = 'S' ");

        List result = null;
        result = this.execSQL(sql.toString(), parametro.toArray());

        if (result == null || result.size() <= 0) {
            resultado = false;
        } else {
            resultado = true;
        }

        return resultado;
    }

    public PermissoesFluxoDTO permissaoGrupoCancelar(final Integer idGrupo, final Integer idTipoFluxo) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List lista = new ArrayList<>();

        sql.append("select cancelar from permissoesfluxo where idgrupo = ? and idtipofluxo = ?");

        parametro.add(idGrupo);
        parametro.add(idTipoFluxo);

        lista = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("cancelar");

        try {
            final List<PermissoesFluxoDTO> resultado = this.listConvertion(this.getBean(), lista, listRetorno);
            return resultado.get(0);
        } catch (final Exception e) {
            return null;
        }
    }

    public PermissoesFluxoDTO permissaoGrupoCriar(final Integer idGrupo, final Integer idTipoFluxo) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List lista = new ArrayList<>();

        sql.append("select criar from permissoesfluxo where idgrupo = ? and idtipofluxo = ?");

        parametro.add(idGrupo);
        parametro.add(idTipoFluxo);

        lista = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("criar");

        try {
            final List<PermissoesFluxoDTO> resultado = this.listConvertion(this.getBean(), lista, listRetorno);
            return resultado.get(0);
        } catch (final Exception e) {
            return null;
        }
    }

    public boolean permissaoGrupoExecutorLiberacaoServico(final Integer idGrupoExecutor, final Integer idTipoFluxo) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        boolean resultado = false;
        final List parametro = new ArrayList<>();
        parametro.add(idGrupoExecutor);
        parametro.add(idTipoFluxo);

        sql.append("SELECT pf.idtipofluxo ");
        sql.append(" FROM permissoesFluxo pf ");
        sql.append(" where pf.idgrupo = ? ");
        sql.append(" AND idtipofluxo = ? ");
        sql.append(" AND criar = 'S' ");

        List result = null;
        result = this.execSQL(sql.toString(), parametro.toArray());

        if (result == null || result.size() <= 0) {
            resultado = false;
        } else {
            resultado = true;
        }

        return resultado;
    }

}
