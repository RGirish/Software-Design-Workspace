/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.InvalidAppealIDException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.Appeal;
import static asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.repositories.AppealRepository.APPEAL_REPOSITORY;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AppealRepresentation;
import static asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AllUris.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Girish
 */
public class ReadAppealEvent {

    public ReadAppealEvent() {
    }

    public AppealRepresentation readAppeal(int appealID) {
        if (APPEAL_REPOSITORY.containsAppealID(appealID)) {
            Appeal appeal = APPEAL_REPOSITORY.getAppealByID(appealID);
            Map<String, String> nextStateUris = new LinkedHashMap<>();
            switch (appeal.getAppealStatus()) {
                case SUBMITTED:
                    String addAppealItemUri = ADD_APPEAL_ITEM_URI + "/" + appealID;
                    String rewordAppealUri = REWORD_URI + "/" + appealID;
                    String addImageUri = ADD_IMAGE_URI + "/" + appealID;
                    String reviewAppealUri = REVIEW_URI + "/" + appealID;
                    String abandonAppealUri = ABANDON_URI + "/" + appealID;
                    String readAppealUri = READ_APPEAL_URI + "/" + appealID;
                    nextStateUris.put("addAppealItemUri", addAppealItemUri);
                    nextStateUris.put("rewordAppealUri", rewordAppealUri);
                    nextStateUris.put("addImageUri", addImageUri);
                    String followUpUri = FOLLOW_UP_URI + "/" + appealID;
                    nextStateUris.put("followUpUri", followUpUri);
                    nextStateUris.put("reviewAppealUri", reviewAppealUri);
                    nextStateUris.put("abandonAppealUri", abandonAppealUri);
                    nextStateUris.put("readAppealUri", readAppealUri);
                case PROCESSING:
                    String finishAppealProcessingUri = FINISH_PROCESSING_URI + "/" + appealID;
                    readAppealUri = READ_APPEAL_URI + "/" + appealID;
                    nextStateUris.put("readAppealUri", readAppealUri);
                    nextStateUris.put("finishAppealProcessingUri", finishAppealProcessingUri);
            }
            System.out.println("\nRead Appeal - Activity Completed successfully!");
            return new AppealRepresentation(appeal, nextStateUris);
        } else {
            System.out.println("\nInvalid ID given. Throwing an Exception.");
            throw new InvalidAppealIDException();
        }
    }
}
