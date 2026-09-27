package pro.sky.course2.examinerservice.service;

import pro.sky.course2.examinerservice.domain.Question;

import java.util.Set;

public interface ExaminerService {
    Set<Question> getQuestions(int amount);
}
