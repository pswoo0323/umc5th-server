package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDto;

public class StoreConverter {

    public static StoreResponse.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponse.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponse.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponse.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponse.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponse.CreateMissionDTO toCreateMissionDTO(Mission mission){
        return StoreResponse.CreateMissionDTO.builder()
                .missionId(mission.getId())
                .build();
    }

    public static StoreResponse.CreateStoreDTO toCreateStoreDTO(Store store){

        return StoreResponse.CreateStoreDTO.builder()
                .storeId(store.getId())
                .build();
    }
}