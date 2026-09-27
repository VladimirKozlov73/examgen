package pro.sky.course2.examinerservice.repository;

import pro.sky.course2.examinerservice.domain.Question;

import java.util.Set;

public interface QuestionRepository {
    void add(Question question);
    void remove(Question question);
    Set<Question> getAll();
}