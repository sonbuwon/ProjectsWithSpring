package son.playground.imagecard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import son.playground.imagecard.service.ImageUploadService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public String imageUploadPost(@RequestParam("image") MultipartFile file, @RequestParam("title") String title, RedirectAttributes redirectAttributes) {

        try {
            imageUploadService.saveImage(file, title);
            redirectAttributes.addFlashAttribute("message", "Image Upload Successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload the file");
        }

        return "redirect:/main/list";
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
}

