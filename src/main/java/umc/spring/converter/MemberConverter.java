package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.MemberRequestDto;
import umc.spring.web.dto.MemberResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDto.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request) {
        Gender gender = null;
        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }
        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
        public static MemberResponseDto.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewPage){

            List<MemberResponseDto.MyReviewDTO> myReviewDTOList = reviewPage.stream()
                    .map(MemberConverter::toMyReviewDTO).collect(Collectors.toList());

            return MemberResponseDto.MyReviewListDTO.builder()
                    .totalReview(reviewPage.getTotalElements())
                    .listSize(myReviewDTOList.size())
                    .totalPage(reviewPage.getTotalPages())
                    .isFirst(reviewPage.isFirst())
                    .isLast(reviewPage.isLast())
                    .reviewList(myReviewDTOList)
                    .build();
        }

        public static MemberResponseDto.MyReviewDTO toMyReviewDTO(Review review){
            return MemberResponseDto.MyReviewDTO.builder()
                    .content(review.getBody())
                    .score(review.getScore().doubleValue())
                    .createdAt(review.getCreatedAt().toLocalDate())
                    .build();
        }
    }