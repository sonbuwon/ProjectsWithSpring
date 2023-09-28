package son.playground.imagecard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PhotoCardRequestDTO {
    private Long id;
    private String title;
    private String imagePath;
}
