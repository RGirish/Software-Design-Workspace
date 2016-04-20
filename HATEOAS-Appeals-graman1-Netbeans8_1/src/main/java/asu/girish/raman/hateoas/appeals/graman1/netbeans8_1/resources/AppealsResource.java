package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.resources;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.AbandonAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.AddAppealItemEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.AddImageEventEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.CreateAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.FinishAppealProcessingEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.FollowUpOnAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.ReadAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.ReviewAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.RewordAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealAlreadyAbandonedException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealAlreadyFinishedProcessingException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealHasNotStartedProcessingYetException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealProcessingAlreadyStartedException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.InvalidRequestException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.Appeal;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AppealRepresentation;
import static java.net.HttpURLConnection.*;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
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

    @PUT
    @Path("/reword/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response rewordAppeal(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            String appealMessage = requestRepresentation.getAppealMessage();

            AppealRepresentation responseRepresentation = new RewordAppealEvent().rewordAppeal(appealID, appealMessage);
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidRequestException e) {
            return Response.status(HTTP_BAD_REQUEST).entity("<InvalidRequest/>").build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/addImage/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response addImage(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            List<String> images = requestRepresentation.getImages();
            AppealRepresentation responseRepresentation = new AddImageEventEvent().addImage(appealID, images);
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidRequestException e) {
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/addAppealItem/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response addAppealItem(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            List<String> appealItems = requestRepresentation.getAppealItems();
            AppealRepresentation responseRepresentation = new AddAppealItemEvent().addAppealItem(appealID, appealItems);
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidRequestException e) {
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/followUp/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response followUpOnAppeal(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            List<String> followUps = requestRepresentation.getFollowUps();
            AppealRepresentation responseRepresentation = new FollowUpOnAppealEvent().followUp(appealID, followUps);
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidRequestException e) {
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/review/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    public Response reviewAppeal(@PathParam("appealID") int appealID) {
        try {
            AppealRepresentation responseRepresentation = new ReviewAppealEvent().reviewAppeal(appealID);
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidRequestException e) {
            return Response.status(HTTP_BAD_REQUEST).entity(String.valueOf(appealID)).build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity(String.valueOf(appealID)).build();
        }
    }

    @PUT
    @Path("/abandonAppeal/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    public Response abandonAppeal(@PathParam("appealID") int appealID) {
        try {
            AppealRepresentation responseRepresentation = new AbandonAppealEvent().abandonAppeal(appealID);
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidRequestException e) {
            return Response.status(HTTP_BAD_REQUEST).entity(String.valueOf(appealID)).build();
        } catch (Exception e) {
            return Response.status(HTTP_INTERNAL_ERROR).entity(String.valueOf(appealID)).build();
        }
    }

    @PUT
    @Path("/finishAppealProcessing/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    public Response finishAppealProcessing(@PathParam("appealID") int appealID) {
        try {
            AppealRepresentation responseRepresentation = new FinishAppealProcessingEvent().finishAppealProcessing(appealID);
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealHasNotStartedProcessingYetException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealHasNotStartedProcessingYet/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("ire " + e.toString());
            return Response.status(HTTP_BAD_REQUEST).entity(String.valueOf(appealID)).build();
        } catch (Exception e) {
            System.out.println("e " + e.toString());
            return Response.status(HTTP_INTERNAL_ERROR).entity(String.valueOf(appealID)).build();
        }
    }

    @GET
    @Path("{appealID}")
    @Produces(APPLICATION_XML)
    public String getAppeal(@PathParam("appealID") int appealID) {
        try {
            AppealRepresentation responseRepresentation = new ReadAppealEvent().readAppeal(appealID);
            return responseRepresentation.toString();
        } catch (InvalidRequestException e) {
            return "Invalid Request";
        } catch (Exception e) {
            return "Internal Server Error";
        }
    }
}
