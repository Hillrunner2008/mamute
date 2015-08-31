package org.mamute.brutauth.auth.rules;

import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import javax.inject.Inject;
import static org.mamute.auth.rules.ComposedRule.composedRule;
import org.mamute.auth.rules.PermissionRules;
import static org.mamute.auth.rules.Rules.hasKarma;
import static org.mamute.auth.rules.Rules.isAuthor;
import static org.mamute.auth.rules.Rules.isModerator;
import org.mamute.model.LoggedUser;
import org.mamute.model.Question;
import org.mamute.model.User;

public class EditQuestionRule implements CustomBrutauthRule {

    private User user;
    private EnvironmentKarma environmentKarma;

    @Deprecated
    public EditQuestionRule() {
    }

    @Inject
    public EditQuestionRule(LoggedUser user, EnvironmentKarma environmentKarma) {
        this.environmentKarma = environmentKarma;
        this.user = user.getCurrent();
    }

    public boolean isAllowed(Question question) {
        long karma = environmentKarma.get(PermissionRules.EDIT_QUESTION);
        return composedRule(isAuthor()).or(hasKarma(karma)).or(isModerator()).isAllowed(user, question);
    }
}
