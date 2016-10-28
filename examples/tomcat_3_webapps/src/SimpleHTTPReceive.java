import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SimpleHTTPReceive extends HttpServlet
{
    // Treat GET requests as errors.
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
		System.out.println("Received GET request");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    // Our SOAP requests are going to be received as HTTP POSTS
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        System.out.println("____________________________");
		System.out.println("Received request.");
        System.out.println("-----------------------");
		
		// Traverse the HTTP headers and show them on the screen
		for(Enumeration enum = request.getHeaderNames(); 
		  enum.hasMoreElements(); )
		{
		    String header = (String)enum.nextElement();
			String value  = request.getHeader(header);

			System.out.println("  " + header + " = " + value);
		}
        
        System.out.println("-----------------------");
		
        // If there is anything in the body of the message, 
        // dump it to the screen as well
        if(request.getContentLength() > 0)
        {
			try{
			    java.io.BufferedReader reader = request.getReader();
	 			String line = null;
			    while((line = reader.readLine()) != null)
			    {
					System.out.println(line);
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

