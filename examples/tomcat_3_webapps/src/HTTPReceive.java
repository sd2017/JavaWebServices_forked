import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HTTPReceive extends HttpServlet
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

		// Traverse the HTTP headers and show them on the screen
		for(Enumeration enum = request.getHeaderNames(); 
		  enum.hasMoreElements(); ){
		    String header = (String)enum.nextElement();
			String value  = request.getHeader(header);

			System.out.println("  " + header + " = " + value);
		}
        
        System.out.println("-----------------------");
		
        if(request.getContentLength() > 0)
        {
			try
			{
			    java.io.BufferedReader reader = request.getReader();

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
                    throw new org.apache.soap.SOAPException 
                        (org.apache.soap.Constants.FAULT_CODE_CLIENT, "parsing error");
                }
                else
                {
                    // call static method to create the envelope from the document
                    org.apache.soap.Envelope env = 
                        org.apache.soap.Envelope.unmarshall(doc.getDocumentElement());

                    // Now that we have an envelope, do the inverse of what we did to
                    // build this
                    org.apache.soap.Body body = env.getBody();
                    java.util.Vector bodyEntries = body.getBodyEntries();

                    java.io.StringWriter writer = new java.io.StringWriter();

                    for (java.util.Enumeration e = bodyEntries.elements(); e.hasMoreElements();)
                    {
                        org.w3c.dom.Element el = (org.w3c.dom.Element)e.nextElement();
                        org.apache.soap.util.xml.DOM2Writer.serializeAsXML((org.w3c.dom.Node)el, writer);
                    }
                    System.out.println(writer.toString());
                }
			}
			catch(Exception e)
			{
			    System.out.println(e);
			}
		}

        System.out.println("____________________________");

        response.setContentType("text/xml"); // Need this to prevent Apache SOAP from gacking
    }
}

