package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Girish
 */
@XmlRootElement(name = "SelectedFoodItems")
public class SelectedFoodItems {

    List<Integer> foodItemIds;

    public List<Integer> getFoodItemIds() {
        return foodItemIds;
    }

    @XmlElement(name = "FoodItemId", required = true)
    public void setfoodItemId(List<Integer> foodItemIds) {
        this.foodItemIds = foodItemIds;
    }
}
