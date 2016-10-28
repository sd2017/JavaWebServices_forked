/*
Writing a SOAP Http Client Application using the standard Java libraries to send an http request
to a tomcat servlet.

usage:
    java HttpClient (options)
        options:
          -url hosturl      Specify host url
                            Default host url: DEFAULT_HOST_URL
          -df datafile      Specify data file name
                            Default host file name: DEFAULT_DATA_FILENAME
          -h                This help screen.
          -at               Specify Attachment file name

You do NOT need to specify any parameters when using the default configuration.

*/

//network communication via HTTP
import java.io.*;
import java.util.*;

public class GenericHTTPSoapClient
{
    //////////////
    //Default values used if no command line parameters are set
    //private static final String DEFAULT_HOST_URL        = "http://localhost:5555/soap/servlet/messagerouter";
    private static final String DEFAULT_HOST_URL        = "http://localhost:8080/soap/servlet/messagerouter";
    //private static final String DEFAULT_HOST_URL    = "http://localhost:8080/examples/servlet/HTTPReceive";
    //private static final String DEFAULT_HOST_URL    = "http://localhost:5555/examples/servlet/FaultServlet";
    private static final String DEFAULT_DATA_FILENAME   = "./PO.xml";

    private static final String URI                     = "urn:oreilly-jaws-samples";
    //////////////
    //Member variables
    private String m_hostURL;
	//data file that will be the body content of a soap envelop
	private String m_dataFileName;

    public GenericHTTPSoapClient(String hostURL, String dataFileName) throws Exception
    {
        m_hostURL = hostURL;
		m_dataFileName    = dataFileName;

		System.out.println();
        System.out.println("_________________________________________________________");
        System.out.println("Starting GenericHTTPSoapClient:");
		System.out.println("    host url        = " + m_hostURL);
		System.out.println("    data file       = " + m_dataFileName);
        System.out.println("___________________________________________________________");
        System.out.println();
    }

    public void sendSOAPMessage()
    {
        try
        {
            // get soap body to include in the SOAP envelope
            FileReader fr = new FileReader (m_dataFileName);
            javax.xml.parsers.DocumentBuilder xdb = 
                org.apache.soap.util.xml.XMLParserUtils.getXMLDocBuilder();
            org.w3c.dom.Document doc = 
                xdb.parse (new org.xml.sax.InputSource (fr));
            if (doc == null) {
                throw new org.apache.soap.SOAPException 
                    (org.apache.soap.Constants.FAULT_CODE_CLIENT, "parsing error");
            }

            // create a vector for collecting the header elements
            Vector headerElements = new Vector();

            // Create a header element in a namespace
            org.w3c.dom.Element headerElement =
                doc.createElementNS(URI,"jaws:MessageHeader");

            headerElement.setAttributeNS(URI,"SOAP-ENV:mustUnderstand","1");

            // Create subnodes within the MessageHeader
            org.w3c.dom.Element ele = doc.createElement("From");
            org.w3c.dom.Text textNode = doc.createTextNode("Me");
            org.w3c.dom.Node tempNode = ele.appendChild(textNode);

            tempNode = headerElement.appendChild(ele);

            ele = doc.createElement("To");
            textNode = doc.createTextNode("You");
            tempNode = ele.appendChild(textNode);

            tempNode = headerElement.appendChild(ele);

            ele = doc.createElement("MessageId");
            textNode = doc.createTextNode("9999");
            tempNode = ele.appendChild(textNode);

            tempNode = headerElement.appendChild(ele);

            headerElements.add(headerElement);

            // create a vector for collecting the body elements
            Vector bodyElements = new Vector();
            //parse xml elemnt as soap body element
            bodyElements.add(doc.getDocumentElement ());

            //Create the SOAP envelope
            org.apache.soap.Envelope envelope = new org.apache.soap.Envelope();

            //Add the SOAP header element to the envelope
            org.apache.soap.Header header = new org.apache.soap.Header();
            header.setHeaderEntries(headerElements);
            envelope.setHeader(header);

            //Create the SOAP body element
            org.apache.soap.Body body = new org.apache.soap.Body();
            body.setBodyEntries(bodyElements);
            //Add the SOAP body element to the envelope
            envelope.setBody(body);

            // Build the Message.
            org.apache.soap.messaging.Message msg = 
                new org.apache.soap.messaging.Message();

            msg.send (new java.net.URL(m_hostURL), URI, envelope);
            System.out.println("Sent SOAP Message with Apache HTTP SOAP Client.");

            // receive response from the transport and dump it to the screen
            System.out.println("Waiting for response....");
            org.apache.soap.transport.SOAPTransport st = msg.getSOAPTransport ();
            BufferedReader br = st.receive ();
            String line = br.readLine();
            if(line == null)
            {
                System.out.println("HTTP POST was successful. \n");
            }
            else
            {
               while (line != null)
               {
                    System.out.println (line);
                    line = br.readLine();
               }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //
    // NOTE: the remainder of this deals with reading arguments
    //
    /** Main program entry point. */
    public static void main(String args[]) {



        // Values to be read from parameters
        String hostURL    	    = DEFAULT_HOST_URL;
        String dataFileName     = DEFAULT_DATA_FILENAME;
        String attachment       = null;

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
                if (arg.equals("-df")) {
                    if (i == args.length - 1 || args[i+1].startsWith("-")) {
                        System.err.println("error: missing data file parameter");
                        System.exit(1);
                    }
                    dataFileName = args[++i];
                    continue;
                }if (arg.equals("-at")) {
                    if (i == args.length - 1 || args[i+1].startsWith("-")) {
                        System.err.println("error: missing attachment parameter");
                        System.exit(1);
                    }
                    attachment = args[++i];
                    continue;
                }

                if (arg.equals("-h")) {
                    printUsage();
                    System.exit(1);
                }
            }
        }

        // Start the HTTPSoapClient
        try
        {
            GenericHTTPSoapClient soapClient =
                new GenericHTTPSoapClient(hostURL, dataFileName);
            soapClient.sendSOAPMessage();

        }
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    }

    /** Prints the usage. */
    private static void printUsage() {

        StringBuffer use = new StringBuffer();
        use.append("usage: java GenericHTTPSoapClient (options) ...\n\n");
        use.append("options:\n");
        use.append("  -url hostURL      Specify host url\n");
        use.append("                    Default host url: " + DEFAULT_HOST_URL+"\n");
        use.append("  -df datafile      Specify data file name\n");
        use.append("                    Default host file name: " + DEFAULT_DATA_FILENAME+"\n");
        use.append("  -h                This help screen.\n");
        System.err.println (use);
    }
}