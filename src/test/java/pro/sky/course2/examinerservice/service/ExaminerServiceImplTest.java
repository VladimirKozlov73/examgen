package pro.sky.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.course2.examinerservice.domain.Question;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    private JavaQuestionService javaQuestionService;
    private MathQuestionService mathQuestionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        javaQuestionService = Mockito.mock(JavaQuestionService.class);
        mathQuestionService = Mockito.mock(MathQuestionService.class);
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void testGetQuestionsSuccess() {
        Set<Question> javaQuestions = new HashSet<>();
        javaQuestions.add(new Question("q1", "a1"));
        javaQuestions.add(new Question("q2", "a2"));

        Set<Question> mathQuestions = new HashSet<>();
        mathQuestions.add(new Question("m1", "ma1"));

        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);
        when(javaQuestionService.getRandomQuestion()).thenReturn(new Question("q1", "a1"));

        Set<Question> result = examinerService.getQuestions(1);
        assertEquals(1, result.size());
    }

    @Test
    void testGetQuestionsThrows() {
        Set<Question> javaQuestions = new HashSet<>();
        javaQuestions.add(new Question("q1", "a1"));

        Set<Question> mathQuestions = Collections.emptySet();

        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(5));
    }

    @Test
    void testGetQuestionsWithAmountZeroReturnsEmpty() {
        Set<Question> javaQuestions = new HashSet<>();
        javaQuestions.add(new Question("q1", "a1"));

        Set<Question> mathQuestions = new HashSet<>();
        mathQuestions.add(new Question("m1", "ma1"));

        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        Set<Question> result = examinerService.getQuestions(0);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetQuestionsWithEmptyCollectionAndAmountZero() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptySet());
        when(mathQuestionService.getAll()).thenReturn(Collections.emptySet());

        Set<Question> result = examinerService.getQuestions(0);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetQuestionsWithEmptyCollectionAndAmountGreaterThanZeroThrows() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptySet());
        when(mathQuestionService.getAll()).thenReturn(Collections.emptySet());

        assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(1));
    }
}