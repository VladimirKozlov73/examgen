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

    @Test
    void testAddWithStringAndString() {
        int beforeSize = service.getAll().size();

        service.add("Новый вопрос?", "Новый ответ");

        Set<Question> all = service.getAll();
        assertEquals(beforeSize + 1, all.size());
        assertTrue(all.stream().anyMatch(q ->
                q.getQuestion().equals("Новый вопрос?") &&
                        q.getAnswer().equals("Новый ответ")
        ));
    }

    // дубликаты при добавлении
    @Test
    void testAddDuplicateDoesNotIncreaseSize() {
        Question q = new Question("Дубликат?", "Дубликат ответа");

        service.add(q);
        int sizeAfterFirstAdd = service.getAll().size();

        service.add(q);
        int sizeAfterSecondAdd = service.getAll().size();

        assertEquals(sizeAfterFirstAdd, sizeAfterSecondAdd);
    }

    @Test
    void testRemoveFromEmptyCollectionDoesNotThrow() {
        Question q = new Question("Несуществующий?", "Несуществующий ответ");

        int beforeSize = service.getAll().size();

        assertDoesNotThrow(() -> service.remove(q));

        int afterSize = service.getAll().size();
        assertEquals(beforeSize, afterSize);
    }

    @Test
    void testGetRandomQuestionReturnsQuestionFromSet() {
        Question q = new Question("Случайный?", "Случайный ответ");
        service.add(q);

        Question random = service.getRandomQuestion();

        assertTrue(service.getAll().contains(random));
    }

    @Test
    void testFindByKeywordWithBlankReturnsEmpty() {
        assertTrue(service.findByKeyword(null).isEmpty());
        assertTrue(service.findByKeyword("").isEmpty());
        assertTrue(service.findByKeyword("   ").isEmpty());
    }
}