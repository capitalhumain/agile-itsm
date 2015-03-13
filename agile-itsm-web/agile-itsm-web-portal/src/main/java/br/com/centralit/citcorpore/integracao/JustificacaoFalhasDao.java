package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.JustificacaoEventoHistoricoDTO;
import br.com.centralit.citcorpore.bean.JustificacaoFalhasDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class JustificacaoFalhasDao extends CrudDaoDefaultImpl {

    private ArrayList<JustificacaoFalhasDTO> listaJustificados;

    public JustificacaoFalhasDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    public ArrayList<JustificacaoEventoHistoricoDTO> findEventosComFalha(final BaseEntity dto) {
        listaJustificados = null;
        final JustificacaoFalhasDTO justificacaoDto = (JustificacaoFalhasDTO) dto;
        final StringBuilder sql = new StringBuilder();

        sql.append("select hist.idhistoricotentativa, de.valorstr, hist.iditemconfiguracao, hist.idbaseitemconfiguracao, bs.nomebaseitemconfiguracao, ");
        sql.append("hist.descricao, even.idevento, gr.nome, un.nome, emp.idempregado from historicotentativa hist ");
        sql.append("inner join evento even on hist.idevento = even.idevento ");
        sql.append("inner join eventoempregado evenemp on evenemp.idevento = even.idevento ");
        sql.append("inner join empregados emp on emp.idempregado = evenemp.idempregado ");
        sql.append("inner join usuario us on us.idempregado = emp.idempregado ");
        sql.append("inner join grupo gr ON gr.idgrupo = emp.idgrupo ");
        sql.append("inner join unidade un ON un.idunidade = emp.idunidade ");
        sql.append("left join baseitemconfiguracao bs on hist.idbaseitemconfiguracao = bs.idbaseitemconfiguracao ");
        sql.append("inner join ");
        sql.append("(select a.valorstr, a.iditemconfiguracaopai, b.identificacao from ");
        sql.append("(select va.valorstr, it.iditemconfiguracaopai from caracteristica car ");
        sql.append("join valor va on va.idcaracteristica = car.idcaracteristica ");
        sql.append("join itemconfiguracao it on it.iditemconfiguracao = va.iditemconfiguracao ");
        sql.append("where car.tagcaracteristica = 'IPADDR') a join ");
        sql.append("(select iditemconfiguracao, identificacao ");
        sql.append("from itemconfiguracao where iditemconfiguracaopai is null) b on a.iditemconfiguracaopai = b.iditemconfiguracao) ");
        sql.append(" de on de.iditemconfiguracaopai = evenemp.iditemconfiguracaopai ");
        sql.append("left join ");
        sql.append("( select val.valorstr, val.iditemconfiguracao ");
        sql.append("from caracteristica carac ");
        sql.append("join valor val on carac.idcaracteristica = val.idcaracteristica ");
        sql.append("where carac.tagcaracteristica = 'NAME') nome on hist.iditemconfiguracao = nome.iditemconfiguracao ");

        final ArrayList<Object> parametrosAux = new ArrayList<Object>();
        boolean primeiro = true;
        if (justificacaoDto.getIdEvento() != null) {
            if (primeiro) {
                sql.append(" WHERE ");
                primeiro = false;
            } else {
                sql.append(" AND ");
            }
            sql.append("ht.idevento = ?");
            parametrosAux.add(justificacaoDto.getIdEvento());
        }

        if (justificacaoDto.getIdUnidade() != null) {
            if (primeiro) {
                sql.append(" WHERE ");
                primeiro = false;
            } else {
                sql.append(" AND ");
            }
            sql.append("un.idunidade = ?");
            parametrosAux.add(justificacaoDto.getIdUnidade());
        }

        if (justificacaoDto.getIdGrupo() != null) {
            if (primeiro) {
                sql.append(" WHERE ");
                primeiro = false;
            } else {
                sql.append(" AND ");
            }
            sql.append("g.idgrupo = ?");
            parametrosAux.add(justificacaoDto.getIdGrupo());
        }

        if (justificacaoDto.getDataInicial() != null) {
            if (primeiro) {
                sql.append(" WHERE ");
                primeiro = false;
            } else {
                sql.append(" AND ");
            }
            sql.append("ht.data >= ?");
            parametrosAux.add(justificacaoDto.getDataInicial());
        }

        if (justificacaoDto.getDataFinal() != null) {
            if (primeiro) {
                sql.append(" WHERE ");
                primeiro = false;
            } else {
                sql.append(" AND ");
            }
            sql.append("ht.data <= ?");
            parametrosAux.add(justificacaoDto.getDataFinal());
        }

        // sql.append(" GROUP BY ht.idhistoricotentativa ");

        Object[] parametros = null;
        if (parametrosAux.size() > 0) {
            parametros = parametrosAux.toArray();
        }

        ArrayList<JustificacaoEventoHistoricoDTO> resultados = null;
        ArrayList<JustificacaoEventoHistoricoDTO> resultadosAux = null;
        try {
            // Object[] params = { 1 };

            final List dados = this.execSQL(sql.toString(), parametros);
            final List fields = new ArrayList<>();
            fields.add("idHistoricoTentativa");
            fields.add("ip");
            fields.add("idItemConfiguracao");
            fields.add("idBaseItemConfiguracao");
            fields.add("identificacaoItemConfiguracao");
            fields.add("descricaoTentativa");
            fields.add("idEvento");
            fields.add("nomeGrupo");
            fields.add("nomeUnidade");
            fields.add("idEmpregado");

            resultados = (ArrayList) this.listConvertion(JustificacaoEventoHistoricoDTO.class, dados, fields);
            if (resultados.isEmpty()) {} else {
                resultadosAux = new ArrayList<JustificacaoEventoHistoricoDTO>();
                // remove os já justificados
                for (int i = 0; i < resultados.size(); i++) {
                    if (!this.isJustificado(resultados.get(i))) {
                        resultadosAux.add(resultados.get(i));
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return resultadosAux;
    }

    private ArrayList<JustificacaoFalhasDTO> getListaJustificados() {
        if (listaJustificados != null) {
            return listaJustificados;
        }

        ArrayList<JustificacaoFalhasDTO> resultados = new ArrayList<JustificacaoFalhasDTO>();
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM justificacaofalha");
        try {
            final List dados = this.execSQL(sql.toString(), null);
            final List fields = new ArrayList<>();
            fields.add("idJustificacaoFalha");
            fields.add("idItemConfiguracao");
            fields.add("idBaseItemConfiguracao");
            fields.add("idEvento");
            fields.add("idEmpregado");
            fields.add("descricao");
            fields.add("data");
            fields.add("hora");
            fields.add("idHistoricoTentativa");

            resultados = (ArrayList) this.listConvertion(JustificacaoFalhasDTO.class, dados, fields);

        } catch (final PersistenceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        listaJustificados = resultados;

        return listaJustificados;
    }

    private boolean isJustificado(final JustificacaoEventoHistoricoDTO just) {
        for (final JustificacaoFalhasDTO j : this.getListaJustificados()) {
            if (just.getIdHistoricoTentativa().equals(j.getIdHistoricoTentativa())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idJustificacaoFalha", "idjustificacaofalha", true, true, false, false));
        listFields.add(new Field("idItemConfiguracao", "iditemconfiguracao", false, false, false, false));
        listFields.add(new Field("idBaseItemConfiguracao", "idbaseitemconfiguracao", false, false, false, false));
        listFields.add(new Field("idEvento", "idevento", false, false, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("data", "data", false, false, false, false));
        listFields.add(new Field("hora", "hora", false, false, false, false));
        listFields.add(new Field("idEmpregado", "idempregado", false, false, false, false));
        listFields.add(new Field("idHistoricoTentativa", "idhistoricotentativa", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "justificacaofalha";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return JustificacaoFalhasDTO.class;
    }

}
