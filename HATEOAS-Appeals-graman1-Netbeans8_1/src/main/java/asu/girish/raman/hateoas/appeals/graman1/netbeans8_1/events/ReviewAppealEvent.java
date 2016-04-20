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
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.AppealStatus;
import static asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.repositories.AppealRepository.APPEAL_REPOSITORY;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AppealRepresentation;
import static asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AllUris.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Girish
 */
public class ReviewAppealEvent {

    public ReviewAppealEvent() {
    }

    public AppealRepresentation reviewAppeal(int id) {
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
                default:
                    break;
            }

            appeal.setAppealStatus(AppealStatus.PROCESSING);
            APPEAL_REPOSITORY.resetAppealByID(id, appeal);

            String finishAppealProcessingUri = FINISH_PROCESSING_URI + "/" + id;
            String readAppealUri = READ_APPEAL_URI + "/" + id;
            Map<String, String> nextStateUris = new LinkedHashMap<>();
            nextStateUris.put("finishAppealProcessingUri", finishAppealProcessingUri);
            nextStateUris.put("readAppealUri", readAppealUri);
            System.out.println("\nReview Appeal - Activity Completed successfully!");
            return new AppealRepresentation(appeal, nextStateUris);
        } else {
            System.out.println("\nInvalid ID given. Throwing an Exception.");
            throw new InvalidAppealIDException();
        }
    }
}
