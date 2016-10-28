/*
Writing a SOAP RPC Client application using the standard Java libraries to
send an RPC request to a xmethods service.

usage:
    java GetBookPrice (options)
         parameter #1           Specify service url
         parameter #2           Specify book ISBN number

    You do not need to specify any parameters when using the default configuration
*/

import java.io.*;
import java.util.*;

public class GetBookPrice {

  // default values to be used if not supplied on the command line
  private static final String DEFAULT_SERVICE_URL =
    "http://services.xmethods.com:80/soap/servlet/rpcrouter";
  private static final String DEFAULT_BOOK_ISBN = "0596000685";
  private String m_serviceURL;
  private String m_bookISBN;

  public GetBookPrice (String serviceURL, String bookISBN) throws Exception
  {
        //this section displays the status of the call to the service
        m_serviceURL = serviceURL;
        m_bookISBN   = bookISBN;

        System.out.println();
        System.out.println(
            "______________________________________________________");
        System.out.println("Starting GetBookPrice:");
        System.out.println("    service url     = " + m_serviceURL);
        System.out.println("    ISBN#           = " + m_bookISBN);
        System.out.println(
            "_______________________________________________________");
        System.out.println();
  }

  public static float sendSoapRPCMessage (String url, String isbn) 
                            throws Exception 
  {

    //Build the call.
    org.apache.soap.rpc.Call call = new org.apache.soap.rpc.Call ();

    //This service uses standard SOAP encoding
    String encodingStyleURI = org.apache.soap.Constants.NS_URI_SOAP_ENC;
    call.setEncodingStyleURI(encodingStyleURI);

    //Set the target URI
    call.setTargetObjectURI ("urn:xmethods-BNPriceCheck");

    //Set the method name to invoke
    call.setMethodName ("getPrice");

    //Create the parameter objects
    Vector params = new Vector ();
    params.addElement (new org.apache.soap.rpc.Parameter("isbn", 
                            String.class, isbn, null));

    //Set the parameters
    call.setParams (params);

    //Invoke the service
    org.apache.soap.rpc.Response resp = call.invoke (new java.net.URL(url),"");

    //Check the response
    if (resp.generatedFault ()) {
       org.apache.soap.Fault fault = resp.getFault();
       System.err.println("Generated fault: ");
       System.out.println("  Fault Code   = " + fault.getFaultCode());
       System.out.println("  Fault String = " + fault.getFaultString());
       return 0;
    } else {
       org.apache.soap.rpc.Parameter result = resp.getReturnValue ();
       Float FL = (Float) result.getValue();
       return FL.floatValue();
    }
  }

    public static void main(String args[]) {

        //Values to be read from parameters
        String serviceURL        = DEFAULT_SERVICE_URL;
        String bookISBN         = DEFAULT_BOOK_ISBN;

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
                    serviceURL = args[++i];
                    continue;
                }
                if (arg.equals("-isbn")) {
                    if (i == args.length - 1 || args[i+1].startsWith("-")) {
                        System.err.println("error: missing book isbn parameter");
                        System.exit(1);
                    }
                    bookISBN = args[++i];
                    continue;
                }
                if (arg.equals("-h")) {
                    printUsage();
                    System.exit(1);
                }
            }
        }

        try
        {
            GetBookPrice soapClient = new GetBookPrice(serviceURL, bookISBN);

            // call method that will perform RPC call using supplied Service
            // url and the book ISBN number to query on
        float f = soapClient.sendSoapRPCMessage(serviceURL, bookISBN);

            // output results of RPC service call
            if (bookISBN != DEFAULT_BOOK_ISBN) {
               System.out.println(
                "The Barnes & Noble price for this book is " + f);
            }else {
              System.out.println(
                "The price for O'Reilly's The Java Message Service book is " + f);
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
    }
    }

    /** Prints the usage. */
    private static void printUsage() {

        StringBuffer use = new StringBuffer();
        use.append("usage: java GetBookPrice (options) ...\n\n");
        use.append("options:\n");
        use.append("  -url hostURL           Specify host url\n");
        use.append("                         Default host url: " 
            + DEFAULT_SERVICE_URL+"\n");
        use.append("  -isbn ISBN number      Specify ISBN number\n");
        use.append("                         Default ISBN number: " 
            + DEFAULT_BOOK_ISBN+"\n");
        use.append("  -h                     This help screen.\n");
        System.err.println (use);
    }
}
