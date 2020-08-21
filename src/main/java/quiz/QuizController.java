package quiz;

import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    private final String title = "The Java Logo";
    private final String text = "What is depicted on the Java logo?";
    private final String[] options = {"Robot","Tea leaf","Cup of coffee","Bug"};
    private final Quiz quiz = new Quiz(title, text, options);

    @GetMapping("/quiz")
    public Quiz getQuiz() {
        return quiz;
    }

    @PostMapping("/quiz")
    public Answer answerQuiz(@RequestParam("answer") int answer) {
        return new Answer(quiz, answer);
    }

}
