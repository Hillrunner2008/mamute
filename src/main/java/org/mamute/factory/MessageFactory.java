package org.mamute.factory;

import br.com.caelum.vraptor.validator.I18nMessage;
import java.util.ResourceBundle;
import javax.inject.Inject;

public class MessageFactory {

    private ResourceBundle bundle;

    @Deprecated
    public MessageFactory() {
    }

    @Inject
    public MessageFactory(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public I18nMessage build(String category, String key, Object... parameters) {
        I18nMessage message = new I18nMessage(category, key, parameters);
        message.setBundle(bundle);
        return message;
    }

}
