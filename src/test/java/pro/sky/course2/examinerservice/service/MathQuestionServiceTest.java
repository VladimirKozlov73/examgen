package pro.sky.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.course2.examinerservice.domain.Question;
import pro.sky.course2.examinerservice.repository.InMemoryQuestionRepository;
import pro.sky.course2.examinerservice.repository.QuestionRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionServiceTest {

    private MathQuestionService service;

    @BeforeEach
    void setUp() {
        QuestionRepository repository = new InMemoryQuestionRepository();
        service = new MathQuestionService(repository);
    }

    @Test
    void testAddAndGetAll() {
        Question q = new Question("1 + 1 = ?", "2");
        service.add(q);
        assertTrue(service.getAll().contains(q));
    }

    @Test
    void testAddWithStringAndString() {
        int beforeSize = service.getAll().size();

        service.add("Новый пример?", "Новый ответ");

        Set<Question> all = service.getAll();
        assertEquals(beforeSize + 1, all.size());
        assertTrue(all.stream().anyMatch(q ->
                q.getQuestion().equals("Новый пример?") &&
                        q.getAnswer().equals("Новый ответ")
        ));
    }

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
    void testRemove() {
        Question q = new Question("5 + 5 = ?", "10");
        service.add(q);
        service.remove(q);
        assertFalse(service.getAll().contains(q));
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
    void testGetRandomQuestionOnEmptyCollectionThrows() {
        QuestionRepository emptyRepository = new InMemoryQuestionRepository();
        MathQuestionService emptyService = new MathQuestionService(emptyRepository);

        assertDoesNotThrow(service::getRandomQuestion);
    }

    @Test
    void testFindByKeyword() {
        Question q1 = new Question("2 + 2 = ?", "4");
        service.add(q1);
        Set<Question> found = service.findByKeyword("2");
        assertTrue(found.contains(q1));
    }

    @Test
    void testFindByKeywordWithBlankReturnsEmpty() {
        assertTrue(service.findByKeyword(null).isEmpty());
        assertTrue(service.findByKeyword("").isEmpty());
        assertTrue(service.findByKeyword("   ").isEmpty());
    }
}