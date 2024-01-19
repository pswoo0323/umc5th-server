package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.base.Code;
import umc.spring.base.exception.GeneralException;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.StoreService.StoreQueryService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService{
    private final StoreQueryService storeQueryService;
    private final MissionRepository missionRepository;
    private final MemberQueryService memberQueryService;
    private final MissionQueryService missionQueryService;
    private final MemberMissionRepository memberMissionRepository;

    /**
     * 가게에 미션 추가하기
     * @param storeId
     * @param request
     * @return
     */
    @Override
    @Transactional
    public Mission createMission(Long storeId, StoreRequest.CreateMissionDTO request){

        Store store = storeQueryService.findStore(storeId);

        Mission newMission = MissionConverter.toMission(request);

        // 양방향 맵핑
        newMission.setStore(store);

        return missionRepository.save(newMission);
    }

    /**
     * 미션 수행하기
     */
    @Transactional
    public void challengeMission(Long memberId, Long storeId, Long missionId){
        Member member = memberQueryService.findMember(memberId);
        Store store = storeQueryService.findStore(storeId);
        Mission mission = missionQueryService.findMission(missionId);

        MemberMission memberMission = MemberMission.builder().build();

        memberMission.setMission(mission);
        memberMission.setMember(member);

        memberMissionRepository.save(memberMission);
    }

    // 진행중인 미션 진행 완료로 바꾸기
    @Transactional
    public void missionComplete(Long memberId, Long missionId){
        Member member = memberQueryService.findMember(memberId);
        Mission mission = missionQueryService.findMission(missionId);

        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission)
                .orElseThrow(() -> new GeneralException(Code.MEMBER_MISSION_NOT_FOUND));

        memberMission.completeMission();
    }
}