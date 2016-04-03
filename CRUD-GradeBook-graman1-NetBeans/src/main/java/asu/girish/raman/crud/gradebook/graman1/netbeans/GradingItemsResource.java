package asu.girish.raman.crud.gradebook.graman1.netbeans;

import asu.girish.raman.crud.gradebook.models.GradingItem;
import static java.net.HttpURLConnection.*;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.*;

@Path("Gradebook/GradingItem")
public class GradingItemsResource {

    static List<GradingItem> gradingItems = null;
    static int GRADING_ITEM_ID = 1;

    @GET
    @Path("getAllIDs")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllGradingItemIDs() {
        List<Integer> allIDs = new ArrayList<>();
        if (gradingItems != null) {
            for (GradingItem gradingItem : gradingItems) {
                allIDs.add(gradingItem.getId());
            }
        }
        return allIDs.toString();
    }

    @POST
    @Path("createGradingItem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGradingItem(String jsonRequest) {
        String jsonResponse;
        try {
            JSONObject root = new JSONObject(jsonRequest);
            if (!root.getString("requestType").equals("createGradingItem")) {
                throw new JSONException("Bad Request");
            }
            String type = root.getString("type");
            String name = root.getString("name");
            double percentage = root.getDouble("percentage");
            GradingItem gradingItem = new GradingItem(GRADING_ITEM_ID, type, name, percentage);
            if (gradingItems == null) {
                gradingItems = new ArrayList<>();
            }
            gradingItems.add(gradingItem);
            jsonResponse = "{\n"
                    + "\"responseType\":\"success\",\n"
                    + "\"id\":" + GRADING_ITEM_ID + "\n"
                    + "}";
            GRADING_ITEM_ID++;
            return Response.status(HTTP_CREATED).entity(jsonResponse).build();
        } catch (JSONException e) {
            jsonResponse = "{\n"
                    + "\"responseType\":\"failure\",\n"
                    + "\"request\":\"" + jsonRequest + "\"\n"
                    + "}";
            return Response.status(HTTP_BAD_REQUEST).entity(jsonResponse).build();
        } catch (Exception e) {
            jsonResponse = "{\n"
                    + "\"responseType\":\"failure\",\n"
                    + "\"request\":\"" + jsonRequest + "\"\n"
                    + "}";
            return Response.status(HTTP_INTERNAL_ERROR).entity(jsonResponse).build();
        }
    }

    @GET
    @Path("readGradingItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGradingItem(@PathParam("id") int id) {
        String jsonResponse;
        try {
            if (gradingItems == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request-id\" : \"" + id + "\"\n"
                        + "}";
                return Response.status(HTTP_GONE).entity(jsonResponse).build();
            }

            for (GradingItem gradingItem : gradingItems) {
                if (gradingItem.getId() == id) {
                    jsonResponse = "{\n"
                            + "\"responseType\" : \"success\",\n"
                            + "\"id\" : \"" + gradingItem.getId() + "\",\n"
                            + "\"type\" : \"" + gradingItem.getType() + "\",\n"
                            + "\"name\" : \"" + gradingItem.getName() + "\",\n"
                            + "\"percentage\" : " + gradingItem.getPercentage() + "\n"
                            + "}";
                    return Response.status(HTTP_OK).entity(jsonResponse).build();
                }
            }
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-id\" : \"" + id + "\"\n"
                    + "}";
            return Response.status(HTTP_NOT_FOUND).entity(jsonResponse).build();
        } catch (JSONException e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-id\" : \"" + id + "\"\n"
                    + "}";
            return Response.status(HTTP_BAD_REQUEST).entity(jsonResponse).build();
        } catch (Exception e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-id\" : \"" + id + "\"\n"
                    + "}";
            return Response.status(HTTP_INTERNAL_ERROR).entity(jsonResponse).build();
        }
    }

    @PUT
    @Path("updateGradingItem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGradingItem(String jsonRequest) {
        String jsonResponse;
        try {
            JSONObject root = new JSONObject(jsonRequest);
            if (!root.getString("requestType").equals("updateGradingItem")) {
                throw new JSONException("Bad Request");
            }
            int id = root.getInt("id");

            if (gradingItems == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request\" : \"" + jsonRequest + "\"\n"
                        + "}";
                return Response.status(HTTP_CONFLICT).entity(jsonResponse).build();
            }

            for (GradingItem gradingItem : gradingItems) {
                if (gradingItem.getId() == id) {
                    gradingItems.remove(gradingItem);
                    gradingItem.setType(root.getString("type"));
                    gradingItem.setName(root.getString("name"));
                    gradingItem.setPercentage(root.getDouble("percentage"));
                    gradingItems.add(gradingItem);

                    jsonResponse = "{\n"
                            + "\"responseType\" : \"success\",\n"
                            + "\"id\" : " + gradingItem.getId() + ",\n"
                            + "\"type\" : \"" + gradingItem.getType() + "\",\n"
                            + "\"name\" : \"" + gradingItem.getName() + "\",\n"
                            + "\"percentage\" : " + gradingItem.getPercentage() + "\n"
                            + "}";
                    return Response.status(HTTP_OK).entity(jsonResponse).build();
                }
            }
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request\" : \"" + jsonRequest + "\"\n"
                    + "}";
            return Response.status(HTTP_NOT_FOUND).entity(jsonResponse).build();
        } catch (JSONException e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request\" : \"" + jsonRequest + "\"\n"
                    + "}";
            return Response.status(HTTP_BAD_REQUEST).entity(jsonResponse).build();
        } catch (Exception e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request\" : \"" + jsonRequest + "\"\n"
                    + "}";
            return Response.status(HTTP_INTERNAL_ERROR).entity(jsonResponse).build();
        }
    }

    @DELETE
    @Path("deleteGradingItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGradingItem(@PathParam("id") int id) {
        String jsonResponse;
        try {
            if (gradingItems == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request-id\" : \"" + id + "\"\n"
                        + "}";
                return Response.status(HTTP_GONE).entity(jsonResponse).build();
            }

            for (GradingItem gradingItem : gradingItems) {
                if (gradingItem.getId() == id) {
                    gradingItems.remove(gradingItem);
                    return Response.status(HTTP_NO_CONTENT).build();
                }
            }
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-id\" : \"" + id + "\"\n"
                    + "}";
            return Response.status(HTTP_NOT_FOUND).entity(jsonResponse).build();
        } catch (JSONException e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-id\" : \"" + id + "\"\n"
                    + "}";
            return Response.status(HTTP_BAD_REQUEST).entity(jsonResponse).build();
        } catch (Exception e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-id\" : \"" + id + "\"\n"
                    + "}";
            return Response.status(HTTP_INTERNAL_ERROR).entity(jsonResponse).build();
        }
    }
}
