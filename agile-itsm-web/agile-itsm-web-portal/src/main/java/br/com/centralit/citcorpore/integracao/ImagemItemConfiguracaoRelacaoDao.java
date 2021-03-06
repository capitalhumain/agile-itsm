package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ImagemItemConfiguracaoRelacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class ImagemItemConfiguracaoRelacaoDao extends CrudDaoDefaultImpl {

    public ImagemItemConfiguracaoRelacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idimagemitemconfiguracaorel", "idImagemItemConfiguracaoRel", true, true, false, false));
        listFields.add(new Field("idimagemitemconfiguracao", "idImagemItemConfiguracao", false, false, false, false));
        listFields.add(new Field("idimagemitemconfiguracaopai", "idImagemItemConfiguracaoPai", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "ImagemItemConfiguracaoRelacao";
    }

    @Override
    public Class getBean() {
        return ImagemItemConfiguracaoRelacaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection<ImagemItemConfiguracaoRelacaoDTO> findByIdImagemItemConfiguracao(final Integer idImagemItemConfiguracao) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();

        sql.append("select idimagemitemconfiguracaorel,idimagemitemconfiguracao,idimagemitemconfiguracaopai ");
        sql.append("from imagemitemconfiguracaorelacao ");
        sql.append("where idImagemItemConfiguracao = ? ");
        parametro.add(idImagemItemConfiguracao);

        list = this.execSQL(sql.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idImagemItemConfiguracaoRel");
        listRetorno.add("idImagemItemConfiguracao");
        listRetorno.add("idImagemItemConfiguracaoPai");

        final List result = engine.listConvertion(this.getBean(), list, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result;
        }

    }

    public Collection<ImagemItemConfiguracaoRelacaoDTO> findByIdImagemItemConfiguracaoPai(final Integer idImagemItemConfiguracao) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();

        sql.append("select idimagemitemconfiguracaorel,idimagemitemconfiguracao,idimagemitemconfiguracaopai ");
        sql.append("from imagemitemconfiguracaorelacao ");
        sql.append("where idImagemItemConfiguracaoPai = ? ");
        parametro.add(idImagemItemConfiguracao);

        list = this.execSQL(sql.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idImagemItemConfiguracaoRel");
        listRetorno.add("idImagemItemConfiguracao");
        listRetorno.add("idImagemItemConfiguracaoPai");

        final List result = engine.listConvertion(this.getBean(), list, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result;
        }

    }

    public ImagemItemConfiguracaoRelacaoDTO findByIdImagemItemConfiguracaoAndIdItemPai(final Integer idImagemItemConfiguracao,
            final Integer idImagemItemConfiguracaoPai) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();

        sql.append("select idimagemitemconfiguracaorel,idimagemitemconfiguracao,idimagemitemconfiguracaopai ");
        sql.append("from imagemitemconfiguracaorelacao ");
        sql.append("where idImagemItemConfiguracao = ? and idimagemitemconfiguracaopai = ? ");
        parametro.add(idImagemItemConfiguracao);
        parametro.add(idImagemItemConfiguracaoPai);

        list = this.execSQL(sql.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idImagemItemConfiguracaoRel");
        listRetorno.add("idImagemItemConfiguracao");
        listRetorno.add("idImagemItemConfiguracaoPai");

        if (list == null || list.size() == 0) {
            return null;
        }
        return (ImagemItemConfiguracaoRelacaoDTO) engine.listConvertion(this.getBean(), list, listRetorno).get(0);
    }

}
