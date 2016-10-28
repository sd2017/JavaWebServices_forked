/*
Writing a SOAP RPC Client application using the standard Java libraries to
send an RPC request to a LOCAL Tomcat service.

usage:
    java CheckStock (options)
         parameter #1           Specify host url
         parameter #2           Specify item number

    You do not need to specify any parameters when using the default configuration
*/

import java.net.*;
import java.util.*;

public class CheckStock {

    private static final String DEFAULT_HOST_URL 
        = "http://localhost:8080/soap/servlet/rpcrouter";
//    private static final String DEFAULT_HOST_URL = "http://localhost:5555/soap/servlet/rpcrouter";
    private static final String DEFAULT_ITEM = "Test";
    private static final String URI = "urn:oreilly-jaws-samples";

    //Member variables
    private String m_hostURL;
    private String m_item;

    public CheckStock (String hostURL, String item) throws Exception
    {
        m_hostURL = hostURL;
        m_item    = item;

        System.out.println();
        System.out.println(
            "______________________________________________________");
        System.out.println("Starting CheckStock client:");
        System.out.println("    host url        = " + m_hostURL);
        System.out.println("    item            = " + m_item);
        System.out.println(
            "_______________________________________________________");
        System.out.println();
    }

    public void checkStock() throws Exception {

      //Build the call.
      org.apache.soap.rpc.Call call = new org.apache.soap.rpc.Call ();

      //This service uses standard SOAP encoding
      String encodingStyleURI = org.apache.soap.Constants.NS_URI_SOAP_ENC;
      call.setEncodingStyleURI(encodingStyleURI);

      //Set the target URI
      call.setTargetObjectURI ("urn:stock-onhand");

      //Set the method name to invoke
      call.setMethodName ("getQty");

      //Create the parameter objects
      Vector params = new Vector ();
      params.addElement (new org.apache.soap.rpc.Parameter("item", 
                            String.class, m_item, null));

      //Set the parameters
      call.setParams (params);

      //Invoke the service
      org.apache.soap.rpc.Response resp 
            = call.invoke ( new java.net.URL(m_hostURL),"");

      //Check the response
      if (resp != null) {
         if (resp.generatedFault ()) {
            org.apache.soap.Fault fault = resp.getFault ();
            System.out.println ("Call failed due to a SOAP Fault: ");
            System.out.println ("  Fault Code   = " + fault.getFaultCode ());
            System.out.println ("  Fault String = " + fault.getFaultString ());
         } else {
            org.apache.soap.rpc.Parameter result = resp.getReturnValue ();
            Integer intresult = (Integer) result.getValue();
            System.out.println ("The stock-on-hand quantity for this item is: " 
                + intresult );
         }
      }
    }

    //
    // NOTE: the remainder of this deals with reading arguments
    //
    /** Main program entry point. */

    public static void main(String args[]) {

        // Values to be read from parameters
        String hostURL          = DEFAULT_HOST_URL;
        String item             = DEFAULT_ITEM;

        // Check parameters
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            // Options
            if (!arg.startsWith("-")) {
                System.err.println ("error: unexpected argument - "+arg);
                printUsage();
                System.exit(1);
            }
            else {
                if (arg.equals("-url")) {
                    if (i == args.length - 1 || args[i+1].startsWith("-")) {
                        System.err.println("error: missing host address parameter");
                        System.exit(1);
                    }
                    hostURL = args[++i];
                    continue;
                }
                if (arg.equals("-item")) {
                    if (i == args.length - 1 || args[i+1].startsWith("-")) {
                        System.err.println("error: missing item parameter");
                        System.exit(1);
                    }
                    item = args[++i];
                    continue;
                }
                if (arg.equals("-h")) {
                    printUsage();
                    System.exit(1);
                }
            }
        }

        // Start the CheckStock client
        try
        {
            CheckStock stockClient = new CheckStock(hostURL, item);
            stockClient.checkStock();
        }catch(Exception e){
                         System.out.println(e.getMessage());
        }
    }

    /** Prints the usage. */
    private static void printUsage() {

        StringBuffer use = new StringBuffer();
        use.append("usage: java CheckStock (options) ...\n\n");
        use.append("options:\n");
        use.append("  -url hostURL      Specify host url\n");
        use.append("                    Default host url: " + DEFAULT_HOST_URL+"\n");
        use.append("  -item ItemNum     Specify item number (or 'Fail')\n");
        use.append("                    Default item: " + DEFAULT_ITEM+"\n");
        use.append("  -h                This help screen.\n");
        System.err.println (use);
    }
}
