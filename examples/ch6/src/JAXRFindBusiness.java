import javax.xml.registry.*; 
import javax.xml.registry.infomodel.*; 
import java.net.*;
import java.util.*;

/*
 * This is the FindBusiness UDDI example implemented using
 * the JAXR libraries and the reference implementation
 * JAXR provider for accessing a UDDI registry.
 */
public class JAXRFindBusiness {

    public JAXRFindBusiness() {}

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java " +
                "JAXRFindBusiness <query-string>");
            System.exit(1);
        }

        String queryString = new String(args[0]);
        System.out.println("Query string is " + queryString);

        doQuery(queryString);
    }
    
    public static void doQuery(String qString) {
        Connection conn = null;

        // Define connection configuration properties 
        // To query, you need only the query URL
        Properties props = new Properties();
        props.setProperty("javax.xml.registry.queryManagerURL", 
                          "http://localhost:8080/wasp/uddi/inquiry/");
        props.setProperty("javax.xml.registry.factoryClass", 
                          "com.sun.xml.registry.uddi.ConnectionFactoryImpl");

        try {
            // Create the connection, passing it the 
            // configuration properties
            ConnectionFactory factory = 
                ConnectionFactory.newInstance();
            factory.setProperties(props);
            conn = factory.createConnection();

            // Get registry service and query manager
            RegistryService rs = conn.getRegistryService();
            BusinessQueryManager bqm = rs.getBusinessQueryManager();

            // Define find qualifiers and name patterns
            Collection qualifiers = new ArrayList();
            qualifiers.add(FindQualifier.SORT_BY_NAME_DESC);
            Collection namePatterns = new ArrayList();
            namePatterns.add(qString);

            // Find using the name
            BulkResponse response = 
                bqm.findOrganizations(qualifiers, 
                    namePatterns, null, null, null, null);
            Collection orgs = response.getCollection();

            // Display information about the organizations found
            Iterator orgIter = orgs.iterator();
            while (orgIter.hasNext()) {
                Organization org = 
                    (Organization) orgIter.next();
                System.out.println("Org name: " + getName(org));
                System.out.println("Org description: " + 
                    getDescription(org));
                System.out.println("Org key id: " + getKey(org));

                // Display primary contact information
                User pc = org.getPrimaryContact();
                if (pc != null) {
                    PersonName pcName = pc.getPersonName();
                    System.out.println(" Contact name: " + 
                        pcName.getFullName());
                    Collection phNums = 
                        pc.getTelephoneNumbers(pc.getType());
                    Iterator phIter = phNums.iterator();
                    while (phIter.hasNext()) {
                        TelephoneNumber num = 
                            (TelephoneNumber) phIter.next();
                        System.out.println("  Phone number: " + 
                            num.getNumber());
                    }
                    Collection eAddrs = pc.getEmailAddresses();
                    Iterator eaIter = eAddrs.iterator();
                    while (phIter.hasNext()) {
                        System.out.println("  Email Address: " + 
                            (EmailAddress) eaIter.next());
                    }
                }

                // Display service and binding information
                Collection services = org.getServices();
                Iterator svcIter = services.iterator();
                while (svcIter.hasNext()) {
                    Service svc = (Service) svcIter.next();
                    System.out.println(" Service name: " + 
                        getName(svc));
                    System.out.println(" Service description: " +
                        getDescription(svc));
                    Collection serviceBindings = 
                        svc.getServiceBindings();
                    Iterator sbIter = serviceBindings.iterator();
                    while (sbIter.hasNext()) {
                        ServiceBinding sb = 
                            (ServiceBinding) sbIter.next();
                        System.out.println("  Binding " +
                            "Description: " + 
                            getDescription(sb));
                        System.out.println("  Access URI: " + 
                            sb.getAccessURI());
                    }
                }
                // Print spacer between organizations
                System.out.println(" --- "); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally  {
            // At end, close connection to registry
            if (conn != null) {
                try {
                    conn.close();
                } catch (JAXRException je) {}
            }
        }
    }

    private static String getName(RegistryObject ro) throws JAXRException {
        try {
            return ro.getName().getValue();
        } catch (NullPointerException npe) {
            return "";
        }
    }
    
    private static String getDescription(RegistryObject ro) throws JAXRException {
        try {
            return ro.getDescription().getValue();
        } catch (NullPointerException npe) {
            return "";
        }
    }
    
    private static String getKey(RegistryObject ro) throws JAXRException {
        try {
            return ro.getKey().getId();
        } catch (NullPointerException npe) {
            return "";
        }
    }
}
