package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.MidiaSoftwareChaveDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class MidiaSoftwareChaveDao extends CrudDaoDefaultImpl {

    /**
     * @author flavio.santana
     */

    public MidiaSoftwareChaveDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idMidiaSoftwareChave", "idMidiaSoftwareChave", true, true, false, false));
        listFields.add(new Field("idMidiaSoftware", "idMidiaSoftware", false, false, false, false));
        listFields.add(new Field("chave", "chave", false, false, false, false));
        listFields.add(new Field("qtdPermissoes", "qtdPermissoes", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "midiasoftwarechave";
    }

    @Override
    public Collection<MidiaSoftwareChaveDTO> find(final BaseEntity obj) throws PersistenceException {
        final List<MidiaSoftwareChaveDTO> ordem = new ArrayList<MidiaSoftwareChaveDTO>();
        return super.find(obj, ordem);
    }

    @Override
    public Collection<MidiaSoftwareChaveDTO> list() throws PersistenceException {
        final List<MidiaSoftwareChaveDTO> list = new ArrayList<MidiaSoftwareChaveDTO>();

        return super.list(list);
    }

    @Override
    public Class getBean() {
        return MidiaSoftwareChaveDTO.class;
    }

    public void deleteByIdMidiaSoftware(final Integer idMidiaSoftware) throws PersistenceException {
        final String sql = "DELETE FROM " + this.getTableName() + " WHERE idMidiaSoftware = ? ";
        super.execUpdate(sql, new Object[] {idMidiaSoftware});
    }

    public Collection<MidiaSoftwareChaveDTO> findByMidiaSoftware(final Integer idMidiaSoftware) throws PersistenceException {
        final String sql = "SELECT idMidiaSoftwareChave, chave, qtdPermissoes  FROM " + this.getTableName() + "  WHERE idMidiaSoftware = ?";
        final List<MidiaSoftwareChaveDTO> dados = this.execSQL(sql, new Object[] {idMidiaSoftware});
        final List<String> fields = new ArrayList<String>();
        fields.add("idMidiaSoftwareChave");
        fields.add("chave");
        fields.add("qtdPermissoes");
        return this.listConvertion(this.getBean(), dados, fields);
    }

}
