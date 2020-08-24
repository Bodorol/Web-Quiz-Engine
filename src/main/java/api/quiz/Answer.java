package api.quiz;

import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class Answer {

    private boolean success;
    private String feedback;
    private HashSet<Integer> answer = new HashSet<>();

    public void init(Quiz quiz) {
        success = quiz.checkAnswer(this.answer);
        feedback = success ? "You won, yay!" : "You lost, oh no";
    }

    public void setAnswers(HashSet<Integer> answers) {
        this.answer = answers;
    }

    public void setAnswer(HashSet<Integer> answer) {
        this.answer = answer;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }

}

