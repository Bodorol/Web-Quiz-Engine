package api.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String roles = "USER";

    @OneToMany(mappedBy = "creator")
    private List<Quiz> createdQuizzes;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    @JsonIgnore
    public List<Quiz> getCreatedQuizzes() {
        return new ArrayList<Quiz>(createdQuizzes);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addQuiz(Quiz quiz) {
        createdQuizzes.add(quiz);
    }
}
