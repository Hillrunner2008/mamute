package org.mamute.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import static org.mamute.model.MarkDown.parse;
import org.mamute.model.MarkedText;
import static org.mamute.model.MarkedText.pureAndMarked;
import org.mamute.sanitizer.HtmlSanitizer;

@Convert(MarkedText.class)
@ApplicationScoped
public class MarkedTextConverter implements Converter<MarkedText> {

    private final HtmlSanitizer sanitizer;

    /**
     * @CDI eyes only
     */
    protected MarkedTextConverter() {
        this(null);
    }

    @Inject
    public MarkedTextConverter(HtmlSanitizer sanitizer) {
        this.sanitizer = sanitizer;
    }

    @Override
    public MarkedText convert(String value, Class<? extends MarkedText> type) {
        return pureAndMarked(value, sanitizer.sanitize(parse(value)).getText());
    }

}
