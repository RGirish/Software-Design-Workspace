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
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.InvalidAppealIDException;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import javax.ws.rs.core.Response;

@Path("appeals")
public class AppealsResource {

    public static final String APPEALS_MEDIA_TYPE = "application/vnd-cse564-appeals+xml";

    @POST
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response createNewAppeal(String xmlRequest) {
        try {
            System.out.println("\nLanded on @POST createNewAppeal in Server...");
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            int studentID = requestRepresentation.getStudentID();
            int gradingItemID = requestRepresentation.getGradingItemID();
            String appealMessage = requestRepresentation.getAppealMessage();

            Appeal appeal = new Appeal();
            appeal.setStudentID(studentID);
            appeal.setGradingItemID(gradingItemID);
            appeal.setAppealMessage(appealMessage);

            AppealRepresentation responseRepresentation = new CreateAppealEvent().createAppeal(appeal);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.created(new URI(responseRepresentation.getLocationURI())).entity(responseRepresentation).build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400  - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/reword/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response rewordAppeal(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            System.out.println("\nLanded on @PUT rewordAppeal in Server...");
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            String appealMessage = requestRepresentation.getAppealMessage();
            AppealRepresentation responseRepresentation = new RewordAppealEvent().rewordAppeal(appealID, appealMessage);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404 - Leaving Server...");
            return Response.status(HTTP_NOT_FOUND).entity("<InvalidAppealID/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400 - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity("<InvalidRequest/>").build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/addImage/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response addImage(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            System.out.println("\nLanded on @PUT addImage in Server...");
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            List<String> images = requestRepresentation.getImages();
            AppealRepresentation responseRepresentation = new AddImageEventEvent().addImage(appealID, images);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404 - Leaving Server...");
            return Response.status(HTTP_NOT_FOUND).entity("<InvalidAppealID/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400 - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/addAppealItem/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response addAppealItem(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            System.out.println("\nLanded on @PUT addAppealItem in Server...");
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            List<String> appealItems = requestRepresentation.getAppealItems();
            AppealRepresentation responseRepresentation = new AddAppealItemEvent().addAppealItem(appealID, appealItems);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404 - Leaving Server...");
            return Response.status(HTTP_NOT_FOUND).entity("<InvalidAppealID/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400 - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/followUp/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    @Consumes(APPEALS_MEDIA_TYPE)
    public Response followUpOnAppeal(@PathParam("appealID") int appealID, String xmlRequest) {
        try {
            System.out.println("\nLanded on @PUT followUpOnAppeal in Server...");
            AppealRepresentation requestRepresentation = AppealRepresentation.XMLRequestToObject(xmlRequest);
            List<String> followUps = requestRepresentation.getFollowUps();
            AppealRepresentation responseRepresentation = new FollowUpOnAppealEvent().followUp(appealID, followUps);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404 - Leaving Server...");
            return Response.status(HTTP_NOT_FOUND).entity("<InvalidAppealID/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400 - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity(xmlRequest).build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity(xmlRequest).build();
        }
    }

    @PUT
    @Path("/review/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    public Response reviewAppeal(@PathParam("appealID") int appealID) {
        try {
            System.out.println("\nLanded on @PUT reviewAppeal in Server...");
            AppealRepresentation responseRepresentation = new ReviewAppealEvent().reviewAppeal(appealID);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404 - Leaving Server...");
            return Response.status(HTTP_NOT_FOUND).entity("<InvalidAppealID/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400 - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity("<AppealID>" + appealID + "</AppealID>").build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity("<AppealID>" + appealID + "</AppealID>").build();
        }
    }

    @PUT
    @Path("/abandonAppeal/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    public Response abandonAppeal(@PathParam("appealID") int appealID) {
        try {
            System.out.println("\nLanded on @PUT abandonAppeal in Server...");
            AppealRepresentation responseRepresentation = new AbandonAppealEvent().abandonAppeal(appealID);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealProcessingAlreadyStartedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealProcessingAlreadyStarted/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404 - Leaving Server...");
            return Response.status(HTTP_NOT_FOUND).entity("<InvalidAppealID/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400 - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity("<AppealID>" + appealID + "</AppealID>").build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity("<AppealID>" + appealID + "</AppealID>").build();
        }
    }

    @PUT
    @Path("/finishAppealProcessing/{appealID}")
    @Produces(APPEALS_MEDIA_TYPE)
    public Response finishAppealProcessing(@PathParam("appealID") int appealID) {
        try {
            System.out.println("\nLanded on @PUT finishAppealProcessing in Server...");
            AppealRepresentation responseRepresentation = new FinishAppealProcessingEvent().finishAppealProcessing(appealID);
            System.out.println("\nSuccess - Leaving Server...");
            return Response.ok().entity(responseRepresentation).build();
        } catch (AppealAlreadyAbandonedException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyAbandoned/>").build();
        } catch (AppealHasNotStartedProcessingYetException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealHasNotStartedProcessingYet/>").build();
        } catch (AppealAlreadyFinishedProcessingException e) {
            System.out.println("\nFailure - 409 - Leaving Server...");
            return Response.status(HTTP_CONFLICT).entity("<AppealAlreadyFinishedProcessing/>").build();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404 - Leaving Server...");
            return Response.status(HTTP_NOT_FOUND).entity("<InvalidAppealID/>").build();
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400 - Leaving Server...");
            return Response.status(HTTP_BAD_REQUEST).entity("<AppealID>" + appealID + "</AppealID>").build();
        } catch (Exception e) {
            System.out.println("\nFailure - 500  - Leaving Server...");
            return Response.status(HTTP_INTERNAL_ERROR).entity("<AppealID>" + appealID + "</AppealID>").build();
        }
    }

    @GET
    @Path("{appealID}")
    @Produces(APPLICATION_XML)
    public String getAppeal(@PathParam("appealID") int appealID) {
        try {
            System.out.println("\nLanded on @GET getAppeal in Server...");
            AppealRepresentation responseRepresentation = new ReadAppealEvent().readAppeal(appealID);
            System.out.println("\nSuccess - Leaving Server...");
            return responseRepresentation.toString();
        } catch (InvalidAppealIDException e) {
            System.out.println("\nFailure - 404  - Leaving Server...");
            return "Response code - 404 - Invalid Appeal ID";
        } catch (InvalidRequestException e) {
            System.out.println("\nFailure - 400  - Leaving Server...");
            return "Response code - 400 - Invalid Request";
        } catch (Exception e) {
            System.out.println("\nFailure - 500 - Leaving Server...");
            return "Response code - 500 - Internal Server Error";
        }
    }
}
