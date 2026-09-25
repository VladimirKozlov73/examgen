package pro.sky.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set<Question> allQuestions = questionService.getAll();
        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Запрашиваемое количество вопросов превышает доступное."
            );
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
