package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.resources;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AppealRepresentation;
import static java.net.HttpURLConnection.*;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;

@Path("appeals")
public class AppealsResource {

    public static final String APPEALS_MEDIA_TYPE = "application/vnd-cse564-appeals+json";

    @Context
    private UriInfo context;

    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    Response createNewAppeal(String jsonRequest) {
        try {
            AppealRepresentation appealRepresentation = AppealRepresentation.createAppealJsonRequestToObject(jsonRequest);
            return Response.status(HTTP_CREATED).location(new URI(appealRepresentation.getLocationURI())).entity(appealRepresentation).build();
        } catch (JSONException e) {
            return Response.status(HTTP_BAD_REQUEST).entity("{}").build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity("{}").build();
        }
    }
}
