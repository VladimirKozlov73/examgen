package pro.sky.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService();
    }

    @Test
    void testAddAndGetAll() {
        Question q = new Question("Вопрос", "Ответ");
        service.add(q);
        assertTrue(service.getAll().contains(q));
    }

    @Test
    void testRemove() {
        Question q = new Question("Вопрос", "Ответ");
        service.add(q);
        service.remove(q);
        assertFalse(service.getAll().contains(q));
    }

    @Test
    void testFindByKeyword() {
        Question q1 = new Question("Что такое JVM?", "Виртуальная машина Java.");
        service.add(q1);
        Set<Question> found = service.findByKeyword("JVM");
        assertTrue(found.contains(q1));
    }
}