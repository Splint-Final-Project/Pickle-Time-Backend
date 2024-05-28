package pickle_time.pickle_time.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsersForSidebar() {
        try {
            // 현재 인증된 사용자 정보를 가져옵니다.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 현재 인증된 사용자의 ID를 가져옵니다.
            String loggedInUserId = authentication.getName();

            // 현재 로그인된 사용자를 제외한 사용자 목록을 가져옵니다.
            List<User> filteredUsers = userService.getUsersForSidebar(loggedInUserId);

            // 필터링된 사용자 목록을 HTTP 200 OK 상태로 반환합니다.
            return ResponseEntity.ok(filteredUsers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
