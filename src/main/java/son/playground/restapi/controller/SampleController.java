package son.playground.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import son.playground.restapi.dto.SampleResponseDTO;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/hello")
    @CrossOrigin
    public ResponseEntity<SampleResponseDTO> index() {

        SampleResponseDTO responseDTO = new SampleResponseDTO();
        responseDTO.setName("memberA");
        responseDTO.setAddress("Seoul");

        return new ResponseEntity<SampleResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
