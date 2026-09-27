package pro.sky.course2.examinerservice.repository;

import org.springframework.stereotype.Repository;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class InMemoryQuestionRepository implements QuestionRepository {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public void remove(Question question) {
        questions.remove(question);
    }

    @Override
    public Set<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }
}