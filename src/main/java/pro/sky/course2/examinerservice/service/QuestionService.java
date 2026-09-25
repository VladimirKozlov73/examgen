package pro.sky.course2.examinerservice.service;

import pro.sky.course2.examinerservice.domain.Question;

import java.util.Set;

public interface QuestionService {
    void add(Question question);
    Set<Question> findByKeyword(String keyword);
    void remove(Question question);
    Set<Question> getAll();
    Question getRandomQuestion();
    void add(String questionText, String answerText);
}
