package asu.girish.raman.crud.gradebook.graman1.netbeans;

import static asu.girish.raman.crud.gradebook.graman1.netbeans.GradingItemsResource.gradingItems;
import asu.girish.raman.crud.gradebook.models.GradingItem;
import asu.girish.raman.crud.gradebook.models.Student;
import static java.net.HttpURLConnection.*;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.*;

@Path("StudentProfiles")
public class StudentProfilesResource {

    static List<Student> students = null;
    static int STUDENT_ID = 1;

    @POST
    @Path("createStudentProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudentProfile(String jsonRequest) {
        String jsonResponse;
        try {
            JSONObject root = new JSONObject(jsonRequest);
            if (!root.getString("requestType").equals("createStudentProfile")) {
                throw new JSONException("Bad Request");
            }
            String name = root.getString("name");
            Student student = new Student(name);
            student.setId(STUDENT_ID);
            student.setPoints(new LinkedHashMap<Integer, Double>());
            student.setFeedbacks(new LinkedHashMap<Integer, String>());
            if (students == null) {
                students = new ArrayList<>();
            }
            students.add(student);
            jsonResponse = "{\n"
                    + "\"responseType\":\"success\",\n"
                    + "\"id\":" + STUDENT_ID + "\n"
                    + "}";
            STUDENT_ID++;
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
    @Path("readStudentProfile/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readStudentProfile(@PathParam("id") int id) {
        String jsonResponse;
        try {
            if (students == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request-id\" : \"" + id + "\"\n"
                        + "}";
                return Response.status(HTTP_GONE).entity(jsonResponse).build();
            }

            for (Student student : students) {
                if (student.getId() == id) {
                    jsonResponse = "{\n"
                            + "\"responseType\" : \"success\",\n"
                            + "\"id\" : \"" + student.getId() + "\",\n"
                            + "\"name\" : \"" + student.getName() + "\"\n"
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
    @Path("updateStudentProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudentProfile(String jsonRequest) {
        String jsonResponse;
        try {
            JSONObject root = new JSONObject(jsonRequest);
            if (!root.getString("requestType").equals("updateStudentProfile")) {
                throw new JSONException("Bad Request");
            }
            int id = root.getInt("id");

            if (students == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request\" : \"" + jsonRequest + "\"\n"
                        + "}";
                return Response.status(HTTP_CONFLICT).entity(jsonResponse).build();
            }

            for (Student student : students) {
                if (student.getId() == id) {
                    students.remove(student);
                    student.setName(root.getString("name"));
                    students.add(student);

                    jsonResponse = "{\n"
                            + "\"responseType\" : \"success\",\n"
                            + "\"id\" : " + student.getId() + ",\n"
                            + "\"name\" : \"" + student.getName() + "\"\n"
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
    @Path("deleteStudentProfile/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudentProfile(@PathParam("id") int id) {
        String jsonResponse;
        try {
            if (students == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request-id\" : \"" + id + "\"\n"
                        + "}";
                return Response.status(HTTP_GONE).entity(jsonResponse).build();
            }

            for (Student student : students) {
                if (student.getId() == id) {
                    students.remove(student);
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

    @PUT
    @Path("/Grades/update")
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
    @Path("/Grades/{studentId}/{gradingItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrade(@PathParam("studentId") int studentId, @PathParam("gradingItemId") int gradingItemId) {
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
                                + "\"responseType\" : \"failure\",\n"
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

    @GET
    @Path("/Grades/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGrades(@PathParam("studentId") int studentId) {
        String jsonResponse;
        try {
            if (students == null || gradingItems == null) {
                jsonResponse = "{\n"
                        + "\"responseType\" : \"failure\",\n"
                        + "\"request-student-id\" : " + studentId + "\n"
                        + "}";
                return Response.status(HTTP_GONE).entity(jsonResponse).build();
            }

            for (Student student : students) {
                if (student.getId() == studentId) {
                    HashMap<Integer, Double> allPoints = (HashMap<Integer, Double>) student.getPoints();
                    HashMap<Integer, String> allFeedbacks = (HashMap<Integer, String>) student.getFeedbacks();
                    if (allPoints.isEmpty()) {
                        jsonResponse = "{\n"
                                + "\"responseType\" : \"failure\",\n"
                                + "\"request-student-id\" : " + studentId + "\n"
                                + "}";
                        return Response.status(HTTP_NOT_FOUND).entity(jsonResponse).build();
                    }
                    StringBuilder builder = new StringBuilder();
                    builder.append("{\n\t\"responseType\" : \"success\",\n\t\"graded-items\" : [");
                    for (Map.Entry pair : allPoints.entrySet()) {
                        builder.append("\n\t{\n\t\"gradinItemId\" : ").append(pair.getKey()).append(",\n");
                        builder.append("\t\"points\" : ").append(pair.getValue()).append(",\n");
                        if (allFeedbacks.containsKey((int) pair.getKey())) {
                            builder.append("\t\"feedback\" : \"").append(allFeedbacks.get((int) pair.getKey())).append("\"\n\t},");
                        } else {
                            builder.append("\t\"feedback\" : \"No feedback given!\"\n\t},");
                        }
                    }
                    String temp = builder.substring(0, builder.length() - 1);
                    builder = new StringBuilder(temp);
                    builder.append("\n\t]\n}");
                    return Response.status(HTTP_OK).entity(builder.toString()).build();
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
                    + "\"request-student-id\" : " + studentId + "\n"
                    + "}";
            return Response.status(HTTP_BAD_REQUEST).entity(jsonResponse).build();
        } catch (Exception e) {
            jsonResponse = "{\n"
                    + "\"responseType\" : \"failure\",\n"
                    + "\"request-student-id\" : " + studentId + "\n"
                    + "}";
            return Response.status(HTTP_INTERNAL_ERROR).entity(jsonResponse).build();
        }
    }
}
