package api.quiz;

import java.util.*;
import org.springframework.stereotype.Component;


@Component
public class Quiz {

    private static int idCount = 0;
    private int id;
    private String title = null;
    private String text = null;
    private String[] options = null;
    private HashSet<Integer> answer = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setId() {
        this.id = ++idCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setAnswer(HashSet<Integer> answer) {
        this.answer = answer;
    }

    public boolean checkAnswer(HashSet<Integer> answers) {
        return this.answer.equals(answers);
    }

}
