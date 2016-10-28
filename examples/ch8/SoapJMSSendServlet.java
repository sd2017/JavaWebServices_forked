// JAVA PSEUDO-CODE FOR INTEGRATING A SERVLET WITH SOAP & JMS
// This code is designed to receive a SOAP message and then
// to parse the message and to place that message onto a JMS Queue

// NOTE: This class is not compilable and does not have proper
//       Exception handling.  It is just pseudo-code to demonstrate
//       how Servlet / JMS / SOAP can work together.

public class SoapJMSSendServlet extends HttpServlet {

    public DestinationSendAdapter() {}

    private static final String SEND_QUEUE_NAME = "SomeQueueName";
    private static final String QUEUE_CONNECTION_FACTORY = "SomeConnectionFactoryName";
    private Destination destination;
    private ConnectionFactory factory;

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException {

        // Parse SOAP input from HTTP Stream.
        SoapMessage soapMessage = SOAPParser.getDocument(new InputSource(request.getInputStream()));

        // Make sure that the SOAP XML is a JMS Send operation.
        if (soapMessage.isJMSSender()) {
            List list = soapMessage.getPayloadValues();
            
            // Make sure the list is >0 and contents are serializable
            // Left out for readability.

            // For each item on the List, take the item off of the list
            // and place the item into a JMS message and place it on a queue.
            sendToQueue((QueueConnectionFactory)factory, (Queue)destination, (Serializable)obj);

            // This servlet is NOT responsible for receiving JMS messages.
            // Web Services are loosely coupled, so there is a separate servlet
            // responsible for checking to see if a response message has been
            // delivered onto the response queue.  That receive servlet has to
            // be invoked separately to check for the correct contents.

            // We have to send a SOAP response to the client informing them of the
            // success.
            SoapMessage responseMessage = new SoapMessageFactory.createSoapMessage();
            response.setContentType("text/xml; charset=UTF-8");
            responseMessage.write(httpservletresponse.getOutputStream());
        }
    }

    public void init() throws ServletException {
        
        // Set up Queue -- only needs to occur once.
        InitialContext ctx = new InitialContext();
        factory = (ConnectionFactory)ctx.lookup(QUEUE_CONNECTION_FACTORY);
        destination = (Destination)ctx.lookup(SEND_QUEUE_NAME);
    }

    // This method is responsible for taking a serializable object that
    // has XML content, converting it to a JMS message, and placing that
    // message onto a JMS queue.
    private static void sendToQueue(QueueConnectionFactory qcf, 
                                    Queue q, 
                                    Serializable serializable) throws ServletException  {
        QueueConnection qc = null;
        QueueSession qs = null;
        QueueSender qsender = null;

        qc = qcf.createQueueConnection();
        qs = qc.createQueueSession(false, 1);
        qsender = qs.createSender(q);
        ObjectMessage message = qs.createObjectMessage();
        objectmessage.setObject(serializable);
        qc.start();
        qsender.send(message);
    }
}
