package umc.spring.service.MemberService;

import jakarta.transaction.Transactional;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDto;

import java.util.List;
import java.util.stream.Collectors;

public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDTO request) {

        Member newMember = MemberConverter.toMember(request);

        // 음식 카테고리 리스트 생성
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryException(Code.FOOD_CATEGORY_NOT_FOUND));
                })
                .collect(Collectors.toList());

        // 유저 선호 음식 리스트 생성
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 양방향 매핑
        for(MemberPrefer memberPrefer : memberPreferList){
            memberPrefer.setMember(newMember);
        }

        return memberRepository.save(newMember);
    }
}
