package umc.spring.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import umc.spring.base.Code;
import umc.spring.base.ResponseDto;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;

public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final MissionCommandService missionCommandService;
    private final StoreQueryService storeQueryService;
    private final ReviewCommandService reviewCommandService;
    private final MissionQueryService missionQueryService;

    /**
     * 특정 지역에 가게 추가하기
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseDto<StoreResponse.CreateStoreDTO> createStore(@RequestBody StoreRequest.CreateStoreDTO request){

        Store newStore = storeCommandService.createStore(request);

        return ResponseDto.onSuccess(StoreConverter.toCreateStoreDTO(newStore), Code.OK);
    }

    /**
     * 가게에 미션 추가하기
     */
    @PostMapping("/{store-id}/mission")
    public ResponseDto<StoreResponse.CreateMissionDTO> createMission(@PathVariable("store-id")Long storeId, @RequestBody StoreRequest.CreateMissionDTO request){

        Mission newMission = missionCommandService.createMission(storeId, request);

        return ResponseDto.onSuccess(StoreConverter.toCreateMissionDTO(newMission), Code.OK);
    }

    /**
     * 미션 도전하기
     */
    @PostMapping("/{store-id}/mission/{mission-id}")
    public ResponseDto challengeMission(@PathVariable("store-id") Long storeId, @PathVariable("mission-id")Long missionId, @RequestHeader("member_id") Long memberId){

        missionCommandService.challengeMission(memberId, storeId, missionId);

        return ResponseDto.onSuccess(null, Code.OK);
    }

    /**
     * 가게 리뷰 목록 조회
     */
    @GetMapping("/{store-id}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ResponseDto<StoreResponse.ReviewPreViewListDTO> getReviewList(@PathVariable(name = "store-id") Long storeId, @RequestParam(name = "page") Integer page){

        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);

        return ResponseDto.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList), Code.OK);
    }

    /**
     * 가게에 리뷰 추가하기
     */
    public ResponseDto<ReviewResponse.AddReviewDTO> addReview(@PathVariable("store-id") Long storeId, @RequestBody ReviewRequest.AddReviewDTO request){

        Review newReivew = reviewCommandService.saveReview(storeId, request);

        return ResponseDto.onSuccess(ReviewConverter.toAddReviewDTO(newReivew), Code.OK);
    }

    /**
     * 특정 가게 리뷰 목록 조회
     */
    public ResponseDto<StoreResponse.MissionListDTO> getMissionList(@PathVariable("store-id") Long storeId, @RequestParam("page") int page){

        Page<Mission> missionPage = missionQueryService.getMissionList(storeId, page);

        return null;
    }
}
