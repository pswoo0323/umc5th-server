package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.base.Code;
import umc.spring.base.exception.StoreException;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Store findStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(Code.STORE_NOT_FOUND));
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
}
