package br.com.centralit.nagios.livestatus.tables;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.centralit.nagios.livestatus.query.LSQuery;
import br.com.centralit.nagios.livestatus.response.Response;

public class LiveStatusBase {

    public static final Map<String, String> mapComments = new HashMap<>();
    public Map<String, String> mapObjects = new HashMap<>();
    public final Map<String, String> mapKeyValue = new HashMap<>();
    private final Map<String, String> mapVazio = new HashMap<String, String>() {

        private static final long serialVersionUID = -1494959981052297712L;

    };

    protected int count = 0;
    protected String host = "";
    protected int port = -1;
    protected String tableName = "hosts";
    protected String socket_type = "tcp";
    protected String path_or_host = "";

    /*
     * To change this license header, choose License Headers in Project Properties. To change this template file, choose Tools | Templates and open the template
     * in the editor.
     */
    /**
     *
     * @author adenir
     */
    /**
     * Initialize the nagios mklivestatus socket informations.
     *
     * @param path
     *            = Two type of socket are supported for now: * TCP : path equal to "tcp://host:port" File : where path is the path to the file
     */
    public LiveStatusBase(final String path) {

        // check socket type
        // if the path start with tcp:// => tcp socket
        if (path.startsWith("tcp://")) {
            final String[] table = path.split(":");
            socket_type = "tcp";
            path_or_host = table[1].split("//")[1];
            port = Integer.parseInt(table[2]);

            // default socket type is set to file
        } else {
            final File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                path_or_host = path;
                socket_type = "file";
            } else {
                throw new IllegalArgumentException("Socket error : socket type not recognized for \"" + path + "\"");
            }
        }
    }

    public Socket connectToSocket(final String host, final int port) throws Exception {
        final Socket socket = new Socket();

        socket.connect(new InetSocketAddress(host, port));

        return socket;
    }

    /**
     * Realiza a conexão e executa a query.
     *
     * @param request
     * @param query
     * @return
     * @throws Exception
     */
    public Response executeQuery(final String request, final LSQuery query) throws Exception {
        if (path_or_host.isEmpty() || port == -1) {
            throw new IllegalArgumentException("Server/host or/and port is invalid");
        }

        final Response response = new Response(query);

        try (Socket socket = this.connectToSocket(path_or_host, port)) {
            final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // System.out.println(socket);
            System.out.println(request);

            out.write(request, 0, request.length());
            out.flush();
            socket.shutdownOutput();

            while (true) {
                final String line = in.readLine();
                if (line == null) {
                    break;
                }

                response.add(line);
            }
        }

        return response;
    }

    public Response execute_query(final String table, final String columns, final String filters) throws Exception {
        final LSQuery query = new LSQuery(table, columns, filters);

        return this.executeQuery(query.to_s(mapVazio), query);
    }

    public String[] getHeaders(final String table) throws Exception {
        final LSQuery query = new LSQuery(table, "", "");
        return this.executeQuery(query.to_s(new HashMap<String, String>() {

            {
                this.put("limit", "0");
            }
        }), query).getListHeaders();
    }

    public void setMapObjects(final String table, final String filter) {
        mapObjects.clear();
        try {
            mapObjects = this.execute_query(table, "", filter).getMapColumnsAndValue();
        } catch (final Exception ex) {
            Logger.getLogger(Hosts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // get as String ---------------------- Date
    public String getAsString(final Date d) {
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (year/day/month)
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    // get as String ---------------------- Int
    public String getAsString(final int d) {
        return Integer.toString(d);
    }

    // get as String ---------------------- Int
    public String getAsString(final Float d) {
        return Float.toString(d);
    }

    // get as String ---------------------- STRING
    public String getAsString(final String column) {
        return mapObjects.get(column);

    }

    // get as signed int 32 - 4 byte ---------------------- int SIGNED
    public int getAsInt(final String column) throws NumberFormatException {

        try {
            return Integer.parseInt(mapObjects.get(column));
        } catch (final NumberFormatException e) {
            return -1;
        }

    }

    // get as signed int 32 - 4 byte ---------------------- int SIGNED
    public String getAsList(final String column) {
        return mapObjects.get(column);
    }

    // get as signed int 32 - 4 byte ---------------------- int SIGNED
    public String getAsDict(final String column) {
        return mapObjects.get(column);
    }

    // get as signed int 32 - 4 byte ---------------------- int SIGNED
    public Date getAsTime(final String column) throws NumberFormatException {
        String value = mapObjects.get(column);
        if (value == null || value.isEmpty() || value.equals("")) {
            System.out.println("................ value = 1000 para " + column);
            value = "0";
        }

        return new java.util.Date(Long.parseLong(value) * 1000);
    }

    // get as signed int 32 - 4 byte ---------------------- int SIGNED
    public float getAsFloat(final String column) throws NumberFormatException {
        String value = mapObjects.get(column);
        if (value == null || value.isEmpty() || value.equals("")) {
            System.out.println("................ value = -255 para " + column);
            value = "-255";
        }
        return Float.parseFloat(value);
    }

    protected Boolean addToHashtable(final String key, final Object ob) {
        // The Add method throws an exception if the new key is
        // already in the hash table.
        try {
            mapKeyValue.put(key, ob.toString());
            return true;
        } catch (final NullPointerException e) {
            System.out.println(String.format("An element with Key = %s already exists.", key));
            return false;
        }

    }

    protected Boolean addToHashtable(final String key, final String ob) {
        // The Add method throws an exception if the new key is
        // already in the hash table.
        try {
            mapKeyValue.put(key, ob);
            return true;
        } catch (final NullPointerException e) {
            System.out.println(String.format("An element with Key = %s already exists.", key));
            return false;
        }

    }

    public String getComment(final String key) {
        if (mapComments.containsKey(key)) {
            return mapComments.get(key);
        } else {
            return "s/c";
        }
    }

    public String toStringKeyValueComment(final String key) {
        return key + " = " + mapObjects.get(key) + " | " + this.getComment(key);
    }

    public String toStringKeyValue(final String key) {
        return key + " = " + mapObjects.get(key);
    }

    public String toStringValue(final String key) {
        return mapObjects.get(key);
    }

    public String getValue(final String key) {
        return mapObjects.get(key);
    }

    public Map<String, String> asArrayString(final String table, final String filter) throws ParseException {
        return mapKeyValue;
    }

    public String[] asArrayStringKeyValues(final String table, final String filter) throws ParseException {
        this.asArrayString(table, filter);

        final String[] array = new String[mapKeyValue.keySet().size()];
        // Sorted - classificando
        final Map<String, String> treeMap = new TreeMap<>(mapKeyValue);
        final Set<Map.Entry<String, String>> entrySet = treeMap.entrySet();

        int idcc = 0;
        for (final Map.Entry<String, String> entry : entrySet) {
            array[idcc++] = entry.getKey() + "  =  " + entry.getValue();
        }

        return array;
    }

}
