package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository;
import umc.spring.service.RegionService.RegionQueryService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{
    private final RegionQueryService regionQueryService;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequest.CreateStoreDTO request) {

        Region region = regionQueryService.findRegion(request.getRegionId());

        Store newStore = Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();

        return storeRepository.save(newStore);
    }
}
