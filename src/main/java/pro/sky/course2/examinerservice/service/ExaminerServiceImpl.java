package pro.sky.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService,
                               MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set<Question> allQuestions = new HashSet<>();
        allQuestions.addAll(javaQuestionService.getAll());
        allQuestions.addAll(mathQuestionService.getAll());

        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Запрашиваемое количество вопросов превышает доступное."
            );
        }

        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            // Случайный выбор: с равной вероятностью из Java или Math
            if (Math.random() < 0.5 && !javaQuestionService.getAll().isEmpty()) {
                result.add(javaQuestionService.getRandomQuestion());
            } else if (!mathQuestionService.getAll().isEmpty()) {
                result.add(mathQuestionService.getRandomQuestion());
            } else if (!javaQuestionService.getAll().isEmpty()) {
                result.add(javaQuestionService.getRandomQuestion());
            }
        }

        return result;
    }
}