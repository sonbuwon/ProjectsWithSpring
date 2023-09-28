package son.playground.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import son.playground.restapi.domain.TempFavorite;
import son.playground.restapi.domain.TempItem;
import son.playground.restapi.dto.ItemRequestDTO;
import son.playground.restapi.repository.TempFavoriteRepository;
import son.playground.restapi.repository.TempItemRepository;

@Service
@RequiredArgsConstructor
public class TempFavoriteService {

    private final TempFavoriteRepository favoriteRepository;
    private final TempItemRepository itemRepository;

    public void addFavorite(ItemRequestDTO requestDTO) {
        TempItem tempItem = this.itemRepository.findById(requestDTO.getId()).orElseThrow();
        TempFavorite tempFavorite = new TempFavorite();
        // 양방향 연관관계에서는 양쪽 엔터티 모두 연관관계를 설정해야한다
        tempFavorite.setTempItem(tempItem);
        tempItem.setTempFavorite(tempFavorite);
        this.favoriteRepository.save(tempFavorite);
    }

    public void removeFavorite(Long itemId) {
        TempItem tempItem = this.itemRepository.findById(itemId).orElseThrow();
        this.favoriteRepository.deleteByTempItem(tempItem);
    }
}
