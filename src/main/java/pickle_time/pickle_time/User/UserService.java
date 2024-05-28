package pickle_time.pickle_time.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public List<User> getUsersForSidebar(String loggedInUserId) {
        return userRepository.findByIdNot(loggedInUserId);
    }

    public User registerUser(String fullName, String username, String password, String confirmPassword, String gender) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);

        String profilePic = gender.equals("male") ?
                "https://avatar.iran.liara.run/public/boy?username=" + username :
                "https://avatar.iran.liara.run/public/girl?username=" + username;

        User newUser = new User(fullName, username, hashedPassword, gender, profilePic);

        return userRepository.save(newUser);
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return user;
    }

    public void logout() {
        // No specific logic needed for logout as token expiration will be handled on client side
    }

    public String generateToken(User user) {
        return jwtTokenProvider.generateToken(user.getId());
    }

//    public void saveUser(User user) {
//        user.setStatus(Status.ONLINE);
//        repository.save(user);
//    }
//
//    public void disconnect(User user) {
//        var storedUser = repository.findById(user.getNickName()).orElse(null);
//        if (storedUser != null) {
//            storedUser.setStatus(Status.OFFLINE);
//            repository.save(storedUser);
//        }
//    }
//
//    public List<User> findConnectedUsers() {
//        return repository.findAllByStatus(Status.ONLINE);
//    }
}
