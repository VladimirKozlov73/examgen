package pro.sky.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.examinerservice.domain.Question;
import pro.sky.course2.examinerservice.service.MathQuestionService;

import java.util.Set;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    private final MathQuestionService mathQuestionService;

    public MathQuestionController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping
    public Set<Question> getAll() {
        return mathQuestionService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestParam String question, @RequestParam String answer) {
        mathQuestionService.add(question, answer);
    }

    @DeleteMapping("/remove")
    public void remove(@RequestParam String question, @RequestParam String answer) {
        mathQuestionService.remove(new Question(question, answer));
    }

    @GetMapping("/find")
    public Set<Question> find(@RequestParam String keyword) {
        return mathQuestionService.findByKeyword(keyword);
    }
}