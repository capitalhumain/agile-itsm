package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.NetMapDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class NetMapDao extends CrudDaoDefaultImpl {

    public NetMapDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idNetMap", "idNetMap", true, true, false, false));
        listFields.add(new Field("ip", "ip", false, false, false, false));
        listFields.add(new Field("mask", "mask", false, false, false, false));
        listFields.add(new Field("mac", "mac", false, false, false, false));
        listFields.add(new Field("date_", "date", false, false, false, false));
        listFields.add(new Field("nome", "nome", false, false, false, false));
        listFields.add(new Field("hardware", "hardware", false, false, false, false));
        listFields.add(new Field("sistemaoper", "sistemaoper", false, false, false, false));
        listFields.add(new Field("uptime", "uptime", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "NetMap";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return NetMapDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection verificarExistenciaIp(final NetMapDTO bean) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final String sql = "SELECT idNetMap,ip,mask,mac,nome,date_ FROM NetMap WHERE  IP = ? ORDER BY ip ASC";

        parametro.add(bean.getIp());
        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idNetMap");
        listRetorno.add("ip");
        listRetorno.add("mask");
        listRetorno.add("mac");
        listRetorno.add("nome");
        listRetorno.add("date_");

        final List listIps = engine.listConvertion(this.getBean(), lista, listRetorno);
        return listIps;
    }

    public Collection listIpByDataInventario(final Date dataInventario) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final String sql = "SELECT idNetMap,ip,mask,mac,nome,date_ FROM NetMap WHERE date_ >= ? ORDER BY ip ASC";

        parametro.add(dataInventario);
        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idNetMap");
        listRetorno.add("ip");
        listRetorno.add("mask");
        listRetorno.add("mac");
        listRetorno.add("nome");
        listRetorno.add("date_");

        final List listIps = engine.listConvertion(this.getBean(), lista, listRetorno);
        return listIps;
    }

    /**
     * @author flavio.santana
     *         Obtem a lista de ips
     *         - A coluna date não funciona no oracle pois é reconhecida como palavra chave
     * @param dataInventario
     * @return
     * @throws Exception
     */
    public List<NetMapDTO> listIp() throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final String sql = "SELECT idNetMap,ip,mask,mac,nome,date_, hardware, sistemaoper, uptime  FROM NetMap ORDER BY ip ASC";

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idNetMap");
        listRetorno.add("ip");
        listRetorno.add("mask");
        listRetorno.add("mac");
        listRetorno.add("nome");
        listRetorno.add("date_");
        listRetorno.add("hardware");
        listRetorno.add("sistemaoper");
        listRetorno.add("uptime");

        final List listIps = engine.listConvertion(this.getBean(), lista, listRetorno);
        return listIps;
    }

    /**
     * Lista os registros que tem data maior ou igual a passada e com o mesmo IP. VERIFICAR
     *
     * @param dataInventario
     *            Data
     * @param ip
     * @return
     * @throws Exception
     */
    public Collection listByDataIp(final Date dataInventario, final String ip) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final String sql = "SELECT idNetMap,ip,mask,mac,nome,datainventario FROM NetMap WHERE date_ >= ? AND ip LIKE %'" + ip + "'% ORDER BY ip ASC";
        parametro.add(dataInventario);
        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idNetMap");
        listRetorno.add("ip");
        listRetorno.add("mask");
        listRetorno.add("mac");
        listRetorno.add("nome");
        listRetorno.add("date_");

        final List listIps = engine.listConvertion(this.getBean(), lista, listRetorno);
        return listIps;

    }

    public Collection existeByNome(final Date dataInventario, final String nome) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final String sql = "SELECT idNetMap,ip,mask,mac,nome,datainventario FROM NetMap WHERE date_ >= ? AND nome = ? ORDER BY nome ASC";
        parametro.add(dataInventario);
        parametro.add(nome);
        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idNetMap");
        listRetorno.add("ip");
        listRetorno.add("mask");
        listRetorno.add("mac");
        listRetorno.add("nome");
        listRetorno.add("date_");

        final List listIps = engine.listConvertion(this.getBean(), lista, listRetorno);
        return listIps;
    }

}
