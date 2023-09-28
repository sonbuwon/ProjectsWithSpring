package son.playground.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import son.playground.restapi.domain.TempItem;
import son.playground.restapi.dto.ItemRequestDTO;
import son.playground.restapi.dto.ItemResponseDTO;
import son.playground.restapi.repository.TempItemRepository;

@Service
@RequiredArgsConstructor
public class TempItemService {

    private final TempItemRepository tempItemRepository;

    public void savedItem(ItemRequestDTO requestDTO) {
        TempItem tempItem = new TempItem();
        tempItem.setTitle(requestDTO.getTitle());
        this.tempItemRepository.save(tempItem);
    }

    public ItemResponseDTO getItem(Long id) {
        TempItem tempItem = this.tempItemRepository.findById(id).orElseThrow();
        ItemResponseDTO responseDTO = new ItemResponseDTO();
        responseDTO.setId(tempItem.getId());
        responseDTO.setTitle(tempItem.getTitle());
        return responseDTO;
    }
}
