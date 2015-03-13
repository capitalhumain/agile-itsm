/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.componenteMaquina;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.TimerTask;

import br.com.centralit.citcorpore.negocio.UsuarioService;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;

/**
 * Implementa a verificação de Status e Liga Computador.
 *
 * @author valdoilo.damasceno
 *
 */
public class WakeOnLan extends Thread {

    public static final int PORT = 8900;
    public static final String NONE = "";
    public static final String OK = "OK";
    public static final String FAIL = "FAIL";
    private String ip;
    private String mascara;
    private String mac;
    private TimerTask tarefaStatus;
    private Boolean status = false;
    private Integer numeroDeTentativas;
    private Integer tempo;
    private String control = WakeOnLan.NONE;

    /**
     * Construtor.
     *
     * @param ip
     * @param mascara
     * @param mac
     * @param tempo
     * @param numeroDeTentativas
     *
     * @author valdoilo.damasceno
     */
    public WakeOnLan(final String ip, final String mascara, final String mac, final Integer tempo, final Integer numeroDeTentativas) {
        this.ip = ip;
        this.mascara = mascara;
        this.mac = mac;
        this.tempo = tempo * 1000;
        this.numeroDeTentativas = numeroDeTentativas;
        this.setControl(WakeOnLan.NONE);
    }

    @Override
    public void run() {
        this.ligarComputador();
        this.verificarStatusComputador();
    }

    /**
     * Verifica Status do Computador.
     *
     * @return Boolean
     *
     * @author valdoilo.damasceno
     */
    public Boolean verificarStatusComputador() {
        try {
            while (this.getNumeroDeTentativas() > 0) {
                this.setStatus(this.pingar(this.getIp()));
                if (this.getStatus()) {
                    this.setControl(WakeOnLan.OK);
                    this.setNumeroDeTentativas(-999);
                    return this.getStatus();
                }
                try {
                    Thread.sleep(this.getTempo());
                } catch (final InterruptedException e) {}
                this.setNumeroDeTentativas(this.getNumeroDeTentativas() - 1);
            }
            if (this.getControl().equalsIgnoreCase(WakeOnLan.NONE)) {
                this.setControl(WakeOnLan.FAIL);
            }
        } catch (final IOException e) {
            this.setControl(WakeOnLan.FAIL);
        }
        return this.getStatus();
    }

    /**
     * Realiza Ping.
     *
     * @param ip
     * @return boolean
     * @throws IOException
     * @author valdoilo.damasceno
     */
    public boolean pingar(final String ip) throws IOException {
        final InetAddress inetAddress = InetAddress.getByName(this.getIp());
        try {
            System.out.println("######TESTA SE O COMPUTADOR LIGOU######");
            // Testa se o outro computador na rede é alcançável
            // Timeout (tempo de espera por resposta) definido em 3 segundos
            return inetAddress.isReachable(3000);
        } catch (final Exception e) {
            System.out.println(this.getClass().getName() + "-> pingar  --> IP: [" + ip + "] ---> " + e.getMessage());
            return false;
        }
    }

