package pickle_time.pickle_time.Review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.Review.dto.ReviewRequest;
import pickle_time.pickle_time.Review.model.Review;
import pickle_time.pickle_time.Review.service.ReviewService;
import pickle_time.pickle_time.global.dto.ApiResponse;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Review>> createReview(@RequestParam Long pickleId, @RequestParam Long userId, @Valid @RequestBody ReviewRequest request) {
        Review review = reviewService.createReview(pickleId, userId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, review, null));
    }

    @GetMapping("/pickle/{pickleId}")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByPickleId(@PathVariable Long pickleId) {
        List<Review> reviews = reviewService.getReviewsByPickleId(pickleId);
        return ResponseEntity.ok(new ApiResponse<>(true, reviews, null));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable Long reviewId, @RequestParam Long userId) {
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "리뷰가 삭제되었습니다.", null));
    }
}
