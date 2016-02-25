/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @XmlElement(name = "FoodItemId", required = true, type = Integer.class)
    public void setfoodItemId(List<Integer> foodItemIds) {
        this.foodItemIds = foodItemIds;
    }
}
