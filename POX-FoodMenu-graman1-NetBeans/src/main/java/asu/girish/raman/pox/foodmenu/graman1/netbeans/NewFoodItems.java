package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Girish
 */

@XmlRootElement(name="NewFoodItems", namespace = "http://cse564.asu.edu/PoxAssignment")
public class NewFoodItems {
    String xmlns;
    List<FoodItem> foodItems;

    public String getXmlns() {
        return xmlns;
    }

    @XmlAttribute
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    @XmlElement(name="FoodItem")
    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}