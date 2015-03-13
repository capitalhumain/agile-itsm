package br.com.centralit.citcorpore.comm.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.citframework.util.UtilTratamentoArquivos;

public class ScanNet {

    private static final String IP_ADDRESS = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
    private static final String SLASH_FORMAT = IP_ADDRESS + "/(\\d{1,3})";
    private static final Pattern addressPattern = Pattern.compile(IP_ADDRESS);
    private static final Pattern cidrPattern = Pattern.compile(SLASH_FORMAT);
    private static final int NBITS = 32;

    public static void main(final String[] args) throws Exception {
        // int[] bounds = ScanNet.rangeFromCidr("10.4.0.1/24");

        // String address1 = ScanNet.intToIp(bounds[0]);
        // String address2 = ScanNet.intToIp(bounds[1]);

        final String address1 = "192.168.4.1";
        final String address2 = "192.168.4.255";

        IPAddress ipAux = new IPAddress(address1);
        final IPAddress ip2 = new IPAddress(address2);
        System.out.println("CITSMART - ScanNet: ip1 = " + ipAux + " ao ip2 = " + address2);
        // System.out.println("CITSMART - Discovery: ip2 = " + ip2);

        final List lst = new ArrayList<>();
        do {
            lst.add(ipAux.toString());
            System.out.println("IP:" + ipAux.toString());

            ipAux = ipAux.next();

        } while (ipAux.getValue() <= ip2.getValue());

        UtilTratamentoArquivos.geraFileTXT("c:\\listaIPs012.txt", lst);
    }

    private void calculate(final String mask) {
        final Matcher matcher = cidrPattern.matcher(mask);

        int netmask = 0;
        int address = 0;
        int network = 0;
        if (matcher.matches()) {
            address = this.matchAddress(matcher);

            /* Create a binary netmask from the number of bits specification /x */
            final int cidrPart = this.rangeCheck(Integer.parseInt(matcher.group(5)), 0, NBITS - 1);
            for (int j = 0; j < cidrPart; ++j) {
                netmask |= 1 << 31 - j;
            }

            /* Calculate base network address */
            network = address & netmask;
        } else {
            throw new IllegalArgumentException("Could not parse [" + mask + "]");
        }
    }

    private int matchAddress(final Matcher matcher) {
        int addr = 0;
        for (int i = 1; i <= 4; ++i) {
            final int n = this.rangeCheck(Integer.parseInt(matcher.group(i)), 0, 255);
            addr |= (n & 0xff) << 8 * (4 - i);
        }
        return addr;
    }

    private int rangeCheck(final int value, final int begin, final int end) {
        if (value >= begin && value <= end) {
            return value;
        }

        throw new IllegalArgumentException("Value out of range: [" + value + "]");
    }

    private String toCidrNotation(final String addr, final String mask) {
        return addr + "/" + this.pop(this.toInteger(mask));
    }

    private int toInteger(final String address) {
        final Matcher matcher = addressPattern.matcher(address);
        if (matcher.matches()) {
            return this.matchAddress(matcher);
        } else {
            throw new IllegalArgumentException("Could not parse [" + address + "]");
        }
    }

    /*
     * Count the number of 1-bits in a 32-bit integer using a divide-and-conquer strategy
     * see Hacker's Delight section 5.1
     */
    int pop(int x) {
        x = x - (x >>> 1 & 0x55555555);
        x = (x & 0x33333333) + (x >>> 2 & 0x33333333);
        x = x + (x >>> 4) & 0x0F0F0F0F;
        x = x + (x >>> 8);
        x = x + (x >>> 16);
        return x & 0x0000003F;
    }

    public static int[] rangeFromCidr(final String cidrIp) {
        final int maskStub = 1 << 31;
        final String[] atoms = cidrIp.split("/");
        final int mask = Integer.parseInt(atoms[1]);
        // System.out.println(mask);

        final int[] result = new int[2];
        result[0] = ScanNet.ipToInt(atoms[0]) & maskStub >> mask - 1; // lower
                                                                      // bound
        result[1] = ScanNet.ipToInt(atoms[0]); // upper bound
        // System.out.println(ScanNet.intToIp(result[0]));
        // System.out.println(ScanNet.intToIp(result[1]));

        return result;
    }

    public static int ipToInt(final String ipAddress) {
        try {
            final byte[] bytes = InetAddress.getByName(ipAddress).getAddress();
            final int octet1 = (bytes[0] & 0xFF) << 24;
            final int octet2 = (bytes[1] & 0xFF) << 16;
            final int octet3 = (bytes[2] & 0xFF) << 8;
            final int octet4 = bytes[3] & 0xFF;
            final int address = octet1 | octet2 | octet3 | octet4;

            return address;
        } catch (final Exception e) {
            e.printStackTrace();

            return 0;
        }
    }

    public static String intToIp(final int ipAddress) {
        final int octet1 = (ipAddress & 0xFF000000) >>> 24;
            final int octet2 = (ipAddress & 0xFF0000) >>> 16;
            final int octet3 = (ipAddress & 0xFF00) >>> 8;
            final int octet4 = ipAddress & 0xFF;

            return new StringBuilder().append(octet1).append('.').append(octet2).append('.').append(octet3).append('.').append(octet4).toString();
    }
}
