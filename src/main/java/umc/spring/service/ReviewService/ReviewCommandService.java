package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDto;

public interface ReviewCommandService {

    public Review saveReview(Long storeId, ReviewRequestDto.AddReviewDTO request)
}
