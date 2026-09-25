package pro.sky.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.examinerservice.domain.Question;
import pro.sky.course2.examinerservice.service.QuestionService;

import java.util.Set;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public Set<Question> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/add")
    public void add(@RequestParam String question, @RequestParam String answer) {
        questionService.add(new Question(question, answer));
    }

    @GetMapping("/remove")
    public void remove(@RequestParam String question, @RequestParam String answer) {
        questionService.remove(new Question(question, answer));
    }

    @GetMapping("/find")
    public Set<Question> find(@RequestParam String keyword) {
        return questionService.findByKeyword(keyword);
    }
}
