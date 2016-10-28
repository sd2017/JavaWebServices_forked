import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.soap.*;

public class FaultServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
		System.out.println("Received GET request");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        System.out.println("____________________________");
		System.out.println("Received request.");
        System.out.println("-----------------------");

        if(request.getContentLength() > 0)
        {
			try
			{
			    java.io.BufferedReader reader = request.getReader();
			    java.io.StringWriter screenWriter = new java.io.StringWriter();
			    
                // get the document builder
                javax.xml.parsers.DocumentBuilder xdb = 
                    org.apache.soap.util.xml.XMLParserUtils.getXMLDocBuilder();
                    
                // parse it into a DOM
                org.w3c.dom.Document doc = 
                    xdb.parse (new org.xml.sax.InputSource (reader));
                if (doc == null) 
                {
                    // Error occured
                    System.out.println("Doc is null!");
                    throw new SOAPException 
                        (Constants.FAULT_CODE_CLIENT, "parsing error");
                }
                else
                {
                    // call static method to create the envelope from the document
                    Envelope env = 
                        Envelope.unmarshall(doc.getDocumentElement());

                    // Get the header and check it for mustunderstand
                    Header header = env.getHeader();
                    java.util.Vector headerEntries = header.getHeaderEntries();
                    
                    screenWriter.write("\nHeader==>\n");
                    for (java.util.Enumeration e = headerEntries.elements(); 
                            e.hasMoreElements();)
                    {
                        org.w3c.dom.Element el = (org.w3c.dom.Element)e.nextElement();
                        org.apache.soap.util.xml.DOM2Writer.serializeAsXML((org.w3c.dom.Node)el, screenWriter);
                        
                        // process mustUnderstand
                        String mustUnderstand=
                            el.getAttributeNS(Constants.NS_URI_SOAP_ENV, "mustUnderstand");
                        screenWriter.write("\nMustUnderstand: " + mustUnderstand + "\n");
                        String tagName = el.getTagName();    
                        screenWriter.write("Tag Name: " + tagName + "\n");
                        if(!tagName.equalsIgnoreCase("IOnlyUnderstandThis"))
                        {
                            //OK, so we don't understand our own header
                            //generate a fault.
                            screenWriter.write("Unsupported header: " + tagName + "\n");
                            screenWriter.write("Generating Fault....\n");
                            SOAPException se = new SOAPException(Constants.FAULT_CODE_MUST_UNDERSTAND, 
                                "Unsupported header: " + tagName);
  
                            Fault fault = new Fault(se);
                            fault.setFaultActorURI (request.getRequestURI ());
                            
                            String respEncStyle = Constants.NS_URI_SOAP_ENC;
  
                            org.apache.soap.rpc.Response soapResponse = 
                                new org.apache.soap.rpc.Response (
                                    null,       // targetObjectURI
                                    null,       // methodName
                                    fault,
                                    null,       // params
                                    null,       // headers
                                    respEncStyle, // encodingStyleURI
                                    null);      // SOAPContext
                            Envelope faultEnvelope = soapResponse.buildEnvelope();  

                            org.apache.soap.encoding.SOAPMappingRegistry smr 
                                = new org.apache.soap.encoding.SOAPMappingRegistry();

                            PrintWriter resW = response.getWriter();

                            faultEnvelope.marshall(resW, smr, soapResponse.getSOAPContext());
                            response.setContentType(request.getContentType()); 
                            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
                        }
                    }
        
                    // Now that we have an envelope, do the inverse of what we did to
                    // build this
                    org.apache.soap.Body body = env.getBody();
                    java.util.Vector bodyEntries = body.getBodyEntries();

                    for (java.util.Enumeration e = bodyEntries.elements(); e.hasMoreElements();)
                    {
                        org.w3c.dom.Element el = (org.w3c.dom.Element)e.nextElement();
                        org.apache.soap.util.xml.DOM2Writer.serializeAsXML((org.w3c.dom.Node)el, screenWriter);
                    }
                    System.out.println(screenWriter.toString());
                }
			}
			catch(Exception e)
			{
			    System.out.println(e);
			}
		}

        System.out.println("____________________________");

        response.setContentType(request.getContentType()); 
    }
}

