package pro.sky.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.domain.Question;
import pro.sky.course2.examinerservice.repository.QuestionRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class MathQuestionService implements QuestionService {

    private final QuestionRepository repository;
    private final Random random = new Random();

    public MathQuestionService(QuestionRepository repository) {
        this.repository = repository;
        initQuestions();
    }

    private void initQuestions() {
        add(new Question("2 + 2 = ?", "4"));
        add(new Question("5 * 6 = ?", "30"));
        add(new Question("10 - 3 = ?", "7"));
        add(new Question("8 / 2 = ?", "4"));
        add(new Question("3 * 3 = ?", "9"));
        add(new Question("7 + 8 = ?", "15"));
        add(new Question("9 - 4 = ?", "5"));
    }

    @Override
    public void add(String questionText, String answerText) {
        add(new Question(questionText, answerText));
    }

    @Override
    public void add(Question question) {
        repository.add(question);
    }

    @Override
    public void remove(Question question) {
        repository.remove(question);
    }

    @Override
    public Set<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Set<Question> all = repository.getAll();
        if (all.isEmpty()) {
            throw new RuntimeException("Нет вопросов для выбора");
        }
        int index = random.nextInt(all.size());
        return new java.util.ArrayList<>(all).get(index);
    }

    @Override
    public Set<Question> findByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return Collections.emptySet();
        }
        String lowerKeyword = keyword.toLowerCase();
        Set<Question> result = new HashSet<>();
        for (Question question : getAll()) {
            if (question.getQuestion().toLowerCase().contains(lowerKeyword) ||
                    question.getAnswer().toLowerCase().contains(lowerKeyword)) {
                result.add(question);
            }
        }
        return result;
    }
}