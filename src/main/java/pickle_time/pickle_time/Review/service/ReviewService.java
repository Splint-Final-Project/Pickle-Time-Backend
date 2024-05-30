package pickle_time.pickle_time.Review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pickle_time.pickle_time.Review.dto.ReviewRequest;
import pickle_time.pickle_time.Review.model.Review;
import pickle_time.pickle_time.Review.repository.ReviewRepository;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.Pickle.model.PickleStatus;
import pickle_time.pickle_time.Pickle.repository.PickleRepository;
import pickle_time.pickle_time.User.Repository.UserRepository;
import pickle_time.pickle_time.User.model.Users;

import pickle_time.pickle_time.global.exception.GlobalExceptionHandler;
import pickle_time.pickle_time.global.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PickleRepository pickleRepository;
    private final UserRepository userRepository;

    public Review createReview(Long pickleId, Long userId, ReviewRequest reviewRequest) {
        Pickle pickle = pickleRepository.findById(pickleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피클이 존재하지 않습니다."));

        if (pickle.getPickleStatus() != PickleStatus.END) {  // 열거형 상수를 사용하여 상태를 비교
            throw new IllegalArgumentException("피클 상태가 완료되지 않았습니다.");
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Review review = new Review(reviewRequest.record(), reviewRequest.star(), user, pickle);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByPickleId(Long pickleId) {
        Pickle pickle = pickleRepository.findById(pickleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피클이 존재하지 않습니다."));

        if (pickle.getPickleStatus() != PickleStatus.END) {  // 열거형 상수를 사용하여 상태를 비교
            throw new IllegalArgumentException("피클 상태가 완료되지 않았습니다.");
        }

        return reviewRepository.findByPickleId(pickleId);
    }

    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("리뷰 삭제 권한이 없습니다.");
        }

        reviewRepository.delete(review);
    }
}
