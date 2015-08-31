package org.mamute.brutauth.auth.rules;

import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import javax.inject.Inject;
import static org.mamute.auth.rules.Rules.isModerator;
import org.mamute.model.LoggedUser;

public class ModeratorOnlyRule implements CustomBrutauthRule {

    @Inject
    private LoggedUser user;

    public boolean isAllowed() {
        return isModerator().isAllowed(user.getCurrent(), null);
    }

}
