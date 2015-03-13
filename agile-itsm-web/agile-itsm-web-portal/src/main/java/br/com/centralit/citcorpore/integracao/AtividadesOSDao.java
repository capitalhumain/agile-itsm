package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AtividadesOSDTO;
import br.com.centralit.citcorpore.bean.OSDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class AtividadesOSDao extends CrudDaoDefaultImpl {

    public AtividadesOSDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAtividadesOS", "idAtividadesOS", true, true, false, false));
        listFields.add(new Field("idOS", "idOS", false, false, false, false));
        listFields.add(new Field("sequencia", "sequencia", false, false, false, false));
        listFields.add(new Field("idAtividadeServicoContrato", "idAtividadeServicoContrato", false, false, false, false));
        listFields.add(new Field("descricaoAtividade", "descricaoAtividade", false, false, false, false));
        listFields.add(new Field("obsAtividade", "obsAtividade", false, false, false, false));
        listFields.add(new Field("custoAtividade", "custoAtividade", false, false, false, false));
        listFields.add(new Field("glosaAtividade", "glosaAtividade", false, false, false, false));
        listFields.add(new Field("qtdeExecutada", "qtdeExecutada", false, false, false, false));
        listFields.add(new Field("complexidade", "complexidade", false, false, false, false));
        listFields.add(new Field("formula", "formula", false, false, false, false));
        listFields.add(new Field("contabilizar", "contabilizar", false, false, false, false));
        listFields.add(new Field("idServicoContratoContabil", "idServicoContratoContabil", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "AtividadesOS";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AtividadesOSDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdOS(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idOS", "=", parm));
        ordenacao.add(new Order("sequencia"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdOS(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idOS", "=", parm));
        super.deleteByCondition(condicao);
    }

    /**
     * Retorna o custo de atividades por id OS.
     *
     * @param idOs
     * @return <code>Double</code>
     * @throws Exception
     */
    public Double retornarCustoAtividadeOSByIdOs(final Integer idOs) throws PersistenceException {
        final String sql = "select sum(custoatividade) from atividadesos where idos = ?";
        final List dados = this.execSQL(sql, new Object[] {idOs});
        final List fields = new ArrayList<>();
        fields.add("custoAtividade");

        final Collection<AtividadesOSDTO> listaDeAtividadeOS = this.listConvertion(this.getBean(), dados, fields);

        if (listaDeAtividadeOS != null && !listaDeAtividadeOS.isEmpty()) {
            for (final AtividadesOSDTO atividade : listaDeAtividadeOS) {
                return atividade.getCustoAtividade();

            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * Retorna list de atividades por id OS.
     *
     * @param idOs
     * @return <code>Double</code>
     * @throws Exception
     */
    public Collection listOSNumeroAtividade(final Integer idAtividadesOS) throws PersistenceException {
        final String sql = "SELECT os.numero, osAtv.descricaoatividade,  osAtv.idos, osAtv.idatividadesos "
                + "FROM atividadesos osAtv INNER JOIN os  ON os.idos = osAtv.idos WHERE osAtv.idatividadesos = ?";
        final List dados = this.execSQL(sql, new Object[] {idAtividadesOS});
        final List fields = new ArrayList<>();
        fields.add("numeroOS");
        fields.add("descricaoAtividade");
        fields.add("idOS");
        fields.add("idAtividadesOS");

        return this.listConvertion(this.getBean(), dados, fields);
    }

    /**
     * Retorna a quantidadedeExecução de atividades por id OS.
     *
     * @param idOs
     * @return <code>Double</code>
     * @throws Exception
     */
    public Double retornarQtdExecucao(final Integer idOs) throws PersistenceException {
        final String sql = "select sum(qtdeExecutada) from atividadesos where idos = ?";
        final List dados = this.execSQL(sql, new Object[] {idOs});
        final List fields = new ArrayList<>();
        fields.add("qtdeExecutada");

        final Collection<AtividadesOSDTO> listaDeAtividadeOS = this.listConvertion(this.getBean(), dados, fields);

        if (listaDeAtividadeOS != null && !listaDeAtividadeOS.isEmpty()) {
            for (final AtividadesOSDTO atividade : listaDeAtividadeOS) {
                return atividade.getQtdeExecutada();
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * Retorna a soma das glosas de atividades por id OS.
     *
     * @param idOs
     * @return
     * @throws Exception
     */
    public Double retornarGlosaAtividadeOSByIdOs(final Integer idOs) throws PersistenceException {
        final String sql = "select sum(glosaatividade) from atividadesos where idos = ?";
        final List dados = this.execSQL(sql, new Object[] {idOs});
        final List fields = new ArrayList<>();
        fields.add("glosaAtividade");

        final Collection<AtividadesOSDTO> listaDeAtividadeOS = this.listConvertion(this.getBean(), dados, fields);

        if (listaDeAtividadeOS != null && !listaDeAtividadeOS.isEmpty()) {
            for (final AtividadesOSDTO atividade : listaDeAtividadeOS) {
                return atividade.getGlosaAtividade();
            }
            return null;
        } else {
            return null;
        }

    }

    /**
     * Método para atualizar observao de os não homologadas
     *
     * @param observacao
     * @param os
     * @throws Exception
     */
    public boolean atualizaObservacao(final Integer idatividadeservicocontrato, final String observacao, final List<OSDTO> os) throws PersistenceException {
        int resp = 0;
        for (final OSDTO osdto : os) {
            final List parametros = new ArrayList<>();
            parametros.add(observacao);
            parametros.add(idatividadeservicocontrato);
            parametros.add(osdto.getIdOS());
            final String sql = "UPDATE atividadesos SET obsatividade = ? WHERE idatividadeservicocontrato = ? AND idos = ? ";
            final int temp = super.execUpdate(sql, parametros.toArray());
            if (temp > 0) {
                resp = temp;
            }
        }
        if (resp > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Collection findByIdOsServicoContratoContabil(final Integer idOS, final Integer idServicoContratoContabil) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idOS", "=", idOS));
        condicao.add(new Condition("idServicoContratoContabil", "=", idServicoContratoContabil));
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idOS"));
        return super.findByCondition(condicao, ordenacao);
    }
}
