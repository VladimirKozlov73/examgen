package pro.sky.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.domain.Question;
import pro.sky.course2.examinerservice.repository.QuestionRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository repository;
    private final Random random = new Random();

    public JavaQuestionService(QuestionRepository repository) {
        this.repository = repository;
        initQuestions();
    }

    private void initQuestions() {
        add(new Question("Что такое JVM?", "Виртуальная машина Java."));
        add(new Question("Что такое Spring Framework?", "Фреймворк для упрощения разработки Java приложений."));
        add(new Question("Как объявить переменную в Java?", "Тип переменной плюс имя, например int x;"));
        add(new Question("Что такое наследование?", "Под наследованием подразумевается, что один класс " +
                "может наследовать другой класс. Таким образом можно переиспользовать код класса, от которого наследуются."));
        add(new Question("Что такое инкапсуляция?", "Сокрытие реализации при помощи модификаторов" +
                " доступа, при помощи геттеров и сеттеров."));
        add(new Question("Что такое Local Variable?", "Переменная, которая определена внутри метода и" +
                " существует вплоть до того момента, пока выполняется этот метод."));
        add(new Question("Что такое Instance Variable?", "Переменная, которая определена внутри класса," +
                " и она существует вплоть до того момента, пока существует объект."));
    }

    @Override
    public void add(String questionText, String answerText) {
        add(new Question(questionText, answerText));
    }

    @Override
    public void add(Question question) {
        repository.add(question);
    }

    @Override
    public void remove(Question question) {
        repository.remove(question);
    }

    @Override
    public Set<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Set<Question> all = repository.getAll();
        if (all.isEmpty()) {
            throw new RuntimeException("Нет вопросов для выбора");
        }
        int index = random.nextInt(all.size());
        return new java.util.ArrayList<>(all).get(index);
    }

    @Override
    public Set<Question> findByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return Collections.emptySet();
        }
        String lowerKeyword = keyword.toLowerCase();
        Set<Question> result = new HashSet<>();
        for (Question question : getAll()) {
            if (question.getQuestion().toLowerCase().contains(lowerKeyword) ||
                    question.getAnswer().toLowerCase().contains(lowerKeyword)) {
                result.add(question);
            }
        }
        return result;
    }
}