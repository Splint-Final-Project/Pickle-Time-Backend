package pickle_time.pickle_time.User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.User.dto.response.UserProfileResponse;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.User.service.UserService;
import pickle_time.pickle_time.User.dto.request.UserJoinRequest;
import pickle_time.pickle_time.User.dto.request.UserLoginRequest;
import pickle_time.pickle_time.User.dto.request.UserUpdateRequest;
import pickle_time.pickle_time.global.dto.ApiResponse;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<String>> join(@Valid @RequestBody UserJoinRequest userJoinRequest) {
        userService.join(userJoinRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "회원가입 성공했습니다.", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        userService.login(userLoginRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "로그인 성공했습니다.", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<Users> user = userService.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateUsers(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        UserProfileResponse updatedUser = userService.updateUsers(id, userUpdateRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, updatedUser, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUsers(@PathVariable Long id) {
        userService.deleteUsers(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "회원 삭제에 성공했습니다.", null));
    }

    private UserProfileResponse toUserProfileResponse(Users user) {
        return new UserProfileResponse(
                user.getNickname(),
                user.getEmail(),
                user.getCompany(),
                user.getImageUrl()
        );
    }
}
