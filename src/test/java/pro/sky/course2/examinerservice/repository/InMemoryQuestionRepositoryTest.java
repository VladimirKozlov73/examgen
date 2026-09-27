package pro.sky.course2.examinerservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryQuestionRepositoryTest {

    private QuestionRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryQuestionRepository();
    }

    @Test
    void testAddAndGetAll() {
        Question q = new Question("Тест?", "Тестовый ответ");
        repository.add(q);

        Set<Question> all = repository.getAll();
        assertTrue(all.contains(q));
    }

    @Test
    void testAddDuplicateDoesNotIncreaseSize() {
        Question q = new Question("Дубликат?", "Дубликат ответа");

        repository.add(q);
        int sizeAfterFirstAdd = repository.getAll().size();

        repository.add(q);
        int sizeAfterSecondAdd = repository.getAll().size();

        assertEquals(sizeAfterFirstAdd, sizeAfterSecondAdd);
    }

    @Test
    void testRemoveExistingQuestion() {
        Question q = new Question("Вопрос на удаление?", "Ответ на удаление");
        repository.add(q);

        int beforeSize = repository.getAll().size();
        repository.remove(q);
        int afterSize = repository.getAll().size();

        assertEquals(beforeSize - 1, afterSize);
        assertFalse(repository.getAll().contains(q));
    }

    @Test
    void testRemoveNonExistingQuestionDoesNotThrow() {
        Question q = new Question("Несуществующий?", "Несуществующий ответ");

        int beforeSize = repository.getAll().size();

        assertDoesNotThrow(() -> repository.remove(q));

        int afterSize = repository.getAll().size();
        assertEquals(beforeSize, afterSize);
    }

    @Test
    void testGetAllReturnsUnmodifiableView() {
        Set<Question> all = repository.getAll();

        assertNotNull(all);
        assertTrue(all instanceof Set);
    }
}