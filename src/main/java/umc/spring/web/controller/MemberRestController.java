package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.base.Code;
import umc.spring.base.ResponseDto;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.web.dto.MemberRequestDto;
import umc.spring.web.dto.MemberResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final ReviewQueryService reviewQueryService;
    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ResponseDto<MemberResponseDto.JoinDTO> join(@RequestBody @Valid MemberRequestDto.JoinDTO request){
        Member member = memberCommandService.joinMember(request);

        return ResponseDto.onSuccess(MemberConverter.toJoinDTO(member), Code.OK);
    }

    @GetMapping("/reviews")
    public ResponseDto<MemberResponseDto.MyReviewListDTO> getMyReviewList(@RequestHeader("member_id") Long memberId, @RequestParam("page") int page){

        Page<Review> reviewPage = reviewQueryService.findMyReviewList(memberId, page);

        return ResponseDto.onSuccess(MemberConverter.toMyReviewListDTO(reviewPage), Code.OK);
    }

    // 내가 진행중인 미션 목록
    @GetMapping("/missions")
    public ResponseDto<MemberResponseDto.MyMissionListDTO> getMyMissionList(@RequestHeader("member_id") Long memberId, @RequestParam("page") int page){

        Page<MemberMission> memberMissionPage = missionQueryService.findMyMissionList(memberId, page);

        return ResponseDto.onSuccess(MissionConverter.toMyMissionListDTO(memberMissionPage), Code.OK);
    }

    // 진행중인 미션 진행 완료로 바꾸기
    @PatchMapping("/mission/{mission-id}")
    public ResponseDto changeMissionComplete(@PathVariable("mission-id") Long missionId, @RequestHeader("member_id") Long memberId){

        missionCommandService.missionComplete(memberId, missionId);

        return ResponseDto.onSuccess(null, Code.OK);
    }
}