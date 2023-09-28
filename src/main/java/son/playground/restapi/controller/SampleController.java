package son.playground.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import son.playground.restapi.domain.TempItem;
import son.playground.restapi.dto.ItemRequestDTO;
import son.playground.restapi.dto.ItemResponseDTO;
import son.playground.restapi.dto.SampleResponseDTO;
import son.playground.restapi.service.TempFavoriteService;
import son.playground.restapi.service.TempItemService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SampleController {

    private final TempItemService tempItemService;
    private final TempFavoriteService tempFavoriteService;

    @GetMapping("/hello")
    public ResponseEntity<SampleResponseDTO> index() {

        SampleResponseDTO responseDTO = new SampleResponseDTO();
        responseDTO.setName("memberA");
        responseDTO.setAddress("Seoul");

        return new ResponseEntity<SampleResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/save-item")
    public ResponseEntity<String> saveItem(@RequestBody ItemRequestDTO requestDTO) {
        try {
            tempItemService.savedItem(requestDTO);
            return new ResponseEntity<>("saved Item successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to save Item", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-item-one")
    public ResponseEntity<?> getItem() {
        try {
            ItemResponseDTO responseDTO = this.tempItemService.getItem(2L);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("failed to get Item", HttpStatus.BAD_REQUEST);
        }
    }

    // 찜하기 생성 및 삭제
    @PostMapping("/favorite")
    public ResponseEntity<String> addFavorite(@RequestBody ItemRequestDTO requestDTO) {
        this.tempFavoriteService.addFavorite(requestDTO);
        return new ResponseEntity<>("Added Favorite successfully", HttpStatus.OK);
    }

    @DeleteMapping("/favorite/{itemId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long itemId) {
        this.tempFavoriteService.removeFavorite(itemId);
        return new ResponseEntity<>("removed favorite successfully", HttpStatus.OK);
    }
}
