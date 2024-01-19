package umc.spring.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDto {

    @Getter
    @NoArgsConstructor
    public static class CreateStoreDTO{

        private String address;
        private String name;

        @JasonProperty("region_id")
        private Long regionId;
    }

    @Getter
    @NoArgsConstructor
    public static class CreateMissionDTO{

        private String deadline;

        @JasonProperty("mission_spec")
        private String missionSpec;
        private int reward;
    }
}