    /**
     * Liga Computador.
     *
     * @return Boolean
     * @author valdoilo.damasceno
     */
    public Boolean ligarComputador() {
        try {
            final String ipBroadcast = this.binaryIpToInteger(this.getIpBroadcastAddressBinary(this.getIp(), this.getMascara()));
            final byte[] ethernetAddressBytes = this.getEnderecoEthernet(this.getMac());
            final byte[] wakeupFrame = new byte[17 * ethernetAddressBytes.length];
            int start = 6;
            for (int i = 0; i < 6; i++) {
                wakeupFrame[i] = (byte) 0xff;
            }
            for (int i = 0; i < 16; i++) {
                System.arraycopy(ethernetAddressBytes, 0, wakeupFrame, start, ethernetAddressBytes.length);
                start += ethernetAddressBytes.length;
            }
            final InetAddress address = InetAddress.getByName(ipBroadcast);
            final DatagramSocket socket = new DatagramSocket(PORT);
            final DatagramPacket packet = new DatagramPacket(wakeupFrame, wakeupFrame.length, address, PORT);
            socket.setBroadcast(true);
            socket.send(packet);
            socket.close();
            return this.getStatus();
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retorna Endereço IP Broadcast.
     *
     * @param enderecoIp
     * @param mascara
     * @return String
     * @author valdoilo.damasceno
     */
    private String[] getIpBroadcastAddressBinary(final String enderecoIp, final String mascara) {
        final String[] ipBinario = this.ipToBinary(mascara);
        final String[] mascaraDeSubRedeBin = this.getSubnetAddressBinary(enderecoIp, mascara);
        final String[] invertMask = new String[ipBinario.length];
        final String[] broadCastIp = new String[ipBinario.length];
        for (int i = 0; i < ipBinario.length; i++) {
            final String ip0 = ipBinario[i];
            final StringBuilder resu = new StringBuilder();
            for (int j = 0; j < ip0.length(); j++) {
                if (ip0.charAt(j) == '1') {
                    resu.append('0');
                } else {
                    resu.append('1');
                }
            }
            invertMask[i] = resu.toString();
        }
        for (int lcI = 0; lcI < invertMask.length; lcI++) {
            final String ip0 = mascaraDeSubRedeBin[lcI];
            final String ip1 = invertMask[lcI];
            final StringBuilder resu = new StringBuilder();
            for (int lcI2 = 0; lcI2 < ip0.length(); lcI2++) {
                if (ip0.charAt(lcI2) == '1' || ip1.charAt(lcI2) == '1') {
                    resu.append('1');
                } else {
                    resu.append('0');
                }
            }
            broadCastIp[lcI] = resu.toString();
        }
        return broadCastIp;
    }

    /**
     * Retorna máscara de sub-rede.
     *
     * @param anIp
     * @param aMask
     * @return String
     * @author valdoilo.damasceno
     */
    private String[] getSubnetAddressBinary(final String anIp, final String aMask) {
        final String[] ipBin = this.ipToBinary(anIp);
        final String[] masqBin = this.ipToBinary(aMask);
        final String[] subMasq = new String[ipBin.length];
        for (int lcI = 0; lcI < masqBin.length; lcI++) {
            final String ip0 = ipBin[lcI];
            final String ip1 = masqBin[lcI];
            final StringBuilder resu = new StringBuilder();
            for (int lcI2 = 0; lcI2 < ip0.length(); lcI2++) {
                if (ip0.charAt(lcI2) == '1' && ip1.charAt(lcI2) == '1') {
                    resu.append('1');
                } else {
                    resu.append('0');
                }
            }
            subMasq[lcI] = resu.toString();
        }
        return subMasq;
    }

    /**
     * Converte IP em formato texto (String) para o fomato binário.
     *
     * @param anIp
     * @return String[]
     * @author valdoilo.damasceno
     */
    private String[] ipToBinary(final String anIp) {
        // Recebe o endereço IP em formato textual e cria um array com as partes entes os pontos (.)
        final String[] ipArray = anIp.split("\\.");
        final String[] ipBin = new String[ipArray.length];
        for (int lcJ = 0; lcJ < ipBin.length; lcJ++) {
            // Converte um texto para inteiro e logo em seguida converte o
            // número para um número binário em formato texto
            ipBin[lcJ] = Integer.toBinaryString(Integer.parseInt(ipArray[lcJ]));
            if (ipBin[lcJ].length() < 8) {
                int nb0 = 8 - ipBin[lcJ].length();
                final StringBuilder buff = new StringBuilder();
                while (nb0 != 0) {
                    buff.append('0');
                    nb0--;
                }
                ipBin[lcJ] = buff.toString() + ipBin[lcJ];
            }
        }
        return ipBin;
    }

    /**
     * Retorna o endereço Ethernet.
     *
     * @param enderecoMAC
     * @return enderecoEthernet
     * @throws IllegalArgumentException
     * @author valdoilo.damasceno
     */
    private byte[] getEnderecoEthernet(final String enderecoMAC) throws IllegalArgumentException {
        final String[] hex = enderecoMAC.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Endereço MAC inválido.");
        }
        final byte[] enderecoEthernet = new byte[6];
        try {
            for (int i = 0; i < 6; i++) {
                enderecoEthernet[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Endereço MAC inválido.");
        }
        return enderecoEthernet;
    }

    /**
     * Converte IP de binário para Integer.
     *
     * @param aBinaryIp
     * @return String
     * @author valdoilo.damasceno
     */
    private String binaryIpToInteger(final String[] aBinaryIp) {
        final StringBuilder resu = new StringBuilder();
        for (final String element : aBinaryIp) {
            resu.append(Integer.parseInt(element, 2)).append('.');
        }
        resu.delete(resu.length() - 1, resu.length());
        return resu.toString();
    }

    /**
     * Retorna Service de Usuario.
     *
     * @return UsuarioService
     * @throws ServiceException
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public UsuarioService getUsuarioService() throws ServiceException, Exception {
        // Obtém um serviço de usuário através do localizador de serviço
        return (UsuarioService) ServiceLocator.getInstance().getService(UsuarioService.class, null);
    }

    /**
     * @return valor do atributo ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Define valor do atributo ip.
     *
     * @param ip
     */
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * @return valor do atributo mascara.
     */
    public String getMascara() {
        return mascara;
    }

    /**
     * Define valor do atributo mascara.
     *
     * @param mascara
     */
    public void setMascara(final String mascara) {
        this.mascara = mascara;
    }

    /**
     * @return valor do atributo mac.
     */
    public String getMac() {
        return mac;
    }

    /**
     * Define valor do atributo mac.
     *
     * @param mac
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     * @return valor do atributo tarefaStatus.
     */
    public TimerTask getTarefaStatus() {
        return tarefaStatus;
    }

    /**
     * Define valor do atributo tarefaStatus.
     *
     * @param tarefaStatus
     */
    public void setTarefaStatus(final TimerTask tarefaStatus) {
        this.tarefaStatus = tarefaStatus;
    }

    /**
     * @return valor do atributo status.
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Define valor do atributo status.
     *
     * @param status
     */
    public void setStatus(final Boolean status) {
        this.status = status;
    }

    /**
     * @return valor do atributo numeroDeTentativas.
     */
    public Integer getNumeroDeTentativas() {
        return numeroDeTentativas;
    }

    /**
     * Define valor do atributo numeroDeTentativas.
     *
     * @param numeroDeTentativas
     */
    public void setNumeroDeTentativas(final Integer numeroDeTentativas) {
        this.numeroDeTentativas = numeroDeTentativas;
    }

    /**
     * @return valor do atributo tempo.
     */
    public Integer getTempo() {
        return tempo;
    }

    /**
     * Define valor do atributo tempo.
     *
     * @param tempo
     */
    public void setTempo(final Integer tempo) {
        this.tempo = tempo;
    }

    /**
     * @return valor do atributo control.
     */
    public String getControl() {
        return control;
    }

    /**
     * Define valor do atributo control.
     *
     * @param control
     */
    public void setControl(final String control) {
        this.control = control;
    }

}
