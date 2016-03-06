package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Girish
 */
@XmlRootElement(name = "SelectedFoodItems", namespace = "http://cse564.asu.edu/PoxAssignment")
public class SelectedFoodItems {

    int[] foodItemIds;

    public int[] getFoodItemIds() {
        return foodItemIds;
    }

    @XmlElement(name = "FoodItemId", required = true)
    public void setfoodItemId(int[] foodItemIds) {
        this.foodItemIds = foodItemIds;
    }
}
