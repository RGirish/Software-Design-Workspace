package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.repositories;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.Appeal;
import java.util.LinkedList;
import java.util.List;

public class AppealRepository {

    private static int APPEAL_ID = 1;
    private static final List<Appeal> allAppeals = new LinkedList<>();
    public static final AppealRepository APPEAL_REPOSITORY = new AppealRepository();

    public int createNew(Appeal appeal) {
        appeal.setAppealID(APPEAL_ID);
        APPEAL_ID++;
        allAppeals.add(appeal);
        return (APPEAL_ID - 1);
    }

    public boolean containsAppealID(int id) {
        for (Appeal appeal : allAppeals) {
            if (appeal.getAppealID() == id) {
                return true;
            }
        }
        return false;
    }

    public Appeal getAppealByID(int id) {
        for (Appeal appeal : allAppeals) {
            if (appeal.getAppealID() == id) {
                return appeal;
            }
        }
        return null;
    }

    public void resetAppealByID(int id, Appeal modifiedAppeal) {
        for (Appeal appeal : allAppeals) {
            if (appeal.getAppealID() == id) {
                allAppeals.remove(appeal);
                break;
            }
        }
        allAppeals.add(modifiedAppeal);
    }
}
