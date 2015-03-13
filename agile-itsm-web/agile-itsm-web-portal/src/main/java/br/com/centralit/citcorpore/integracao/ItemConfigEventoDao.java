package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ItemConfigEventoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

public class ItemConfigEventoDao extends CrudDaoDefaultImpl {

    public ItemConfigEventoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ItemConfigEventoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDITEMCONFIGURACAOEVENTO", "idItemConfiguracaoEvento", true, true, false, false));
        listFields.add(new Field("IDITEMCONFIGURACAO", "idItemConfiguracao", false, false, false, false));
        listFields.add(new Field("IDEVENTO", "idEvento", false, false, false, false));
        listFields.add(new Field("TIPOEXECUCAO", "tipoExecucao", false, false, false, false));
        listFields.add(new Field("GERARQUANDO", "gerarQuando", false, false, false, false));
        listFields.add(new Field("DATA", "data", false, false, false, false));
        listFields.add(new Field("HORA", "hora", false, false, false, false));
        listFields.add(new Field("IDBASEITEMCONFIGURACAO", "idBaseItemConfiguracao", false, false, false, false));
        listFields.add(new Field("LINHACOMANDO", "linhaComando", false, false, false, false));
        listFields.add(new Field("LINHACOMANDOLINUX", "linhaComandoLinux", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "ITEMCONFIGURACAOEVENTO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection<ItemConfigEventoDTO> listByIdEvento(final Integer idEvento) throws PersistenceException {
        final String sql = "select ic.idItemConfiguracao, bic.idbaseitemconfiguracao, bic.nomebaseitemconfiguracao, v.valorstr, ice.tipoExecucao, ice.gerarQuando, ice.data, ice.hora, ice.linhaComando, ice.linhaComandoLinux "
                + "from "
                + this.getTableName()
                + " ice "
                + "left join itemConfiguracao ic on ic.iditemconfiguracao = ice.iditemconfiguracao "
                + "left join baseitemconfiguracao bic on bic.idbaseitemconfiguracao = ice.idbaseitemconfiguracao "
                + "left join valor v on v.iditemconfiguracao = ic.iditemconfiguracao "
                + "where ice.idEvento = ? and (v.idcaracteristica in (select idcaracteristica from caracteristica where tagcaracteristica = 'NAME') or v.idcaracteristica is null)";
        final List dados = this.execSQL(sql, new Object[] {idEvento});
        final List fields = new ArrayList<>();
        fields.add("idItemConfiguracao");
        fields.add("idBaseItemConfiguracao");
        fields.add("nomeBaseItemConfiguracao");
        fields.add("identificacao");
        fields.add("tipoExecucao");
        fields.add("gerarQuando");
        fields.add("data");
        fields.add("hora");
        fields.add("linhaComando");
        fields.add("linhaComandoLinux");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public void deleteByIdEvento(final Integer idEvento) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition(Condition.AND, "idEvento", "=", idEvento));
        super.deleteByCondition(lstCondicao);
    }

    public Collection<ItemConfigEventoDTO> verificaDataHoraEvento() throws PersistenceException {
        final String sql = "SELECT IDITEMCONFIGURACAO, IDBASEITEMCONFIGURACAO, IDEVENTO, TIPOEXECUCAO, LINHACOMANDO, LINHACOMANDOLINUX FROM "
                + this.getTableName() + " WHERE DATA = ? AND HORA = ? AND GERARQUANDO <> 'A'";
        final List dados = this.execSQL(sql,
                new Object[] {UtilDatas.getDataAtual(), UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()).replaceAll(":", "")});
        final List fields = new ArrayList<>();
        fields.add("idItemConfiguracao");
        fields.add("idBaseItemConfiguracao");
        fields.add("idEvento");
        fields.add("tipoExecucao");
        fields.add("linhaComando");
        fields.add("linhaComandoLinux");
        return this.listConvertion(this.getBean(), dados, fields);
    }

}
