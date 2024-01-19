package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

public class ReviewResponseDto {

    @AllArgsConstructor
    @Builder
    public static class AddReviewDTO_
    @JasonProperty("review_id")
    private Long reviewId;
}
