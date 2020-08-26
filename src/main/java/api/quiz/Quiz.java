package api.quiz;

import java.util.*;

import javax.persistence.*;


@Entity(name="quiz")
public class Quiz {

    private static Long idCount = 0L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String title = null;

    @Column
    private String text = null;

    @Column
    private String[] options = null;

    @Column
    private HashSet<Integer> answer = new HashSet<>();

    public Quiz() {}

    public Long getId() {
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
