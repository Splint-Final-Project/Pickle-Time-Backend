package pickle_time.pickle_time.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.User.dto.JoinRequest;
import pickle_time.pickle_time.User.dto.UpdateRequest;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinRequest joinRequest) {
        try {
            userService.join(joinRequest);
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            userService.login(email, password);
            return ResponseEntity.ok("로그인 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        Optional<Users> member = userService.findById(id);
        return member.isPresent() ? ResponseEntity.ok(member.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllMembers() {
        List<Users> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody UpdateRequest updateRequest) {
        try {
            Users updatedUsers = userService.updateMember(id, updateRequest);
            return ResponseEntity.ok(updatedUsers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        try {
            userService.deleteMember(id);
            return ResponseEntity.ok("회원 삭제 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
