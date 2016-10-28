/*
Writing a SOAP Http Client Application that sends UDDI-formatted messages
using the standard Java libraries to send an http request to a tomcat servlet.

usage:
    java UDDISoapClient (options)
        options:
          -url hosturl      Specify host url
                            Default host url: DEFAULT_HOST_URL
          -df datafile      Specify data file name
                            Default host file name: DEFAULT_DATA_FILENAME
          -h                This help screen.
          -at               Specify Attachment file name

You do NOT need to specify any parameters when using the default configuration.

*/

import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.apache.xml.serialize.*;
public class UDDISoapClient
{
    // Default values used if no command line parameters are set
    // private static final String DEFAULT_HOST_URL    = "http://localhost:8443/wasp/uddi/publishing/";
    private static final String DEFAULT_HOST_URL    = "http://localhost:8080/wasp/uddi/inquiry/";
    private static final String DEFAULT_DATA_FILENAME   = "./Default.xml";

    // In the SOAP chapter, we used "urn:oreilly:jaws:samples", 
    // but Systinet UDDI requires this to be blank.
    private static final String URI                     = "";

    //Member variables
    private String m_hostURL;

    //data file that will be the body content of a soap envelop
    private String m_dataFileName;

    public UDDISoapClient(String hostURL, String dataFileName) throws Exception
    {
        m_hostURL = hostURL;
        m_dataFileName    = dataFileName;
        
        System.out.println();
        System.out.println("_________________________________________________________");
        System.out.println("Starting UDDISoapClient:");
        System.out.println("    host url        = " + m_hostURL);
        System.out.println("    data file       = " + m_dataFileName);
        System.out.println("___________________________________________________________");
        System.out.println();
    }
    
    public void sendSOAPMessage() {
        try {
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
            
            // Create a vector for collecting the body elements
            Vector bodyElements = new Vector();
            
            // Parse XML element as soap body element
            bodyElements.add(doc.getDocumentElement ());
            
            // Create the SOAP envelope
            org.apache.soap.Envelope envelope = new org.apache.soap.Envelope();
            envelope.declareNamespace("idoox", "http://idoox.com/uddiface");
            envelope.declareNamespace("ua", "http://idoox.com/uddiface/account");
            envelope.declareNamespace("config", "http://idoox.com/uddiface/config");
            envelope.declareNamespace("attr", "http://idoox.com/uddiface/attr");
            envelope.declareNamespace("fxml", "http://idoox.com/uddiface/formxml");
            envelope.declareNamespace("inner", "http://idoox.com/uddiface/inner");
            envelope.declareNamespace("", "http://idoox.com/uddiface/inner");
            envelope.declareNamespace("uddi", "urn:uddi-org:api_v2");
            
            //
            // NO SOAP HEADER ELEMENT AS SYSTINET WASP DOES NOT REQUIRE IT
            //
            
            // Create the SOAP body element
            org.apache.soap.Body body = new org.apache.soap.Body();
            body.setBodyEntries(bodyElements);
            envelope.setBody(body);
            
            // Build and send the Message.
            org.apache.soap.messaging.Message msg = 
                new org.apache.soap.messaging.Message();
            msg.send (new java.net.URL(m_hostURL), URI, envelope);
            System.out.println("Sent SOAP Message with Apache HTTP SOAP Client.");
            
            
            // Receive response from the transport and dump it to the screen
            System.out.println("Waiting for response....");
            org.apache.soap.transport.SOAPTransport st = msg.getSOAPTransport ();
            BufferedReader br = st.receive ();
            StringBuffer content = new StringBuffer();
            String line = br.readLine();

            if(line == null) {
                System.out.println("HTTP POST was unsuccessful. \n");
            } else {
                while (line != null) {
                    content.append(line);
                    line = br.readLine();
                }
            }

            // This pretty-prints the output with indentation.
            DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = 
                builder.parse(new InputSource(new StringReader(content.toString())));

            OutputFormat format = new OutputFormat(document);
            format.setIndenting(true);
            format.setLineWidth(160);

            StringWriter  writer = new StringWriter();
            XMLSerializer serial = new XMLSerializer(writer, format);

            serial.asDOMSerializer();
            serial.serialize(document.getDocumentElement() );

            System.out.println(writer.toString());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    //
    // NOTE: the remainder of this deals with reading arguments
    //
    /** Main program entry point. */
    public static void main(String args[]) {
        
        // Values to be read from parameters
        String hostURL    	= DEFAULT_HOST_URL;
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

        try     
            {
                UDDISoapClient soapClient =
                    new UDDISoapClient(hostURL, dataFileName);
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
        use.append("usage: java UDDISoapClient (options) ...\n\n");
        use.append("options:\n");
        use.append("  -url hostURL      Specify host url\n");
        use.append("                    Default host url: " + DEFAULT_HOST_URL+"\n");
        use.append("  -df datafile      Specify data file name\n");
        use.append("                    Default host file name: " + DEFAULT_DATA_FILENAME+"\n");
        use.append("  -h                This help screen.\n");
        System.err.println (use);
    }
}
