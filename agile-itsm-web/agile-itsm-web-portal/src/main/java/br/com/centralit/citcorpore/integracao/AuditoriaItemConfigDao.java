package br.com.centralit.citcorpore.integracao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AuditoriaItemConfigDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.SQLConfig;

public class AuditoriaItemConfigDao extends CrudDaoDefaultImpl {

    public AuditoriaItemConfigDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {

        return null;
    }

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idAuditoriaItemConfig", "idAuditoriaItemConfig", true, true, false, false));
        listFields.add(new Field("idItemConfiguracao", "idItemConfiguracao", false, false, false, false));
        listFields.add(new Field("idItemConfiguracaoPai", "idItemConfiguracaoPai", false, false, false, false));
        listFields.add(new Field("idValor", "idValor", false, false, false, false));
        listFields.add(new Field("idHistoricoIC", "idHistoricoIC", false, false, false, false));
        listFields.add(new Field("idHistoricoValor", "idHistoricoValor", false, false, false, false));
        listFields.add(new Field("idUsuario", "idUsuario", false, false, false, false));
        listFields.add(new Field("dataHoraAlteracao", "dataHoraAlteracao", false, false, false, false));
        listFields.add(new Field("tipoAlteracao", "tipoAlteracao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "AuditoriaItemConfig";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("dataHoraAlteracao"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return AuditoriaItemConfigDTO.class;
    }

    public Collection findByIdItemconfiguracaoPai(final AuditoriaItemConfigDTO auditoriaItemConfigDTO) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idItemConfiguracaoPai", "=", auditoriaItemConfigDTO.getIdItemConfiguracaoPai()));
        ordenacao.add(new Order("dataHoraAlteracao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdItemconfiguracao(final AuditoriaItemConfigDTO auditoriaItemConfigDTO) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idItemConfiguracao", "=", auditoriaItemConfigDTO.getIdItemConfiguracao()));
        ordenacao.add(new Order("dataHoraAlteracao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdHistoricoIC(final AuditoriaItemConfigDTO auditoriaItemConfigDTO) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idHistoricoIC", "=", auditoriaItemConfigDTO.getIdHistoricoIC()));
        ordenacao.add(new Order("dataHoraAlteracao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdHistoricoValor(final AuditoriaItemConfigDTO auditoriaItemConfigDTO) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idHistoricoValor", "=", auditoriaItemConfigDTO.getIdHistoricoValor()));
        ordenacao.add(new Order("dataHoraAlteracao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public List<AuditoriaItemConfigDTO> historicoAlteracaoItemConfiguracaoByIdItemConfiguracao(final ItemConfiguracaoDTO itemConfiguracaoDTO) throws Exception {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        final StringBuilder str = new StringBuilder();

        str.append(" select it.identificacao, tp.nometipoitemconfiguracao ,au.datahoraalteracao,  us.login , au.tipoalteracao FROM itemconfiguracao it  "
                + "  inner join auditoriaitemconfig au  on it.iditemconfiguracao = au.iditemconfiguracao "
                + "	 inner join tipoitemconfiguracao tp on tp.idtipoitemconfiguracao = it.idtipoitemconfiguracao "
                + "  left join usuario us on us.idusuario = au.idusuario   " + " where it.iditemconfiguracaopai = ? ");

        if (itemConfiguracaoDTO.getIdItemConfiguracao() != null && itemConfiguracaoDTO.getIdItemConfiguracaoPai() != null) {
            str.append(" and it.iditemconfiguracao = ? ");
            parametro.add(itemConfiguracaoDTO.getIdItemConfiguracaoPai());
            parametro.add(itemConfiguracaoDTO.getIdItemConfiguracao());
        } else {
            parametro.add(itemConfiguracaoDTO.getIdItemConfiguracao());
        }

        if (CITCorporeUtil.SGBD_PRINCIPAL.toUpperCase().equals(SQLConfig.ORACLE)) {
            final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            str.append("  and au.datahoraalteracao BETWEEN ? and ? ");
            parametro.add(formatter.format(itemConfiguracaoDTO.getDataInicioHistorico()));
            parametro.add(formatter.format(itemConfiguracaoDTO.getDataFimHistorico()));
        } else {
            str.append("  and au.datahoraalteracao BETWEEN ? and ? ");
            parametro.add(itemConfiguracaoDTO.getDataInicioHistorico());
            parametro.add(this.transformaHoraFinal(itemConfiguracaoDTO.getDataFimHistorico()));
        }

        str.append(" group by au.idauditoriaitemConfig, it.identificacao, tp.nometipoitemconfiguracao, us.login, it.iditemconfiguracao, au.datahoraalteracao order by au.datahoraalteracao desc, it.iditemconfiguracao, au.tipoalteracao  ");

        final List dados = this.execSQL(str.toString(), parametro.toArray());
        fields.add("identificacao");
        fields.add("tipoItemConfiguracao");
        fields.add("dataHoraAlteracao");
        fields.add("login");
        fields.add("tipoAlteracao");

        return this.listConvertion(AuditoriaItemConfigDTO.class, dados, fields);
    }

    private Timestamp transformaHoraFinal(final Timestamp dataTimestamp) throws ParseException {

        final java.sql.Date data = new java.sql.Date(dataTimestamp.getTime());
        final String dataHora = data + " 23:59:59";
        final String pattern = "yyyy-MM-dd hh:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        final java.util.Date d = sdf.parse(dataHora);
        final java.sql.Timestamp sqlDate = new java.sql.Timestamp(d.getTime());
        return sqlDate;
    }

}
