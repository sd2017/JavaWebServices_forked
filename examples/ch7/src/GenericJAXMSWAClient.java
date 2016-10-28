/*
Writing a Simple JAXM Client Application using the standard Java libraries to send an HTTP request
to a tomcat servlet.

usage:
    java GenericJAXMSWAClient (options)
        options:
          -url hosturl      Specify host url
                            Default host url: DEFAULT_HOST_URL
          -df datafile      Specify data file name
                            Default host file name: DEFAULT_DATA_FILENAME
          -at               Specify Attachment file name
          -h                This help screen.

You do NOT need to specify any parameters when using the default configuration.

*/

import java.io.*;
import java.util.*;

public class GenericJAXMSWAClient {

    //Default values used if no command line parameters are set
    private static final String DEFAULT_DATA_FILENAME   = "PO.xml";
    private static final String DEFAULT_HOST_URL 
        = "http://localhost:8080/examples/servlet/SimpleJAXMReceive";
    private static final String URI = "urn:oreilly-jaws-samples";
    private static final String DEFAULT_ATTACHMENT_FILENAME  
        = "Attachment.txt";

    //Member variables
    private String m_hostURL;
    private String m_dataFileName;
    private String m_attachment;

    public GenericJAXMSWAClient(String hostURL, String dataFileName,
                                            String attachment) throws Exception
    {
        m_hostURL = hostURL;
        m_dataFileName    = dataFileName;
        m_attachment = attachment;

        System.out.println();
        System.out.println("______________________________________________________");
        System.out.println("Starting GenericJAXMSWAClient:");
        System.out.println("    host url        = " + m_hostURL);
        System.out.println("    data file       = " + m_dataFileName);
        System.out.println("    attachment      = " + m_attachment);
        System.out.println("_______________________________________________________");
        System.out.println();
    }

