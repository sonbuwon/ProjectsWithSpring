package son.playground.imagecard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import son.playground.imagecard.domain.Card;
import son.playground.imagecard.dto.PhotoCardRequestDTO;
import son.playground.imagecard.service.CardService;
import son.playground.imagecard.service.ImageUploadService;

import java.nio.file.Files;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final ImageUploadService imageUploadService;

    @GetMapping("/main/list")
    public ResponseEntity<?> getList() {
        try {
            // 저장 순서대로 출력
//            List<Card> cards = this.cardService.getCardAll();
            // 수정 날짜 기준 내림차순 출력
            List<Card> cards = this.cardService.getCardAllByModifiedAtDesc();
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Failed to get list", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> imageUploadPost(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        try {
            imageUploadService.saveImage(file, title);
            return new ResponseEntity<>("card saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to save card", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] imageBytes = this.imageUploadService.getImage(id);

        if (imageBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeCard(@PathVariable Long id) {
        try {
            this.cardService.deleteCard(id);
            return new ResponseEntity<>("card deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to delete card", HttpStatus.NOT_FOUND);
        }
    }
}
