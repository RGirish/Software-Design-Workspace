/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealAlreadyAbandonedException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealAlreadyFinishedProcessingException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealProcessingAlreadyStartedException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.InvalidAppealIDException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.Appeal;
import static asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.repositories.AppealRepository.APPEAL_REPOSITORY;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AppealRepresentation;
import static asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AllUris.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Girish
 */
public class AddAppealItemEvent {

    public AddAppealItemEvent() {
    }

    public AppealRepresentation addAppealItem(int id, List<String> appealItems) {
        if (APPEAL_REPOSITORY.containsAppealID(id)) {
            Appeal appeal = APPEAL_REPOSITORY.getAppealByID(id);
            switch (appeal.getAppealStatus()) {
                case ABANDONED:
                    System.out.println("\nResource found to be in an unexpected state! Throwing an Exception.");
                    throw new AppealAlreadyAbandonedException();
                case PROCESSING:
                    System.out.println("\nResource found to be in an unexpected state! Throwing an Exception.");
                    throw new AppealProcessingAlreadyStartedException();
                case FINISHED:
                    System.out.println("\nResource found to be in an unexpected state! Throwing an Exception.");
                    throw new AppealAlreadyFinishedProcessingException();
            }

            List<String> oldAppealItems = appeal.getAppealItems();
            for (String appealItem : appealItems) {
                oldAppealItems.add(appealItem);
            }
            appeal.setAppealItems(oldAppealItems);
            APPEAL_REPOSITORY.resetAppealByID(id, appeal);

            String addAppealItemUri = ADD_APPEAL_ITEM_URI + "/" + id;
            String rewordAppealUri = REWORD_URI + "/" + id;
            String addImageUri = ADD_IMAGE_URI + "/" + id;
            String reviewAppealUri = REVIEW_URI + "/" + id;
            String abandonAppealUri = ABANDON_URI + "/" + id;
            Map<String, String> nextStateUris = new LinkedHashMap<>();
            nextStateUris.put("addAppealItemUri", addAppealItemUri);
            nextStateUris.put("rewordAppealUri", rewordAppealUri);
            nextStateUris.put("addImageUri", addImageUri);
            String followUpUri = FOLLOW_UP_URI + "/" + id;
            nextStateUris.put("followUpUri", followUpUri);
            nextStateUris.put("reviewAppealUri", reviewAppealUri);
            nextStateUris.put("abandonAppealUri", abandonAppealUri);
            String readAppealUri = READ_APPEAL_URI + "/" + id;
            nextStateUris.put("readAppealUri", readAppealUri);
            System.out.println("\nAddAppealItem - Activity Completed successfully!");
            return new AppealRepresentation(appeal, nextStateUris);
        } else {
            System.out.println("\nInvalid ID given. Throwing an Exception.");
            throw new InvalidAppealIDException();
        }
    }
}
