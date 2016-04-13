/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealAlreadyAbandonedException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealHasNotStartedProcessingYetException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.AppealProcessingAlreadyStartedException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.CannotAbandonAppealNowException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.exceptions.InvalidAppealIDException;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.Appeal;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.AppealStatus;
import static asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.repositories.AppealRepository.APPEAL_REPOSITORY;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations.AppealRepresentation;

/**
 *
 * @author Girish
 */
public class AbandonAppealEvent {

    public AbandonAppealEvent() {
    }

    public AppealRepresentation abandonAppeal(int id) {
        if (APPEAL_REPOSITORY.containsAppealID(id)) {
            Appeal appeal = APPEAL_REPOSITORY.getAppealByID(id);
            switch (appeal.getAppealStatus()) {
                case ABANDONED:
                    throw new AppealAlreadyAbandonedException();
                case PROCESSING:
                    throw new CannotAbandonAppealNowException();
                case FINISHED:
                    throw new CannotAbandonAppealNowException();
                default:
                    break;
            }

            appeal.setAppealStatus(AppealStatus.ABANDONED);
            APPEAL_REPOSITORY.resetAppealByID(id, appeal);
            return new AppealRepresentation(appeal);
        } else {
            throw new InvalidAppealIDException();
        }
    }
}
