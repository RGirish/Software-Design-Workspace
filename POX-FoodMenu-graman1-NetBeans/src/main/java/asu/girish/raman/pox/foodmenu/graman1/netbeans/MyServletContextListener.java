package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import static asu.girish.raman.pox.foodmenu.graman1.netbeans.FoodMenuResource.foodItemList;
import java.io.File;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * Web application life cycle listener.
 *
 * @author Girish
 */
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FoodItemData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            FoodItemData foodItemData = (FoodItemData) jaxbUnmarshaller.unmarshal(new File("FoodItemData.xml"));

            List<FoodItem> foodItems = foodItemData.getFoodItems();
            for (FoodItem fi : foodItems) {
                foodItemList.add(fi);
            }
        } catch (Exception ex) {
            Logger.getLogger(FoodMenuResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
