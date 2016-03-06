package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import static asu.girish.raman.pox.foodmenu.graman1.netbeans.FoodMenuResource.foodItemsList;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Web application life cycle listener.
 *
 * @author Girish
 */
public class MyServletContextListener implements ServletContextListener {

    /**
     * The foodItemsList object is populated with an initial set of FoodItems
     * data from the XML file FoodItemData.xml
     *
     * @param sce The current context of the Servlet.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FoodItemData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File file = new File(sce.getServletContext().getRealPath("FoodItemData.xml"));
            FoodItemData foodItemData = (FoodItemData) jaxbUnmarshaller.unmarshal(file);

            List<FoodItem> foodItems = foodItemData.getFoodItems();
            for (FoodItem foodItem : foodItems) {
                FoodItem temp = new FoodItem();
                temp.setCategory(foodItem.getCategory());
                temp.setCountry(foodItem.getCountry());
                temp.setDescription(foodItem.getDescription());
                temp.setName(foodItem.getName());
                temp.setPrice(foodItem.getPrice());
                temp.setId(foodItem.getId());
                foodItemsList.put(foodItem.getId(), temp);
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
