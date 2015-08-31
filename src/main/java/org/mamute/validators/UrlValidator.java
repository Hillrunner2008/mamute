package org.mamute.validators;

import br.com.caelum.vraptor.environment.Environment;
import javax.inject.Inject;

public class UrlValidator {

    private String siteUrl;

    @Deprecated
    public UrlValidator() {
    }

    @Inject
    public UrlValidator(Environment env) {
        this.siteUrl = env.get("host");
    }

    public boolean isValid(String url) {
        if (url != null && !url.startsWith(siteUrl)) {
            return false;
        }
        return true;
    }

}
