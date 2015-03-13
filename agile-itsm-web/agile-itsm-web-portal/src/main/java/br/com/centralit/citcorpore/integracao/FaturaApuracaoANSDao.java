package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.FaturaApuracaoANSDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class FaturaApuracaoANSDao extends CrudDaoDefaultImpl {

    public FaturaApuracaoANSDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idFaturaApuracaoANS", "idFaturaApuracaoANS", true, true, false, false));
        listFields.add(new Field("idFatura", "idFatura", false, false, false, false));
        listFields.add(new Field("idAcordoNivelServicoContrato", "idAcordoNivelServicoContrato", false, false, false, false));
        listFields.add(new Field("valorApurado", "valorApurado", false, false, false, false));
        listFields.add(new Field("detalhamento", "detalhamento", false, false, false, false));
        listFields.add(new Field("percentualGlosa", "percentualGlosa", false, false, false, false));
        listFields.add(new Field("valorGlosa", "valorGlosa", false, false, false, false));
        listFields.add(new Field("dataApuracao", "dataApuracao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "FaturaApuracaoANS";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return FaturaApuracaoANSDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdFatura(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idFatura", "=", parm));
        ordenacao.add(new Order("idFaturaApuracaoANS"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdFatura(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idFatura", "=", parm));
        super.deleteByCondition(condicao);
    }

    public FaturaApuracaoANSDTO findByIdAcordoNivelServicoContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServicoContrato", "=", parm));
        ordenacao.add(new Order("idFaturaApuracaoANS"));

        final List faturas = (List) super.findByCondition(condicao, ordenacao);

        if (faturas != null && !faturas.isEmpty()) {
            return (FaturaApuracaoANSDTO) faturas.get(0);
        } else {
            return null;
        }
    }

    public void deleteByIdAcordoNivelServicoContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServicoContrato", "=", parm));
        super.deleteByCondition(condicao);
    }

}
