import org.apache.soap.Envelope;
import org.apache.soap.Constants;
import org.apache.soap.SOAPException;

import org.apache.soap.rpc.SOAPContext;

import javax.mail.internet.MimeBodyPart;

import java.io.ByteArrayOutputStream;

public class PurchaseOrderAcceptor
{

  public void PurchaseOrder(Envelope requestEnvelope, SOAPContext requestContext,
                            SOAPContext responseContext)
    throws SOAPException
  {
    System.out.println("Received a PurchaseOrder!!");

    java.io.StringWriter writer = new java.io.StringWriter();

    org.apache.soap.Header header = requestEnvelope.getHeader();
    java.util.Vector headerEntries = header.getHeaderEntries();
    
    writer.write("\nHeader==>\n");
    for (java.util.Enumeration e = headerEntries.elements(); e.hasMoreElements();)
    {
        org.w3c.dom.Element el = (org.w3c.dom.Element)e.nextElement();
        org.apache.soap.util.xml.DOM2Writer.serializeAsXML((org.w3c.dom.Node)el, writer);
        
        // process mustUnderstand
        String mustUnderstand=el.getAttribute("SOAP-ENV:mustUnderstand");
        writer.write("\nMustUnderstand: ");
        if (mustUnderstand!=null)
            writer.write(mustUnderstand + "\n");
        else
            writer.write("null\n");
        String tagName = el.getTagName();    
        writer.write("Tag Name: " + tagName + "\n");
        if(tagName.equalsIgnoreCase("jaws:MessageHeader"))
        {
            //OK, so we don't understand our own header
            //generate a fault.
            writer.write("Unsupported header: " + tagName + "\n");
            writer.write("Generating Fault....\n");
            
        }
    }
    
    org.apache.soap.Body body = requestEnvelope.getBody();
    java.util.Vector bodyEntries = body.getBodyEntries();
    
    writer.write("\nBody====>\n");
    for (java.util.Enumeration e = bodyEntries.elements(); e.hasMoreElements();)
    {
        org.w3c.dom.Element el = (org.w3c.dom.Element)e.nextElement();
        org.apache.soap.util.xml.DOM2Writer.serializeAsXML((org.w3c.dom.Node)el, writer);
    }
    System.out.println(writer.toString());
    try
    {
      //should really be better XML with declaration and namespaces
      responseContext.setRootPart("<PurchaseOrderResponse>Accepted</PurchaseOrderResponse>", "text/xml");
    }
    catch(Exception e)
    {
      throw new SOAPException(Constants.FAULT_CODE_SERVER, "Error writing response", e);
    }
  }

  public void PurchaseOrderWithAttachment(Envelope requestEnvelope,
                                          SOAPContext requestContext,
                                          SOAPContext responseContext)
    throws SOAPException
  {
    System.out.println("Received a PurchaseOrderWithAttachment!!");
    String cid = null;
    java.io.StringWriter writer = new java.io.StringWriter();

    org.apache.soap.Header header = requestEnvelope.getHeader();
    java.util.Vector headerEntries = header.getHeaderEntries();
    
    writer.write("\nHeader==>\n");
    for (java.util.Enumeration e = headerEntries.elements(); e.hasMoreElements();)
    {
        org.w3c.dom.Element el = (org.w3c.dom.Element)e.nextElement();
        org.apache.soap.util.xml.DOM2Writer.serializeAsXML((org.w3c.dom.Node)el, writer);
    }
    
    org.apache.soap.Body body = requestEnvelope.getBody();
    java.util.Vector bodyEntries = body.getBodyEntries();
    
    writer.write("\nBody====>\n");
    for (java.util.Enumeration e = bodyEntries.elements(); e.hasMoreElements();)
    {
        org.w3c.dom.Element el = (org.w3c.dom.Element)e.nextElement();
        org.apache.soap.util.xml.DOM2Writer.serializeAsXML((org.w3c.dom.Node)el, writer);
        org.w3c.dom.Element attachmentEl = (org.w3c.dom.Element)el.getElementsByTagName("attachment").item(0);
        if (attachmentEl != null)
        {
            writer.write("\nAttachment==>\n");
            cid = attachmentEl.getAttribute("href").substring(4);//get rid of cid:
            writer.write("Content-ID = "+cid+"\n");
            MimeBodyPart attachment = requestContext.getBodyPart(cid);
            try
            {
                writer.write("The attachment is...\n"+attachment.getContent()+"\n");
            }catch(Exception ex)
            {
                throw new SOAPException(Constants.FAULT_CODE_SERVER, 
                    "Error writing response", ex);
            }
        }else
            writer.write("The Content-ID is null!\n");
    }
    System.out.println(writer.toString());

    try
    {
      //should really be better XML with declaration and namespaces
      responseContext.setRootPart("<PurchaseOrderResponse>Accepted</PurchaseOrderResponse>", "text/xml");
    }
    catch(Exception e)
    {
      throw new SOAPException(Constants.FAULT_CODE_SERVER, "Error writing response", e);
    }
  }
}