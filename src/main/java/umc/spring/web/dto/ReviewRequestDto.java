package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public class ReviewRequestDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter

    public static class ReviewDTO{
        @JasonProperty("user_id")
        private Long userId;
        private Double rate;
        private String content;
    }
}
