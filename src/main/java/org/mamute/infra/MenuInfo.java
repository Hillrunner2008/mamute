package org.mamute.infra;

import br.com.caelum.vraptor.Result;
import javax.inject.Inject;
import org.mamute.brutauth.auth.rules.EnvironmentKarma;
import org.mamute.dao.FlaggableDAO;
import org.mamute.dao.InformationDAO;
import org.mamute.model.LoggedUser;

public class MenuInfo {

    @Inject
    private Result result;
    @Inject
    private LoggedUser loggedUser;
    @Inject
    private FlaggableDAO flaggables;
    @Inject
    private InformationDAO informations;
    @Inject
    private EnvironmentKarma environmentKarma;

    public void include() {
        result.include("currentUser", loggedUser);
        if (loggedUser.canModerate(environmentKarma)) {
            Long pendingCount = informations.pendingCount();
            int flaggedCount = flaggables.flaggedButVisibleCount();
            if (loggedUser.isModerator()) {
                result.include("pendingForModeratorCount", pendingCount + flaggedCount);
            } else {
                result.include("pendingForModeratorCount", pendingCount);
            }
        }
    }

}
