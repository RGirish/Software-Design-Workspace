package asu.girish.raman.pox.foodmenu.graman1.netbeans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FoodItem")
public class FoodItem {

    String country, name, description, category, price;

    public String getCountry() {
        return country;
    }

    @XmlAttribute(name = "country", required = true)
    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name", required = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "description", required = true)
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    @XmlElement(name = "category", required = true)
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    @XmlElement(name = "price", required = true)
    public void setPrice(String price) {
        this.price = price;
    }
}