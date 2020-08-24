package api.quiz;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    private final List<Quiz> quizzes = new ArrayList<>();

    @GetMapping("/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return this.quizzes;
    }

    @PostMapping(value = "api/quizzes", consumes = "application/json")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        if (quiz.getTitle() == null || quiz.getText() == null || quiz.getOptions() == null || quiz.getOptions().length < 2) {
            throw new RequiredException("Test");
        }
        quiz.setId();
        quizzes.add(quiz);
        return quiz;
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizzes.get(id - 1);
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public Answer answerQuiz(@PathVariable int id, @RequestBody Answer answer) {
        if (answer == null) {
            answer = new Answer();
        }
        answer.init(quizzes.get(id - 1));
        return answer;
    }
}