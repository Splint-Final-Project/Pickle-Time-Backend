package pickle_time.pickle_time.User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.Scrap.dto.ScrapResponse;
import pickle_time.pickle_time.Scrap.model.Scrap;
import pickle_time.pickle_time.User.dto.response.UserInfoResponse;
import pickle_time.pickle_time.User.dto.response.UserLoginResponse;
import pickle_time.pickle_time.User.dto.response.UserProfileResponse;
import pickle_time.pickle_time.User.dto.response.UserScrapResponse;
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
//@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<String>> join(@Valid @RequestBody UserJoinRequest userJoinRequest) {
        userService.join(userJoinRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "회원가입 성공했습니다.", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(new ApiResponse<>(true,new UserLoginResponse(userService.login(userLoginRequest)), null));
    }

    @GetMapping
    public ResponseEntity<?> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        Users user = userService.findById(Long.parseLong(userDetails.getUsername()))
                .orElseThrow(() -> new IllegalStateException("해당 유저가 존재하지 않습니다."));

        List<Scrap> scraps = user.getScraps();
        List<ScrapResponse> scrapResponses = scraps.stream().map(scrap -> new ScrapResponse(scrap.getPickle().getTitle(), scrap.getPickle().getId())).toList();
        UserInfoResponse userInfoResponse = new UserInfoResponse(user.getId(), user.getNickname(), user.getRole(), user.getProviderType(), user.getCompany(), user.getImageUrl(), scrapResponses);
        return ResponseEntity.ok(new ApiResponse<>(true, userInfoResponse, null));
    }

    @GetMapping("/pickle")
    public ResponseEntity<?> getMyScrap() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Long userId = Long.parseLong(userDetails.getUsername());

        List<Scrap> scraps = userService.findScrapsById(userId);
        List<ScrapResponse> scrapResponses = scraps.stream().map(scrap -> new ScrapResponse(scrap.getPickle().getTitle(), scrap.getPickle().getId())).toList();

        return ResponseEntity.ok(new ApiResponse<>(true, new UserScrapResponse(userId, scrapResponses), null));
    }

    @PutMapping
    public ResponseEntity<?> updateMe(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Long userId = Long.parseLong(userDetails.getUsername());

        UserProfileResponse userProfileResponse = userService.updateUsers(userId, userUpdateRequest);
        return ResponseEntity.ok(new ApiResponse<>(true,userProfileResponse,null));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Long userId = Long.parseLong(userDetails.getUsername());

        userService.deleteUsers(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "회원 삭제에 성공했습니다." ,null));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println(userDetails.getUsername());

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
