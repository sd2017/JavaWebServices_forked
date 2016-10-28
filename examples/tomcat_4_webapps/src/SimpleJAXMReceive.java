/*
Writing a Simple JAXM Receiver.
*/

import java.io.*;
import java.util.*;

public class SimpleJAXMReceive
    extends javax.xml.messaging.JAXMServlet
    implements javax.xml.messaging.ReqRespListener {

    static javax.xml.soap.MessageFactory fac = null;

    static {
        try {
            fac = javax.xml.soap.MessageFactory.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };

    public void init(javax.servlet.ServletConfig servletConfig) throws javax.servlet.ServletException {
        super.init(servletConfig);
    }

    // This is the application code for handling the message. We simply display
    // the message and create and send a response.

    public javax.xml.soap.SOAPMessage onMessage(javax.xml.soap.SOAPMessage message) {
        System.out.println("Here we are!!! On message called in receiving servlet");
        try {

            int count = message.countAttachments();
            System.out.println("There are: " + count + " message parts");

            /// Dump the raw message out
            System.out.println("Here's the message: ");
            message.writeTo(System.out);

            /// Construct and send SOAP message response
            javax.xml.soap.SOAPMessage msg = fac.createMessage();
            javax.xml.soap.SOAPPart part = msg.getSOAPPart();
            javax.xml.soap.SOAPEnvelope env = part.getEnvelope();
            javax.xml.soap.SOAPBody body = env.getBody();
            javax.xml.soap.Name name = env.createName("Response");
	    javax.xml.soap.SOAPBodyElement bodyElement = body.addBodyElement (name);
            bodyElement.addTextNode ("This is the response");

            return msg;

        } catch(Exception e) {
            System.out.println("Error in processing or replying to a message");
            return null;
        }
    }
}

