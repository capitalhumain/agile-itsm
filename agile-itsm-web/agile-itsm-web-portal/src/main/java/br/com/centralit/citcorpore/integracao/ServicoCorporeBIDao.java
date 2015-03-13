package br.com.centralit.citcorpore.integracao;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ServicoBIDTO;
import br.com.centralit.citcorpore.bean.ServicoCorporeBIDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ServicoCorporeBIDao extends CrudDaoDefaultImpl {

    public ServicoCorporeBIDao() {
        super(Constantes.getValue("DATABASE_BI_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idservicocorpore", "idServicoCorpore", true, true, false, false));
        listFields.add(new Field("nomeservico", "nomeServico", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "servicocorpore";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idServicoCorpore"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return ServicoCorporeBIDTO.class;
    }

    public ServicoCorporeBIDTO findById(Integer id) {
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
            final String sql = "SELECT " + campos + " FROM " + this.getTableName() + " WHERE idservicocorpore=?";
            parametro.add(id);
            resp = this.execSQL(sql, parametro.toArray());
            result = engine.listConvertion(this.getBean(), resp, listRetorno);
        } catch (final PersistenceException e) {
            e.printStackTrace();
            result = null;
        } catch (final Exception e) {
            e.printStackTrace();
            result = null;
        }
        return (ServicoCorporeBIDTO) (result == null || result.size() <= 0 ? new ServicoCorporeBIDTO() : result.get(0));
    }

    public Collection<ServicoCorporeBIDTO> findByNome(String nome) {
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
            final String sql = "SELECT " + campos + " FROM " + this.getTableName() + " WHERE (UPPER(nomeservico) LIKE UPPER(?))";
            parametro.add(nome);
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
