package pickle_time.pickle_time.Pickle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.Pickle.dto.request.CreatePickleRequest;
import pickle_time.pickle_time.Pickle.dto.request.UpdatePickleRequest;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.Pickle.model.PickleStatus;
import pickle_time.pickle_time.Pickle.service.PickleService;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.dto.ApiResponse;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pickle")
public class PickleController {

    private final PickleService pickleService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> createPickle(Long id, @Valid @RequestBody CreatePickleRequest request) {
        pickleService.createPickle(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "피클 생성이 완료되었습니다.", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPickleById(@PathVariable Long id) {
        Optional<Pickle> pickle = pickleService.findById(id);
        return pickle.isPresent() ? ResponseEntity.ok(pickle.get()) : ResponseEntity.notFound().build();}

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Pickle>> updatePickle(@PathVariable Long id, @Valid @RequestBody UpdatePickleRequest request) {
        Pickle updatedPickle = pickleService.updatePickle(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, updatedPickle, null));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Pickle>> updatePickleStatus(@PathVariable Long id, @RequestParam PickleStatus status) {
        Pickle updatedPickle = pickleService.updatePickleStatus(id, status);
        return ResponseEntity.ok(new ApiResponse<>(true, updatedPickle, null));
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<ApiResponse<Pickle>> endPickle(@PathVariable Long id) {
        Pickle endPickle = pickleService.endPickle(id);
        return ResponseEntity.ok(new ApiResponse<>(true, endPickle, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePickle(@PathVariable Long id) {
        pickleService.deletePickle(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "피클을 삭제했습니다.", null));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Pickle>>> getAllPickles() {
        List<Pickle> pickles = pickleService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(true, pickles, null));
    }

    @GetMapping("/user/{userId}/myPickles")
    public ResponseEntity<ApiResponse<List<Pickle>>> getAllMyPickles(@PathVariable Long userId) {
        List<Pickle> pickles = pickleService.findPicklesByUserParticipation(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, pickles, null));
    }

}
