package pro.sky.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.course2.examinerservice.domain.Question;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    private QuestionService questionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        questionService = Mockito.mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void testGetQuestionsSuccess() {
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("q1", "a1"));
        questions.add(new Question("q2", "a2"));
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(new Question("q1", "a1"));

        Set<Question> result = examinerService.getQuestions(1);
        assertEquals(1, result.size());
    }

    @Test
    void testGetQuestionsThrows() {
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("q1", "a1"));
        when(questionService.getAll()).thenReturn(questions);

        assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(5));
    }
}