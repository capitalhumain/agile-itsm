package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ImagemItemConfiguracaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 * @author breno.guimaraes
 *
 */
public class ImagemItemConfiguracaoDao extends CrudDaoDefaultImpl {

    public ImagemItemConfiguracaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public void delete(final BaseEntity obj) {
        try {
            super.delete(obj);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    };

    @Override
    public Collection find(final BaseEntity obj) {
        Collection retorno = null;

        try {
            retorno = super.find(obj, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idimagemitemconfiguracao", "idImagemItemConfiguracao", true, true, false, false));
        listFields.add(new Field("idservico", "idServico", false, false, false, false));
        listFields.add(new Field("iditemconfiguracao", "idItemConfiguracao", false, false, false, false));
        listFields.add(new Field("posx", "posx", false, false, false, false));
        listFields.add(new Field("posy", "posy", false, false, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("idimagemitemconfiguracaopai", "idImagemItemConfiguracaoPai", false, false, false, false));
        listFields.add(new Field("caminhoimagem", "caminhoImagem", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "imagemitemconfiguracao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public BaseEntity create(final BaseEntity obj) {
        BaseEntity retorno = null;
        try {
            retorno = super.create(obj);
        } catch (final Exception e) {
            System.out.println("Não foi possível cadastrar a imagem." + e.getMessage());
        }

        return retorno;
    };

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<ImagemItemConfiguracaoDTO> findByIdItemConfiguracao(final Integer id) throws PersistenceException {
        final List condicao = new ArrayList();
        condicao.add(new Condition("idItemConfiguracao", "=", id));
        return super.findByCondition(condicao, null);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection<ImagemItemConfiguracaoDTO> findByIdImagemItemConfiguracaoPai(final Integer id) {
        Collection retorno = null;
        final List condicao = new ArrayList();
        condicao.add(new Condition("idImagemItemConfiguracaoPai", "=", id));
        try {
            retorno = super.findByCondition(condicao, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection<ImagemItemConfiguracaoDTO> findByIdImagemItemConfiguracao(final Integer id) {
        Collection retorno = null;
        final ImagemItemConfiguracaoDTO img = new ImagemItemConfiguracaoDTO();
        img.setIdImagemItemConfiguracao(id);
        final List condicao = new ArrayList();
        condicao.add(new Condition("idimagemitemconfiguracao", "=", id));
        try {
            retorno = super.find(img, null);
        } catch (final Exception e) {
            System.out.println("Não foi possível buscar imagemItemConfiguracao com id " + id);
            e.printStackTrace();
        }
        return retorno;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection<ImagemItemConfiguracaoDTO> findByIdServico(final Integer id) {
        final ImagemItemConfiguracaoDTO img = new ImagemItemConfiguracaoDTO();
        img.setIdServico(id);
        Collection retorno = null;

        final List condicao = new ArrayList();
        condicao.add(new Condition("idservico", "=", id.intValue()));
        try {
            // retorno = super.findByCondition(condicao, null);
            retorno = super.find(img, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    public Class<ImagemItemConfiguracaoDTO> getBean() {
        return ImagemItemConfiguracaoDTO.class;
    }

    @Override
    public void update(final BaseEntity obj) {
        try {
            super.update(obj);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void excluiIdPaiDeItensFilho(final int idPai) {
        final Object[] parametros = new Object[] {null, idPai};

        final String sql = "UPDATE " + getTableName() + " SET idimagemitemconfiguracaopai = ? WHERE idimagemitemconfiguracaopai = ?";
        try {
            execUpdate(sql, parametros);
        } catch (final PersistenceException e) {
            e.printStackTrace();
        }
    }

}
