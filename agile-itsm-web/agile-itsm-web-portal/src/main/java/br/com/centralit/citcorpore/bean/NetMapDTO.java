package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilDatas;

public class NetMapDTO extends BaseEntity {

    public static String INDEFINIDO = "X";
    public static String ATIVO = "A";
    public static String INATIVO = "I";

    private static final long serialVersionUID = 5374265048891655945L;

    private Integer idNetMap;
    private String ip;

    @Override
    public String toString() {
        return "NetMapDTO [ip=" + ip + "]";
    }

    private String mask;
    private String mac;
    private java.sql.Date date;
    private String ativo;
    private String corRetorno;
    private String nome;
    private java.sql.Date dataInventario;
    private String icNovo;
    private boolean novoIC = false;
    private boolean force = false;
    private String hardware;
    private String sistemaoper;
    private String uptime;
    private String ipFaixaGerar;
    private String validarIP;
    private Integer numThreads;
    private String nativePing;
    private String statusPing = INDEFINIDO;
    private String tipoDado;
    private String valor;

    private Timestamp dateTimeControlProcessInv = UtilDatas.getDataHoraAtual();

    public NetMapDTO() {
        statusPing = INDEFINIDO;
    }

    /**
     * @return Retorna o valor de idNetMap.
     */
    public Integer getIdNetMap() {
        return idNetMap;
    }

    /**
     * @param pIdNetMap
     *            modifica o atributo idNetMap.
     */
    public void setIdNetMap(final Integer pIdNetMap) {
        idNetMap = pIdNetMap;
    }

    /**
     * @return Retorna o valor de ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param pIp
     *            modifica o atributo ip.
     */
    public void setIp(final String pIp) {
        ip = pIp;
    }

    /**
     * @return Retorna o valor de mask.
     */
    public String getMask() {
        return mask;
    }

    /**
     * @param pMask
     *            modifica o atributo mask.
     */
    public void setMask(final String pMask) {
        mask = pMask;
    }

    /**
     * @return Retorna o valor de mac.
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param pMac
     *            modifica o atributo mac
     */
    public void setMac(final String pMac) {
        mac = pMac;
    }

    /**
     * @return Retorna o valor de date.
     */
    public java.sql.Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(final java.sql.Date pDate) {
        date = pDate;
    }

    /**
     * @return Retorna o valor de ativo.
     */
    public String getAtivo() {
        return ativo;
    }

    /**
     * @param pAtivo
     *            modifica o atributo ativo.
     */
    public void setAtivo(final String pAtivo) {
        ativo = pAtivo;
    }

    /**
     * @return Retorna o valor de corRetorno.
     */
    public String getCorRetorno() {
        return corRetorno;
    }

    /**
     * @param pCorRetorno
     *            modifica o atributo corRetorno.
     */
    public void setCorRetorno(final String pCorRetorno) {
        corRetorno = pCorRetorno;
    }

    /**
     * @return Retorna o valor de nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param pNome
     *            modifica o atributo nome.
     */
    public void setNome(final String pNome) {
        nome = pNome;
    }

    public java.sql.Date getDataInventario() {
        return dataInventario;
    }

    public void setDataInventario(final java.sql.Date dataInventario) {
        this.dataInventario = dataInventario;
    }

    public String getIcNovo() {
        return icNovo;
    }

    public void setIcNovo(final String icNovo) {
        this.icNovo = icNovo;
    }

    public boolean isNovoIC() {
        return novoIC;
    }

    public void setNovoIC(final boolean novoIC) {
        this.novoIC = novoIC;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(final boolean force) {
        this.force = force;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(final String hardware) {
        this.hardware = hardware;
    }

    public String getSistemaoper() {
        return sistemaoper;
    }

    public void setSistemaoper(final String sistemaoper) {
        this.sistemaoper = sistemaoper;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(final String uptime) {
        this.uptime = uptime;
    }

    public String getIpFaixaGerar() {
        return ipFaixaGerar;
    }

    public void setIpFaixaGerar(final String ipFaixaGerar) {
        this.ipFaixaGerar = ipFaixaGerar;
    }

    public String getValidarIP() {
        return validarIP;
    }

    public void setValidarIP(final String validarIP) {
        this.validarIP = validarIP;
    }

    public Integer getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(final Integer numThreads) {
        this.numThreads = numThreads;
    }

    public String getNativePing() {
        return nativePing;
    }

    public void setNativePing(final String nativePing) {
        this.nativePing = nativePing;
    }

    public String getStatusPing() {
        return statusPing;
    }

    public void setStatusPing(final String statusPing) {
        this.statusPing = statusPing;
    }

    public Timestamp getDateTimeControlProcessInv() {
        return dateTimeControlProcessInv;
    }

    public void setDateTimeControlProcessInv(final Timestamp dateTimeControlProcessInv) {
        this.dateTimeControlProcessInv = dateTimeControlProcessInv;
    }

    public boolean okTimeToProcess() {
        if (this.getDateTimeControlProcessInv() == null) {
            return true;
        }
        if (this.getDateTimeControlProcessInv().before(UtilDatas.getDataHoraAtual())) {
            return true;
        } else {
            return false;
        }
    }

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(final String tipoDado) {
        this.tipoDado = tipoDado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(final String valor) {
        this.valor = valor;
    }

}
