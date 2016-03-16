package asu.girish.raman.pox.foodmenu.graman1.netbeans.client;

/**
 *
 * @author Girish
 */
public class App {

    public static void main(String[] args) {
        FoodMenuClient foodMenuClient = new FoodMenuClient();

        /*
            Change the value of requestMessage with a request message of your own!
         */
        String requestMessage
                = "<SelectedFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">\n"
                + "   <FoodItemId>118</FoodItemId>\n"
                + "   <FoodItemId>214</FoodItemId>\n"
                + "</SelectedFoodItems>";

        String responseMessage = foodMenuClient.postXMLRequest(requestMessage);

        System.out.println("\n\n\n\n");
        if (responseMessage.startsWith("error")) {
            System.out.println("Client: The server responded with a HTTP " + responseMessage.split("_")[1]);
        } else {
            System.out.println("Client: The server responded with a HTTP 200 and the response message -");
            System.out.println(responseMessage);
        }
        System.out.println("\n\n\n\n");
        foodMenuClient.close();
    }
}
