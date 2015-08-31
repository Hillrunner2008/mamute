package org.mamute.brutauth.auth.rules;

import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import javax.inject.Inject;
import org.mamute.brutauth.auth.handlers.LoggedHandler;
import org.mamute.model.LoggedUser;

@HandledBy(LoggedHandler.class)
public class LoggedRule implements CustomBrutauthRule {

    @Inject
    private LoggedUser loggedUser;

    public boolean isAllowed() {
        return loggedUser.isLoggedIn();
    }

}
