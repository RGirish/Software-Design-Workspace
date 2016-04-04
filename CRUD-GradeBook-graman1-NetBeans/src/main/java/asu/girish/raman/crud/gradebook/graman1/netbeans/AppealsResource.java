package asu.girish.raman.crud.gradebook.graman1.netbeans;

import asu.girish.raman.crud.gradebook.models.Appeal;
import static java.net.HttpURLConnection.*;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.*;

@Path("Gradebook/Appeals")
public class AppealsResource {

    static List<Appeal> appeals = new ArrayList<>();

    @GET
    @Path("viewAllAppeals")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAllAppeals() {
        StringBuilder builder = new StringBuilder("{\n\t\"appeals\" : [");
        for (Appeal appeal : appeals) {
            builder.append("\n\t{\n");
            builder.append("\t\t\"studentId\" : ").append(appeal.getStudentId()).append(",\n");
            builder.append("\t\t\"gradingItemId\" : ").append(appeal.getGradingItemId()).append(",\n");
            builder.append("\t\t\"appealMessage\" : \"").append(appeal.getAppealMessage()).append("\"\n");
            builder.append("\t},\n");
        }
        String jsonSoFar;
        if (builder.toString().endsWith("[")) {
            jsonSoFar = builder.toString();
        } else {
            jsonSoFar = builder.substring(0, builder.length() - 2);
        }
        builder = new StringBuilder(jsonSoFar);
        builder.append("\n\t]\n}");
        return Response.status(HTTP_OK).entity(builder.toString()).build();
    }

    @POST
    @Path("fileAnAppeal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fileAnAppeal(String jsonRequest) {
        String jsonResponse;
        try {
            JSONObject root = new JSONObject(jsonRequest);
            if (!root.getString("requestType").equals("fileAnAppeal")) {
                throw new JSONException("Bad Request");
            }
            int studentId = root.getInt("studentId");
            int gradingItemId = root.getInt("gradingItemId");
            String appealMessage = root.getString("appealMessage");
            Appeal appeal = new Appeal(studentId, gradingItemId, appealMessage);
            appeals.add(appeal);
            jsonResponse = "{\n"
                    + "\"responseType\":\"success\"\n"
                    + "}";
            return Response.status(HTTP_CREATED).entity(jsonResponse).build();
        } catch (JSONException e) {
            jsonResponse = "{\n"
                    + "\"responseType\":\"failure\",\n"
                    + "\"request\":" + jsonRequest + "\n"
                    + "}";
            return Response.status(HTTP_BAD_REQUEST).entity(jsonResponse).build();
        } catch (Exception e) {
            jsonResponse = "{\n"
                    + "\"responseType\":\"failure\",\n"
                    + "\"request\":" + jsonRequest + "\n"
                    + "}";
            return Response.status(HTTP_INTERNAL_ERROR).entity(jsonResponse).build();
        }
    }
}
