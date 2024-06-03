package pickle_time.pickle_time.Review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.Review.dto.request.CreateReviewRequest;
import pickle_time.pickle_time.Review.dto.response.ReviewInfoResponse;
import pickle_time.pickle_time.Review.service.ReviewService;
import pickle_time.pickle_time.global.dto.ApiResponse;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ReviewInfoResponse>> createReview(@RequestParam Long pickleId, @RequestParam Long userId, @RequestBody @Valid CreateReviewRequest createReviewRequest) {
        ReviewInfoResponse reviewInfoResponse = reviewService.convertToReviewInfoResponse(reviewService.createReview(pickleId, userId, createReviewRequest));
        return ResponseEntity.ok(new ApiResponse<>(true, reviewInfoResponse, null));
    }

    @GetMapping("/pickle/{pickleId}")
    public ResponseEntity<ApiResponse<List<ReviewInfoResponse>>> getReviewsByPickleId(@PathVariable Long pickleId) {
        List<ReviewInfoResponse> reviews = reviewService.getReviewsByPickleId(pickleId).stream()
                .map(reviewService::convertToReviewInfoResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, reviews, null));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteReview(@RequestParam Long reviewId, @RequestParam Long userId) {
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "리뷰를 삭제했습니다.", null));
    }
}
