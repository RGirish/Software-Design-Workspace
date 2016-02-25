package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Girish
 */
@XmlRootElement(name = "NewFoodItems", namespace = "http://cse564.asu.edu/PoxAssignment")
public class NewFoodItems {

    List<FoodItem> foodItems;

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    @XmlElement(name = "FoodItem", required = true, type = FoodItem.class)
    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
