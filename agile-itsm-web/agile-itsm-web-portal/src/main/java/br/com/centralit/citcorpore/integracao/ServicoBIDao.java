package br.com.centralit.citcorpore.integracao;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ServicoBIDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ServicoBIDao extends CrudDaoDefaultImpl {

    public ServicoBIDao() {
        super(Constantes.getValue("DATABASE_BI_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idServico", "idServico", true, true, false, false));
        listFields.add(new Field("idCategoriaServico", "idCategoriaServico", false, false, false, false));
        listFields.add(new Field("idSituacaoServico", "idSituacaoServico", false, false, false, false));
        listFields.add(new Field("idTipoServico", "idTipoServico", false, false, false, false));
        listFields.add(new Field("idImportanciaNegocio", "idImportanciaNegocio", false, false, false, false));
        listFields.add(new Field("idEmpresa", "idEmpresa", false, false, false, false));
        listFields.add(new Field("idTipoEventoServico", "idTipoEventoServico", false, false, false, false));
        listFields.add(new Field("idTipoDemandaServico", "idTipoDemandaServico", false, false, false, false));
        listFields.add(new Field("idLocalExecucaoServico", "idLocalExecucaoServico", false, false, false, false));
        listFields.add(new Field("nomeServico", "nomeServico", false, false, false, false));
        listFields.add(new Field("detalheServico", "detalheServico", false, false, false, false));
        listFields.add(new Field("objetivo", "objetivo", false, false, false, false));
        listFields.add(new Field("passosServico", "passosServico", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("linkProcesso", "linkProcesso", false, false, false, false));
        listFields.add(new Field("descricaoProcesso", "descricaoProcesso", false, false, false, false));
        listFields.add(new Field("tipoDescProcess", "tipoDescProcess", false, false, false, false));
        listFields.add(new Field("dispPortal", "dispPortal", false, false, false, false));
        listFields.add(new Field("siglaAbrev", "siglaAbrev", false, false, false, false));
        // listFields.add(new Field("quadroOrientPortal" ,"quadroOrientPortal", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("idBaseconhecimento", "idBaseconhecimento", false, false, false, false));
        listFields.add(new Field("idTemplateSolicitacao", "idTemplateSolicitacao", false, false, false, false));
        listFields.add(new Field("idTemplateAcompanhamento", "idTemplateAcompanhamento", false, false, false, false));
        listFields.add(new Field("idConexaoBI", "idConexaoBI", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "Servico";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idServico"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return ServicoBIDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idServico"));
        return super.find(arg0, ordenacao);
    }

    public ServicoBIDTO findByIdEconexaoBI(Integer id, final Integer idConexaoBI) {
        List result;
        try {
            if (id == null) {
                id = 0;
            }
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
            final String sql = "SELECT " + campos + " FROM " + this.getTableName() + " WHERE idservico=? and idconexaobi=?";
            parametro.add(id);
            parametro.add(idConexaoBI);
            resp = this.execSQL(sql, parametro.toArray());
            result = engine.listConvertion(this.getBean(), resp, listRetorno);
        } catch (final PersistenceException e) {
            e.printStackTrace();
            result = null;
        } catch (final Exception e) {
            e.printStackTrace();
            result = null;
        }
        return (ServicoBIDTO) (result == null || result.size() <= 0 ? new ServicoBIDTO() : result.get(0));
    }

    public Collection<ServicoBIDTO> findByNomeEconexaoBI(String nome, final Integer idConexaoBI) {
        List result;
        try {
            if (nome == null) {
                nome = "";
            }
            String text = nome;
            text = Normalizer.normalize(text, Normalizer.Form.NFD);
            text = text.replaceAll("[^\\p{ASCII}]", "");
            text = text.replaceAll("áàãâéêíóôõúüçÁÀÃÂÉÊÍÓÔÕÚÜÇ´`^''-+=", "aaaaeeiooouucAAAAEEIOOOUUC");
            nome = text;
            nome = "%" + nome.toUpperCase() + "%";
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
            final String sql = "SELECT " + campos + " FROM " + this.getTableName() + " WHERE (UPPER(nomeservico) LIKE UPPER(?)) and idconexaobi=?";
            parametro.add(nome);
            parametro.add(idConexaoBI);
            resp = this.execSQL(sql, parametro.toArray());
            result = engine.listConvertion(this.getBean(), resp, listRetorno);
        } catch (final PersistenceException e) {
            e.printStackTrace();
            result = null;
        } catch (final Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result == null ? new ArrayList<ServicoBIDTO>() : result;
    }

}
