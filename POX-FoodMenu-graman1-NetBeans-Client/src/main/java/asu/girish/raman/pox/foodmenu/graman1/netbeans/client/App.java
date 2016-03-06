/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.pox.foodmenu.graman1.netbeans.client;

/**
 *
 * @author Girish
 */
public class App {

    public static void main(String[] args) {
        FoodMenuClient foodMenuClient = new FoodMenuClient();

        String responseMessage = foodMenuClient.postXMLRequest(
                "<NewFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">\n"
                + "    <FoodItem country=\"GB2\">\n"
                + "        <name>Cornish Pasty</name>\n"
                + "        <description>Tender cubes of steak, potatoes and swede wrapped in flakey short crust pastry.  Seasoned with lots of pepper.  Served with mashed potatoes, peas and a side of gravy</description>\n"
                + "        <category>Dinner</category>\n"
                + "        <price>15.95</price>\n"
                + "    </FoodItem>\n"
                + "</NewFoodItems>");

        System.out.println("Client: The response is ");
        System.out.println(responseMessage);
    }
}
