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
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * REST Web Service
 *
 * @author Girish
 */
@Path("FoodItem")
public class FoodMenuResource {

    static List<FoodItem> foodItemList = new ArrayList<>();

    @Context
    private UriInfo context;

    public FoodMenuResource() {
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String processRequest(@FormParam("xmlRequestString") String xmlRequestString) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(NewFoodItems.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlRequestString)));
            return addFoodItem(xmlRequestString);
        } catch (UnmarshalException ex) {
            return getFoodItem(xmlRequestString);
        } catch (JAXBException ex) {
            return "Some error occurred! Probably because of t=an invalid request message. Pleae try again with a valid request message!";
        }
    }

    /**
     *
     * @param xmlRequestString
     * @return
     */
    public String addFoodItem(String xmlRequestString) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(NewFoodItems.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            NewFoodItems newFoodItems = (NewFoodItems) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlRequestString)));

            List<FoodItem> foodItems = newFoodItems.getFoodItems();
            StringBuilder builder = new StringBuilder();
            for (FoodItem fi : foodItems) {
                if (fi.getCountry() == null || fi.getCategory() == null || fi.getDescription() == null || fi.getName() == null || fi.getPrice() == null) {
                    builder.append("<InvalidMessage xmlns=\"http://cse564.asu.edu/PoxAssignment\"/>");
                    continue;
                }
                int position = -1;
                for (int i = 0; i < foodItemList.size(); ++i) {
                    if (position != -1) {
                        continue;
                    }
                    FoodItem foodItem = foodItemList.get(i);
                    if (foodItem.getName().equals(fi.getName()) && foodItem.getCountry().equals(fi.getCountry()) && foodItem.getCategory().equals(fi.getCategory())) {
                        position = i;
                    }
                }
                if (position != -1) {
                    builder.append("<FoodItemExists xmlns=\"http://cse564.asu.edu/PoxAssignment\">\n")
                            .append("   <FoodItemId>")
                            .append(position)
                            .append("</FoodItemId>\n")
                            .append("</FoodItemExists>");
                } else {
                    foodItemList.add(fi);
                    builder.append("<FoodItemAdded xmlns=\"http://cse564.asu.edu/PoxAssignment\">\n")
                            .append("   <FoodItemId>")
                            .append(foodItemList.size() - 1)
                            .append("</FoodItemId>\n")
                            .append("</FoodItemAdded>");
                }
            }
            return builder.toString();
        } catch (Exception ex) {
            Logger.getLogger(FoodMenuResource.class.getName()).log(Level.SEVERE, null, ex);
            return "Exception in addFoodItem " + ex.getMessage() + ex.toString();
        }
    }

    /**
     *
     * @param xmlRequestString
     * @return
     */
    public String getFoodItem(String xmlRequestString) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SelectedFoodItems.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SelectedFoodItems selectedFoodItems = (SelectedFoodItems) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlRequestString)));

            int[] foodItemIds = selectedFoodItems.getFoodItemIds();
            StringBuilder builder = new StringBuilder("<RetrievedFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">");
            for (int foodItemId : foodItemIds) {
                if (foodItemId >= foodItemList.size()) {
                    builder.append("<InvalidFoodItem>\n        <FoodItemId>")
                            .append(foodItemId)
                            .append("</FoodItemId>\n    </InvalidFoodItem>");
                } else {
                    FoodItem foodItem = foodItemList.get(foodItemId);
                    builder.append("<FoodItem country=\"")
                            .append(foodItem.getCountry())
                            .append("\">\n        <id>")
                            .append(foodItemId)
                            .append("</id>\n        <name>")
                            .append(foodItem.getName())
                            .append("</name>\n        <description>")
                            .append(foodItem.getDescription())
                            .append("</description>\n        <category>")
                            .append(foodItem.getCategory())
                            .append("</category>\n        <price>")
                            .append(foodItem.getPrice())
                            .append("</price>\n    </FoodItem>");
                }
            }
            builder.append("</RetrievedFoodItems>");
            return builder.toString();
        } catch (Exception ex) {
            Logger.getLogger(FoodMenuResource.class.getName()).log(Level.SEVERE, null, ex);
            return "Exception in getFoodItem " + ex.getMessage() + ex.toString();
        }
    }

}
