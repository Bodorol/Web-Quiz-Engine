package api.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompletedQuizRepository completedQuizRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return new UserPrincipal(user);
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

    public void addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Username not available.");
        } else if (user.getEmail().matches("\\w+@\\w+\\.\\w+") && user.getPassword().matches("(\\w){5,}")) {
            userRepository.save(user);
        } else {
            throw new InvalidUsernameOrPasswordException("Invalid username or password");
        }
    }

    public Page<CompletedQuiz> getCompletedQuizzes(Integer page, Integer pageSize, String sortBy) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String solver = ((UserDetails)principal).getUsername();
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        return completedQuizRepository.findBySolverContaining(solver, paging);
    }
}
