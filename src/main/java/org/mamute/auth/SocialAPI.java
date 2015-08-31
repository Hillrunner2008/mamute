package org.mamute.auth;

import com.google.common.base.Optional;
import org.scribe.model.Token;

public interface SocialAPI {

    public Optional<SignupInfo> getSignupInfo();

    public Token getAccessToken();
}
