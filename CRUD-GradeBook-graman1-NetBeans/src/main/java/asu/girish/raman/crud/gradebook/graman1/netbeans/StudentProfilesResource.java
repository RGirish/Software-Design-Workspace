package asu.girish.raman.crud.gradebook.graman1.netbeans;

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
}
