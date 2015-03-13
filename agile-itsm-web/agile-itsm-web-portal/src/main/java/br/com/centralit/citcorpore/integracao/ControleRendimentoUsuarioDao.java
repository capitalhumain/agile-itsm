package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ControleRendimentoUsuarioDTO;
import br.com.citframework.dto.Usuario;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class ControleRendimentoUsuarioDao extends CrudDaoDefaultImpl {

    public ControleRendimentoUsuarioDao(final String aliasDB, final Usuario usuario) {
        super(aliasDB, usuario);
    }

    public ControleRendimentoUsuarioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idControleRendimentoUsuario", "idControleRendimentoUsuario", true, true, false, true));
        listFields.add(new Field("idControleRendimento", "idControleRendimento", false, false, false, false));
        listFields.add(new Field("idGrupo", "idGrupo", false, false, false, false));
        listFields.add(new Field("idUsuario", "idUsuario", false, false, false, false));
        listFields.add(new Field("qtdTotalPontos", "qtdTotalPontos", false, false, false, false));
        listFields.add(new Field("aprovacao", "aprovacao", false, false, false, false));
        listFields.add(new Field("ano", "ano", false, false, false, false));
        listFields.add(new Field("mes", "mes", false, false, false, false));
        listFields.add(new Field("qtdPontosPositivos", "qtdPontosPositivos", false, false, false, false));
        listFields.add(new Field("qtdPontosNegativos", "qtdPontosNegativos", false, false, false, false));
        listFields.add(new Field("qtdItensEntregues", "qtdItensEntregues", false, false, false, false));
        listFields.add(new Field("qtdItensRetornados", "qtdItensRetornados", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "controlerendimentousuario";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ControleRendimentoUsuarioDTO.class;
    }

    public Collection<ControleRendimentoUsuarioDTO> findByIdControleRendimentoUsuario(final Integer idGrupo, final String mes, final String ano)
            throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();
        sql.append("select idusuario,qtdpontospositivos,qtdpontosnegativos, qtditensentregues,qtditensretornados, qtdTotalPontos  "
                + "from controlerendimentousuario where idgrupo = ? and ano= ? and mes = ? ");
        parametro.add(idGrupo);
        parametro.add(ano);
        parametro.add(mes);

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idUsuario");
        listRetorno.add("qtdPontosPositivos");
        listRetorno.add("qtdPontosNegativos");
        listRetorno.add("qtdItensEntregues");
        listRetorno.add("qtdItensRetornados");
        listRetorno.add("qtdTotalPontos");

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, listRetorno);
        } else {
            return null;
        }
    }

    public Collection<ControleRendimentoUsuarioDTO> findByIdControleRendimentoMelhoresUsuario(final Integer idGrupo, final String mesInicio,
            final String mesFim, final String anoInicio, final String anoFim, final Boolean deUmAnoParaOutro) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();
        if (deUmAnoParaOutro == false) {
            sql.append("select idusuario,qtdpontospositivos,qtdpontosnegativos, qtditensentregues,qtditensretornados, qtdTotalPontos  "
                    + "from controlerendimentousuario where idgrupo = ? and ano= ? and (mes between ? and ?) ");

            parametro.add(idGrupo);
            parametro.add(anoInicio);
            parametro.add(mesInicio);
            parametro.add(mesFim);
        } else {
            sql.append("select idusuario,qtdpontospositivos, qtdpontosnegativos, qtditensentregues,qtditensretornados, qtdTotalPontos "
                    + " from controlerendimentousuario where idgrupo = ? "
                    + " and (ano = ? and (mes between ? and ?)) or (ano = ? and (mes between ? and ?)); ");

            parametro.add(idGrupo);
            parametro.add(anoInicio);
            parametro.add(mesInicio);
            parametro.add("12");
            parametro.add(anoFim);
            parametro.add("1");
            parametro.add(mesFim);
        }

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idUsuario");
        listRetorno.add("qtdPontosPositivos");
        listRetorno.add("qtdPontosNegativos");
        listRetorno.add("qtdItensEntregues");
        listRetorno.add("qtdItensRetornados");
        listRetorno.add("qtdTotalPontos");

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, listRetorno);
        } else {
            return null;
        }
    }

    public Collection<ControleRendimentoUsuarioDTO> findIdsControleRendimentoUsuarioPorPeriodo(final Integer idGrupo, final String mesInicio,
            final String mesFim, final String anoInicio, final String anoFim, final Boolean deUmAnoParaOutro) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();
        if (deUmAnoParaOutro == false) {
            sql.append("select distinct idusuario " + "from controlerendimentousuario where idgrupo = ? and ano= ? and (mes between ? and ?) ");

            parametro.add(idGrupo);
            parametro.add(anoInicio);
            parametro.add(mesInicio);
            parametro.add(mesFim);
        } else {
            sql.append("select distinct idusuario " + " from controlerendimentousuario where idgrupo = ? "
                    + " and (ano = ? and (mes between ? and ?)) or (ano = ? and (mes between ? and ?)); ");

            parametro.add(idGrupo);
            parametro.add(anoInicio);
            parametro.add(mesInicio);
            parametro.add("12");
            parametro.add(anoFim);
            parametro.add("1");
            parametro.add(mesFim);
        }

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idUsuario");

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, listRetorno);
        } else {
            return null;
        }
    }

}
