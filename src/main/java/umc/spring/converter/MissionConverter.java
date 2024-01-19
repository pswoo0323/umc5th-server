package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MemberResponseDto;

import java.time.LocalDate;

public class MissionConverter {
    public static Mission toMission(StoreRequest.CreateMissionDTO request){
        return Mission.builder()
                .reward(request.getReward())
                .deadline(LocalDate.parse(request.getDeadline()))
                .missionSpec(request.getMissionSpec())
                .build();
    }

    public static MemberResponseDto.MyMissionListDTO toMyMissionListDTO(Page<MemberMission> memberMissionPage){

        List<MemberResponseDto.MyMissionDTO> myMissionDTOList = memberMissionPage.stream().map(
                memberMission -> {
                    return toMyMissionDTO(memberMission);
                }
        ).collect(Collectors.toList());

        return MemberResponse.MyMissionListDTO.builder()
                .missionList(myMissionDTOList)
                .missionsSize(myMissionDTOList.size())
                .totalMission(memberMissionPage.getTotalElements())
                .totalPage(memberMissionPage.getTotalPages())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    private static MemberResponse.MyMissionDTO toMyMissionDTO(MemberMission memberMission){

        return  MemberResponse.MyMissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .missionReward(memberMission.getMission().getReward())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
