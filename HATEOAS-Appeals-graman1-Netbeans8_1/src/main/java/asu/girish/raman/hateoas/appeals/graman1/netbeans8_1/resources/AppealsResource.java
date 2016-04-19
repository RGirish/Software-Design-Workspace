package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.resources;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.CreateAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.InvalidRequestException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.Appeal;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AppealRepresentation;
import static java.net.HttpURLConnection.*;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import javax.ws.rs.core.Response;

@Path("appeals")
public class AppealsResource {

    public static final String APPEALS_MEDIA_TYPE = "application/vnd-cse564-appeals+xml";

    @Context
    private UriInfo context;

    @POST
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response createNewAppeal(String xmlRequest) {
        try {
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            int studentID = requestRepresentation.getStudentID();
            int gradingItemID = requestRepresentation.getGradingItemID();
            String appealMessage = requestRepresentation.getAppealMessage();

            Appeal appeal = new Appeal();
            appeal.setStudentID(studentID);
            appeal.setGradingItemID(gradingItemID);
            appeal.setAppealMessage(appealMessage);

            AppealRepresentation responseRepresentation = new CreateAppealEvent().createAppeal(appeal);
            return Response.created(new URI(responseRepresentation.getLocationURI())).entity(responseRepresentation).build();
        } catch (InvalidRequestException e) {
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @GET
    @Produces(TEXT_PLAIN)
    public String getAppeal() {
        return "yo";
    }
}
