package pro.sky.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.examinerservice.domain.Question;
import pro.sky.course2.examinerservice.service.JavaQuestionService;

import java.util.Set;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(@Qualifier("javaQuestionService") JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public Set<Question> getAll() {
        return javaQuestionService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.add(question, answer);
    }

    @DeleteMapping("/remove")
    public void remove(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.remove(new Question(question, answer));
    }

    @GetMapping("/find")
    public Set<Question> find(@RequestParam String keyword) {
        return javaQuestionService.findByKeyword(keyword);
    }
}