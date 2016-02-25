package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FoodItem")
public class FoodItem {
    
    String country, name, description, category, price;

    public String getCountry() {
        return country;
    }

    @XmlAttribute
    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }
    
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    @XmlElement
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(String price) {
        this.price = price;
    }
}