    public void sendJAXMMessage()
    {
        try {

            // for doing JAXP transformations
            javax.xml.transform.TransformerFactory tFact 
                = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer 
                = tFact.newTransformer();

            // Create an specific URLEndpoint
            javax.xml.messaging.URLEndpoint endpoint 
                = new javax.xml.messaging.URLEndpoint(m_hostURL);

            // Create a connection 
            javax.xml.soap.SOAPConnectionFactory scf 
                = javax.xml.soap.SOAPConnectionFactory.newInstance();
            javax.xml.soap.SOAPConnection connection = scf.createConnection();

            // Get an instance of the MessageFactory class
            javax.xml.soap.MessageFactory mf 
                = javax.xml.soap.MessageFactory.newInstance();

            // Create a message from the message factory. 
            // It already contains a SOAP part
            javax.xml.soap.SOAPMessage message = mf.createMessage();

            // Get the message's SOAP part
            javax.xml.soap.SOAPPart soapPart = message.getSOAPPart();

            // Get the SOAP envelope from the SOAP part of the message. 
            javax.xml.soap.SOAPEnvelope envelope = soapPart.getEnvelope();

            // Read in the XML that will become the body in the SOAP envelope
            javax.xml.parsers.DocumentBuilderFactory dbf = 
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
            // dbf.setValidating(true);
            dbf.setNamespaceAware(true);
            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document poDoc = db.parse(m_dataFileName);

            // Get the empty SOAP Envelope as a generic Source
            // and put it into a DOMResult
            javax.xml.transform.Source spSrc = soapPart.getContent();
            javax.xml.transform.dom.DOMResult domResultEnv 
                    = new javax.xml.transform.dom.DOMResult();
            transformer.transform(spSrc, domResultEnv);
            
            // Now that we have the empty SOAP envelope in a DOMSource, we
            // need to put it together with the DOM we just built from the
            // input file.
            // Get the document
            org.w3c.dom.Node envelopeRoot = domResultEnv.getNode();
            if (envelopeRoot.getNodeType() == org.w3c.dom.Node.DOCUMENT_NODE)
            {
                // Get the root element of the document.
                org.w3c.dom.Element docEl 
                    = ((org.w3c.dom.Document)envelopeRoot).getDocumentElement();

                // Find the <SOAP-ENV:Body> tag using the envelope namespace
                org.w3c.dom.NodeList nList 
                    = docEl.getElementsByTagNameNS(
                        javax.xml.soap.SOAPConstants.URI_NS_SOAP_ENVELOPE,"Body");
                if (nList.getLength() > 0)
                {
                    // Found our <PurchaseOrder> element.  Plug it in
                    org.w3c.dom.Node bodyNode = nList.item(0);
                    org.w3c.dom.Node poRoot = poDoc.getDocumentElement();
                    
                    // Must import the node into this document.  Can't just 
                    // reassign a Node from one document into another 
                    // because it will generate error when you try and do 
                    // that.  Import does a copy.  If you are feeling brave, 
                    // there is an experimental adoptNode() in DOM3
                    org.w3c.dom.Node importedNode   
                        = ((org.w3c.dom.Document)envelopeRoot).importNode(poRoot, 
                                true);
                    bodyNode.appendChild(importedNode);

                    // Now shove it all back into the envelope.
                    // Convert it to a DOMSource, which is what the JAXM
                    // setContent() expects.
                    javax.xml.transform.dom.DOMSource domSource 
                        = new javax.xml.transform.dom.DOMSource(envelopeRoot);
                    soapPart.setContent(domSource);
                }
                    
            }
            else if (envelopeRoot.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE)
                System.out.println("ElementNode");
            else
                System.out.println("Unknown Node type");
                

            // Get the Header from the SOAP envelope
            javax.xml.soap.SOAPHeader header = envelope.getHeader();

            // Add an element and content to the Header
            javax.xml.soap.Name name 
                = envelope.createName("MessageHeader",
                    "jaxm","urn:oreilly-jaws-samples");
            javax.xml.soap.SOAPHeaderElement headerElement 
                = header.addHeaderElement(name);

            // Add an element and content to the Header
            name = envelope.createName("From");
            javax.xml.soap.SOAPElement childElement 
                = headerElement.addChildElement (name);
            childElement.addTextNode ("Me");

            // Add an element and content to the Header
            name = envelope.createName("To");
            childElement = headerElement.addChildElement(name);
            childElement.addTextNode ("You");

            // Add additional Parts to the message
            javax.activation.FileDataSource fds 
                = new javax.activation.FileDataSource(m_attachment);
            javax.activation.DataHandler dh 
                = new  javax.activation.DataHandler(fds);
            javax.xml.soap.AttachmentPart ap1 
                = message.createAttachmentPart(dh);
            message.addAttachmentPart(ap1);

            javax.xml.soap.AttachmentPart ap2 
                = message.createAttachmentPart("Another Part",
                    "text/plain; charset=ISO-8859-1");
            message.addAttachmentPart(ap2);

            // Save the changes made to the message
            message.saveChanges();

            System.err.println("Sending message to URL: "+ endpoint.getURL());

            // Send the message to the endpoint and wait for a reply
            javax.xml.soap.SOAPMessage reply 
                = connection.call(message, endpoint);

            System.out.println("Received reply from: " + endpoint);

            // Display the reply received from the endpoint
            boolean displayResult = true;

            if( displayResult ) {
                // Document source, do a transform.
                System.out.println("Result:");
                javax.xml.soap.SOAPPart replyPart = reply.getSOAPPart();
                javax.xml.transform.Source src = replyPart.getContent();
                javax.xml.transform.stream.StreamResult result 
                  = new javax.xml.transform.stream.StreamResult( System.out );
                transformer.transform(src, result);
                System.out.println();
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
        String dataFileName     = DEFAULT_DATA_FILENAME;
        String attachment       = DEFAULT_ATTACHMENT_FILENAME;

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

        // Start the GenericJAXMSWAClient
        try
        {
            GenericJAXMSWAClient jaxmClient =
                new GenericJAXMSWAClient(hostURL, dataFileName, attachment);
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
        use.append("usage: java GenericJAXMSWAClient (options) ...\n\n");
        use.append("options:\n");
        use.append("  -url hostURL      Specify host url\n");
        use.append("                    Default host url: " 
            + DEFAULT_HOST_URL+"\n");
        use.append("  -df datafile      Specify data file name\n");
        use.append("                    Default data file name: " 
            + DEFAULT_DATA_FILENAME+"\n");
        use.append("  -at attachment    Specify attachment file name\n");
        use.append("                    Default attachment file name: " 
            + DEFAULT_ATTACHMENT_FILENAME+"\n");
        use.append("  -h                This help screen.\n");
        System.err.println (use);
    }
}


