package asu.girish.raman.crud.gradebook.graman1.netbeans;

import static asu.girish.raman.crud.gradebook.graman1.netbeans.GradingItemsResource.gradingItems;
import static asu.girish.raman.crud.gradebook.graman1.netbeans.StudentProfilesResource.students;
import asu.girish.raman.crud.gradebook.models.GradingItem;
import asu.girish.raman.crud.gradebook.models.Student;
import static java.net.HttpURLConnection.*;
import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.*;

@Path("Grades")
public class GradesResource {

    @PUT
    @Path("updateGrades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGrades(String jsonRequest) {
        String jsonResponse;
        try {
            JSONObject root = new JSONObject(jsonRequest);
            if (!root.getString("requestType").equals("updateGrades")) {
                throw new JSONException("Bad Request");
            }

            if (students == null || gradingItems == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request\" : \"" + jsonRequest + "\"\n"
                        + "}";
                return Response.status(HTTP_CONFLICT).entity(jsonResponse).build();
            }

            int studentId = root.getInt("studentId");
            int gradingItemId = root.getInt("gradingItemId");
            double points = root.getDouble("points");
            String feedback = root.getString("feedback");

            for (Student student : students) {
                if (student.getId() == studentId) {
                    for (GradingItem gradingItem : gradingItems) {
                        if (gradingItem.getId() == gradingItemId) {
                            students.remove(student);
                            HashMap<Integer, Double> allPoints = (HashMap<Integer, Double>) student.getPoints();
                            allPoints.put(gradingItemId, points);
                            HashMap<Integer, String> allFeedbacks = (HashMap<Integer, String>) student.getFeedbacks();
                            allFeedbacks.put(gradingItemId, feedback);
                            student.setFeedbacks(allFeedbacks);
                            student.setPoints(allPoints);
                            students.add(student);
                            jsonResponse = "{\n"
                                    + "\"responseType\" : \"success\",\n"
                                    + "\"message\" : \"Points and Feedback updated!\"\n"
                                    + "}";
                            return Response.status(HTTP_OK).entity(jsonResponse).build();
                        }
                    }
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

    @GET
    @Path("{studentId}/{gradingItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrades(@PathParam("studentId") int studentId, @PathParam("gradingItemId") int gradingItemId) {
        String jsonResponse;
        try {
            if (students == null || gradingItems == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request-student-id\" : " + studentId + ",\n"
                        + "\"request-grading-item-id\" : " + gradingItemId + "\n"
                        + "}";
                return Response.status(HTTP_GONE).entity(jsonResponse).build();
            }

            for (Student student : students) {
                if (student.getId() == studentId) {
                    HashMap<Integer, Double> allPoints = (HashMap<Integer, Double>) student.getPoints();
                    HashMap<Integer, String> allFeedbacks = (HashMap<Integer, String>) student.getFeedbacks();

                    if (allPoints.containsKey(gradingItemId)) {
                        if (allFeedbacks.containsKey(gradingItemId)) {
                            jsonResponse = "{\n"
                                    + "\"responseType\" : \"success\",\n"
                                    + "\"points\" : " + allPoints.get(gradingItemId) + ",\n"
                                    + "\"feedback\" : \"" + allFeedbacks.get(gradingItemId) + "\"\n"
                                    + "}";
                            return Response.status(HTTP_OK).entity(jsonResponse).build();
                        } else {
                            jsonResponse = "{\n"
                                    + "\"responseType\" : \"success\",\n"
                                    + "\"points\" : " + allPoints.get(gradingItemId) + ",\n"
                                    + "\"feedback\" : \"No feedback given!\"\n"
                                    + "}";
                            return Response.status(HTTP_OK).entity(jsonResponse).build();
                        }
                    } else {
                        for (GradingItem gradingItem : gradingItems) {
                            if (gradingItem.getId() == gradingItemId) {
                                jsonResponse = "{\n"
                                        + "\"responseType\" : \"success\",\n"
                                        + "\"points\" : \"Not graded yet!\",\n"
                                        + "\"feedback\" : \"No feedback given!\"\n"
                                        + "}";
                                return Response.status(HTTP_OK).entity(jsonResponse).build();
                            }
                        }
                        jsonResponse = "{\n"
                                + "\"responseType\" : \"success\",\n"
                                + "\"request-grading-item-id\" : " + gradingItemId + "\n"
                                + "}";
                        return Response.status(HTTP_NOT_FOUND).entity(jsonResponse).build();
                    }
                }
            }
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-student-id\" : " + studentId + "\n"
                    + "}";
            return Response.status(HTTP_NOT_FOUND).entity(jsonResponse).build();
        } catch (JSONException e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-student-id\" : " + studentId + ",\n"
                    + "\"request-grading-item-id\" : " + gradingItemId + "\n"
                    + "}";
            return Response.status(HTTP_BAD_REQUEST).entity(jsonResponse).build();
        } catch (Exception e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-student-id\" : " + studentId + ",\n"
                    + "\"request-grading-item-id\" : " + gradingItemId + "\n"
                    + "}";
            return Response.status(HTTP_INTERNAL_ERROR).entity(jsonResponse).build();
        }
    }
}
