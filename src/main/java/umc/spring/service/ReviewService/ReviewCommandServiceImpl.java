package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.ReviewRequestDto;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final ReviewRepository reviewRepository;
    private final MemberQueryService memberQueryService;
    private final StoreQueryService storeQueryService;

    @Override
    public Review saveReview(Long storeId, ReviewRequestDto.AddReviewDTO request) {

        // 가게 찾기
        Store store = storeQueryService.findStore(storeId);

        // 유저 찾기
        Member member = memberQueryService.findMember(request.getUserId());

        // 리뷰 생성
        Review newReview = ReviewConverter.toReview(request);

        // 양방향 설정
        newReview.setStore(store);
        newReview.setMember(member);

        // 리뷰 저장
        return reviewRepository.save(newReview);
    }


}
