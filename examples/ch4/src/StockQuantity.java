/*
Writing a SOAP RPC service using the standard Java libraries

usage:
    This service must be registered as a service with Tomcat using a Deployment
    Descriptor or the graphical admin tool. The class file must also be available
    to the Tomcat server.
*/

import java.net.*;
import java.io.*;
import java.util.*;

public class StockQuantity{

  public int getQty (String item)
    throws org.apache.soap.SOAPException {

    int inStockQty = (int)(java.lang.Math.random() * (double)1000);

    if (item.equalsIgnoreCase("Fail"))
        throw new org.apache.soap.SOAPException 
            (org.apache.soap.Constants.FAULT_CODE_SERVER,
                "Test Fault");

    return inStockQty;

   }

   public static void main(String args[]) {

    if (args.length != 1 ) {
      System.err.println ("Usage: java " + StockQuantity.class.getName () +
                          " ItemNumber");
      System.exit (1);
    }

    // Process the arguments being passed in.
    String itemnum = new String (args[0]);

    StockQuantity stockqty = new StockQuantity();

    try{
        stockqty.getQty(itemnum);
    }catch(Exception e){
        e.printStackTrace();
    }

   }
}