package org.mamute.controllers;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.routes.annotation.Routed;
import br.com.caelum.vraptor.validator.Validator;
import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.mamute.brutauth.auth.rules.LoggedRule;
import org.mamute.dao.AnswerDAO;
import org.mamute.dao.QuestionDAO;
import org.mamute.factory.MessageFactory;
import org.mamute.filesystem.AttachmentRepository;
import org.mamute.infra.ClientIp;
import org.mamute.model.Attachment;
import org.mamute.model.LoggedUser;
import org.mamute.model.User;

@Routed
@Controller
public class AttachmentController {

    @Inject
    private Result result;
    @Inject
    private QuestionDAO questions;
    @Inject
    private AnswerDAO answers;
    @Inject
    private LoggedUser loggedUser;
    @Inject
    private ClientIp clientIp;
    @Inject
    private HttpServletResponse response;
    @Inject
    private AttachmentRepository attachments;
    @Inject
    private Validator validator;
    @Inject
    private MessageFactory messageFactory;

    @CustomBrutauthRules(LoggedRule.class)
    @Post
    public void uploadAttachment(UploadedFile file) throws IOException {
        Attachment attachment = new Attachment(file, loggedUser.getCurrent(),
                clientIp.get());
        attachments.save(attachment);

        result.use(json()).withoutRoot().from(attachment).serialize();
    }

    @Get
    public InputStreamDownload downloadAttachment(Long attachmentId) throws IOException {
        Attachment attachment = attachments.load(attachmentId);
        InputStream is = attachments.open(attachment);

        return new InputStreamDownload(is, attachment.getMime(), attachment.getName());
    }

    @CustomBrutauthRules(LoggedRule.class)
    @Delete
    public void deleteAttachment(Long attachmentId) throws IOException {
        Attachment attachment = attachments.load(attachmentId);
        User current = loggedUser.getCurrent();
        if (!attachment.canDelete(current) && !current.isModerator()) {
            validator.add(messageFactory.build("error", "unauthorized.title"));
            result.use(http()).sendError(403);
            return;
        }
        attachments.delete(attachment);
        result.nothing();
    }

}
