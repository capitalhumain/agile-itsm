package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ControleRendimentoDTO;
import br.com.citframework.dto.Usuario;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class ControleRendimentoDao extends CrudDaoDefaultImpl {

    public ControleRendimentoDao(final String aliasDB, final Usuario usuario) {
        super(aliasDB, usuario);
    }

    public ControleRendimentoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("anoApuracao", "anoApuracao", false, false, false, false));
        listFields.add(new Field("aprovado", "aprovado", false, false, false, false));
        listFields.add(new Field("dataHoraExecucao", "dataHoraExecucao", false, false, false, false));
        listFields.add(new Field("idControleRendimento", "idControleRendimento", true, true, false, true));
        listFields.add(new Field("idGrupo", "idGrupo", false, false, false, false));
        listFields.add(new Field("idPessoa", "idPessoa", false, false, false, false));
        listFields.add(new Field("mediaRelativa", "mediaRelativa", false, false, false, false));
        listFields.add(new Field("mesApuracao", "mesApuracao", false, false, false, false));
        listFields.add(new Field("qtdPontos", "qtdPontos", false, false, false, false));
        listFields.add(new Field("qtdPontosNegativos", "qtdPontosNegativos", false, false, false, false));
        listFields.add(new Field("qtdPontosPositivos", "qtdPontosPositivos", false, false, false, false));
        listFields.add(new Field("qtdSolicitacoes", "qtdSolicitacoes", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "controlerendimento";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ControleRendimentoDTO.class;
    }

    public Collection<ControleRendimentoDTO> findByMesAno(final String mes, final String ano, final Integer idGrupo) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();
        sql.append("select idcontrolerendimento from controlerendimento where mesapuracao = ? and anoapuracao = ? and idgrupo = ?");
        parametro.add(mes);
        parametro.add(ano);
        parametro.add(idGrupo);

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idControleRendimento");

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, listRetorno);
        } else {
            return null;
        }
    }

    public Collection<ControleRendimentoDTO> findPontuacaoRendimento(final String mes, final String ano, final Integer idGrupo) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();
        sql.append("select idgrupo,qtdpontospositivos,qtdpontosnegativos, qtdsolicitacoes, qtdpontos "
                + " from controlerendimento where idgrupo = ? and anoapuracao= ? and mesapuracao = ? ");
        parametro.add(idGrupo);
        parametro.add(ano);
        parametro.add(mes);

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idGrupo");
        listRetorno.add("qtdPontosPositivos");
        listRetorno.add("qtdPontosNegativos");
        listRetorno.add("qtdSolicitacoes");
        listRetorno.add("qtdPontos");

        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, listRetorno);
        } else {
            return null;
        }
    }

}
