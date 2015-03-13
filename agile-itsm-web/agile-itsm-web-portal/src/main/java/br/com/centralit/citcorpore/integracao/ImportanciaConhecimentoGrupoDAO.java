/**
 *
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BaseConhecimentoDTO;
import br.com.centralit.citcorpore.bean.GrupoEmpregadoDTO;
import br.com.centralit.citcorpore.bean.ImportanciaConhecimentoGrupoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author Vadoilo Damasceno
 *
 */
public class ImportanciaConhecimentoGrupoDAO extends CrudDaoDefaultImpl {

    public ImportanciaConhecimentoGrupoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDBASECONHECIMENTO", "idBaseConhecimento", true, false, false, false));
        listFields.add(new Field("IDGRUPO", "idGrupo", true, false, false, false));
        listFields.add(new Field("GRAUIMPORTANCIAGRUPO", "grauImportanciaGrupo", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "IMPORTANCIACONHECIMENTOGRUPO";
    }

    @Override
    public Class getBean() {
        return ImportanciaConhecimentoGrupoDTO.class;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idConhecimento"));
        return super.list(ordenacao);
    }

    /**
     * Deleta ImportanciaConhecimentoUsuário da Base de Conhecimento.
     *
     * @param idBaseConhecimento
     * @throws Exception
     * @author Vadoilo Damasceno
     */
    public void deleteByIdConhecimento(final Integer idBaseConhecimento) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();

        condicao.add(new Condition("idBaseConhecimento", "=", idBaseConhecimento));

        this.deleteByCondition(condicao);

    }

    /**
     * Lista ImportanciaConhecimentoGrupo por idBaseConhecimento.
     *
     * @param idBaseConhecimento
     * @return Collection<ImportanciaConhecimentoUsuarioDTO>
     * @throws Exception
     * @author Vadoilo Damasceno
     */
    public Collection<ImportanciaConhecimentoGrupoDTO> listByIdBaseConhecimento(final Integer idBaseConhecimento) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();

        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idBaseConhecimento", "=", idBaseConhecimento));

        ordenacao.add(new Order("idBaseConhecimento", "ASC"));

        return this.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna lista de ImportanciaConhecimentoGrupo de acordo com a BaseConhecimento e a Lista de Grupos do Usuário.
     *
     * @param baseConhecimentoDto
     * @param listGrupoEmpregado
     * @return Collection<ImportanciaConhecimentoGrupoDTO>
     * @throws Exception
     */
    public ImportanciaConhecimentoGrupoDTO obterGrauDeImportancia(final BaseConhecimentoDTO baseConhecimentoDto,
            final Collection<GrupoEmpregadoDTO> listGrupoEmpregado, final UsuarioDTO usuarioDto) throws PersistenceException {

        List<ImportanciaConhecimentoGrupoDTO> listImportanciaConhecimentoGrupo = new ArrayList<ImportanciaConhecimentoGrupoDTO>();

        final List parametros = new ArrayList<>();

        final List listRetorno = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        sql.append("(select distinct idbaseconhecimento, grauimportanciagrupo from importanciaconhecimentogrupo  ");

        if (baseConhecimentoDto.getIdBaseConhecimento() != null) {
            sql.append("where  idbaseconhecimento = ? ");
            parametros.add(baseConhecimentoDto.getIdBaseConhecimento());
        }

        boolean aux = false;
        if (listGrupoEmpregado != null && !listGrupoEmpregado.isEmpty()) {

            for (final GrupoEmpregadoDTO grupoEmpregadoDto : listGrupoEmpregado) {

                if (!aux) {
                    sql.append(" AND (idgrupo = ? ");
                    parametros.add(grupoEmpregadoDto.getIdGrupo());
                    aux = true;
                } else {

                    sql.append(" OR idgrupo = ? ");
                    parametros.add(grupoEmpregadoDto.getIdGrupo());
                }
            }

            sql.append(")");
        }

        sql.append(") union ");
        sql.append("(select distinct idbaseconhecimento, grauimportanciausuario ");
        sql.append("from importanciaconhecimentousuario ");

        if (baseConhecimentoDto.getIdBaseConhecimento() != null) {
            sql.append("where idbaseconhecimento = ? ");
            parametros.add(baseConhecimentoDto.getIdBaseConhecimento());
        }
        if (usuarioDto.getIdUsuario() != null) {
            sql.append("and idusuario = ? ");
            parametros.add(usuarioDto.getIdUsuario());
        }
        sql.append(")");
        sql.append(" order by grauimportanciagrupo desc ");

        final List list = this.execSQL(sql.toString(), parametros.toArray());

        listRetorno.add("idBaseConhecimento");
        listRetorno.add("grauImportancia");

        if (list != null && !list.isEmpty()) {
            listImportanciaConhecimentoGrupo = this.listConvertion(ImportanciaConhecimentoGrupoDTO.class, list, listRetorno);
            return listImportanciaConhecimentoGrupo.get(0);
        }
        return null;
    }

}
