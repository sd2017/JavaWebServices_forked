import org.idoox.uddi.client.api.v2.request.inquiry.*;
import org.idoox.uddi.client.structure.v2.tmodel.*;
import org.idoox.uddi.client.api.v2.response.*;
import org.idoox.uddi.client.structure.v2.base.*;
import org.idoox.uddi.client.structure.v2.business.*;
import org.idoox.uddi.client.api.v2.*;
import org.idoox.uddi.client.*;

/**
 * This is simple example of Systinet's UDDI Java API for accessing a UDDI registry.
 * This program does a find_business call by name.
 */

public class SystinetFindBusiness {

    public static void findBusinessByName(String name) throws Exception
    {
        System.out.println("Searching for businesses named '"+name+"'...");

        // Create a FindBusiness instance.
        // This creates a SOAP message.
        FindBusiness findBusiness = new FindBusiness();

        // Set the name to use in the query.
        findBusiness.addName(new Name(name));

        // This will limit the number of returned matches.  
        // maxRows is an optional attribute.
        findBusiness.setMaxRows(new MaxRows("10"));

        // This will retrieve a stub to the UDDI inquiry port.
        UDDIApiInquiry inquiry = 
            UDDILookup.getInquiry("http://localhost:8080/wasp/uddi/inquiry/");

        // Send the message and retrieve the response.
        BusinessList businessList=inquiry.find_business(findBusiness);

        // Show the results.
        if (businessList==null) {
            System.err.println("ERROR: Business list is null!");
        }
        else {
            // Business list is holder for results - business infos.
            BusinessInfos businessInfos = businessList.getBusinessInfos();
            System.out.println("\nFound: " + 
                               businessInfos.size() + 
                               " businesses.\n");

            // Iterate through each company found in the query.
            BusinessInfo businessInfo = businessInfos.getFirst();
            BusinessKey result;
            if (businessInfo != null) {
                result=businessInfo.getBusinessKey();
                
                while (businessInfo!=null) {
                    System.out.println("BusinessEntity name = " + 
                               businessInfo.getNames().getFirst().getValue());
                    System.out.println("BusinessEntity UUID = " + 
                               businessInfo.getBusinessKey());
                    System.out.println("***");
                    businessInfo = businessInfos.getNext();
                }
            }
        }
    }
    
    // Program Entry Point
    public static void main(String args[]) throws Exception
    {
        String company = "Demi Credit";
        findBusinessByName(company);
    }
}
