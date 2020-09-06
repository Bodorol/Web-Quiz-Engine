package api.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    @JoinColumn(name="Creator")
    private User creator;

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

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @JsonIgnore
    public User getCreator() {
        return creator;
    }

    public boolean checkAnswer(HashSet<Integer> answers) {
        return this.answer.equals(answers);
    }

}
