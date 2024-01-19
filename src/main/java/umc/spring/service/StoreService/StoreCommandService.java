package umc.spring.service.StoreService;

import umc.spring.domain.Store;

public interface StoreCommandService {

    public Store createStore(StoreRequest.CreateStoreDTO request);
}
