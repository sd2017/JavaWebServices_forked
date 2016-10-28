import java.io.*;
import java.util.*;

public class JAXMGetBookPrice {

    //Default values used if no command line parameters are set
    private static final String DEFAULT_HOST_URL = "http://services.xmethods.com:80/soap/servlet/rpcrouter";
//    private static final String DEFAULT_HOST_URL = "http://localhost:5555/examples/servlet/rpcrouter";
    private static final String DEFAULT_ISBN = "0596000685";
    private static final String URI = "urn:oreilly:jaws:samples";

    //Member variables
    private String m_hostURL;
    private String m_bookISBN;

    public JAXMGetBookPrice(String hostURL, String bookISBN) throws Exception
    {
        m_hostURL = hostURL;
        m_bookISBN = bookISBN;

	System.out.println();
        System.out.println("_________________________________________________________");
        System.out.println("Starting JAXMGetBookPrice:");
	System.out.println("    host url        = " + m_hostURL);
        System.out.println("    book ISBN       = " + m_bookISBN);
        System.out.println("___________________________________________________________");
        System.out.println();
    }

    public void sendJAXMMessage()
    {
        try {

             // Create a connection to a remote party, when not using a messaging provider
	    javax.xml.soap.SOAPConnectionFactory scf = javax.xml.soap.SOAPConnectionFactory.newInstance();
            javax.xml.soap.SOAPConnection connection = scf.createConnection();

            // Get an instance of the MessageFactory class
            javax.xml.soap.MessageFactory mf = javax.xml.soap.MessageFactory.newInstance();

            // Create a message from the message factory. It already contains a SOAP part
            javax.xml.soap.SOAPMessage message = mf.createMessage();

            // Get the message's SOAP part
            javax.xml.soap.SOAPPart soapPart = message.getSOAPPart();

            // Get the SOAP part envelope.
            javax.xml.soap.SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("xsi","http://www.w3.org/1999/XMLSchema-instance");
            envelope.addNamespaceDeclaration("xsd","http://www.w3.org/1999/XMLSchema");

            // Get the Body from the SOAP envelope
            javax.xml.soap.SOAPBody body = envelope.getBody();

            // Add an element and content to the Body
            javax.xml.soap.Name name = envelope.createName("getPrice","ns1","urn:xmethods-BNPriceCheck");
	    javax.xml.soap.SOAPBodyElement bodyElement = body.addBodyElement (name);
            bodyElement.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");

            name = envelope.createName("isbn");
            javax.xml.soap.SOAPElement book = bodyElement.addChildElement(name);
            book.addTextNode("0596000686");
            name = envelope.createName("xsi:type");
            book.addAttribute(name,"xsd:string");

            // Send the message
            System.err.println("Sending message to URL: "+ m_hostURL);

            // Synchronously send the message to the endpoint and wait for a reply
            javax.xml.soap.SOAPMessage reply =
                connection.call(message,
                    new javax.xml.messaging.URLEndpoint (m_hostURL));

            System.out.println("Received reply from: " + m_hostURL);

            // Display the reply received from the endpoint
            boolean displayResult = true;
            if( displayResult ) {
                // Dump out message response.
                System.out.println("Result:");
                reply.writeTo(System.out);
            }

            connection.close();

        } catch(Throwable e) {
            e.printStackTrace();
        }
    }

    //
    // NOTE: the remainder of this deals with reading arguments
    //
    /** Main program entry point. */

    public static void main(String args[]) {

        // Values to be read from parameters
        String hostURL          = DEFAULT_HOST_URL;
        String bookISBN         = DEFAULT_ISBN;

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

        // Start the SimpleJAXMClient
        try
        {
            JAXMGetBookPrice jaxmClient = new JAXMGetBookPrice(hostURL, bookISBN);
            jaxmClient.sendJAXMMessage();

        }
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    }

    /** Prints the usage. */
    private static void printUsage() {

        StringBuffer use = new StringBuffer();
        use.append("usage: java JAXMGetBookPrice (options) ...\n\n");
        use.append("options:\n");
        use.append("  -url hostURL      Specify host url\n");
        use.append("                    Default host url: " + DEFAULT_HOST_URL+"\n");
        use.append("  -isbn boookISBN   Specify book ISBN #\n");
        use.append("                    Default book ISBN #: " + DEFAULT_ISBN+"\n");
        use.append("  -h                This help screen.\n");
        System.err.println (use);
    }
}


