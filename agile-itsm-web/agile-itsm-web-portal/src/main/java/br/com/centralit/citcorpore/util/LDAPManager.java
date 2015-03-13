package br.com.centralit.citcorpore.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.AttributeInUseException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.NoSuchAttributeException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LDAPManager {

    /** The OU (organizational unit) to add users to */
    private static final String USERS_OU = "ou=administracao,dc=centralit,dc=com,dc=br";

    /** The OU (organizational unit) to add groups to */
    private static final String GROUPS_OU = "ou=Groups,dc=centralit,dc=com,dc=br";

    /** The OU (organizational unit) to add permissions to */
    private static final String PERMISSIONS_OU = "ou=Permissions,dc=centralit,dc=com,dc=br";

    /** The default LDAP port */
    private static final int DEFAULT_PORT = 389;

    /** The LDAPManager instance object */
    private static Map instances = new HashMap<>();

    /** The connection, through a <code>DirContext</code>, to LDAP */
    private DirContext context;

    /** The hostname connected to */
    private final String hostname;

    /** The port connected to */
    private final int port;

    protected LDAPManager(final String hostname, final int port, final String username, final String password) throws NamingException {
        context = this.getInitialContext(hostname, port, username, password);

        // Only save data if we got connected
        this.hostname = hostname;
        this.port = port;
    }

    public static LDAPManager getInstance(final String hostname, final int port, final String username, final String password) throws NamingException {
        // Construct the key for the supplied information
        final String key = new StringBuilder().append(hostname).append(":").append(port).append("|").append(username == null ? "" : username).append("|")
                .append(password == null ? "" : password).toString();

        if (!instances.containsKey(key)) {
            synchronized (LDAPManager.class) {
                if (!instances.containsKey(key)) {
                    final LDAPManager instance = new LDAPManager(hostname, port, username, password);
                    instances.put(key, instance);
                    return instance;
                }
            }
        }

        return (LDAPManager) instances.get(key);
    }

    public static LDAPManager getInstance(final String hostname, final int port) throws NamingException {

        return getInstance(hostname, port, null, null);
    }

    public static LDAPManager getInstance(final String hostname) throws NamingException {

        return getInstance(hostname, DEFAULT_PORT, null, null);
    }

    public void addUser(final String username, final String firstName, final String lastName, final String password) throws NamingException {

        // Create a container set of attributes
        final Attributes container = new BasicAttributes();

        // Create the objectclass to add
        final Attribute objClasses = new BasicAttribute("objectClass");
        objClasses.add("top");
        objClasses.add("person");
        objClasses.add("organizationalPerson");
        objClasses.add("inetOrgPerson");

        // Assign the username, first name, and last name
        final String cnValue = new StringBuilder(firstName).append(" ").append(lastName).toString();
        final Attribute cn = new BasicAttribute("cn", cnValue);
        final Attribute givenName = new BasicAttribute("givenName", firstName);
        final Attribute sn = new BasicAttribute("sn", lastName);
        final Attribute uid = new BasicAttribute("uid", username);

        // Add password
        final Attribute userPassword = new BasicAttribute("userpassword", password);

        // Add these to the container
        container.put(objClasses);
        container.put(cn);
        container.put(sn);
        container.put(givenName);
        container.put(uid);
        container.put(userPassword);

        // Create the entry
        context.createSubcontext(this.getUserDN(username), container);
    }

    public void deleteUser(final String username) throws NamingException {
        try {
            context.destroySubcontext(this.getUserDN(username));
        } catch (final NameNotFoundException e) {
            // If the user is not found, ignore the error
        }
    }

    public boolean isValidUser(final String username, final String password) throws Exception {
        try {
            context = this.getInitialContext(hostname, port, username, password);
            // DirContext context =
            // getInitialContext(hostname, port, getUserDN(username),
            // password);
            return true;
        } catch (final javax.naming.NameNotFoundException e) {
            e.printStackTrace();
            throw new Exception(username);
        } catch (final NamingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addGroup(final String name, final String description) throws NamingException {

        // Create a container set of attributes
        final Attributes container = new BasicAttributes();

        // Create the objectclass to add
        final Attribute objClasses = new BasicAttribute("objectClass");
        objClasses.add("top");
        objClasses.add("groupOfUniqueNames");
        objClasses.add("groupOfForethoughtNames");

        // Assign the name and description to the group
        final Attribute cn = new BasicAttribute("cn", name);
        final Attribute desc = new BasicAttribute("description", description);

        // Add these to the container
        container.put(objClasses);
        container.put(cn);
        container.put(desc);

        // Create the entry
        context.createSubcontext(this.getGroupDN(name), container);
    }

    public void deleteGroup(final String name) throws NamingException {
        try {
            context.destroySubcontext(this.getGroupDN(name));
        } catch (final NameNotFoundException e) {
            // If the group is not found, ignore the error
        }
    }

    public void addPermission(final String name, final String description) throws NamingException {

        // Create a container set of attributes
        final Attributes container = new BasicAttributes();

        // Create the objectclass to add
        final Attribute objClasses = new BasicAttribute("objectClass");
        objClasses.add("top");
        objClasses.add("forethoughtPermission");

        // Assign the name and description to the group
        final Attribute cn = new BasicAttribute("cn", name);
        final Attribute desc = new BasicAttribute("description", description);

        // Add these to the container
        container.put(objClasses);
        container.put(cn);
        container.put(desc);

        // Create the entry
        context.createSubcontext(this.getPermissionDN(name), container);
    }

    public void deletePermission(final String name) throws NamingException {
        try {
            context.destroySubcontext(this.getPermissionDN(name));
        } catch (final NameNotFoundException e) {
            // If the permission is not found, ignore the error
        }
    }

    public void assignUser(final String username, final String groupName) throws NamingException {

        try {
            final ModificationItem[] mods = new ModificationItem[1];

            final Attribute mod = new BasicAttribute("uniqueMember", this.getUserDN(username));
            mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod);
            context.modifyAttributes(this.getGroupDN(groupName), mods);
        } catch (final AttributeInUseException e) {
            // If user is already added, ignore exception
        }
    }

    public void removeUser(final String username, final String groupName) throws NamingException {

        try {
            final ModificationItem[] mods = new ModificationItem[1];

            final Attribute mod = new BasicAttribute("uniqueMember", this.getUserDN(username));
            mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, mod);
            context.modifyAttributes(this.getGroupDN(groupName), mods);
        } catch (final NoSuchAttributeException e) {
            // If user is not assigned, ignore the error
        }
    }

    public boolean userInGroup(final String username, final String groupName) throws NamingException {

        // Set up attributes to search for
        final String[] searchAttributes = new String[1];
        searchAttributes[0] = "uniqueMember";

        final Attributes attributes = context.getAttributes(this.getGroupDN(groupName), searchAttributes);
        if (attributes != null) {
            final Attribute memberAtts = attributes.get("uniqueMember");
            if (memberAtts != null) {
                for (final NamingEnumeration vals = memberAtts.getAll(); vals.hasMoreElements();) {
                    if (username.equalsIgnoreCase(this.getUserUID((String) vals.nextElement()))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List getMembers(final String groupName) throws NamingException {
        final List members = new LinkedList();

        // Set up attributes to search for
        final String[] searchAttributes = new String[1];
        searchAttributes[0] = "uniqueMember";

        final Attributes attributes = context.getAttributes(this.getGroupDN(groupName), searchAttributes);
        if (attributes != null) {
            final Attribute memberAtts = attributes.get("uniqueMember");
            if (memberAtts != null) {
                for (final NamingEnumeration vals = memberAtts.getAll(); vals.hasMoreElements(); members.add(this.getUserUID((String) vals.nextElement()))) {
                    ;
                }
            }
        }

        return members;
    }

    public List getGroups(final String username) throws NamingException {
        final List groups = new LinkedList();

        final Hashtable env = new Hashtable();
        // String adminName = "CN=emauri,CN=Users,DC=centralit,DC=com,DC=br";
        final String adminName = "emauri";
        final String adminPassword = "pereba05";

        final String ldapURL = "ldap://10.100.100.30:389";
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // set security credentials, note using simple cleartext authentication
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        env.put(Context.SECURITY_CREDENTIALS, adminPassword);

        // connect to my domain controller
        env.put(Context.PROVIDER_URL, ldapURL);
        // specify attributes to be returned in binary format
        env.put("java.naming.ldap.attributes.binary", "tokenGroups");

        // env.put(Context.REFERRAL, "follow");

        final LdapContext ctx = new InitialLdapContext(env, null);

        // Search for groups the user belongs to in order to get their names
        // Create the search controls
        final SearchControls groupsSearchCtls = new SearchControls();

        // Specify the search scope
        groupsSearchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // Specify the attributes to return
        // String groupsReturnedAtts[]={"sAMAccountName"};
        // groupsSearchCtls.setReturningAttributes(groupsReturnedAtts);

        // Search for objects using the filter
        // NamingEnumeration results = ctx.search(groupsSearchBase, "(objectClass=Groups)", groupsSearchCtls);

        NamingEnumeration results = null;
        final SearchControls search = new SearchControls();
        search.setSearchScope(SearchControls.SUBTREE_SCOPE);
        results = ctx.search("dc=centralit,dc=com,dc=br", "(&(objectClass=group)(member=cn=emauri,ou=Users,dc=centralit,dc=com,dc=br))", search);

        // NamingEnumeration results =
        // context.search(base, filter, cons);
        // NamingEnumeration results =
        // context.search(GROUPS_OU, filter, cons);

        while (results.hasMore()) {
            final SearchResult result = (SearchResult) results.next();
            groups.add(this.getGroupCN(result.getName()));
        }
        return groups;
    }

    public List getGroups2(final String username) throws NamingException {
        final List groups = new LinkedList();

        // Set up criteria to search on
        /*
         * String filter = new StringBuilder()
         * .append("(&")
         * .append("(objectClass=group)")
         * .append("(uniqueMember=")
         * .append(getUserDN(username))
         * .append(")")
         * .append(")")
         * .toString();
         */

        final String filter = "(&(objectClass=group)(member=cn=emauri,ou=Users,dc=centralit,dc=com,dc=br))";

        // Set up search constraints
        final SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.SUBTREE_SCOPE);

        final NamingEnumeration results = context.search("dc=centralit,dc=com,dc=br", filter, cons);

        while (results.hasMore()) {
            final SearchResult result = (SearchResult) results.next();
            groups.add(this.getGroupCN(result.getName()));
        }

        return groups;
    }

    public void assignPermission(final String groupName, final String permissionName) throws NamingException {

        try {
            final ModificationItem[] mods = new ModificationItem[1];

            final Attribute mod = new BasicAttribute("uniquePermission", this.getPermissionDN(permissionName));
            mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod);
            context.modifyAttributes(this.getGroupDN(groupName), mods);
        } catch (final AttributeInUseException e) {
            // Ignore the attribute if it is already assigned
        }
    }

    public void revokePermission(final String groupName, final String permissionName) throws NamingException {

        try {
            final ModificationItem[] mods = new ModificationItem[1];

            final Attribute mod = new BasicAttribute("uniquePermission", this.getPermissionDN(permissionName));
            mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, mod);
            context.modifyAttributes(this.getGroupDN(groupName), mods);
        } catch (final NoSuchAttributeException e) {
            // Ignore errors if the attribute doesn't exist
        }
    }

    public boolean hasPermission(final String groupName, final String permissionName) throws NamingException {

        // Set up attributes to search for
        final String[] searchAttributes = new String[1];
        searchAttributes[0] = "uniquePermission";

        final Attributes attributes = context.getAttributes(this.getGroupDN(groupName), searchAttributes);
        if (attributes != null) {
            final Attribute permAtts = attributes.get("uniquePermission");
            if (permAtts != null) {
                for (final NamingEnumeration vals = permAtts.getAll(); vals.hasMoreElements();) {
                    if (permissionName.equalsIgnoreCase(this.getPermissionCN((String) vals.nextElement()))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List getPermissions(final String groupName) throws NamingException {
        final List permissions = new LinkedList();

        // Set up attributes to search for
        final String[] searchAttributes = new String[1];
        searchAttributes[0] = "uniquePermission";

        final Attributes attributes = context.getAttributes(this.getGroupDN(groupName), searchAttributes);
        if (attributes != null) {
            final Attribute permAtts = attributes.get("uniquePermission");
            if (permAtts != null) {
                for (final NamingEnumeration vals = permAtts.getAll(); vals.hasMoreElements(); permissions
                        .add(this.getPermissionCN((String) vals.nextElement()))) {
                    ;
                }
            }
        }

        return permissions;
    }

    private String getUserDN(final String username) {
        return new StringBuilder().append("uid=").append(username).append(",").append(USERS_OU).toString();
    }

    private String getUserUID(final String userDN) {
        final int start = userDN.indexOf("=");
        int end = userDN.indexOf(",");

        if (end == -1) {
            end = userDN.length();
        }

        return userDN.substring(start + 1, end);
    }

    private String getGroupDN(final String name) {
        return new StringBuilder().append("cn=").append(name).append(",").append(GROUPS_OU).toString();
    }

    private String getGroupCN(final String groupDN) {
        final int start = groupDN.indexOf("=");
        int end = groupDN.indexOf(",");

        if (end == -1) {
            end = groupDN.length();
        }

        return groupDN.substring(start + 1, end);
    }

    private String getPermissionDN(final String name) {
        return new StringBuilder().append("cn=").append(name).append(",").append(PERMISSIONS_OU).toString();
    }

    private String getPermissionCN(final String permissionDN) {
        final int start = permissionDN.indexOf("=");
        int end = permissionDN.indexOf(",");

        if (end == -1) {
            end = permissionDN.length();
        }

        return permissionDN.substring(start + 1, end);
    }

    private DirContext getInitialContext(final String hostname, final int port, final String username, final String password) throws NamingException {

        final String providerURL = new StringBuilder("ldap://").append(hostname).append(":").append(port).toString();

        final Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, providerURL);

        if (username != null && !username.equals("")) {
            props.put(Context.SECURITY_AUTHENTICATION, "simple");
            props.put(Context.SECURITY_PRINCIPAL, username);
            props.put(Context.SECURITY_CREDENTIALS, password == null ? "" : password);
        }

        return new InitialDirContext(props);
    }

    public static final String binarySidToStringSid(final byte[] SID) {

        String strSID = "";

        // convert the SID into string format

        long version;
        long authority;
        long count;
        long rid;

        strSID = "S";
        version = SID[0];
        strSID = strSID + "-" + Long.toString(version);
        authority = SID[4];

        for (int i = 0; i < 4; i++) {
            authority <<= 8;
            authority += SID[4 + i] & 0xFF;
        }

        strSID = strSID + "-" + Long.toString(authority);
        count = SID[2];
        count <<= 8;
        count += SID[1] & 0xFF;

        for (int j = 0; j < count; j++) {

            rid = SID[11 + j * 4] & 0xFF;

            for (int k = 1; k < 4; k++) {

                rid <<= 8;

                rid += SID[11 - k + j * 4] & 0xFF;

            }

            strSID = strSID + "-" + Long.toString(rid);

        }

        return strSID;

    }

}
