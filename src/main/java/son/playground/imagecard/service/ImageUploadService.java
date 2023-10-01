package son.playground.imagecard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import son.playground.imagecard.domain.Card;
import son.playground.imagecard.repository.CardRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final CardRepository cardRepository;

    @Value("${file.upload-path}")
    private String uploadPath;

    // 이미지 저장하기
    @Transactional
    public void saveImage(MultipartFile file, String title) throws IOException {
        UUID uuid = UUID.randomUUID();

        if (file.isEmpty()) {
            throw new IllegalStateException("cannot save empty file");
        }

        String savedImageName = uploadPath + File.separator + uuid.toString() + file.getOriginalFilename();
        Path savedPath = Paths.get(savedImageName);

        Card card = new Card();
        card.setTitle(title);
        card.setImagePath(savedImageName);
        this.cardRepository.save(card);

        Files.copy(file.getInputStream(), savedPath);
    }

    // 저장된 이미지 불러오기
    @Transactional
    public byte[] getImage(Long id) {
        Card card = this.cardRepository.findById(id).orElseThrow();
        String imagePath = card.getImagePath();
        try {
            Path path = Paths.get(imagePath);
            byte[] imageBytes = Files.readAllBytes(path);

            return imageBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
