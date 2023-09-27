package son.playground.imagecard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}

