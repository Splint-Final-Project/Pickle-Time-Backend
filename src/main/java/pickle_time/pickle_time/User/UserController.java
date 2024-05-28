package pickle_time.pickle_time.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    // 회원가입 엔드포인트
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> payload, HttpServletResponse response) {
        try {
            String fullName = payload.get("fullName");
            String username = payload.get("username");
            String password = payload.get("password");
            String confirmPassword = payload.get("confirmPassword");
            String gender = payload.get("gender");

            User newUser = userService.registerUser(fullName, username, password, confirmPassword, gender);
            String token = userService.generateToken(newUser);

            // JWT 토큰을 응답 쿠키에 설정
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true); // 자바스크립트에서 쿠키에 접근할 수 없도록 설정
            cookie.setPath("/");
            response.addCookie(cookie);

            Map<String, Object> result = new HashMap<>();
            result.put("_id", newUser.getId());
            result.put("fullName", newUser.getFullName());
            result.put("username", newUser.getUsername());
            result.put("profilePic", newUser.getProfilePic());

            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
//            logger.error("Error during signup", e);
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }

    // 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload, HttpServletResponse response) {
        try {
            String username = payload.get("username");
            String password = payload.get("password");

            User user = userService.authenticateUser(username, password);
            String token = userService.generateToken(user);

            // JWT 토큰을 응답 쿠키에 설정
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true); // 자바스크립트에서 쿠키에 접근할 수 없도록 설정
            cookie.setPath("/");
            response.addCookie(cookie);

            Map<String, Object> result = new HashMap<>();
            result.put("_id", user.getId());
            result.put("fullName", user.getFullName());
            result.put("username", user.getUsername());
            result.put("profilePic", user.getProfilePic());

            return ResponseEntity.ok(result);
        } catch (Exception e) {
//            logger.error("Error during signup", e);
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }

    // 로그아웃 엔드포인트
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        try {
            // JWT 토큰을 무효화하기 위해 쿠키 삭제
            Cookie cookie = new Cookie("jwt", "");
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(0); // 쿠키 만료시간을 0으로 설정하여 삭제
            response.addCookie(cookie);

            return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error"));
        }
    }

//    @GetMapping("/chat")
//    public String chat() {
//        return "chat"; // "chat"은 chat.html 파일을 가리킵니다.
//    }

//    @MessageMapping("/user.addUser")
//    @SendTo("/user/public")
//    public User addUser(
//            @Payload User user
//    ) {
//        userService.saveUser(user);
//        return user;
//    }
//
//    @MessageMapping("/user.disconnectUser")
//    @SendTo("/user/public")
//    public User disconnectUser(
//            @Payload User user
//    ) {
//        userService.disconnect(user);
//        return user;
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> findConnectedUsers() {
//        return ResponseEntity.ok(userService.findConnectedUsers());
//    }
}
