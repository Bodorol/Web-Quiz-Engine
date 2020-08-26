package api.quiz;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @PostMapping(value = "api/quizzes", consumes = "application/json")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        if (quiz.getTitle() == null || quiz.getText() == null || quiz.getOptions() == null || quiz.getOptions().length < 2) {
            throw new RequiredException("Test");
        }
        return quizRepository.save(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizRepository.findById(id).get();
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public Answer answerQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        if (answer == null) {
            answer = new Answer();
        }
        answer.init(quizRepository.findById(id).get());
        return answer;
    }
}