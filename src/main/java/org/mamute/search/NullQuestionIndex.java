package org.mamute.search;

import java.util.Collection;
import java.util.List;
import javax.enterprise.inject.Vetoed;
import org.mamute.model.Question;

@Vetoed
public class NullQuestionIndex implements QuestionIndex {

    @Override
    public void indexQuestion(Question q) {
        //does nothing
    }

    @Override
    public void indexQuestionBatch(Collection<Question> questions) {
        //does nothing
    }

    @Override
    public List<Long> find(String title, int maxResults) {
        return null;
    }

    @Override
    public void delete(Question question) {
        //does nothing
    }
}
