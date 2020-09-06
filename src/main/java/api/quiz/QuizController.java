package api.quiz;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CompletedQuizRepository completedQuizRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @GetMapping("/api/quizzes")
    public Page<Quiz> getAllQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return quizService.getAllQuizzes(page, pageSize, sortBy);
    }

    @PostMapping(value = "api/quizzes", consumes = "application/json")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        if (quiz.getTitle() == null || quiz.getText() == null || quiz.getOptions() == null || quiz.getOptions().length < 2) {
            throw new RequiredException("Test");
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByUsername(username);
        quiz.setCreator(user);
        user.addQuiz(quiz);
        return quizRepository.save(quiz);
    }

    @PostMapping(value = "/api/register", consumes = "application/json")
    public void registerUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizRepository.findById(id).get();
    }

    @GetMapping("/api/quizzes/completed")
    public Page<CompletedQuiz> getSolvedQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "completedAt") String sortBy
    ) {
        return userService.getCompletedQuizzes(page, pageSize, sortBy);
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public Answer answerQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        if (answer == null) {
            answer = new Answer();
        }
        answer.init(quizRepository.findById(id).get());
        if (answer.getSuccess()) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String solver = ((UserDetails)principal).getUsername();
            CompletedQuiz quiz = new CompletedQuiz();
            quiz.setId(id);
            quiz.setSolver(solver);
            completedQuizRepository.save(quiz);
        }
        return answer;
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id).get();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByUsername(username);
        if (quiz.getCreator() != user) {
            throw new ForbiddenActionException("Unable to delete quiz: User does not own quiz");
        } else {
            quizRepository.delete(quiz);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}