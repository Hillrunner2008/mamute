package org.mamute.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import java.util.Map;
import javax.inject.Inject;
import org.mamute.i18n.MessagesLoader;

@Controller
public class MessagesController {

    @Inject
    private MessagesLoader loader;

    @Inject
    private Result result;

    @Get("/messages/loadAll")
    public void loadMessages() {
        Map<String, String> messages = loader.getAllMessages();
        result.use(Results.json()).from(messages).serialize();
    }

}
