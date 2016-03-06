package asu.girish.raman.pox.foodmenu.graman1.netbeans.client2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Girish
 */
public class App {

    public static void main(String[] args) {
        try {

            /*
            You might want to change the port number here
             */
            URL url = new URL("http://localhost:8080/POX-FoodMenu-graman1-NetBeans/webresources/FoodItem");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/xml");
            httpURLConnection.setDoOutput(true);


            /*
                Change the value of xmlRequestString with a request message of your own!
             */
            String xmlRequestString
                    = "<NewFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">\n"
                    + "    <FoodItem country=\"GB2\">\n"
                    + "        <name>Cornish Pasty</name>\n"
                    + "        <description>Tender cubes of steak, potatoes and swede wrapped in flakey short crust pastry.  Seasoned with lots of pepper.  Served with mashed potatoes, peas and a side of gravy</description>\n"
                    + "        <category>Dinner</category>\n"
                    + "        <price>15.95</price>\n"
                    + "    </FoodItem>\n"
                    + "    <FoodItem country=\"GB2\">\n"
                    + "        <name>Ploughman’s Salad</name>\n"
                    + "        <description>Pork Pie, Pickled Onions, Pickled relish Stilton and Cheddar cheeses and crusty French bread.</description>\n"
                    + "        <category>Lunch</category>\n"
                    + "        <price>10.95</price>\n"
                    + "    </FoodItem>\n"
                    + "</NewFoodItems>";

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(xmlRequestString.getBytes());
            outputStream.flush();

            if (httpURLConnection.getResponseCode() != 200) {
                System.out.println("Client: The server responded with HTTP " + httpURLConnection.getResponseCode() + ".");
                return;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
            String response;
            System.out.println("\n\n\n\n");
            System.out.println("Client: The server responded with HTTP 200. The response message is -\n\n");
            while ((response = bufferedReader.readLine()) != null) {
                System.out.println(response);
            }
            System.out.println("\n\n\n\n");
            httpURLConnection.disconnect();
        } catch (Exception e) {
            System.out.println("Exception occured in the client - " + e.toString());
        }
    }
}
