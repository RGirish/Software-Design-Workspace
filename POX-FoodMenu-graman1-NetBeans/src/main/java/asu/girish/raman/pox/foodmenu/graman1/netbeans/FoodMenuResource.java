/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * REST Web Service
 *
 * @author Girish
 */
@Path("FoodMenu")
public class FoodMenuResource {

    static List<FoodItem> foodItemList = new ArrayList<>();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FoodMenuResource
     */
    public FoodMenuResource() {
    }

    /**
     * Retrieves representation of an instance of
     * asu.girish.raman.pox.foodmenu.graman1.netbeans.FoodMenuResource
     *
     * @param xmlRequestString
     * @return an instance of java.lang.String
     */
    @Path("addFoodItem")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@FormParam("xmlRequestString") String xmlRequestString) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(NewFoodItems.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            NewFoodItems newFoodItems = (NewFoodItems) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlRequestString)));

            List<FoodItem> foodItems = newFoodItems.getFoodItems();
            for(FoodItem fi : foodItems){
                foodItemList.add(fi);
            }
            
            String s = "<FoodItemAdded xmlns=”http://cse564.asu.edu/PoxAssignment”>\n" +
                        "   <FoodItemId>" + (foodItemList.size() - 1) + "</FoodItemId>\n" +
                        "</FoodItemAdded>";
            return s;
        } catch (Exception ex) {
            Logger.getLogger(FoodMenuResource.class.getName()).log(Level.SEVERE, null, ex);
            return (ex.getMessage());
        }
    }

}